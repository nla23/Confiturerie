
public class Etiquettage {
	Bocal currentBocal; //Bocal qui va se faire étiquetter
	TypeConfiture typeEtiquette; //Type d'étiquette
	
	public Etiquettage(final TypeConfiture _type)
	{
		this.typeEtiquette = _type;
	}
	public void changerBocalAEtiquetter(final Bocal _nextBocal) //Changer le bocal à étiquetter
	{
		this.currentBocal = _nextBocal;
	}
	public TypeConfiture getTypeEtiquette()
	{
		return this.typeEtiquette;
	}
}
