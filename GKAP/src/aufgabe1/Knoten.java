package aufgabe1;

/**
 * Die Klasse Knoten speichert den Namen und Wert eines Knoten. Weitere Informationen wie Kanten, etc, sind in der Klasse OGraph zu finden.
 * 
 * @author Kira Wewer, Kristian Exﬂ
 *
 */
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
