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
	
	//Classe qui controle les valves qui sont donné aux tapis pour le remplissage
	public ControleurValve(LinkedBlockingQueue<Valve> ressourcesValve, char typePrioritaire,int nombreMaxPrioritaireAffile,HashMap<typesDisponibles,LinkedBlockingQueue<Valve>> filesAttente) {
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
		this.nombreUneSorteAffile = nombreMaxPrioritaireAffile;
		this.nombreDePrioritaireObtenusValve = 0;
		this.nombreDeNonprioritaireObtenusValve = 0;

	}
	//Permet de transformer un char en son correspondant dans l'enum
	//Retourne null si le char n'est pas dans l'enum
	private typesDisponibles compareCharToEnum(char c){
		typesDisponibles retour = null;
		for(typesDisponibles type : typesDisponibles.values()) {
			if(type.getToChar() == c) {
				retour = type;
			}
		}
		return retour;
	}

	//Permet de déterminer s'il y a eu suffisamment du type prioritaire de bocal de passé. Si oui on change de type
	private synchronized typesDisponibles determinerTour() {
		if(nombreDePrioritaireObtenusValve <= nombreUneSorteAffile) {
			nombreDePrioritaireObtenusValve++;
			return compareCharToEnum(typePrioritaire);
		}
		else if(nombreDeNonprioritaireObtenusValve >= nombreUneSorteAffile ) {
			nombreDePrioritaireObtenusValve = 0;
			nombreDeNonprioritaireObtenusValve = 0;
			return compareCharToEnum(typePrioritaire);
		}
		else {
			nombreDeNonprioritaireObtenusValve++;
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
				Thread.sleep(500);
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
