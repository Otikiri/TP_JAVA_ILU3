package test;

import carte.Attaque;
import carte.Borne;
import carte.Carte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.JeuDeCartes;
import carte.Parade;
import carte.Type;

public class TestJeuDeCartes {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		
		// init les jeux de cartes
		jeu.ajouterCarte(10, new Borne(25));
		jeu.ajouterCarte(10, new Borne(50));
		jeu.ajouterCarte(10, new Borne(75));
		jeu.ajouterCarte(12, new Borne(100));
		jeu.ajouterCarte(4, new Borne(200));
		jeu.ajouterCarte(14, new Parade(Type.FEU));
		jeu.ajouterCarte(6, new FinLimite());
		jeu.ajouterCarte(6, new Parade(Type.ESSENCE));
		jeu.ajouterCarte(6, new Parade(Type.CREVAISON));
		jeu.ajouterCarte(6, new Parade(Type.ACCIDENT));
		jeu.ajouterCarte(5,new Attaque(Type.FEU));
		jeu.ajouterCarte(4, new DebutLimite());
		
		System.out.println("JEU:\n"+jeu.affichageDeCartes());
		/*
		 * Carte[] carte = jeu.donnerCartes(); for (Carte carte2 : carte) {
		 * System.out.println(carte2); }
		 */
	}
}
