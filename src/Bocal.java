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
	private Mecanisme mecanisme;
	
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
		System.out.println("Le bocal " + this.bocalNumero + " de type " + this.getTypeConfiture() + " a obtenu la valve " + valveDisponible.getIdentifiantValve());
		System.out.println("Debut du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
		//Appel de la méthode consommer de la confiture sur la valve.
		valveDisponible.Consommer(Utils.compareCharToEnum(type));
		//Tant que la valve est ouverte, le pot se fait remplir
		while(valveDisponible.isOpened());
		System.out.println("Fin du remplissage du bocal " + this.bocalNumero + " de type " + this.type + ".");
		isFull = true;
	}
	public synchronized void setAuthorizedValve(Valve v) {
		this.valve = v;
	}
	public synchronized Valve getAuthorizedValve() {
		return valve;
	}
	public synchronized void setAuthorizedMechanisme(Mecanisme m) {
		this.mecanisme = m;
	}
	public synchronized Mecanisme getAuthorizedMecanisme() {
		return mecanisme;
	}
	public void etiqueter(Mecanisme mecanismeDisponible) {
		System.out.println("Le bocal " + this.bocalNumero + " de type " + this.getTypeConfiture() + " a obtenu le mecanisme " + mecanismeDisponible.getIdentifiantMecanisme());
		System.out.println("Debut de l'etiquetage du bocal " + this.bocalNumero + " de type " + this.type + ".");
		mecanismeDisponible.ChangerEtatMecanisme();
		try {
			//Simule un temps de remplissage
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Fin de l'etiquetage du bocal " + this.bocalNumero + " de type " + this.type + ".");
		mecanismeDisponible.ChangerEtatMecanisme();
		this.isLabel = true;
	}
	public Bocal(Bocal b) {
		this.bocalNumero = b.bocalNumero;
		this.etiquette = b.etiquette;
		this.isFull = b.isFull;
		this.isLabel = b.isLabel;
		this.mecanisme = b.mecanisme;
		this.reservoir = b.reservoir;
		this.type = b.type;
		this.type_priorite = b.type_priorite;
		this.valve = b.valve;
	}
	public Valve getValve() {
		return this.valve;
	}
	public Mecanisme getMecanisme() {
		return this.mecanisme;
	}
	@Override
	public Object call() throws Exception {
		if(!this.isFull) {		
			Valve valveDonne = valve;
			remplir(valveDonne);
		}
		else if(!this.isLabel) {
			Mecanisme mecanismeDonne = mecanisme;
			etiqueter(mecanismeDonne);
		}		
		return this;
	}
}
