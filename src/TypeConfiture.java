
public class TypeConfiture {
	private TypeDeConfiture type; //Pour différencer les types de confitures, ici on a que le type A et B
	enum TypeDeConfiture 
	{
		A,B;
	}
	public TypeConfiture(TypeDeConfiture _type)
	{
		this.type = _type;
	}
	public TypeDeConfiture getTypeConfiture()
	{
		return this.type;
	}
	public void setTypeConfiture(final TypeDeConfiture _type)
	{
		this.type = _type;
	}
}
