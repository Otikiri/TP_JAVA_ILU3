package jeu;

import java.util.ArrayList;
import java.util.List;

import carte.Attaque;
import carte.Bataille;
import carte.Borne;
import carte.Carte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.Limite;
import carte.Parade;
import carte.Type;

public class ZoneDeJeu {
	private List<Limite> pileLim = new ArrayList<>();
	private List<Bataille> pileBat = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();

	public int donnerLimitationVitesse() {
		if (pileLim.isEmpty() || pileLim.get(0) instanceof FinLimite) {
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
		} else {
			throw new IllegalArgumentException("Type carte inconnu");
		}
	}

	public boolean peutAvancer() {
		if (!pileBat.isEmpty()) {
			Bataille b = pileBat.get(0);
			if (b instanceof Parade bn) { //si b est une parade
				return bn.getType() == Type.FEU; 
				// on retourne si b la parade est de type FEU -> equivalent a regarder si b = FEU VERT
				// logique s'applique sur les autres partie aussi
			}
		}
		return false;
	}

	private boolean estDepotFeuVertAutorise() {
		if (pileBat.isEmpty()) {
			return true;
		} else {
			Bataille b = pileBat.get(0);
			if (b instanceof Attaque && b.getType() == Type.FEU) {
				return true;
			}
			if (b instanceof Parade && b.getType()!=Type.FEU) {
				return true;
			}
		}
		return false;
	}

	private boolean estDepotBorneAutorisee(Borne borne) {
		Bataille b = pileBat.get(0);
		if (b instanceof Attaque && b.getType() == Type.FEU) { //si b est une attaque de type feu (feu rouge)
			return false;
		}
		if (b instanceof Parade && b.getType() != Type.FEU) {
			return false;
		}
		return donnerValeur(borne) < donnerLimitationVitesse() && donnerKmParcourus() <= 1000;
		
	}

	private boolean estDepotLimiteAutorisee(Limite limite) {
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
		
		//carte bataille est une attaque
		if (bataille instanceof Attaque) {
			if (pileBat.isEmpty()) {
				return false;
			}else { 
				Bataille som = pileBat.get(0);
				if (som instanceof Attaque) {
					return false;
				}
			}
			return true;
		}
		
		//carte bataille est une parade 
		if (bataille instanceof Parade pa) {
			if (pa.getType() == Type.FEU) {
				return estDepotFeuVertAutorise();
			}else {
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
//		if (c instanceof Parade) {
//			return estDepotFeuVertAutorise();
//		}
		if (c instanceof Borne b) {
			return estDepotBorneAutorisee(b);
		}
		if (c instanceof Limite l) {
			return estDepotLimiteAutorisee(l);
		}
		if (c instanceof Bataille bat) {
			return estDepotBatailleAutorisee(bat);
		}
		return false;
	}
}
