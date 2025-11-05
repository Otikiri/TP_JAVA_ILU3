package carte;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}

	@Override
	public String toString() {
		return getType().getBotName();
	}
	
	@Override
	public int hashCode() {
		String s = toString();
		return 31*s.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Botte bo) {
			String boS = bo.toString();
			return boS.equals(toString());
		}
		return false;
	}
}
