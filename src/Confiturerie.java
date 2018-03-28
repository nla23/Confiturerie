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
		Scanner reader = new Scanner(System.in);
		Valve valveA = new Valve('A');
		Valve valveB = new Valve('B');
		char type_priorite;
		Etiquettage etiquetteA = new Etiquettage('A');
		Etiquettage etiquetteB = new Etiquettage('B');
		Reservoir reservoirA = new Reservoir(5, 5,'A');
		Reservoir reservoirB = new Reservoir(5, 5,'A');
		
		int nb_BocalA = askInputInt("Entrer le nombre de bocaux A:", reader);
		int nb_BocalB = askInputInt("Entrer le nombre de bocaux B:", reader);
	
		// On n'accepte pas plus de 100 bocaux de n'importe quel type.
		while ((nb_BocalA > 100) || (nb_BocalA < 1) || (nb_BocalB > 100) || (nb_BocalB < 1))
		{
			System.out.println("Le nombre de bocal de type A ou B doit etre inclus entre 1 et 100");
			nb_BocalA = askInputInt("Entrer le nombre de bocaux A:", reader);
			nb_BocalB = askInputInt("Entrer le nombre de bocaux B:", reader);
		}
		
		Scanner scan_type=new Scanner(System.in);
		type_priorite = askInputChar("Quel type doit-on traiter en priorit�? (A, B)", reader);
		
		
		// On s'assure que seul les types A et B sont accept�s.
		while ((type_priorite != 'A') && (type_priorite != 'B'))
		{
			System.out.println("Seul les type A ou B sont accept�s (majuscules seulement)");
			type_priorite = askInputChar("Quel type doit-on traiter en priorit�? (A, B)", reader);
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
				System.out.println("Cr�ation du bocal " + i + " de type A");
				bocauxA[i] = new Bocal('A',etiquetteA,valveA,reservoirA);
				bocauxA[i].setNumero(i);
				bocauxA[i].setTypePriorite(type_priorite);
				Thread newThreadA = new Thread(bocauxA[i]);
				newThreadA.start();
			
				System.out.println("Cr�ation du bocal " + i + " de type B");
				bocauxB[i] = new Bocal('B',etiquetteB,valveB,reservoirB);
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
					System.out.println("Cr�ation du bocal " + i2 + " de type A");
					bocauxA[i2] = new Bocal('A',etiquetteA,valveA,reservoirA);
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
				System.out.println("Cr�ation du bocal " + i + " de type A");
				bocauxA[i] = new Bocal('A',etiquetteA,valveA,reservoirA);
				bocauxA[i].setNumero(i);
				bocauxA[i].setTypePriorite(type_priorite);
				Thread newThreadA = new Thread(bocauxA[i]);
				newThreadA.start();
			
				System.out.println("Cr�ation du bocal " + i + " de type B");
				bocauxB[i] = new Bocal('B',etiquetteB,valveB,reservoirB);
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
			
				System.out.println("Cr�ation du bocal " + i2 + " de type B");
				bocauxB[i2] = new Bocal('B',etiquetteB,valveB, reservoirB);
				bocauxB[i2].setNumero(i2);
				bocauxB[i2].setTypePriorite(type_priorite);
				Thread newThreadB = new Thread(bocauxB[i2]);
				newThreadB.start();
				i2++;
			}
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
