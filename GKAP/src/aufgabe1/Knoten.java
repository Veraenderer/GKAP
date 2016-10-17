package aufgabe1;

public class Knoten {
	private String name;
	private double wert;
	
	public Knoten (String namePar, double wertPar) {
		name=namePar;
		wert=wertPar;
	}
	
	public Knoten (String namePar) {
		name=namePar;
		wert=0;
	}

	public String getName() {
		return name;
	}

	public double getWert() {
		return wert;
	}

	public void setWert(double wertPar) {
		wert = wertPar;
	}
}
