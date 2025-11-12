package testFonctionnels;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import carte.Attaque;
import carte.Borne;
import carte.Botte;
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

		// test a System.out.println("Test a avec boucle while: \n");
//		Sabot sabot = new Sabot(jeu.donnerCartes());
//		while (!sabot.estVide()) {
//			Carte c = sabot.piocher();
//			System.out.println("je pioche " + c);
//		}

		// test b System.out.println("\nTest b avec iterateur et remove: \n"); \
//		sabot = new Sabot(jeu.donnerCartes());
//		Iterator<Carte> ite = sabot.iterator();
//		while (ite.hasNext()) {
//			Carte c = ite.next();
//			System.out.println("je pioche " + c);
//			ite.remove();
//		}

		// test c
		try {
			Sabot sabot = new Sabot(jeu.donnerCartes());
			Carte cartePiochee = sabot.piocher();
			System.out.println("Je pioche " + cartePiochee);
			for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
				Carte carte = iterator.next();
				System.out.println("Je pioche " + carte);
				iterator.remove();
				//cartePiochee = sabot.piocher();
				//sabot.ajouterCarte(new Botte(Type.ACCIDENT));
			}
			Iterator<Carte> iterator = sabot.iterator();
			System.out.println("\nLa pioche contient encore des cartes ? " + iterator.hasNext());
		} catch (ConcurrentModificationException e) {
			System.out.println(e);
		}

	}

}
