package jeu;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCarte;

public class Jeu {
	private Sabot sabot;

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();// comme dans les fonctions de teste
		Collections.addAll(listeCarteNonMelangee, jeu.donnerCartes());
		List<Carte> lMelange = GestionCarte.melanger(listeCarteNonMelangee);
		Carte[] c = (Carte[])lMelange.toArray();
		sabot = new Sabot(c);
	}
}
