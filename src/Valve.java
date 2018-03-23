import java.util.concurrent.locks.ReentrantLock;

public class Valve {

	private int currentBocal = 0;
	private char typePriorite;
	private int nbTypeA;
	private int nbTypeB;
	ReentrantLock lock = new ReentrantLock();
	public Valve(final int _nbTypeA, final int _nbTypeB, final char _typePriorite)
	{
		this.nbTypeA = _nbTypeA;
		this.nbTypeB = _nbTypeB;
		this.typePriorite = _typePriorite;
	}
	public void remplissage(final int _numero, final char _type) 
	{
		System.out.println("Debut du remplissage du bocal " + _numero + " de type " + _type + ".");
		System.out.println("Valve ouverte.");
		System.out.println("Valve fermer.");
		System.out.println("Fin du remplissage du bocal " + _numero + " de type " + _type + ".");
		
		if(_type == 'A')
		{
			nbTypeA--;
		}
		else if(_type == 'B')
		{
			nbTypeB--;
		}
		this.checkPriority();
	}
	public int getCurrentBocal()
	{
		return this.currentBocal;
	}
	public void changeNextBocal(final int _numero)
	{
		this.currentBocal = _numero;
	}
	public void setPriority(final char _type)
	{
		this.typePriorite = _type;
	}
	public char getPriority()
	{
		return this.typePriorite;
	}

	public void checkPriority()
	{
		if(nbTypeA == 0)
		{
			this.setPriority('B');
		}
		else if(nbTypeB == 0)
		{
			this.setPriority('A');
		}
	}
}

