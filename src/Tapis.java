import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class Tapis implements Runnable  {
	private char type;
	private LinkedList<Bocal> listeBocaux;
	private LinkedBlockingQueue<Valve> valvesDisponibles;
	private LinkedBlockingQueue<Mecanisme> mecanismesDisponibles;
	private ExecutorService executorServiceValve;
	private CompletionService taskCompletionServiceValve;
	private ExecutorService executorServiceMecanisme;
	private CompletionService taskCompletionServiceMecanisme;
	private retourneurValve retourneurValve;
	private retourneurMecanisme retourneurMecanisme;
	private LinkedBlockingQueue<Bocal> bocauxPleins;
	//Classe qui permet de passer les bocaux en ordre de leur arriv�e en demandant une valve au controleur de valve
	public Tapis(char type, LinkedList<Bocal> listeBocaux,LinkedBlockingQueue<Valve> valvesDisponibles,LinkedBlockingQueue<Valve> valvesRetournes,LinkedBlockingQueue<Mecanisme> mecanismesRetournes,LinkedBlockingQueue<Mecanisme> mecanismesDisponibles) {
		this.type = type;
		this.listeBocaux = listeBocaux;
		this.valvesDisponibles = valvesDisponibles;
		//Permet de faire une m�thode "callback", ce qui veut dire que la m�thode rappelle le taskcompletion lorsqu'elle termine son execution
		executorServiceValve = Executors.newCachedThreadPool();
		taskCompletionServiceValve = new ExecutorCompletionService(
				executorServiceValve);
		this.bocauxPleins = new LinkedBlockingQueue<Bocal>();
		this.retourneurValve = new retourneurValve(valvesRetournes,taskCompletionServiceValve,bocauxPleins);
		executorServiceMecanisme = Executors.newCachedThreadPool();
		taskCompletionServiceMecanisme = new ExecutorCompletionService(
				executorServiceMecanisme);
		this.mecanismesDisponibles = mecanismesDisponibles;
		this.retourneurMecanisme = new retourneurMecanisme(mecanismesRetournes, taskCompletionServiceMecanisme); 
		Thread retourneurMecanismeThread = new Thread(retourneurMecanisme);
		retourneurMecanismeThread.start();
		Etiquetteur etiquetteur = new Etiquetteur(bocauxPleins,taskCompletionServiceMecanisme, mecanismesDisponibles);
		Thread etiquetteurThread = new Thread(etiquetteur);
		etiquetteurThread.start();
		//Le retourneur de valve est un thread qui attend les callbacks de remplissage de pots et remets les valves dans les valves disponibles du contr�leur de pots
		Thread retourneurValveThread = new Thread(retourneurValve);
		retourneurValveThread.start();
	}

	@Override
	public void run() {
		while(!listeBocaux.isEmpty()) {
			try {
				//S'il y a une valve disponible dans la queue, on la prend et on remplit le bocal.
				//Sinon le thread tombe en wait (puisque c'est une linkeblockingqueue)
				Valve valveDisponible = valvesDisponibles.take();
				Bocal bocalCourant = listeBocaux.removeFirst();
				bocalCourant.setAuthorizedValve(valveDisponible);
				//On "submit" la tache de remplir le pot � une m�thode qui callback lorsque c'est remplit.
				//Lorsque c'est remplit, la classe retourneur de valve sera appel� et 
				//remettra le pot dans la liste de valve disponible du controleur de valve
				taskCompletionServiceValve.submit(bocalCourant);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Tous les bocaux de "+ this.type + " sont remplis");
		
	}
	
}