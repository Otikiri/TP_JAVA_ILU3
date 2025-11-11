package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import carte.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> mainJoueur = new ArrayList<>();
	
	public void ajouter(Carte c) {
		mainJoueur.add(c);
	}
	
	public void jouer(Carte c) {
		assert(mainJoueur.contains(c)==true);
		mainJoueur.remove(c);
	}
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		
		return strb.toString();
	}

	@Override
	public Iterator<Carte> iterator() {
		return mainJoueur.listIterator();
	}

	public List<Carte> getmJoueur() {
		return mainJoueur;
	}

	
}
