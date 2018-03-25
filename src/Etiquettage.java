
public class Etiquettage 
{
	
	private char typeEtiquette; //Type d'étiquette
	private int nextBocal = 0;
	
	
	public Etiquettage(final char _type)
	{
		this.typeEtiquette = _type;
	}

	synchronized public char getTypeEtiquette()
	{
		return this.typeEtiquette;
	}
	
	synchronized public int getNextBocal()
	{
		return this.nextBocal;
	}
	synchronized public void changeNextBocal()
	{
		this.nextBocal++;
	}
}
