import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

public class ControleurValve implements Runnable{
	private Vector<Valve> ressourcesValve;
	private char typePrioritaire;
	private HashMap<typesDisponibles,LinkedBlockingQueue<Valve>> filesAttente;
	private int nombreDePrioritaireObtenusValve;
	private int nombreDeNonprioritaireObtenusValve;
	private int nombreUneSorteAffile;
	private Vector<typesDisponibles> typesNonPrioritaires;
	private LinkedBlockingQueue<Valve> _valvesDisponibles;
	private int nombrePotsPrioritaires;
	private int nombrePotsPrioritairesDejaPasse;
	private int nombreTotalDePots;
	private int nombrePotsNonPrioritairesDejaPasse;
	
	//Classe qui controle les valves qui sont donné aux tapis pour le remplissage
	public ControleurValve(LinkedBlockingQueue<Valve> ressourcesValve, char typePrioritaire,int nombreMaxPrioritaireAffile,int nombreTotalPots,int nombrePotsPrioritaires,HashMap<typesDisponibles,LinkedBlockingQueue<Valve>> filesAttente) {
		this._valvesDisponibles = ressourcesValve;
		this.typePrioritaire = typePrioritaire;		
		this.filesAttente = new HashMap<typesDisponibles,LinkedBlockingQueue<Valve>>();
		this.typesNonPrioritaires = new Vector<typesDisponibles>();
		//On crée une file attente pour chaque type de confiture.
		this.filesAttente = filesAttente;
		for(typesDisponibles type : typesDisponibles.values()) {
			if(type.getToChar()!= typePrioritaire) {
				typesNonPrioritaires.add(type);
			}
		}
		this.nombreTotalDePots = nombreTotalPots;
		this.nombrePotsPrioritairesDejaPasse = 0;
		this.nombrePotsPrioritaires = nombrePotsPrioritaires;
		this.nombreUneSorteAffile = nombreMaxPrioritaireAffile;
		this.nombreDePrioritaireObtenusValve = 0;
		this.nombreDeNonprioritaireObtenusValve = 0;

	}

	//Permet de déterminer s'il y a eu suffisamment du type prioritaire de bocal de passé. Si oui on change de type
	private synchronized typesDisponibles determinerTour() {
		int nombrePotsNonPrioritairesRestant = nombreTotalDePots - nombrePotsPrioritaires -nombrePotsNonPrioritairesDejaPasse;
		if((nombreDePrioritaireObtenusValve <= nombreUneSorteAffile && nombrePotsPrioritairesDejaPasse < nombrePotsPrioritaires) ||  nombrePotsNonPrioritairesRestant == 0) {
			nombreDePrioritaireObtenusValve++;
			nombrePotsPrioritairesDejaPasse++;
			return Utils.compareCharToEnum(typePrioritaire);
		}
		else if(nombreDeNonprioritaireObtenusValve >= nombreUneSorteAffile ) {
			nombreDePrioritaireObtenusValve = 0;
			nombreDeNonprioritaireObtenusValve = 0;
			return Utils.compareCharToEnum(typePrioritaire);
		}
		else {
			nombreDeNonprioritaireObtenusValve++;
			nombrePotsNonPrioritairesDejaPasse++;
			return typesNonPrioritaires.get(0);
		}		
	}

	@Override
	public void run() {
		while(true) {
			try {
				Valve valveDisponible = _valvesDisponibles.take();
				typesDisponibles typeMeritantValve = determinerTour();			
				filesAttente.get(typeMeritantValve).put(valveDisponible);
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
/*//Permet de trouver une valve qui est dans l'état fermé
private synchronized Valve trouverValve() {
	Valve retour = null;
	while(retour == null) {
		for(int i = 0; i < ressourcesValve.size(); i++) {
			Valve valveCourante = ressourcesValve.get(i);
			//Si la valve n'est pas ouverte, elle est disponible
			if(!ressourcesValve.get(i).getEtatValve()) {
				retour = valveCourante;					
			}
		}
	}
	return retour;
}

public void obtenirValve(Bocal b) {
	char typeBocalCourant = b.getTypeConfiture();
	if(typeBocalCourant == typePrioritaire) {
		LinkedBlockingQueue<Valve> fileAttenteCorrespondate = filesAttente.get(compareCharToEnum(typeBocalCourant));
		try {
			fileAttenteCorrespondate.put(trouverValve());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}*/
