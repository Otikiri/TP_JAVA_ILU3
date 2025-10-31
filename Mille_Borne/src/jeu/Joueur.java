package jeu;

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
		if(sabot.estVide()) {
			return null;
		}
		Carte c = sabot.piocher();
		donner(c);
		return c;
	}
}
