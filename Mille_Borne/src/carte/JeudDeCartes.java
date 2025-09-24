package carte;

public class JeudDeCartes {
	private Configurations[] conf = new Configurations[19];

	public String affichageDeCartes() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < conf.length; i++) {
			str.append(conf[i].getNbExemplaires() + " " + conf[i].getCarte());
		}
		return str.toString();
	}

	public Carte[] donnerCartes() {
		int sumTailles = 0;
		for (int i = 0; i < conf.length; i++) {
			sumTailles += conf[i].getNbExemplaires();
		}
		Carte[] carteTab = new Carte[sumTailles];
		int nbExe = 0;
		int jind = 0;
		for (int i = 0; i < carteTab.length; i++) {
			nbExe = conf[i].getNbExemplaires();
			for (int j = 0; j < nbExe; j++) {
				carteTab[j + jind] = conf[i].getCarte();
			}
			jind += nbExe;
		}
		return carteTab;
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
