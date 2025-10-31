package testFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import carte.Carte;
import carte.JeuDeCartes;
import utils.GestionCarte;

public class TestGestionCarte {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = GestionCarte.melanger(listeCartes);
		System.out.println(listeCartes);
		
		System.out.println(
				"liste mélangée sans erreur ? " + GestionCarte.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = GestionCarte.rassembler(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblée sans erreur ? " + GestionCarte.verifierRassembler(listeCartes));
	}
}
