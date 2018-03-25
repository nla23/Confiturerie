
public class Reservoir {
	private int volume;
	private int capacite;
	
	public Reservoir(final int _capacite, final int _volume)
	{
		this.capacite = _capacite;
		this.volume= _volume;
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
	
	public void remplirPot(){
		this.volume--;
	}
	
	public void approvionnement(final int _volume) {
		this.volume = _volume;
	}
	
}
