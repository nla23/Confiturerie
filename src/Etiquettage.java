
public class Etiquettage 
{
	
	private char typeEtiquette; //Type d'étiquette
	private int nextBocal = 0;
	
	
	public Etiquettage(final char _type)
	{
		this.typeEtiquette = _type;
	}

	public char getTypeEtiquette()
	{
		return this.typeEtiquette;
	}
	public int getNextBocal()
	{
		return this.nextBocal;
	}
	public void changeNextBocal()
	{
		this.nextBocal++;
	}
}
