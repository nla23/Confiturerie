import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class retourneurValve implements Runnable {
	private ExecutorService executorService;
	private CompletionService taskCompletionService;
	private LinkedBlockingQueue<Valve> valvesDisponibles;
	private LinkedBlockingQueue<Bocal> bocauxPleins;
	
	//Classe qui permet d'avoir un thread qui attend les remplissages de pots et remet dans la liste de pots disponibles du controleur de valve
	public retourneurValve(LinkedBlockingQueue<Valve> valvesDisponibles, CompletionService taskCompletionService, LinkedBlockingQueue<Bocal> bocauxPleins) {
		executorService = Executors.newCachedThreadPool();
		this.taskCompletionService = taskCompletionService;
		this.valvesDisponibles = valvesDisponibles;
		this.bocauxPleins = bocauxPleins;
	}
	@Override
	public void run() {
		try {
			while(true) {
				//À chaque fois qu'une méthode termine, le taskCompletionService se fait 
				//notify et il prend la valves disponible et la remet dans la file du controleur
				Future<Bocal> valveUtilisee = taskCompletionService.take();
				Bocal bocalCourant= valveUtilisee.get();
				bocauxPleins.put(bocalCourant);
				valvesDisponibles.put(bocalCourant.getValve());				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
