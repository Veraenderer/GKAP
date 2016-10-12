package aufgabe1;

import edu.uci.ics.jung.graph.util.EdgeType;

public class Kante {
private String name;
private double wert;
private EdgeType typ;
private Knoten startKnoten;
private Knoten zielKnoten;

public Kante (String namePar, double wertPar) {
	name=namePar;
	wert=wertPar;
	typ=EdgeType.UNDIRECTED;
	startKnoten=null;
	zielKnoten=null;
}

public Kante (String namePar, double wertPar, EdgeType typPar) {
	name=namePar;
	wert=wertPar;
	typ=typPar;
	startKnoten=null;
	zielKnoten=null;
}

public Kante (String namePar, double wertPar, EdgeType typPar, Knoten startKnotenPar, Knoten zielKnotenPar) {
	name=namePar;
	wert=wertPar;
	typ=typPar;
	startKnoten=startKnotenPar;
	zielKnoten=zielKnotenPar;
}

public String getName() {
	return name;
}

public Knoten getStartKnoten() {
	return startKnoten;
}

public void setStartKnoten(Knoten startKnotenPar) {
	startKnoten = startKnotenPar;
}

public Knoten getZielKnoten() {
	return zielKnoten;
}

public void setZielKnoten(Knoten zielKnotenPar) {
	zielKnoten = zielKnotenPar;
}

public double getWert() {
	return wert;
}

public void setWert(double wertPar) {
	wert = wertPar;
}

public EdgeType getTyp() {
	return typ;
}

public void setTyp(EdgeType typPar) {
	typ = typPar;
}

}
