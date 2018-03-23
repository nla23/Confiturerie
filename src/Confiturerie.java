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

	static Thread bocauxA[];
	static Thread bocauxB[];
	
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
		
		// On s'assure que seul les types A et B sont accept�s.
		while ((type_priorite != 'A') && (type_priorite != 'B'))
		{
			System.out.println("Seul les type A ou B sont acceptes (majuscules seulement)");
			System.out.println("Quel type doit-on traiter en priorite: (A, B)");
			type_priorite=scan_type.next().charAt(0);
		}
			
		int i;
		bocauxA = new Thread[nb_BocalA];
		bocauxB = new Thread[nb_BocalB];
		
		for (i = 0; i < nb_BocalA; i++)
		{
			// bocauxA[i] = new Bocal('A',etiquetteA,valveA);
			//bocauxA[i].setNumero(i);
			//bocauxA[i].setTypePriorite(type_priorite);
			bocauxA[i] = new Thread(new Bocal('A',etiquetteA,valveA,i+1,type_priorite));
			//bocauxA[i].start();
		}
		for (i = 0; i < nb_BocalB; i++)
		{
			//bocauxB[i] = new Bocal('B',etiquetteB,valveB);
			//bocauxB[i].setNumero(i);
			//bocauxB[i].setTypePriorite(type_priorite);
			bocauxB[i] = new Thread(new Bocal('B',etiquetteB,valveB,i+1,type_priorite));
			//bocauxB[i].start();
		}
		
		bocauxA[0].start();
		
		/*
		for (int j = 0; j < nb_BocalA; j++) {
				bocauxA[j].interrupt();
			}
		for (int k = 0; k < nb_BocalB; k++) {
				bocauxB[k].interrupt();
		}
		*/
		
	}
	//TODO arr�ter les thread
	public static int askInput(String _message) 
	{
		//On passe en parametre le message en string et on retourne un int comme input de l'utilisateur
		Scanner reader = new Scanner(System.in);
		System.out.println(_message);
		int n = reader.nextInt();
		return n;
	}
}
