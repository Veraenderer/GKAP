package aufgabe1;

import edu.uci.ics.jung.graph.util.EdgeType;

public class Knoten {
	private String name;
	private double wert;

	public Knoten (String namePar, double wertPar) {
		name=namePar;
		wert=wertPar;
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