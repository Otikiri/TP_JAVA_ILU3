package jeu;

import java.util.Iterator;

import carte.Borne;
import carte.Carte;

public class Joueur {
	private String nom;
	private MainJoueur main = new MainJoueur();

	public MainJoueur getMain() {
		return main;
	}

	public Joueur(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur j) {
			return nom.equals(j.nom);
		}
		return false;
	}

	public void donner(Carte c) {
		main.ajouter(c);
	}

	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte c = sabot.piocher();
		donner(c);
		return c;
	}

	public void deposer(Carte c) {
		main.ajouter(c);
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
	
	public int donnerKmParcourus(){
		int sum = 0;
		for(Iterator<Carte> cIte = main.iterator(); cIte.hasNext();) {
			Carte c = cIte.next(); 
			if (c instanceof Borne b) {
				sum += donnerValeur(b);
			}
		}
		return sum;
	} 

}
