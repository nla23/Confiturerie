
public class Etiquettage {
	Bocal currentBocal; //Bocal qui va se faire �tiquetter
	TypeConfiture typeEtiquette; //Type d'�tiquette
	
	public Etiquettage(final TypeConfiture _type)
	{
		this.typeEtiquette = _type;
	}
	public void changerBocalAEtiquetter(final Bocal _nextBocal) //Changer le bocal � �tiquetter
	{
		this.currentBocal = _nextBocal;
	}
	public TypeConfiture getTypeEtiquette()
	{
		return this.typeEtiquette;
	}
}
