package testFonctionnels;

import carte.JeuDeCartes;


public class TestJeuDeCartes {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		
		// init les jeux de cartes
		System.out.println("JEU:\n"+jeu.affichageDeCartes());
		/*
		 * Carte[] carte = jeu.donnerCartes(); for (Carte carte2 : carte) {
		 * System.out.println(carte2); }
		 */
		
		System.out.println(jeu.checkCount());
	}
}
