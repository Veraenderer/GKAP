package aufgabe1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SaveLoad { 
	
	public static boolean save (OGraph graphPar, String destination) {
		ArrayList<Kante> kanten;
		Collection<Kante> kantenCollect = graphPar.getEdges();
		if (kantenCollect.getClass()==ArrayList.class) {
			kanten=(ArrayList<Kante>)kantenCollect;
		}
		else {
			return false;
		}
		
		ArrayList<Knoten> knoten;
		Collection<Knoten> knotenCollect = graphPar.getVertices();
		if (knotenCollect.getClass()==ArrayList.class) {
			knoten=(ArrayList<Knoten>)knotenCollect;
		}
		else {
			return false;
		}
		
		StringBuilder builder = new StringBuilder (10000);
		
		//schreibe alle Knoten ohne Kanten auf
		for (int i =0; i < knoten.size(); i++) {
			if(graphPar.getNeighborCount(knoten.get(i))==0){
				builder.append(""+knoten.get(i).getName()+";\n");
			}
		}
		
		//schreibe alle Kanten auf
		for (int i=0; i< kanten.size(); i++) {
			String kantenString = kanten.toString()+"\n";
			builder.append(kantenString);
		}
		//TODO Eventuell noch eine Abfrage ob der destination String mit .gka endet
		File file = new File(destination);
		try {
		FileWriter schreiberling = new FileWriter (file);
		schreiberling.write(builder.toString());
		schreiberling.flush();
		schreiberling.close();
		}
		catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public static OGraph load (File filePar) {
		return null;
	}
}
