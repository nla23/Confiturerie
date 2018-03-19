
public class Valve {

	private boolean ouvert = false; //False = fermé, true = ouvert
	TypeConfiture type;
	public Valve(final TypeConfiture _type)
	{
		this.type = _type;
	}
	public void ChangerEtatValve() // Change l'état de la valve
	{
		if(this.ouvert)
		{
			this.ouvert = false;
		}
		else
		{
			this.ouvert = true;
		}
	}
}
