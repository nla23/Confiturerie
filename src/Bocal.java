
public class Bocal implements Runnable {

	private TypeConfiture type; //Pour diff�rencer les types de confitures, ici on a que le type A et B
	private int bocalNumero; //Numero du bocal, le plus petit nombre �tant le premier
	enum TypeConfiture {
		A,B;
	}
	
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
	//Ex�cution du thread � ins�rer ici
	public void run() {
		
	}
}
