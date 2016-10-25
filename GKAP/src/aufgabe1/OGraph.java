package aufgabe1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * Die Klasse OGraph speichert die Knoten und Kanten eines Graphen als Listen, bietet Funktionalit�ten zum Hinzuf�gen und Entfernen und weitere n�tige Operationen f�r das Zeichnen des Graphen an.
 * 
 * @author Kira Wewer, Kristian Ex�
 *
 */
public class OGraph implements Graph<Knoten,Kante> {
	private List<Knoten> knotenList;
	private List<Kante> kantenList;
	
	/**
	 * Kontruktor
	 */
	public OGraph () {
		knotenList=new ArrayList<Knoten>();
		kantenList=new ArrayList<Kante>();
	}
	
	/**
	 * Verbindet eine Kante mit einem Start und Zielknoten. �berschreibt eventuell vorhandene Informationen im Kantenobjekt.
	 * 
	 * @param kante Zielobjekt
	 * @param startKnoten Startknoten bei einem gerichteten Graphen.
	 * @param zielKnoten Zielknoten bei einem gerichteten Graphen.
	 */
	private boolean knotenZuKante(Kante kante, Knoten startKnoten, Knoten zielKnoten) {
		if (kante==null||startKnoten==null||zielKnoten==null) {
			return false;
		}
		kante.setStartKnoten(startKnoten);
		kante.setZielKnoten(zielKnoten);
		return true;
	}
	
	/**
	 * Helper um sicher zu stellen, dass nicht mehrfach der gleiche Knoten hinzugef�gt wird.
	 * 
	 * @param startKnoten
	 * @param zielKnoten
	 */
	private boolean kontrollierKnoten (Knoten startKnoten, Knoten zielKnoten) {
		if (startKnoten==null||zielKnoten==null) {
			return false;
		}
		if (!containsVertex(startKnoten)) {
			addVertex(startKnoten);
		}
		if (!containsVertex(zielKnoten)) {
			addVertex(zielKnoten);
		}
		return true;
	}
	
	/**
	 * Erstellt eine gerichtete oder ungerichtete Kante und f�gt sie der Kantenliste des Graphen hinzu.
	 * 
	 * @param startKnoten Startknoten bei einem gerichteten Graphen.
	 * @param zielKnoten Zielknoten bei einem gerichteten Graphen.
	 * @param kantenName Name der Kante.
	 * @param typ EdgeType.UNDIRECTED oder EdgeType.DIRECTED
	 * @param wert F�r eine Gewichtung != 0 setzen, ansonsten auf 0 setzen.
	 */
	public boolean createEdge(Knoten startKnoten, Knoten zielKnoten, String kantenName, EdgeType typ, int wert) {
		Kante kante = new Kante(kantenName, wert, typ, startKnoten, zielKnoten);
		return addEdge(kante,startKnoten,zielKnoten);
	}
	
	/**
	 * F�gt eine (ungerichtete, sofern nicht vorher modifiziert) Kante mit Start- und Zielknoten der Kantenliste des Graphen hinzu. Name und Wert werden der Kante entnommen.
	 */
	@Override
	public boolean addEdge(Kante kante, Knoten startKnoten, Knoten zielKnoten) {
		if (!knotenZuKante(kante, startKnoten, zielKnoten)||!kontrollierKnoten(startKnoten,zielKnoten)) {
			return false;
		}
		kantenList.add(kante);
		return true;
	}
	
	/**
	 * F�gt der Kantenliste eine gerichtete oder ungerichtete Kante mit Start- und Zielknoten der Kantenliste des Graphen hinzu. Name und Wert werden der Kante entnommen.
	 */
	@Override
	public boolean addEdge(Kante kante, Knoten startKnoten, Knoten zielKnoten, EdgeType typ) {
		if (typ==null||kante==null) {
			return false;
		}
		kante.setTyp(typ);
		return addEdge(kante,startKnoten,zielKnoten);
	}
	
