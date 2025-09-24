package carte;

public class Botte extends Probleme {

	protected Botte(Type type) {
		super(type);
	}

	@Override
	public String toString() {
		Type t = getType();
		return "Carte "+t+" "+t.getBotName();
	}
	
	
}
