package aufgabe1;

import java.util.Collection;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

public class OGraph implements Graph<Knoten,Kante> {

	@Override
	public boolean addEdge(Kante arg0, Collection<? extends Knoten> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(Kante arg0, Collection<? extends Knoten> arg1, EdgeType arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addVertex(Knoten arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsEdge(Kante arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsVertex(Knoten arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int degree(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Kante findEdge(Knoten arg0, Knoten arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Kante> findEdgeSet(Knoten arg0, Knoten arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EdgeType getDefaultEdgeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEdgeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEdgeCount(EdgeType arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EdgeType getEdgeType(Kante arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Kante> getEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Kante> getEdges(EdgeType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIncidentCount(Kante arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Kante> getIncidentEdges(Knoten arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Knoten> getIncidentVertices(Kante arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNeighborCount(Knoten arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Knoten> getNeighbors(Knoten arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVertexCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Knoten> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIncident(Knoten arg0, Kante arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNeighbor(Knoten arg0, Knoten arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEdge(Kante arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeVertex(Knoten arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(Kante arg0, Knoten arg1, Knoten arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(Kante arg0, Knoten arg1, Knoten arg2, EdgeType arg3) {
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