
public class Bocal implements Runnable {

	
	private int bocalNumero; //Numero du bocal, le plus petit nombre étant le premier
	private TypeConfiture type;
	
	public Bocal(final TypeConfiture _type) 
	{
		this.type = _type;
	}
	public TypeConfiture getTypeConfiture()
	{
		return this.type;
	}
	public void setNumero(final int _numeroDuBocal) 
	{
		assert (_numeroDuBocal >=0);
		bocalNumero = _numeroDuBocal;
	}
	public int getNumero() {
		return bocalNumero;
	}
	@Override
	//Exécution du thread à insérer ici
	public void run() {
		
	}
}
