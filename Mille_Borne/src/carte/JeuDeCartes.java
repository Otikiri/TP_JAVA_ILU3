package carte;

public class JeuDeCartes {
	private Configurations[] conf = new Configurations[19];
	private int nbcase = 0;

	public String affichageDeCartes() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < nbcase; i++) {
			str.append(conf[i].getNbExemplaires() + " " + conf[i].getCarte()+"\n");
		}
		return str.toString();
	}

	public Carte[] donnerCartes() {
		int sumTailles = 0;
		for (int i = 0; i < nbcase; i++) {
			sumTailles += conf[i].getNbExemplaires();
		}
		Carte[] carteTab = new Carte[sumTailles];
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

	public void ajouterCarte(int nbEx, Carte carte) {
		if (nbcase < conf.length) {
			conf[nbcase] = new Configurations(nbEx, carte);
			nbcase++;
		} else {
			throw new IndexOutOfBoundsException();
		}
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
