package carte;

public class Borne extends Carte {
	private int km;
	
	
	public Borne(int km) {
		super();
		this.km = km;
	}

	@Override
	public String toString() {
		return km+"KM";
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && this.km == ((Borne) obj).km;
	}
	
	@Override
	public int hashCode() {
		return 31*(super.hashCode()+km);
	}

}
