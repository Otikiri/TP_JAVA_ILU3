package testFonctionnels;

import jeu.Jeu;
import jeu.Joueur;

public class TestJeu {
	public static void main(String[] args) {
		Joueur jack = new Joueur("Jack");
		Joueur luffy = new Joueur("Luffy");
		Joueur bill = new Joueur("Bill");
		
		System.out.println("---------------------------INIT--------------------------------\n\n");
		Jeu jeu = new Jeu();
		jeu.inscrire(jack,bill,luffy);
		
		System.out.println("Starting round:\nDistributing cards:\n");
		jeu.distribuer();
		System.out.println("Done distributing\nInital states:\n");
		System.out.println(jack.afficherEtatJouer());
		System.out.println(bill.afficherEtatJouer());
		System.out.println(luffy.afficherEtatJouer());
	
		System.out.println("---------------------------PARTIE--------------------------------\n\n");
		
//		System.out.println(jeu.jouerTour(jack));
//		System.out.println(jeu.jouerTour(bill));
//		System.out.println(jeu.jouerTour(luffy));
		System.out.println(jeu.lancerTour()+"\n\n");
		System.out.println(jack.afficherEtatJouer());
		System.out.println(bill.afficherEtatJouer());
		System.out.println(luffy.afficherEtatJouer());
		
	
	}
}
