import java.util.Scanner;

public class Bocal implements Runnable 
{
	private int bocalNumero; //Numero du bocal, le plus petit nombre étant le premier
	private char type;
	private char type_priorite;
	private Etiquettage etiquette;
	private Valve valve;
	private boolean isFull = false;
	private boolean isLabel = false;
	
	enum TypeDeConfiture 
	{
		A,B;
	}
	
	public Bocal(final char _type) 
	{
		this.type = _type;
	}
	
	public Bocal(final char _type, final Etiquettage _etiquette, final Valve _valve)
	{
		this.type = _type;
		this.etiquette = _etiquette;
		this.valve = _valve;
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
		//System.out.println("Creation Bocal: " + this.bocalNumero);
		
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
		System.out.println("Le bocal " + this.bocalNumero + " de type " + this.type + " est termine.");
		
		if (this.type_priorite != this.type)
			
		// Il faudrait que les threads du type non-prioritaire soit en wait le temps que ceux du type 
		// prioritaire ait tous passe. Si quelqu'un a une idee de comment on pourrait faire.
			
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
	}
}
