import java.util.LinkedList;

public class Etiquettage 
{

	private char typePriorite;
	private int nbTypeA;
	private int nbTypeB;
	private LinkedList<Integer> queue = new LinkedList<Integer>();
	
	public Etiquettage(final int _nbTypeA, final int _nbTypeB, final char _typePriorite)
	{
		this.nbTypeA = _nbTypeA;
		this.nbTypeB = _nbTypeB;
		this.typePriorite = _typePriorite;
	}
	public int getNextBocal()
	{
		return this.queue.remove();
	}
	public int peekNextBocal()
	{
		if(this.queue.peekFirst() == null)
		{
			return -1;
		}
		return this.queue.peekFirst();
	}
	public void setNextBocal(final int _numero)
	{
		this.queue.add(_numero);
	}
	public void setPriority(final char _type)
	{
		this.typePriorite = _type;
	}
	public char getPriority()
	{
		return this.typePriorite;
	}
}
