package carte;

public enum Type {
	FEU("Feu rouge","Feu vert","Prioritaire"),
	ESSENCE("Panne d'essence","Essence","Citerne"),
	CREVAISON("Crevaison","Roue de secours","Increvable"),
	ACCIDENT("Accident","Reparations","As du volant");
	

	private final String nomAtq; 
	private final String nomBot;
	private final String nomPar;
	
	Type(String nomA, String nomB, String nomP) {
		this.nomAtq = nomA;
		this.nomBot = nomB;
		this.nomPar = nomP; 
	}
	
	public String getAtkName() {
		return nomAtq;
	}
	public String getBotName() {
		return nomBot;
	}
	public String getParName() {
		return nomPar;
	}
}
