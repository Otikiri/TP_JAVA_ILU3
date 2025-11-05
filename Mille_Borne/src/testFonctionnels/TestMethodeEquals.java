package testFonctionnels;

import carte.Type;
import carte.Attaque;
import carte.Borne;
import carte.Carte;
import carte.Parade;

public class TestMethodeEquals {
	public static void main(String[] args) {
		Carte f1 = new Attaque(Type.FEU);
		Carte f2 = new Attaque(Type.FEU);
		
		System.out.println("Feu rouge = Feu rouge ? "+ f1.equals(f2));
		
		Carte b1 = new Borne(25);
		Carte b2 = new Borne(25);
		
		System.out.println(b1.equals(b2));
		
		Carte fv = new Parade(Type.FEU);
		System.out.println("Feu rouge = Feu vert ? "+f1.equals(fv));
	}
	
}
