package jeu;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import carte.Borne;
import carte.Carte;

public class Joueur {
	private String nom;
	private MainJoueur main = new MainJoueur();
	private ZoneDeJeu zone = new ZoneDeJeu();
	private Random rand = new Random();

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
		main.ajouter(c);
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

	private int donnerValeur(Borne b) {
		Borne b25 = new Borne(25);
		Borne b50 = new Borne(50);
		Borne b100 = new Borne(100);
		Borne b75 = new Borne(75);
		if (b.equals(b25)) {
			return 25;
		} else if (b.equals(b50)) {
			return 50;
		} else if (b.equals(b100)) {
			return 100;
		} else if (b.equals(b75)) {
			return 75;
		} else {
			return 200;
		}
	}

	public int donnerKmParcourus() {
		int sum = 0;
		for (Iterator<Carte> cIte = main.iterator(); cIte.hasNext();) {
			Carte c = cIte.next();
			if (c instanceof Borne b) {
				sum += donnerValeur(b);
			}
		}
		return sum;
	}

	public Set<Coup> coupsDefausse() {
		Set<Coup> s = new HashSet<>();
		for (Carte c : main) {
			Coup cp = new Coup(this, null, c);
			if (cp.estValide()) {
				s.add(cp);
			}
		}
		return s;
	}

	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}

	public Set<Coup> coupsPossible(Set<Joueur> participants) {
		Set<Coup> s = new HashSet<>();

		for (Carte carte : this.getMain()) {
			// on verifie que les cartes avec les participants
			for (Joueur p : participants) {
				Coup cp = new Coup(this, p, carte);
				if (cp.estValide()) {
					s.add(cp);
				}
			}

			// si la cible est null
			Coup cp = new Coup(this, null, carte);
			if (cp.estValide()) {
				s.add(cp);
			}
		}
		return s;
	}

	public Coup choisirCoup(Set<Joueur> participant) {
		
		Set<Coup> sC = coupsPossible(participant);
		if (sC.isEmpty()) {
			sC = coupsDefausse();			
		}
		List<Coup> lC = new ArrayList<>(sC);
		return lC.get(rand.nextInt(lC.size()));
	}
}



