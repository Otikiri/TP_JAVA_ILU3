package carte;

public class JeuDeCartes {
	private Configurations[] conf = new Configurations[19];
	private int nbcase = 0;

	public JeuDeCartes() {
		this.ajouterCarte(10, new Borne(25));
		this.ajouterCarte(10, new Borne(50));
		this.ajouterCarte(10, new Borne(75));
		this.ajouterCarte(12, new Borne(100));
		this.ajouterCarte(4, new Borne(200));
		this.ajouterCarte(14, new Parade(Type.FEU));
		this.ajouterCarte(6, new FinLimite());
		this.ajouterCarte(6, new Parade(Type.ESSENCE));
		this.ajouterCarte(6, new Parade(Type.CREVAISON));
		this.ajouterCarte(6, new Parade(Type.ACCIDENT));
		this.ajouterCarte(5, new Attaque(Type.FEU));
		this.ajouterCarte(4, new DebutLimite());
		this.ajouterCarte(3, new Attaque(Type.ESSENCE));
		this.ajouterCarte(3, new Attaque(Type.CREVAISON));
		this.ajouterCarte(3, new Attaque(Type.ACCIDENT));
		this.ajouterCarte(1, new Botte(Type.FEU));
		this.ajouterCarte(1, new Botte(Type.ESSENCE));
		this.ajouterCarte(1, new Botte(Type.CREVAISON));
		this.ajouterCarte(1, new Botte(Type.ACCIDENT));

	}

	public String affichageDeCartes() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < nbcase; i++) {
			str.append(conf[i].getNbExemplaires() + " " + conf[i].getCarte() + "\n");
		}
		return str.toString();
	}

	private int donnerNbCarteTotal() {
		int sum = 0;
		for (int i = 0; i < nbcase; i++) {
			sum += conf[i].getNbExemplaires();
		}
		return sum;
	}

	public Carte[] donnerCartes() {
		int sumCarte = donnerNbCarteTotal();
		Carte[] carteTab = new Carte[sumCarte];
		int nbExe = 0;
		int jind = 0;
		for (int i = 0; i < nbcase; i++) {
			nbExe = conf[i].getNbExemplaires();
			for (int j = 0; j < nbExe; j++) {
				carteTab[j + jind] = conf[i].getCarte();
			}
			jind += nbExe;
		}
		return carteTab;
	}

	private void ajouterCarte(int nbEx, Carte carte) {
		if (nbcase < conf.length) {
			conf[nbcase] = new Configurations(nbEx, carte);
			nbcase++;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	private boolean contains(Carte[] tabCarte, Carte carte) {
		for (int i = 0; i < tabCarte.length; i++) {
			Carte cRead = tabCarte[i];
			if (cRead != null && cRead.equals(carte)) {
				return true;
			}
		}
		return false;
	}

	private int nbCarte(Carte[] tabCarte, Carte carte) {
		int nbC = 0;
		for (int i = 0; i < tabCarte.length; i++) {
			Carte c = tabCarte[i];
			if (c.equals(carte)) {
				nbC++;
			}
		}
		return nbC;
	}

	private boolean compareTab(Carte[] cTab, int[] fTab) {

		for (int i = 0; i < fTab.length; i++) {
			int frequency = fTab[i];
			Carte cRead = cTab[i];
			Carte cBase = conf[i].getCarte();
			int frequencyBase = conf[i].getNbExemplaires();
			if (!cRead.equals(cBase) || frequency != frequencyBase) {
				return false;
			}
		}
		return true;
	}

	public boolean checkCount() {
		Carte[] c = donnerCartes();
		Carte[] base = new Carte[19];
		int[] frequency = new int[19];
		int indice = 0;
		for (int i = 0; i < c.length; i++) {
			Carte cRead = c[i];
			if (!contains(base, cRead)) {
				base[indice] = cRead;
				frequency[indice] = nbCarte(c, cRead);
				indice++;
			}
		}
		return compareTab(base, frequency);
	}

	private static class Configurations {
		private int nbExmplaires;
		private Carte carte;

		private Configurations(int nbExmplaires, Carte carte) {
			this.nbExmplaires = nbExmplaires;
			this.carte = carte;
		}

		public Carte getCarte() {
			return carte;
		}

		public int getNbExemplaires() {
			return nbExmplaires;
		}
	}
}
