package aufgabe1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

public class OGraph implements Graph<Knoten,Kante> {
	private List<Knoten> knotenList;
	private List<Kante> kantenList;
	
	public OGraph () {
		knotenList=new ArrayList<Knoten>();
		kantenList=new ArrayList<Kante>();
	}
	
	private boolean knotenZuKante(Kante kante, Knoten startKnoten, Knoten zielKnoten) {
		if (kante==null||startKnoten==null||zielKnoten==null) {
			return false;
		}
		kante.setStartKnoten(startKnoten);
		kante.setZielKnoten(zielKnoten);
		return true;
	}
	
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
	

	@Override
	public boolean addEdge(Kante kante, Knoten startKnoten, Knoten zielKnoten) {
		if (knotenZuKante(kante, startKnoten, zielKnoten)||kontrollierKnoten(startKnoten,zielKnoten)) {
			return false;
		}
		kantenList.add(kante);
		return true;
	}

	@Override
	public boolean addEdge(Kante kante, Knoten startKnoten, Knoten zielKnoten, EdgeType typ) {
		if (typ==null||kante==null) {
			return false;
		}
		kante.setTyp(typ);
		return addEdge(kante,startKnoten,zielKnoten);
	}
	
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

	@Override
	public boolean addEdge(Kante kante, Collection<? extends Knoten> knoten, EdgeType typ) {
		if (typ==null||kante==null) {
			return false;
		}
		kante.setTyp(typ);
		return addEdge(kante,knoten);
	}

	@Override
	public boolean addVertex(Knoten knoten) {
		if (knoten==null||containsVertex(knoten)) {
			return false;
		}
		knotenList.add(knoten);
		return true;
	}

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

	@Override
	public int degree(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

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

	@Override
	public EdgeType getDefaultEdgeType() {
		return EdgeType.UNDIRECTED;
	}

	@Override
	public int getEdgeCount() {
		return kantenList.size();
	}

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

	@Override
	public EdgeType getEdgeType(Kante kante) {
		if (kante==null) {
			return null;
		}
		return kante.getTyp();
	}

	@Override
	public Collection<Kante> getEdges() {
		return kantenList;
	}

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

	//TODO
	//Knoten und Kante geh�ren zusammen
	@Override
	public boolean isIncident(Knoten knoten, Kante kante) {
		if (kante.getStartKnoten().equals(knoten)||kante.getZielKnoten().equals(knoten)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getIncidentCount(Kante kante) {
		return getIncidentVertices(kante).size();
	}

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
	//TODO
	@Override
	public Collection<Knoten> getIncidentVertices(Kante kante) {
		LinkedList<Knoten> list = new LinkedList<Knoten>();
		list.add(kante.getStartKnoten());
		if (!kante.getStartKnoten().equals(kante.getZielKnoten())) {
			list.add(kante.getZielKnoten());
		}
		return list;
	}

	//2 Knoten sind durch Kanten verbunden
	@Override
	public int getNeighborCount(Knoten knoten) {
		return getNeighbors(knoten).size();
	}
	//TODO
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
	
	@Override
	public int getVertexCount() {
		return kantenList.size();
	}

	@Override
	public Collection<Knoten> getVertices() {
		return knotenList;
	}

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
	//TODO
	@Override
	public boolean removeEdge(Kante arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	//TODO
	@Override
	public boolean removeVertex(Knoten arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Knoten getDest(Kante arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<Knoten> getEndpoints(Kante arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Kante> getInEdges(Knoten arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Knoten getOpposite(Knoten arg0, Kante arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	//TODO
	@Override
	public Collection<Kante> getOutEdges(Knoten arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPredecessorCount(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Knoten> getPredecessors(Knoten arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Knoten getSource(Kante arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSuccessorCount(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Knoten> getSuccessors(Knoten arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inDegree(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDest(Knoten arg0, Kante arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPredecessor(Knoten arg0, Knoten arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSource(Knoten arg0, Kante arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuccessor(Knoten arg0, Knoten arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int outDegree(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
