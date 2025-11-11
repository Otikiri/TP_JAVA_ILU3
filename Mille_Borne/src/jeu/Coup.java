package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import carte.Attaque;
import carte.Carte;
import carte.DebutLimite;

public class Coup {
	private Joueur joueur;
	private Joueur cible = null;
	private Carte carteJoue;

	public Coup(Joueur joueur, Joueur cible, Carte carteJoue) {
		this.joueur = joueur;
		this.cible = cible;
		this.carteJoue = carteJoue;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Joueur getCible() {
		return cible;
	}

	public Carte getCarteJoue() {
		return carteJoue;
	}

	public boolean estValide() {
		// si la carte joue est une attaque ou une parade
		if (carteJoue instanceof Attaque || carteJoue instanceof DebutLimite) {
			// on verifie que la cible n'est pas null et que ce n'est pas le joueur
			return cible != null && !cible.equals(joueur);
		}
		// sinon on verifie que soit la cible est null ou que la cible est le joueur ->
		// on depose la carte dans la zone du joueur
		return cible == null || cible.equals(joueur);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup cp) {
			return cp.joueur != null && joueur.equals(cp.joueur) && carteJoue.equals(cp.carteJoue)
					&& cible.equals(cp.cible);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * (joueur.hashCode() + carteJoue.hashCode() + cible.hashCode());
	}

	public Set<Coup> coupsPossible(Set<Joueur> participants) {
		Set<Coup> s = new HashSet<>();
		Joueur j = joueur;
		for (Carte carte : j.getMain()) {
			// on verifie que les cartes avec les participants
			for (Joueur p : participants) {
				Coup cp = new Coup(j, p, carte);
				if (cp.estValide()) {
					s.add(cp);
				}
			}
			
			// si la cible est null
			Coup cp = new Coup(j,null,carte);
			if (cp.estValide()) {
				s.add(cp);
			}
		}
		return s;
	}

}
