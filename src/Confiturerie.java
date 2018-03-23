import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Equipe 1 - Projet Confiturerie - GLO-3003
 * 
 * Stephane Lalancette 		- 908 279 903    
 * Francis Charest 	   		- 905 179 619
 * Felix Veillette-Potvin 	-
 * Nicolas Lauzon			- 111 101 145
 */
/*--------------------------------------------------
 * Bug connus : 
 * Si on priorise B, les threads de type A ne font rien. Genre thread en famine.
 * 
 */
public class Confiturerie 
{

	public static void main(String[] args) 
	{
		Scanner reader = new Scanner(System.in);
		Valve valve;
		Etiquettage etiquette;
		ReentrantLock lock = new ReentrantLock();
		int nb_BocalA = askInputInt("Entrer le nombre de bocaux A:",reader);
		int nb_BocalB = askInputInt("Entrer le nombre de bocaux B:",reader);
	
		// On n'accepte pas plus de 100 bocaux de n'importe quel type.
		while ((nb_BocalA > 100) || (nb_BocalA < 1) || (nb_BocalB > 100) || (nb_BocalB < 1))
		{
			System.out.println("Le nombre de bocal de type A ou B doit etre inclus entre 1 et 100");
			nb_BocalA = askInputInt("Entrer le nombre de bocaux A:",reader);
			nb_BocalB = askInputInt("Entrer le nombre de bocaux B:",reader);
		}
		char type_priorite = askInputChar("Quel type doit-on traiter en priorite: (A, B)",reader);
		// On s'assure que seul les types A et B sont acceptés.
		while ((type_priorite != 'A') && (type_priorite != 'B'))
		{
			System.out.println("Seul les type A ou B sont acceptes (majuscules seulement)");
			type_priorite = askInputChar("Quel type doit-on traiter en priorite: (A, B)",reader);
		}
		Bocal[] bocaux = new Bocal[nb_BocalA+nb_BocalB];
		valve = new Valve(nb_BocalA, nb_BocalB, type_priorite);
		etiquette = new Etiquettage(nb_BocalA, nb_BocalB,type_priorite);

		for (int i = 0; i < nb_BocalA; i++)
		{
			bocaux[i] = new Bocal('A',etiquette,valve,lock);
			bocaux[i].setNumero(i);
			Thread newThreadA = new Thread(bocaux[i]);
			newThreadA.start();
		}
		for (int i = 0; i < nb_BocalB; i++)
		{
			bocaux[i+nb_BocalA] = new Bocal('B',etiquette,valve,lock);
			bocaux[i+nb_BocalA].setNumero(i+nb_BocalA);
			Thread newThreadB = new Thread(bocaux[i+nb_BocalA]);
			newThreadB.start();
		}
		reader.close();
	}
	
	public static int askInputInt(String _message, Scanner _reader) 
	{
		//On passe en parametre le message en string et on retourne un int comme input de l'utilisateur
		System.out.println(_message);
		int n = _reader.nextInt();
		return n;
	}
	public static char askInputChar(String _message, Scanner _reader)
	{
		//On passe en parametre le message en string et on retourne un int comme input de l'utilisateur
		System.out.println(_message);
		String n = _reader.next();
		return n.charAt(0);
	}
	
}
