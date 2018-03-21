
public class Valve {

	private boolean ouvert = false; //False = fermé, true = ouvert
	private char typeValve;
	private int nextBocal = 0;
	public Valve(final char _type)
	{
		this.typeValve = _type;
	}
	public void ChangerEtatValve() // Change l'état de la valve
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
	public int getNextBocal()
	{
		return this.nextBocal;
	}
	public void changeNextBocal()
	{
		 nextBocal++;
	}
}
