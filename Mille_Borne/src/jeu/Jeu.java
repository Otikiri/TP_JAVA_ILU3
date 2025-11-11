package jeu;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCarte;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> jEns = new HashSet<>();

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();// comme dans les fonctions de teste
		Collections.addAll(listeCarteNonMelangee, jeu.donnerCartes());
		List<Carte> lMelange = GestionCarte.melanger(listeCarteNonMelangee);
		Carte[] c = (Carte[]) lMelange.toArray();
		sabot = new Sabot(c);
	}

	public void inscrire(Joueur... joueur) {
		for(Joueur j : joueur) {
			jEns.add(j);
			System.out.println("Joueur : "+j.toString()+" est ajoute.");
		}
	}
	
	public void distribuerCarte() {
		int compteur = 0;
		for(Joueur j : jEns) {
			if(compteur>=6) break;
			j.donner(sabot.piocher());
			compteur++;
		}
	}
	
}
