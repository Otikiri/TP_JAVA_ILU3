package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import carte.Attaque;
import carte.Bataille;
import carte.Borne;
import carte.Carte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.Limite;
import carte.Parade;
import carte.Type;
import carte.Botte;

public class ZoneDeJeu {
	private List<Limite> pileLim = new ArrayList<>();
	private List<Bataille> pileBat = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (pileLim.isEmpty() || pileLim.get(0) instanceof FinLimite || estPrioritaire()) {
			return 200;
		}
		return 50;
	}

	public Set<Botte> getBottes() {
		return bottes;
	}

	public List<Bataille> getPileBat() {
		return pileBat;
	}

	private int donnerValeur(Borne b) {
		Borne b25 = new Borne(25);
		Borne b50 = new Borne(50);
		Borne b100 = new Borne(100);
		Borne b75 = new Borne(75);
		if (b.equals(b25)) {
			return 25;
		} else if (b.equals(b50)) {
			return 50;
		} else if (b.equals(b100)) {
			return 100;
		} else if (b.equals(b75)) {
			return 75;
		} else {
			return 200;
		}
	}

	public int donnerKmParcourus() {
		int sum = 0;
		for (Borne b : pileBorne) {
			sum += donnerValeur(b);
		}
		return sum;
	}

	public void deposer(Carte c) {
		if (c instanceof Borne b) {
			pileBorne.add(0, b);
		} else if (c instanceof Limite l) {
			pileLim.add(0, l);
		} else if (c instanceof Bataille ba) {
			pileBat.add(0, ba);
		} else if (c instanceof Botte bo) {
			bottes.add(bo);
		} else {
			throw new IllegalArgumentException("Type carte inconnu");
		}
	}

	private boolean isBotteNAtkSameType(Attaque a) {
		if (bottes.isEmpty()) {
			return false;
		} else {
			Type aT = a.getType();
			return bottes.contains(new Botte(aT));
		}
	}

	public boolean peutAvancer() {
		// Pile vide et prioritaire
		if (pileBat.isEmpty()) {
			return estPrioritaire();
		}
		
		Bataille som = pileBat.get(0);
		
		// Sommet est un feu vert
		if (som instanceof Parade && som.getType() == Type.FEU) {
			return true;
		}
		
		// Sommet est une parade (non-feu) et on est prioritaire
		if (som instanceof Parade) {
			return estPrioritaire();
		}
		
		// Sommet est une attaque de type FEU et on est prioritaire
		if (som instanceof Attaque && som.getType() == Type.FEU) {
			return estPrioritaire();
		}
		
		// Sommet est une attaque d'un autre type, on a la botte ET on est prioritaire
		if (som instanceof Attaque a) {
			return isBotteNAtkSameType(a) && estPrioritaire();
		}
		
		return false;
	}

	private boolean estDepotFeuVertAutorise() {
		// Si prioritaire, on ne peut pas déposer de feu vert
		if (estPrioritaire()) {
			return false;
		}
		
		// Si pile vide, on peut déposer un feu vert
		if (pileBat.isEmpty()) {
			return true;
		}
		
		Bataille b = pileBat.get(0);
		
		// Si le sommet est un feu rouge, on peut déposer un feu vert
		if (b instanceof Attaque && b.getType() == Type.FEU) {
			return true;
		}
		
		// Si le sommet est une parade qui n'est pas un feu vert, on peut déposer un feu vert
		if (b instanceof Parade && b.getType() != Type.FEU) {
			return true;
		}
		
		// Si le sommet est une attaque dont on a la botte, on peut déposer un feu vert
		if (b instanceof Attaque a) {
			return isBotteNAtkSameType(a);
		}
		
		return false;
	}

	private boolean estDepotBorneAutorisee(Borne borne) {
		// On ne peut pas avancer
		if (!peutAvancer()) {
			return false;
		}
		
		// Si la pile n'est pas vide, vérifier les conditions supplémentaires
		if (!pileBat.isEmpty()) {
			Bataille b = pileBat.get(0);
			
			// Si c'est un feu rouge, on ne peut pas déposer de borne
			if (b instanceof Attaque && b.getType() == Type.FEU) {
				return false;
			}
			
			// Si c'est une parade qui n'est pas un feu vert, on ne peut pas déposer de borne
			if (b instanceof Parade && b.getType() != Type.FEU) {
				return false;
			}
		}
		
		// Vérifier la limitation de vitesse et le total de km
		int valeurBorne = donnerValeur(borne);
		return valeurBorne <= donnerLimitationVitesse() 
			&& (donnerKmParcourus() + valeurBorne) <= 1000;
	}

	private boolean estDepotLimiteAutorisee(Limite limite) {
		// Si prioritaire, on ne peut pas déposer de limite
		if (estPrioritaire()) {
			return false;
		}
		
		// Si pile vide, on peut déposer une limite
		if (pileLim.isEmpty()) {
			return true;
		}
		
		Limite l = pileLim.get(0);
		
		// On peut déposer DebutLimite seulement si le sommet est FinLimite
		if (limite instanceof DebutLimite) {
			return l instanceof FinLimite;
		}
		
		// On peut déposer FinLimite seulement si le sommet est DebutLimite
		if (limite instanceof FinLimite) {
			return l instanceof DebutLimite;
		}
		
		return false;
	}

	public boolean estDepotBatailleAutorisee(Bataille bataille) {
		// Carte bataille est une attaque
		if (bataille instanceof Attaque a) {
			// On ne peut pas attaquer si on ne peut pas avancer
			if (!peutAvancer()) {
				return false;
			}
			// On ne peut pas déposer une attaque si on a la botte correspondante
			if (isBotteNAtkSameType(a)) {
				return false;
			}
			return true;
		}

		// Carte bataille est une parade
		if (bataille instanceof Parade pa) {
			if (pa.getType() == Type.FEU) {
				return estDepotFeuVertAutorise();
			} else {
				// Pour les autres parades, il faut qu'il y ait une attaque correspondante au sommet
				if (pileBat.isEmpty()) {
					return false;
				}
				Bataille som = pileBat.get(0);
				return som instanceof Attaque && som.getType() == pa.getType();
			}
		}
		
		return false;
	}

	public boolean estDepotAutorise(Carte c) {
		if (c instanceof Borne b) {
			return estDepotBorneAutorisee(b);
		}
		if (c instanceof Limite l) {
			return estDepotLimiteAutorisee(l);
		}
		if (c instanceof Bataille bat) {
			return estDepotBatailleAutorisee(bat);
		}
		// Les bottes peuvent toujours être déposées
		return c instanceof Botte;
	}

	public boolean estPrioritaire() {
		Botte b = new Botte(Type.FEU);
		return bottes.contains(b);
	}
}
