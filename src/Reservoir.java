
public class Reservoir 
{
	private int volume;
	private int capacite;
	private char type; 
	
	enum TypeDeConfiture 
	{
		A,B;
	}
	
	
	public Reservoir(final int _capacite, final int _volume, final char _type)
	{
		this.capacite = _capacite;
		this.volume= _volume;
		this.type = _type;
	}
	
	synchronized public void setType(final char _type) 
	{
		this.type = _type;
	}
	
	synchronized public char getTypeConfiture() 
	{
		return this.type;
	}
	
	synchronized public void setCapacite(final int _capacite) 
	{
		this.capacite = _capacite;
	}
	
	synchronized public int getCapacite()
	{
		return this.capacite;
	}
	
	synchronized public void setVolume(final int _volume) 
	{
		this.volume = _volume;
	}
	
	synchronized public int getVolume() 
	{
		return this.volume;
	}
	
	synchronized public void remplirPot()
	{
		 this.volume--;
	}
	
	synchronized public void approvisionnement(final int _volume)
	{
		this.volume = _volume;
	}
	
}
