
public class Reservoir 
{
	private int volume;
	private int capacite;
	private char type; 
	
	enum TypeDeConfiture 
	{
		A,B;
	}	
	
	public Reservoir(final int _capacite, final char _type)
	{
		this.capacite = _capacite;
		this.volume= capacite;
		this.type = _type;
	}
	public synchronized void consommerConfiture() {
		if(this.volume >= 0 ) {
			System.out.println("Consommation de confiture");
			volume--;
		}
		else {
			System.out.println("Remplissage du réservoir pour le type " + type);
			for(int i = 0; i <= capacite; i++) {
				try {
					Thread.sleep(1000);
					volume++;
					System.out.println(this.volume + "L de remplis...");					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}

	

	
}
