package jeu;

import java.util.Set;

import carte.Attaque;
import carte.Carte;
import carte.DebutLimite;

public class Coup {
	private Joueur joueur;
	private Carte carteJoue;
	private Joueur cible;

	public Coup(Joueur joueur, Carte carteJoue, Joueur cible) {
		this.joueur = joueur;
		this.carteJoue = carteJoue;
		this.cible = cible;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Carte getCarteJoue() {
		return carteJoue;
	}

	public Joueur getCible() {
		return cible;
	}
	
	public boolean estValide() {
		if (cible == null) {
			return true;
		}
		if (cible.equals(joueur)) {
			if (carteJoue instanceof Attaque || carteJoue instanceof DebutLimite) {
				return false;
			}
			//System.out.println(" cible is joueur : ");
			return joueur.peutDeposerCarte(carteJoue);
		}else if (carteJoue instanceof Attaque || carteJoue instanceof DebutLimite) {
			//System.out.println(" cible is not joueur : ");
			return cible.peutDeposerCarte(carteJoue);
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup coup) {
			if (cible==null) {
				return coup.getJoueur() != null 
					&& joueur.equals(coup.getJoueur())
					&& coup.getCarteJoue() != null 
					&& carteJoue.equals(coup.getCarteJoue())
					&& coup.getCible()==null;
			}else {
				return coup.getJoueur() != null 
						&& joueur.equals(coup.getJoueur())
						&& coup.getCarteJoue() != null 
						&& carteJoue.equals(coup.getCarteJoue())
						&& coup.getCible()!=null && cible.equals(coup.getCible());
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if (cible == null) {
			return 31 *(joueur.hashCode()+carteJoue.hashCode());
		}
		return 31 *(joueur.hashCode()+carteJoue.hashCode()+cible.hashCode());
	}
	
	@Override
	public String toString() {
		if (cible == null ) {
			return " defausse " + carteJoue.toString();
		}
		if(cible.equals(joueur)) {
			return  " depose " + carteJoue.toString() + " dans ma zone du jeu ";
		}
		return " depose " + carteJoue.toString() + " dans la zone du jeu "+cible.toString();
	}
	

}
