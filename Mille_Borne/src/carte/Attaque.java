package carte;

public class Attaque extends Bataille {

	public Attaque(Type type) {
		super(type);
	}

	@Override
	public String toString() {
		Type t = getType();
		return "Carte "+t+" "+t.getAtkName();
	}

}
