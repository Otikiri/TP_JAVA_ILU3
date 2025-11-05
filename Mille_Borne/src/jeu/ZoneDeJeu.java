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

	private boolean pAvAuxParade(Parade pa) {
		if (pa.getType() == Type.FEU) {
			return true;
		} else {
			return estPrioritaire();
		}
	}

	private boolean pAvAuxAttaque(Attaque a) {
		if (a.getType() == Type.FEU) {
			return estPrioritaire();
		}
		return isBotteNAtkSameType(a) && estPrioritaire();
	}

	private boolean peutAvancerAux() {
		if (pileBat.isEmpty() && estPrioritaire()) {
			return true;
		}
		if (!pileBat.isEmpty()) {
			Bataille som = pileBat.get(0);
			if (som instanceof Parade pa) {
				return pAvAuxParade(pa);
			}
			if (som instanceof Attaque a) {
				return pAvAuxAttaque(a);
			}

		}
		return false;
	}

	public boolean peutAvancer() {
		if (!pileBat.isEmpty()) {
			Bataille b = pileBat.get(0);
			if (b instanceof Parade bn) { // si b est une parade
				return bn.getType() == Type.FEU;
			}
		}
		return peutAvancerAux();
	}

	private boolean estDepotFeuVertAutorise() {
		if (pileBat.isEmpty()) {
			return true;
		} else {
			if (estPrioritaire()) {
				return false;
			}
			Bataille b = pileBat.get(0);
			if (b instanceof Attaque && b.getType() == Type.FEU) {
				return true;
			}
			if (b instanceof Parade && b.getType() != Type.FEU) {
				return true;
			}
			if (b instanceof Attaque a) {
				return isBotteNAtkSameType(a);
			}
		}
		return false;
	}

	private boolean estDepotBorneAutorisee(Borne borne) {
		Bataille b = pileBat.get(0);
		if (b instanceof Attaque && b.getType() == Type.FEU) { // si b est une attaque de type feu (feu rouge)
			return false;
		}
		if (b instanceof Parade && b.getType() != Type.FEU) {
			return false;
		}
		return donnerValeur(borne) < donnerLimitationVitesse() && donnerKmParcourus() <= 1000;

	}

	private boolean estDepotLimiteAutorisee(Limite limite) {
		if (estPrioritaire()) {
			return false;
		}
		if (pileLim.isEmpty()) {
			return true;
		}
		Limite l = pileLim.get(0);
		if (limite instanceof DebutLimite) {
			return l instanceof FinLimite;
		}
		if (limite instanceof FinLimite) {
			return l instanceof DebutLimite;
		}
		return false;
	}

	public boolean estDepotBatailleAutorisee(Bataille bataille) {

		// carte bataille est une attaque
		if (bataille instanceof Attaque a) {
			if (!peutAvancer()) {
				return false;
			}
			if (isBotteNAtkSameType(a)) {
				return false;
			}
			return true;
		}

		// carte bataille est une parade
		if (bataille instanceof Parade pa) {
			if (pa.getType() == Type.FEU) {
				return estDepotFeuVertAutorise();
			} else {
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
		return c instanceof Botte;
	}

	public boolean estPrioritaire() {
		Botte b = new Botte(Type.FEU);
		return bottes.contains(b);
	}
}
