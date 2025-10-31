package jeu;

import java.util.ArrayList;
import java.util.List;

import carte.Bataille;
import carte.Borne;
import carte.FinLimite;
import carte.Limite;

public class ZoneDeJeu {
	private List<Limite> pileLim = new ArrayList<>();
	private List<Bataille> pileBat = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if (pileLim.isEmpty() || pileLim.get(0).equals(new FinLimite())) {
			return 200;
		}
		return 50;
	}
	
	private int valeurAjouter(Borne b) {
		Borne b25 = new Borne(25);
		Borne b50 = new Borne(50);
		Borne b100 = new Borne(100);
		if (b.equals(b25)) {
			return 25;
		}else if(b.equals(b50)) {
			return 50;
		}else if(b.equals(b100)) {
			return 100;
		}else {
			return 200;
		}
	}
	public int donnerKmParcourus() {
		int sum = 0; 

		for(Borne b : pileBorne) {
			sum += valeurAjouter(b);
		}
		return sum;
	}
}
