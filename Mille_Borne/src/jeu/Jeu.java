package jeu;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCarte;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> participants = new LinkedHashSet<>();
	private int suivant = 0;

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();// taken from the test functions
		Collections.addAll(listeCarteNonMelangee, jeu.donnerCartes());
		List<Carte> lMelange = GestionCarte.melanger(listeCarteNonMelangee);
		Carte[] c = lMelange.toArray(new Carte[0]);
		sabot = new Sabot(c);
	}

	public void inscrire(Joueur... joueurs) {
		for (Joueur joueur : joueurs) {
			System.out.println("> Added : " + joueur);
			participants.add(joueur);
		}
	}

	public void distribuer() {
		int nbCartes = 6;
		for (Joueur joueur : participants) {
			for (int i = 0; i < nbCartes; i++) {
				Carte carteARaj = sabot.piocher();
				joueur.donner(carteARaj);
			}
		}
	}

	public String jouerTour(Joueur joueur) {
		StringBuilder tour = new StringBuilder();

		Carte pioche = joueur.prendreCarte(sabot);
		tour.append("Le joueur " + joueur.toString() + " a pioche " + pioche.toString() + "\n");

		tour.append("Il a dans sa main:  " +joueur.getMain()+" ("+joueur.getMain().sizeMain()+")\n");

		Coup coup = joueur.choisirCoup(participants);
		Carte carte = coup.getCarteJoue();
		joueur.retirerDeLaMain(carte);
		
		tour.append(joueur.toString() + coup.toString()+"\n");
		tour.append("Il a dans sa main:  " + joueur.getMain() +" ("+joueur.getMain().sizeMain()+")\n");
		
		
		if (coup.getCible() == null) {
			sabot.ajouterCarte(carte);
		} else {
			Joueur cible = coup.getCible();
			cible.deposer(carte);
		}

		tour.append(joueur + " a parcouru en tout: " + joueur.donnerKmParcourus() + "\n");
		// tour.append("Voici l'etat de son jeu à la fin du
		// tour:\n"+joueur.afficherEtatJouer()+"\n");

		return tour.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		int i=0;
		for (Iterator<Joueur> iterator = participants.iterator();iterator.hasNext();i++){
			Joueur joueur = iterator.next();
			if (i==suivant) {
				suivant = (suivant + 1)%participants.size();
				return joueur;
			}
			
		}
		return null;
	}
	
	private boolean won(Joueur joueur) {
		return joueur.donnerKmParcourus() == 1000;
	}
	
	public String lancerTour() {
		int tourMax = 10000;
		int tour=0;
		StringBuilder partie = new StringBuilder();
		boolean winner = false;
		Joueur playerCur = null;
		while (!winner && !sabot.estVide()&&tour<tourMax) {
			playerCur = donnerJoueurSuivant();
			System.out.println(jouerTour(playerCur));
			winner = won(playerCur);
			tour++;
		}
		if (sabot.estVide()) {
			partie.append("Sabot vide\n");
		}
		if (tourMax<=tour) {
			partie.append("stalemate\n");
		}
		if (winner) {
			partie.append(playerCur.toString()+" a gagner");
		}
		return partie.toString();
		
	}
}