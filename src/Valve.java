
public class Valve
{

	private boolean ouvert = false; //False = fermé, true = ouvert
	private char typeValve;
	private int identifiantValve;
	private int nextBocal = 0;
	public Valve(int identifiantValve)
	{
		//this.typeValve = _type;
		this.identifiantValve = identifiantValve;
	}
	public int getIdentifiantValve() {
		return identifiantValve;
	}
	public synchronized void ChangerEtatValve() // Change l'état de la valve
	{
		if(this.ouvert)
		{
			this.ouvert = false;
			System.out.println("Valve " + this.typeValve + " fermer.");
		}
		else
		{
			this.ouvert = true;
			System.out.println("Valve " + this.typeValve + " ouverte."); 
		}
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
