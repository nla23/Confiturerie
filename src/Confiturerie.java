import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
/*
 * Equipe 1 - Projet Confiturerie - GLO-3003
 * 
 * Stephane Lalancette 		- 908 279 903    
 * Francis Charest 	   		- 905 179 619
 * Felix Veillette-Potvin 	- 111 074 805
 * Nicolas Lauzon			- 111 101 145
 */
import java.util.concurrent.LinkedBlockingQueue;

public class Confiturerie 
{

	public static void main(String[] args) 
	{
		int nombreValve = 2;
		int nombreMecanisme = 2;
		int nombreMaxPrioritaireAffile = 5;
		Reservoir reservoirA = new Reservoir(5,'A');
		Reservoir reservoirB = new Reservoir(5,'B');
		HashMap <typesDisponibles,Reservoir> reservoirs = new HashMap<typesDisponibles,Reservoir>();
		reservoirs.put(typesDisponibles.A, reservoirA);
		reservoirs.put(typesDisponibles.B,reservoirB);
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
		//Cr�ation des objets bocal avec le num�ro et le type correspondant
		//Ces objets sont envoy�s au tapis pour �tre remplis et �tiquetter
		LinkedList<Bocal> bocauxA = new LinkedList<Bocal>();
		LinkedList<Bocal> bocauxB = new LinkedList<Bocal>();
		for(i = 0 ; i < nb_BocalA ; i++) {
			Bocal nouveauBocal = new Bocal('A');
			nouveauBocal.setNumero(i);
			bocauxA.add(nouveauBocal);
		}
		for(i = 0; i < nb_BocalB;i++) {
			Bocal nouveauBocal = new Bocal('B');
			nouveauBocal.setNumero(i);
			bocauxB.add(nouveauBocal);
		}
		//Cr�ation des files d'attentes qui correspondent aux valves disponibles pour chaque type
		//� chaque fois qu'une valve est disponible, le contr�leur determine quel type doit obtenir la valve et le place dans la bonne file
		//pour permettre au prochain bocal de ce type � etre remplis
		HashMap<typesDisponibles,LinkedBlockingQueue<Valve>> filesAttente = new HashMap<typesDisponibles,LinkedBlockingQueue<Valve>>();

		//Cr�ation de toutes les valves du syst�me. Ces valves sont toutes les valves auxquelles le contr�leur a acc�s.
		//� chaque fois qu'une valve est plac�(ou replac� par le retourneurValve), elle est retourn� dans une des files cr�e plus haut pour �tre consomm�
		//par le bon type de bocal.
		LinkedBlockingQueue<Valve> valvesDisponibles = new LinkedBlockingQueue<Valve>();
		for(i = 0 ; i < nombreValve ; i++) {
			try {
				valvesDisponibles.put(new Valve(i,reservoirs));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Les m�canismes suivent exactement le m�me processus que les valves.
		LinkedBlockingQueue<Mecanisme> mecanismesDisponibles = new LinkedBlockingQueue<Mecanisme>();
		for(i = 0 ; i < nombreMecanisme ; i++) {
			try {
				mecanismesDisponibles.put(new Mecanisme(i));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Cr�ation d'une linkedblockingqueue par type de bocal, ces files sont partag�s avec le controleur
		//� chaque fois que le contr�leurValve ins�re une valve, le tapis est r�veill� et peut remplir un pot
		LinkedBlockingQueue<Valve> fileAvalve = new LinkedBlockingQueue<Valve>();
		filesAttente.put(typesDisponibles.A, fileAvalve);
		LinkedBlockingQueue<Valve> fileBvalve = new LinkedBlockingQueue<Valve>();
		filesAttente.put(typesDisponibles.B, fileBvalve);
		
		//On d�termine le nombre de pots prioritaires, permettant l'altenrnace entre les types sans causer de famine
		int nombrePotsPrioritaires;
		if(type_priorite == 'A') {
			nombrePotsPrioritaires = nb_BocalA;
		}
		else {
			nombrePotsPrioritaires = nb_BocalB;
		}
		//Le nombre total de pots dans le syst�me
		int nombreTotalPots = nb_BocalA+nb_BocalB;
		
		//Cr�ation du contr�leur de valve qui permet de d�terminer quel type de bocal doit obtenir une valve.
		//� chaque fois qu'une valve est disponible, le controleur l'ins�re dans la file de valve du type appropri�
		ControleurValve controleurValve = new ControleurValve(valvesDisponibles, type_priorite,nombreMaxPrioritaireAffile,nombreTotalPots,nombrePotsPrioritaires,  filesAttente);
		Thread controleurThread = new Thread(controleurValve);
		controleurThread.start();
		
		//Cr�ation des files attentes des m�canismes, suivant le m�me processus que les valves
		HashMap<typesDisponibles,LinkedBlockingQueue<Mecanisme>> filesAttenteMecanismes = new HashMap<typesDisponibles,LinkedBlockingQueue<Mecanisme>>();
		LinkedBlockingQueue<Mecanisme> fileAmecanisme = new LinkedBlockingQueue<Mecanisme>();
		filesAttenteMecanismes.put(typesDisponibles.A, fileAmecanisme);
		LinkedBlockingQueue<Mecanisme> fileBmecanisme = new LinkedBlockingQueue<Mecanisme>();
		filesAttenteMecanismes.put(typesDisponibles.B, fileBmecanisme);
		ControleurMecanisme controleurMecanisme = new ControleurMecanisme(mecanismesDisponibles, type_priorite,nombreMaxPrioritaireAffile,nombreTotalPots, nombrePotsPrioritaires, filesAttenteMecanismes);
		Thread controleurMecanismeThread = new Thread(controleurMecanisme);
		controleurMecanismeThread.start();
		
		//Cr�ation des tapis pour chaque type de bocal. Ces tapis attendent d'avoir une valve dans leur file d'attente respective
		//et remplissent le bocal lorsqu'il y en a une de disponible. Ces threads s'executent jusqu'� ce qu'il n'y ait plus de bocal dans la 
		Tapis nouveauTapisB = new Tapis('B', bocauxB, fileBvalve,valvesDisponibles,mecanismesDisponibles,fileBmecanisme);
		Thread tapisCourantB = new Thread(nouveauTapisB);
		tapisCourantB.start();
		Tapis nouveauTapisA = new Tapis('A', bocauxA, fileAvalve,valvesDisponibles,mecanismesDisponibles,fileAmecanisme);
		Thread tapisCourantA = new Thread(nouveauTapisA);
		tapisCourantA.start();
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
