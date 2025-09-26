package testFonctionnels;

import java.util.Iterator;

import carte.Attaque;
import carte.Borne;
import carte.Carte;
import carte.DebutLimite;
import carte.FinLimite;
import carte.JeuDeCartes;
import carte.Parade;
import carte.Type;
import jeu.Sabot;

public class TestSabot {
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
        jeu.ajouterCarte(5, new Attaque(Type.FEU));
        jeu.ajouterCarte(4, new DebutLimite());
	
        
		
		// test a
        System.out.println("Test a avec boucle while: \n");
        Sabot sabot = new Sabot(jeu.donnerCartes());
		while (!sabot.estVide()) {
			Carte c = sabot.piocher();
			System.out.println("je pioche "+c);
		}
		
		//test b
		System.out.println("\nTest b avec iterateur et remove: \n");
		sabot = new Sabot(jeu.donnerCartes());
		Iterator<Carte> ite = sabot.iterator();
		while(ite.hasNext()) {
			Carte c = ite.next();
			System.out.println("je pioche "+c);
			ite.remove();
		}
	}
	
}
