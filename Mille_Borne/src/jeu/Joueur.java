package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import carte.Carte;
import carte.Bataille;

public class Joueur {
	private String nom;
	private MainJoueur main = new MainJoueur();
	private ZoneDeJeu zone = new ZoneDeJeu();

	public MainJoueur getMain() {
		return main;
	}

	public Joueur(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur j) {
			return nom.equals(j.nom);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * nom.hashCode();
	}

	public void donner(Carte c) {
		main.prendre(c);
	}

	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte c = sabot.piocher();
		donner(c);
		return c;
	}

	public void deposer(Carte c) {
		zone.deposer(c);
	}

	public int donnerKmParcourus() {
		return zone.donnerKmParcourus();
	}

	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}

	public boolean peutDeposerCarte(Carte c) {
		return zone.estDepotAutorise(c);
	}

	public Set<Coup> coupsPossible(Set<Joueur> participants) {
		Set<Coup> coupSet = new HashSet<>();

		for (Iterator<Carte> iterator = main.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();

			for (Joueur participant : participants) {
				Coup coupToAdd = new Coup(this, carte, participant);
				if (coupToAdd.estValide()) {
					coupSet.add(coupToAdd);
				}
			}
		}
		return coupSet;
	}

	public Set<Coup> coupsDefausse() {
		Set<Coup> coupSet = new HashSet<>();

		for (Iterator<Carte> iterator = main.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			Coup defausse = new Coup(this, carte, null);
			coupSet.add(defausse);
		}
		return coupSet;
	}


	public String afficherEtatJouer() {
		StringBuilder sb = new StringBuilder();
		sb.append("Etat du joueur : " + nom + "\n");
		sb.append(">Bottes : " + zone.getBottes() + "\n");
		List<Bataille> b = zone.getPileBat();
		Bataille bat = null;
		if (!b.isEmpty()) {
			bat = b.get(0);
		}
		sb.append(">Sommet de la pile : " + bat + "\n");
		sb.append(">Main : " + main.toString() + "\n");
		return sb.toString();
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupSet = coupsPossible(participants);
		if (coupSet.isEmpty()) {
			coupSet = coupsDefausse();
		}
		if (coupSet.isEmpty()) {
			return null;
		}
		Random rand = new Random();
		List<Coup> coupList = new LinkedList<>(coupSet);
		return coupList.get(rand.nextInt(coupList.size()));
	}
}
