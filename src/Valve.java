import java.util.HashMap;

public class Valve
{

	private boolean ouvert = false; //False = fermé, true = ouvert
	private int identifiantValve;
	private int nextBocal = 0;
	private HashMap<typesDisponibles,Reservoir> reservoirs;
	public Valve(int identifiantValve,HashMap<typesDisponibles,Reservoir> reservoirs)
	{
		this.identifiantValve = identifiantValve;
		this.reservoirs = reservoirs;		
	}
	public int getIdentifiantValve() {
		return identifiantValve;
	}
	public synchronized void Consommer(typesDisponibles typeConfiture)
	{	
		this.ouvert =true;
		System.out.println("Valve ouverte.");
		try {
			Thread.sleep(1000);
			Reservoir reservoirConfiture = reservoirs.get(typeConfiture);
			reservoirConfiture.consommerConfiture();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ouvert = false;
		System.out.println("Valve fermée.");
	}
	public boolean isOpened() {
		return this.ouvert;
	}
	synchronized public int getNextBocal()
	{
		return this.nextBocal;
	}
	synchronized public void changeNextBocal()
	{
		 nextBocal++;
	}
	public boolean getEtatValve() {
		return ouvert;
	}
}
