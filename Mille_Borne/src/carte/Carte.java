package carte;


public abstract class Carte {
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carte carte) {
			return carte.getClass() == getClass();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31*getClass().hashCode();
	}
}
