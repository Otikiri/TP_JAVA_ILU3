package jeu;

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
					&& cp.cible!= null && cible.equals(cp.cible);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (cible == null) {
			return 31 * (joueur.hashCode() + carteJoue.hashCode()); 
		}
		return 31 * (joueur.hashCode() + carteJoue.hashCode() + cible.hashCode());
	}

	@Override
	public String toString() {
		if (cible == null) {
			return "defausse "+carteJoue.toString();
		}
		return "depose "+carteJoue.toString()+" dans la zone du jeu "+cible.toString();
	}


}
