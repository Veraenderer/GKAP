package aufgabe1;

import edu.uci.ics.jung.graph.util.EdgeType;

public class Kante {
private String name;
private int wert;
private EdgeType typ;
private Knoten startKnoten;
private Knoten zielKnoten;

public Kante () {
	name="";
	wert=0;
	typ=EdgeType.UNDIRECTED;
	startKnoten=null;
	zielKnoten=null;
}

public Kante (String namePar) {
	name=namePar;
	wert=0;
	typ=EdgeType.UNDIRECTED;
	startKnoten=null;
	zielKnoten=null;
}

public Kante (String namePar, int wertPar) {
	name=namePar;
	wert=wertPar;
	typ=EdgeType.UNDIRECTED;
	startKnoten=null;
	zielKnoten=null;
}

public Kante (String namePar, int wertPar, EdgeType typPar) {
	name=namePar;
	wert=wertPar;
	typ=typPar;
	startKnoten=null;
	zielKnoten=null;
}

public Kante (String namePar, int wertPar, EdgeType typPar, Knoten startKnotenPar, Knoten zielKnotenPar) {
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

public int getWert() {
	return wert;
}

public void setWert(int wertPar) {
	wert = wertPar;
}

public EdgeType getTyp() {
	return typ;
}

public void setTyp(EdgeType typPar) {
	typ = typPar;
}

public String toString () {
	if(startKnoten==null||zielKnoten==null) {
		return "";
	}
	String kantenString=startKnoten.getName();
	if (typ.equals(EdgeType.UNDIRECTED)) {
		kantenString=kantenString+" -- "+zielKnoten.getName();
	}
	else {
		kantenString=kantenString+" -> "+zielKnoten.getName();
	}
	if (!name.equals("")){
		kantenString=kantenString+" ("+name+")";
	}
	kantenString=kantenString+" : "+wert+";";
	return kantenString;
}

}