	/**
	 * 
	 */
	//TODO Was macht diese Funktion bitte?
	@Override
	public boolean addEdge(Kante kante, Collection<? extends Knoten> knoten) {
		if(knoten==null||knoten.size()==0) {
			return false;
		}
		Knoten[] knotenArray = knoten.toArray(new Knoten[knoten.size()]);
		if (knotenArray.length==1) {
			return addEdge(kante,knotenArray[0],knotenArray[0]);
		}
		else {
			return addEdge(kante,knotenArray[0],knotenArray[1]);
		}
	}

	/**
	 * Setzt den Typ einer Kante fest und f�gt sie dann der Kantenliste hinzu.
	 * 
	 */
	@Override
	public boolean addEdge(Kante kante, Collection<? extends Knoten> knoten, EdgeType typ) {
		if (typ==null||kante==null) {
			return false;
		}
		kante.setTyp(typ);
		return addEdge(kante,knoten);
	}

	/**
	 * Falls ein Knoten noch nicht Teil der Knotenliste ist wird er hinzu gef�gt.
	 */
	@Override
	public boolean addVertex(Knoten knoten) {
		if (knoten==null||containsVertex(knoten)) {
			return false;
		}
		knotenList.add(knoten);
		return true;
	}
	
	/**
	 * Pr�ft ob eine Kante in der Kantenliste vorhanden ist. Jede Kante darf es nur einmal geben, wobei mehrere Kanten (zB mit unterschiedlichen Namen und Gewichtungen) von einem Start- zum gleichen Zielknoten gehen k�nnen.
	 */
	@Override
	public boolean containsEdge(Kante kante) {
		if (kante==null) {
			return false;
		}
		for (int i=0; i<kantenList.size();i++) {
			if (kante.equals(kantenList.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Pr�ft ob ein Knoten in der Knotenliste vorhanden ist. Jeder Knoten muss einzigartig sein.
	 */
	@Override
	public boolean containsVertex(Knoten knoten) {
		if (knoten==null) {
			return false;
		}
		for (int i=0; i<knotenList.size();i++) {
			if (knoten.equals(knotenList.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	//TODO Methode im Auge behalten. Nicht sicher wie sie zu implementiert ist wie sie sein soll. Eventuell sollten auch ein- und ausgangsdegree Methoden eingebaut werden
	@Override
	public int degree(Knoten knoten) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Gibt die Anzahl der ausgehenden Kanten an.
	 */
	@Override
	public int outDegree(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Gibt die Anzahl der eingehenden Kanten an.
	 */
	@Override
	public int inDegree(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return kante Gibt die Kante zur�ck wenn sie enthalten ist, ansonsten gibt die Funktion null zur�ck.
	 */
	@Override
	public Kante findEdge(Knoten startKnoten, Knoten zielKnoten) {
		if (startKnoten==null||zielKnoten==null) {
			return null;
		}
		for (int i=0; i<kantenList.size();i++) {
			Kante kante = kantenList.get(i);
			if (kante.getStartKnoten().equals(startKnoten)&&kante.getZielKnoten().equals(zielKnoten)) {
				return kante;
			}
			
		}
		return null;
	}

	/**
	 * Gibt ein Set von Kanten zur�ck, die den gleichen Start- und Zielknoten verbinden.
	 */
	//TODO Lieber mit Set arbeiten!
	@Override
	public Collection<Kante> findEdgeSet(Knoten startKnoten, Knoten zielKnoten) {
		LinkedList<Kante> kanten = new LinkedList<Kante>();
		if (startKnoten==null||zielKnoten==null) {
			return kanten;
		}
		for (int i=0; i<kantenList.size();i++) {
			Kante kante = kantenList.get(i);
			if (kante.getStartKnoten().equals(startKnoten)&&kante.getZielKnoten().equals(zielKnoten)) {
				kanten.add(kante);
			}
			
		}
		return kanten;
	}
	
	/**
	 * @return EdgeType.UNDIRECTED
	 */
	@Override
	public EdgeType getDefaultEdgeType() {
		return EdgeType.UNDIRECTED;
	}

	/**
	 * Gibt die Anzahl der Kanten in der Kantenliste zur�ck.
	 */
	@Override
	public int getEdgeCount() {
		return kantenList.size();
	}

	/**
	 * Gibt die Anzahl der Kanten eines bestimmten Types zur�ck.
	 */
	//TODO Alternativ nur gerichtete oder ungerichtete Kanten erlauben in einem Graphen, dann wird die unn�tig
	//TODO Die Methode ist im Interface enthalten wie generell alle Methoden derren Namen in Englisch sind (zumindest in dieser Klasse)
	@Override
	public int getEdgeCount(EdgeType typ) {
		// TODO Effizientere M�glichkeit einbauen, wenn m�glich
		int anzahl=0;
		if (typ!=null) {
			for (int i=0;i<kantenList.size();i++) {
				if (kantenList.get(i).getTyp().equals(typ)) {
					anzahl++;
				}
			}
		}
		return anzahl;
	}
	
	/**
	 * @return EdgeType der Kante
	 */
	@Override
	public EdgeType getEdgeType(Kante kante) {
		if (kante==null) {
			return null;
		}
		return kante.getTyp();
	}

	/**
	 * @return Gibt das Set der Kanten zur�ck.
	 */
	//TODO zu Set �ndern
	@Override
	public Collection<Kante> getEdges() {
		return kantenList;
	}
	
	/**
	 * Gibt das Set der Kanten eine bestimmten Typs zur�ck.
	 */
	//TODO zu Set �ndern
	@Override
	public Collection<Kante> getEdges(EdgeType typ) {
		ArrayList<Kante> kanten = new ArrayList<Kante>();
		if (typ==null) {
			return kanten;
		}
		for (int i=0;i<kantenList.size();i++) {
			Kante kante=kantenList.get(i);
			if (kante.getTyp().equals(typ)) {
				kanten.add(kante);
			}
		}
		return kanten;
	}
	
	/**
	 * Incident bedeutet eine Kante ist mit einem Knoten verbunden.
	 * 
	 * @return True oder False
	 */
	@Override
	public boolean isIncident(Knoten knoten, Kante kante) {
		if (knoten==null||kante==null) {
			return false;
		}
		if (kante.getStartKnoten().equals(knoten)||kante.getZielKnoten().equals(knoten)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return Gibt 1 zur�ck bei einer Schleife und 2 bei einer Kante, die zwei verschiedene Knoten verbindet.
	 */
	@Override
	public int getIncidentCount(Kante kante) {
		return getIncidentVertices(kante).size();
	}

	/**
	 * Gibt ein Set aus Kanten zur�ck, die mit diesem Knoten incident sind.
	 */
	//TODO zu Set �ndern
	@Override
	public Collection<Kante> getIncidentEdges(Knoten knoten) {
		ArrayList<Kante> incidentKanten = new ArrayList<Kante>();
		for (int i=0; i<kantenList.size();i++) {
			if(isIncident(knoten,kantenList.get(i))) {
				incidentKanten.add(kantenList.get(i));
			}
		}
		return incidentKanten;
	}
	
	/**
	 * Gibt den/die Knoten einer Kante als Set zur�ck.
	 */
	//TODO zu Set �ndern
	@Override
	public Collection<Knoten> getIncidentVertices(Kante kante) {
		LinkedList<Knoten> list = new LinkedList<Knoten>();
		list.add(kante.getStartKnoten());
		if (!kante.getStartKnoten().equals(kante.getZielKnoten())) {
			list.add(kante.getZielKnoten());
		}
		return list;
	}
	
	/**
	 * Gibt die Anzahl der Knoten zur�ck, die mit einer Kante mit diesem Knoten verbunden sind.
	 */
	//2 Knoten sind durch Kanten verbunden
	@Override
	public int getNeighborCount(Knoten knoten) {
		return getNeighbors(knoten).size();
	}
	
	/**
	 * Gibt ein Set aus Nachbarn (= �ber eine Kante verbundene Knoten) eines Knoten zur�ck.
	 */
	//TODO zu Set �ndern
	@Override
	public Collection<Knoten> getNeighbors(Knoten knoten) {
		ArrayList<Knoten> list = new ArrayList<Knoten>();
		for (int i=0;i<knotenList.size();i++) {
			if(isNeighbor(knoten,knotenList.get(i))) {
				list.add(knotenList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * Gibt die Anzahl der Kanten im Graph an.
	 */
	@Override
	public int getVertexCount() {
		return kantenList.size();
	}

	/**
	 * Gibt das Set mit allen Kanten des Graphen zur�ck.
	 */
	//TODO zu Set �ndern
	@Override
	public Collection<Knoten> getVertices() {
		return knotenList;
	}

	/**
	 * @return True wenn eine Kante aus dem Kantenset diese Knoten verbindet, sonst False.
	 */
	@Override
	public boolean isNeighbor(Knoten knoten1, Knoten knoten2) {
		for (int i=0;i<kantenList.size();i++){
			Knoten startKnoten = kantenList.get(i).getStartKnoten();
			Knoten endKnoten = kantenList.get(i).getZielKnoten();
			if((knoten1.equals(startKnoten)&&knoten2.equals(endKnoten))||(knoten2.equals(startKnoten)&&knoten1.equals(endKnoten))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Entfernt eine Kante aus dem Kantenset des Graphen.
	 */
	@Override
	public boolean removeEdge(Kante kante) {
		if (kante==null) {
			return false;
		}
		for (int i=0; i<kantenList.size(); i++) {
			if (kante.equals(kantenList.get(i))){
				kantenList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Entfernt einen Knoten aus dem Kantenset des Graphen.
	 */
	@Override
	public boolean removeVertex(Knoten knoten) {
		if (knoten==null||!containsVertex(knoten)) {
			return false;
		}
		Collection<Kante> kanten = getIncidentEdges(knoten);
		Iterator<Kante> iterator = kanten.iterator();
		while (iterator.hasNext()) {
			Kante kante = iterator.next();
			if (!removeEdge(kante)) {
				return false;
			}
		}
		for (int i=0; i<knotenList.size();i++) {
			if(knotenList.get(i).equals(knoten)) {
				knotenList.remove(i);
			}
		}
		return true;
	}

	/**
	 * Gibt den Zielknoten einer gerichteten Kante zur�ck oder den 2. Knoten einer ungerichteten Kante.
	 */
	@Override
	public Knoten getDest(Kante kante) {
		return kante.getZielKnoten();
	}

	/**
	 * Gibt den Start- und Zielknoten einer Kante als Pair<Knoten> zur�ck
	 */
	@Override
	public Pair<Knoten> getEndpoints(Kante kante) {
		if (kante==null) {
			return null;
		}
		return new Pair<Knoten> (kante.getStartKnoten(),kante.getZielKnoten());
		
	}
	
	/**
	 * Gibt ein Set aller eingehenden gerichteten Kanten und aller ungerichteten Kanten dieses Knotens aus.
	 */
	@Override
	public Collection<Kante> getInEdges(Knoten knoten) {
		if(knoten==null) {
			return null;
		}
		Iterator<Kante> iterator = getIncidentEdges(knoten).iterator();
		HashSet<Kante> set = new HashSet<Kante> ();
		while (iterator.hasNext()) {
			Kante kante = iterator.next();
			if ((kante.getTyp().equals(EdgeType.UNDIRECTED)&&kante.getStartKnoten().equals(knoten))||(knoten.equals(kante.getZielKnoten()))) {
				set.add(kante);
			}
		}
		return set;
	}
	
	/**
	 * Gibt den anderen Knoten mit dem die Kante verbunden ist zur�ck
	 */
	@Override
	public Knoten getOpposite(Knoten knoten, Kante kante) {
		if(knoten==null||kante==null) {
			return null;
		}
		if (knoten.equals(kante.getStartKnoten())) {
			return kante.getZielKnoten();
		}
		else if (knoten.equals(kante.getZielKnoten())) {
			return kante.getStartKnoten();
		}
		return null;
	}
	
	/**
	 * Gibt ein Set aller ausgehenden gerichteten Kanten und aller ungerichteten Kanten dieses Knotens aus.
	 */
	@Override
	public Collection<Kante> getOutEdges(Knoten knoten) {
		if(knoten==null) {
			return null;
		}
		Iterator<Kante> iterator = getIncidentEdges(knoten).iterator();
		HashSet<Kante> set = new HashSet ();
		while (iterator.hasNext()) {
			Kante kante = iterator.next();
			if ((kante.getTyp().equals(EdgeType.UNDIRECTED)&&kante.getZielKnoten().equals(knoten))||(knoten.equals(kante.getStartKnoten()))) {
				set.add(kante);
			}
		}
		return set;
	}


	@Override
	public int getPredecessorCount(Knoten knoten) {
		return getPredecessors(knoten).size();
	}


	@Override
	public Collection<Knoten> getPredecessors(Knoten knoten) {
		if (knoten==null) {
			return null;
		}
		Iterator<Knoten> iterator = knotenList.iterator();
		HashSet<Knoten> set = new HashSet<Knoten>();
		while (iterator.hasNext()) {
			Knoten predecessor = iterator.next();
			if (isPredecessor(predecessor,knoten)) {
				set.add(predecessor);
			}
		}
		return set;
	}

	/**
	 * Gibt den Startknoten einer gerichteten Kante aus oder den ersten Knoten einer ungerichteten Kante.
	 */
	@Override
	public Knoten getSource(Kante kante) {
		return kante.getStartKnoten();
	}
	
	/**
	 * Gibt die Zielknoten von allen incidenten gerichteten Kanten aus und die anderen Knoten aller incidenten ungerichteten Kanten
	 */
	@Override
	public int getSuccessorCount(Knoten knoten) {
		return getSuccessors(knoten).size();
	}

	/**
	 * Gibt die Zielknoten von allen incidenten gerichteten Kanten aus und die anderen Knoten aller incidenten ungerichteten Kanten
	 * Wenn der knoten null ist wird null zur�ckgegeben
	 */
	@Override
	public Collection<Knoten> getSuccessors(Knoten knoten) {
		if (knoten==null) {
			return null;
		}
		Iterator<Knoten> iterator = knotenList.iterator();
		HashSet<Knoten> set = new HashSet<Knoten>();
		while (iterator.hasNext()) {
			Knoten successor = iterator.next();
			if (isSuccessor(successor,knoten)) {
				set.add(successor);
			}
		}
		return set;
	}



	/**
	 * Testet ob ein Knoten Zielknoten dieser Kante ist.
	 */
	@Override
	public boolean isDest(Knoten knoten, Kante kante) {
		if((kante.getTyp().equals(EdgeType.UNDIRECTED)&&kante.getStartKnoten().equals(knoten))||kante.getZielKnoten().equals(kante)) {
			return true;
		}
		return false;
	}
	/**
	 * Kontrolliert ob vom Successor aus eine Kante zum Predecessor f�hrt
	 */
	@Override
	public boolean isPredecessor(Knoten vorgaenger, Knoten knoten) {
		Iterator<Kante> iterator =getOutEdges(vorgaenger).iterator();
		while (iterator.hasNext()) {
			Kante kante = iterator.next();
			if (getOpposite(vorgaenger, kante).equals(knoten)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests ob ein Knoten Startknoten der Kante ist.
	 */
	@Override
	public boolean isSource(Knoten knoten, Kante kante) {
		if((kante.getTyp().equals(EdgeType.UNDIRECTED)&&kante.getZielKnoten().equals(knoten))||kante.getStartKnoten().equals(kante)) {
			return true;
		}
		return false;
	}

	/**
	 * Kontrolliert ob vom Successor aus eine Kante zum Predecessor f�hrt
	 */
	@Override
	public boolean isSuccessor(Knoten nachfolger, Knoten knoten) {
		Iterator<Kante> iterator =getInEdges(nachfolger).iterator();
		while (iterator.hasNext()) {
			Kante kante = iterator.next();
			if (getOpposite(nachfolger, kante).equals(knoten)) {
				return true;
			}
		}
		return false;
	}



}
