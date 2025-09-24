package carte;

public class Parade extends Bataille {

	protected Parade(Type type) {
		super(type);
	}

	@Override
	public String toString() {
		Type t = getType();
		return "Carte "+t+" "+t.getParName();
	}

}
