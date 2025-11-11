package jeu;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCarte;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> jEns = new HashSet<>();
	private Iterator<Joueur> jIte = null;
	
	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();// comme dans les fonctions de teste
		Collections.addAll(listeCarteNonMelangee, jeu.donnerCartes());
		List<Carte> lMelange = GestionCarte.melanger(listeCarteNonMelangee);
		Carte[] c = lMelange.toArray(new Carte[0]);
		sabot = new Sabot(c);
	}

	public Sabot getSabot() {
		return sabot;
	}

	public void inscrire(Joueur... joueur) {
		for (Joueur j : joueur) {
			jEns.add(j);
			System.out.println("Joueur : " + j.toString() + " est ajoute.");
		}
	}

	public void distribuerCarte() {
		for (int i = 0; i < 6; i++) {
			for (Joueur j : jEns) {
				Carte c = sabot.piocher();
				if (c != null) {
					j.donner(c);
				}
			}
		}
	}

	public String jouerTour(Joueur joueur) {
		StringBuilder sb = new StringBuilder();

		Carte c = joueur.prendreCarte(sabot);
		

		sb.append(joueur.toString() + " a pioche " + c.toString()+"\n");
		MainJoueur mj = joueur.getMain();
		sb.append("Il a dans sa main "+mj.toString()+"\n");
		Coup cp = joueur.choisirCoup(jEns);
		

		Carte cj = cp.getCarteJoue();
		joueur.retirerDeLaMain(cj);

		if (cp.getCible() == null) {
			sb.append(joueur.toString()+" remet "+cj+" dans le sabot\n");
			sabot.ajouterCarte(cj);
		} else {

			if (cp.getCible().equals(joueur)) {
				sb.append(joueur.toString() + " depose " + cj.toString() + " dans sa zone de jeu\n");
				joueur.deposer(cj);
			} else {
				sb.append(joueur.toString() + " depose " + cj.toString() + " dans la zone de "
						+ cp.getCible().toString()+"\n");
				cp.getCible().deposer(cj);
			}
		}
		return sb.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		if (jEns.isEmpty()) {
			return null;
		}
		
		if (jIte == null||!jIte.hasNext()) {
			jIte = jEns.iterator();
		}
		return jIte.next();
	}
	
	public String lancer() {
		StringBuilder sb = new StringBuilder();
		boolean jeuFini = false;
		
		while (!sabot.estVide() && !jeuFini) {
			Joueur j = donnerJoueurSuivant();
			sb.append(jouerTour(j)+"\n");
			if (j.donnerKmParcourus()>=1000) {
				sb.append(j.toString()+" a gagne\n");
				jeuFini = true;
			}
		}
		if (!jeuFini) {
			sb.append("Aucun joueur a atteint les 1000 bornes\n");
		}
		return sb.toString();
	}
}
