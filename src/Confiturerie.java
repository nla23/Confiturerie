import java.util.Scanner;
/*
 * Equipe 1 - Projet Confiturerie - GLO-3003
 * 
 * Stephane Lalancette 		- 908 279 903    
 * Francis Charest 	   		- 905 179 619
 * Felix Veillette-Potvin 	-
 * Nicolas Lauzon			- 111 101 145
 */

public class Confiturerie 
{

	public static void main(String[] args) 
	{
		Valve valveA = new Valve('A');
		Valve valveB = new Valve('B');
		Etiquettage etiquetteA = new Etiquettage('A');
		Etiquettage etiquetteB = new Etiquettage('B');
		int nb_BocalA = askInput("Entrer le nombre de bocaux A:");
		int nb_BocalB = askInput("Entrer le nombre de bocaux B:");
		int i;
		Bocal[] bocauxA = new Bocal[nb_BocalA];
		Bocal[] bocauxB = new Bocal[nb_BocalB];
		for (i = 0; i < nb_BocalA; i++)
		{
			bocauxA[i] = new Bocal('A',etiquetteA,valveA);
			bocauxA[i].setNumero(i);
			Thread newThreadA = new Thread(bocauxA[i]);
			newThreadA.start();
		}
		for (i = 0; i < nb_BocalB; i++)
		{
			bocauxB[i] = new Bocal('B',etiquetteB,valveB);
			bocauxB[i].setNumero(i);
			Thread newThreadB = new Thread(bocauxB[i]);
			newThreadB.start();
		}
	}
	public static int askInput(String _message) 
	{
		//On passe en parametre le message en string et on retourne un int comme input de l'utilisateur
		Scanner reader = new Scanner(System.in);
		System.out.println(_message);
		int n = reader.nextInt();
		return n;
	}
}
