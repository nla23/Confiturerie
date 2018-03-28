import java.util.Scanner;
import java.util.concurrent.Callable;

public class Bocal implements Callable 
{
	private int bocalNumero; //Numero du bocal, le plus petit nombre étant le premier
	private char type;
	private char type_priorite;
	private Etiquettage etiquette;
	private Valve valve;
	private boolean isFull = false;
	private boolean isLabel = false;
	private Reservoir reservoir;
	
	enum TypeDeConfiture 
	{
		A,B;
	}
	
	public Bocal(final char _type) 
	{
		this.type = _type;
	}
	
	public Bocal(final char _type, final Etiquettage _etiquette, final Valve _valve, final Reservoir _reservoir)
	{
		this.type = _type;
		this.etiquette = _etiquette;
		this.valve = _valve;
		this.reservoir = _reservoir;
	}
	
	
	
	public char getTypeConfiture()
	{
		return this.type;
	}
	
	public char getTypePriorite()
	{
		return this.type_priorite;
	}
	
	synchronized public void setTypePriorite(final char _typePriorite)
	{
		type_priorite = _typePriorite;
	}
	
	synchronized public void setNumero(final int _numeroDuBocal) 
	{
		assert (_numeroDuBocal >=0);
		bocalNumero = _numeroDuBocal;
	}
	
	public int getNumero() 
	{
		return bocalNumero;
	}
	
	
	
	//Exécution du thread à insérer ici
	/*public void run()	
	{		
			
		// On passe le type prioritaire en premier sur le tapis (a ou b). 
		
		if (this.type_priorite == this.type)
		{
			while(!isFull && !isLabel)
			{
				if(this.bocalNumero == this.valve.getNextBocal())
				{								
					System.out.println("Debut du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
					this.valve.ChangerEtatValve();
					System.out.println("Fin du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
					this.valve.ChangerEtatValve();
					this.reservoir.remplirPot();
					System.out.println("Le volume de confiture dans le réservoir est de  " + this.reservoir.getVolume() + " de type " + this.type + ".");
					this.isFull = true;
					this.valve.changeNextBocal();
				}
			}
			if(this.bocalNumero == this.etiquette.getNextBocal() && this.isFull)
			{
				System.out.println("Etiquettage du bocal " + this.bocalNumero + " de type " + this.type + ".");
				this.etiquette.changeNextBocal();
				this.isLabel = true;
			}
		}
			
		if (this.type_priorite != this.type)
			{
			while(!isFull && !isLabel)
			{
				if(this.bocalNumero == this.valve.getNextBocal())
				{
					System.out.println("Debut du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
					this.valve.ChangerEtatValve();
					System.out.println("Fin du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
					this.valve.ChangerEtatValve();
					this.isFull = true;
					this.reservoir.remplirPot();
					System.out.println("Le volume de confiture dans le réservoir est de  " + this.reservoir.getVolume() + " de type " + this.type + ".");
					this.valve.changeNextBocal();
				}
			}
			if(this.bocalNumero == this.etiquette.getNextBocal() && this.isFull)
			{
				System.out.println("Etiquettage du bocal " + this.bocalNumero + " de type " + this.type + ".");
				this.etiquette.changeNextBocal();
				this.isLabel = true;
			}
	
		}
		
		System.out.println("Le bocal " + this.bocalNumero + " de type " + this.type + " est termine.");
	
	}*/

	public void remplir(Valve valveDisponible) {
		System.out.println("Le bocal " + this.bocalNumero + " de type " + this.getTypeConfiture() + " a obtenu la valve" + valveDisponible.getIdentifiantValve());
		System.out.println("Debut du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
		valveDisponible.ChangerEtatValve();
		try {
			//Simule un temps de remplissage
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Fin du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
		valveDisponible.ChangerEtatValve();
		this.isFull = true;
	}
	public synchronized void setAuthorizedValve(Valve v) {
		this.valve = v;
	}

	@Override
	public Valve call() throws Exception {
		// TODO Auto-generated method stub
		Valve valveDonne = valve;
		remplir(valveDonne);
		//valve = null;
		return valveDonne;
	}
}
