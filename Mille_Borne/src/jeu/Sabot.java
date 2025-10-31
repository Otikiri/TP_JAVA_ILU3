package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import carte.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] carteTab;
	private int nbCartes;
	private int nbOp = 0 ;

	public Sabot(Carte[] carteTab) {
		this.carteTab = carteTab;
		this.nbCartes = carteTab.length;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}

	public void ajouterCarte(Carte carte) {
		if (nbCartes < carteTab.length) {
			carteTab[nbCartes] = carte;
			nbCartes++;
			nbOp++;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	public Carte piocher() {
		if (estVide()) {
			throw new IllegalStateException("Sabot vide");
		}
		Iterateur ite = new Iterateur();
		Carte c = ite.next();
		ite.remove();
		return c;
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int indIte = 0;
		private boolean nextEff = false;
		private int nbOPref = nbOp;
		
		private void verifConcurence() {
			if (nbOPref != nbOp) {
				throw new ConcurrentModificationException();
			}
		}
		@Override
		public boolean hasNext() {
			return indIte < nbCartes;
		}

		@Override
		public Carte next() {
			verifConcurence();
			if (hasNext()) {
				Carte c = carteTab[indIte];
				indIte ++;
				nextEff = true;
				return c;
			}else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verifConcurence();
			if (!nextEff || nbCartes <1) {
				throw new IllegalStateException();
			}
			for (int i = indIte-1; i < nbCartes-1; i++) {
				carteTab[i] = carteTab[i+1];
			}
			nextEff = false;
			indIte --; 
			nbCartes--; 
			nbOPref ++;
			nbOp ++;
		}
	}
}
