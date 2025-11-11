package testFonctionnels;


import jeu.Jeu;
import jeu.Joueur;

public class TestJeu {
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Joueur luffy = new Joueur("Luffy");
		Joueur jack = new Joueur("Jack");
		Joueur bill = new Joueur("Bill");
	
		
		jeu.inscrire(luffy,jack,bill);
		System.out.println("\nDistributing the cards");
		jeu.distribuerCarte();
		System.out.println("Distributing the cards done\n");
		
		System.out.println(jeu.lancer());
		
	}
}
