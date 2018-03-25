
public class Reservoir {
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
	
	public void setType(final char _type) {
		this.type = _type;
	}
	
	public char getTypeConfiture() {
		return this.type;
	}
	
	public void setCapacite(final int _capacite) {
		this.capacite = _capacite;
	}
	
	public int getCapacite() {
		return this.capacite;
	}
	
	public void setVolume(final int _volume) {
		this.volume = _volume;
	}
	
	public int getVolume() {
		return this.volume;
	}
	
	synchronized public void remplirPot(){
		 this.volume--;
	}
	
	synchronized public void approvionnement(final int _volume) {
		this.volume = _volume;
	}
	
}
