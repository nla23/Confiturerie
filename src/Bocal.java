
import java.util.concurrent.locks.ReentrantLock;


public class Bocal implements Runnable 
{
	private int bocalNumero; //Numero du bocal, le plus petit nombre étant le premier
	private char type;
	private char type_priorite;
	private Etiquettage etiquette;
	private Valve valve;
	private boolean isNotFull = true;
	private boolean isNotLabeled = true;
	ReentrantLock lock;
	
	public Bocal(final char _type) 
	{
		this.type = _type;
	}
	
	public Bocal(final char _type, final Etiquettage _etiquette, final Valve _valve, final ReentrantLock _lock)
	{
		this.type = _type;
		this.etiquette = _etiquette;
		this.valve = _valve;
		this.lock = _lock;
	}
	
	public char getTypeConfiture()
	{
		return this.type;
	}
	
	public char getTypePriorite()
	{
		return this.type_priorite;
	}
	
	public void setTypePriorite(final char _typePriorite)
	{
		type_priorite = _typePriorite;
	}
	
	public void setNumero(final int _numeroDuBocal) 
	{
		assert (_numeroDuBocal >=0);
		bocalNumero = _numeroDuBocal;
	}
	
	public int getNumero() 
	{
		return bocalNumero;
	}
	
	@Override
	//Exécution du thread à insérer ici
	public void run()	
	{		
		
		while(isNotFull)
		{
			if(isNotFull && this.type == this.valve.getPriority() && lock.tryLock())
			{
				this.valve.remplissage(this.bocalNumero, this.type);
				this.isNotFull = false;
				this.etiquette.setNextBocal(this.bocalNumero);
				lock.unlock();
			}
			
		}
		while(!isNotFull && isNotLabeled)
		{
			if(!isNotFull && this.bocalNumero == this.etiquette.peekNextBocal() )
			{
				//Peut etre ajouter un 2e lock ici
				System.out.println("Etiquettage du bocal " + this.bocalNumero + " de type " + this.type + ".");
				this.isNotLabeled = false;
				System.out.println("Le bocal " + this.bocalNumero + " de type " + this.type + " est termine.");
				this.etiquette.getNextBocal();

			}
		}
		//Debug
		//System.out.println("Fin thread " + this.getNumero());
		
	}
}
