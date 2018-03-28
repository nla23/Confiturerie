
//Cet enum pourrait servir à avoir plus de types dans le futur...
public enum typesDisponibles {
	
	A,B;
	private char toChar;

	static {
		A.toChar = 'A';
		B.toChar = 'B';
	}
	public char getToChar() {
		return toChar;
	}
}
