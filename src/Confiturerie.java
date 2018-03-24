import java.util.Scanner;
/*
 * Equipe 1 - Projet Confiturerie - GLO-3003
 * 
 * Stephane Lalancette 		- 908 279 903    
 * Francis Charest 	   		- 905 179 619
 * Felix Veillette-Potvin 	- 111 074 805
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
	
		// On n'accepte pas plus de 100 bocaux de n'importe quel type.
		while ((nb_BocalA > 100) || (nb_BocalA < 1) || (nb_BocalB > 100) || (nb_BocalB < 1))
		{
			System.out.println("Le nombre de bocal de type A ou B doit etre inclus entre 1 et 100");
			nb_BocalA = askInput("Entrer le nombre de bocaux A:");
			nb_BocalB = askInput("Entrer le nombre de bocaux B:");
		}
		
		Scanner scan_type=new Scanner(System.in);
		System.out.println("Quel type doit-on traiter en priorite: (A, B)");
		char type_priorite=scan_type.next().charAt(0);
		
		// On s'assure que seul les types A et B sont acceptés.
		while ((type_priorite != 'A') && (type_priorite != 'B'))
		{
			System.out.println("Seul les type A ou B sont acceptes (majuscules seulement)");
			System.out.println("Quel type doit-on traiter en priorite: (A, B)");
			type_priorite=scan_type.next().charAt(0);
		}
			
		int i;
		Bocal[] bocauxA = new Bocal[nb_BocalA];
		Bocal[] bocauxB = new Bocal[nb_BocalB];
		
		// On verifie l'ecart dans le nombre de bocaux A par rapport au nombre de bocaux B,  
		// car nous voulons creer par alternance les A et les B jusqu'a ce que le 
		// plus petit nombre parmis les 2 type de bocaux soit atteint et ensuite on cree les derniers bocaux du type manquant
		// Ex:  Si on a 4 Type A et 2 Type B;  On va les creer dans cet ordre (A, B, A, B, A, A)
		
		int ecart_type = Math.abs(nb_BocalA - nb_BocalB);
		
		// Traitement dans ce if du cas 1 et 2: On a soit plus de BocauxA que de BocauxB ou le nombre est egal.
		// Cas 1: Plus de A que de B: On commence donc par alternance et on finit par faire les A restants (A, B, A, B, A, A ... A).
		// Cas 2: On a le meme nombre de A que de B. On les fait tous par alternance tout simplement (A, B, A, B, ... A, B)
	
		if (nb_BocalA >= nb_BocalB)
		{
			for (i = 0; i < nb_BocalB; i++)
			{
				System.out.println("Creation Bocal " + i + " de type A");
				bocauxA[i] = new Bocal('A',etiquetteA,valveA);
				bocauxA[i].setNumero(i);
				bocauxA[i].setTypePriorite(type_priorite);
				Thread newThreadA = new Thread(bocauxA[i]);
				newThreadA.start();
			
				System.out.println("Creation Bocal " + i + " de type B");
				bocauxB[i] = new Bocal('B',etiquetteB,valveB);
				bocauxB[i].setNumero(i);
				bocauxB[i].setTypePriorite(type_priorite);
				Thread newThreadB = new Thread(bocauxB[i]);
				newThreadB.start();
			}
			
			// Creation des Bocaux A manquants, car on a plus de Bocaux A que de Bocaux B.
			if (nb_BocalA > nb_BocalB)
			{
				// i2 sert a garder le bon numero d'index dans le tableau.
				int i2 = i;
				
				for (i = 0; i < ecart_type; i++)
				{
					System.out.println("Creation Bocal " + i2 + " de type A");
					bocauxA[i2] = new Bocal('A',etiquetteA,valveA);
					bocauxA[i2].setNumero(i2);
					bocauxA[i2].setTypePriorite(type_priorite);
					Thread newThreadA = new Thread(bocauxA[i2]);
					newThreadA.start();
					i2++;
				}
			}
		}
				
		// Cas 3: Plus de B que de A: On commence donc par alternance et on finit par faire les B restants (A, B, A, B, B, ... B).
		if (nb_BocalA < nb_BocalB)
		{
			for (i = 0; i < nb_BocalA; i++)
			{
				System.out.println("Creation Bocal " + i + " de type A");
				bocauxA[i] = new Bocal('A',etiquetteA,valveA);
				bocauxA[i].setNumero(i);
				bocauxA[i].setTypePriorite(type_priorite);
				Thread newThreadA = new Thread(bocauxA[i]);
				newThreadA.start();
			
				System.out.println("Creation Bocal " + i + " de type B");
				bocauxB[i] = new Bocal('B',etiquetteB,valveB);
				bocauxB[i].setNumero(i);
				bocauxB[i].setTypePriorite(type_priorite);
				Thread newThreadB = new Thread(bocauxB[i]);
				newThreadB.start();
			}
			
			// i2 sert a garder le bon numero d'index dans le tableau..
			int i2 = i;
			
			// Creation des B restants, car on a plus de B que A.
			for (i = 0; i < ecart_type; i++)
			{
			
				System.out.println("Creation Bocal " + i2 + " de type B");
				bocauxB[i2] = new Bocal('B',etiquetteB,valveB);
				bocauxB[i2].setNumero(i2);
				bocauxB[i2].setTypePriorite(type_priorite);
				Thread newThreadB = new Thread(bocauxB[i2]);
				newThreadB.start();
				i2++;
			}
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
