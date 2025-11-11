package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import carte.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> mainJoueur = new ArrayList<>();
	
	public void prendre(Carte c) {
		mainJoueur.add(c);
	}
	
	public void jouer(Carte c) {
		assert(mainJoueur.contains(c)==true);
		mainJoueur.remove(c);
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Carte c : mainJoueur) {
			sb.append(c.toString()+" ");
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public Iterator<Carte> iterator() {
		return mainJoueur.listIterator();
	}

	public List<Carte> getmJoueur() {
		return mainJoueur;
	}

	
}
