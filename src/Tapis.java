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
	private ExecutorService executorService;
	private CompletionService taskCompletionService;
	private retourneurValve retourneur;
	//Classe qui permet de passer les bocaux en ordre de leur arrivée en demandant une valve au controleur de valve
	public Tapis(char type, LinkedList<Bocal> listeBocaux,LinkedBlockingQueue<Valve> valvesDisponibles,LinkedBlockingQueue<Valve> valvesRetournes) {
		this.type = type;
		this.listeBocaux = listeBocaux;
		this.valvesDisponibles = valvesDisponibles;
		//Permet de faire une méthode "callback", ce qui veut dire que la méthode rappelle le taskcompletion lorsqu'elle termine son execution
		executorService = Executors.newCachedThreadPool();
		taskCompletionService = new ExecutorCompletionService(
				executorService);
		this.retourneur = new retourneurValve(valvesRetournes,taskCompletionService);
		//Le retourneur de valve est un thread qui attend les callbacks de remplissage de pots et remets les valves dans les valves disponibles du contrôleur de pots
		Thread retourneurThread = new Thread(retourneur);
		retourneurThread.start();
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
				//On "submit" la tache de remplir le pot à une méthode qui callback lorsque c'est remplit.
				//Lorsque c'est remplit, la classe retourneur de valve sera appelé et 
				//remettra le pot dans la liste de valve disponible du controleur de valve
				taskCompletionService.submit(bocalCourant);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Tous les bocaux de "+ this.type + " sont remplis");
		
	}
	
}
