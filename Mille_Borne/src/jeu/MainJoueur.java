package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import carte.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> mJoueur = new ArrayList<>();
	
	public void ajouter(Carte c) {
		mJoueur.add(c);
	}
	
	public void jouer(Carte c) {
		assert(mJoueur.contains(c)==true);
		mJoueur.remove(c);
	}
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		
		return strb.toString();
	}

	@Override
	public Iterator<Carte> iterator() {
		return mJoueur.listIterator();
	}

	
}
