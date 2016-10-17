package aufgabe1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import edu.uci.ics.jung.graph.util.EdgeType;

public class SaveLoad { 
	//Alle todo's hier sind f�r mich und dienen mir als Notizen was ich sp�ter noch ausprobieren sollte
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
	/**
	 * 
	 * @param filePar
	 * @return 
	 * @throws IOException
	 */
	//Alle Whitespaces werden entfernt und Knotennamen d�rfen keine - oder : oder ( ) enthalten sein. Sollte eins von beiden ein Problem sein muss ich nochmal dr�ber gehen
	public static OGraph load (File filePar) throws IOException {
		String dateiinhalt;
		try (BufferedReader reader = new BufferedReader (new FileReader (filePar))) {
			long lenght = filePar.length();
			//TODO
			StringBuilder builder = new StringBuilder ((int) lenght+100); //Die +100 k�nnen wahrscheinlich weg aber ich wollte zumindest eine potenzielle Fehlerquelle ausschalten bevor ich es testen kann
			while (true) {
				String line = reader.readLine();
				if (line==null) {
					break;
				}
				builder.append(line);
			}
			dateiinhalt=builder.toString();
		} catch (IOException e) {
			throw new IOException();
		}
		//Alle Anweisungen werden in eigene Strings geschrieben und das Semicolon wird entfernt
		StringTokenizer tokenizer = new StringTokenizer(dateiinhalt, ";");
		LinkedList<String> tokenList = new LinkedList<String> ();
		while(tokenizer.hasMoreTokens()){
			tokenList.add(tokenizer.nextToken());
		}
		//Alle Whitespaces werden entfernt
		int anzahlDerAnw = tokenList.size();
		tokenList.stream().forEach((String s)->tokenList.add(s.replaceAll("\\s+", "")));
		for(int i=0;i<anzahlDerAnw;i++) {
			tokenList.removeFirst();
		}
		//Knoten Map wird erstellt
		HashMap<String,Knoten> knotenMap = new HashMap<String,Knoten>();
		for(int i=0;i<tokenList.size();i++) {
			String knotenname;
			if (tokenList.get(i).indexOf('-')==-1) {
				knotenname = tokenList.get(i);
			}
			else {
				knotenname = tokenList.get(i).substring(0, tokenList.get(i).indexOf('-'));
			}
			if(!knotenMap.containsKey(knotenname)) {
				knotenMap.put(knotenname, new Knoten(tokenList.get(i)));
			}
		}
		//Graph wird erstellt und Knoten werden eingelesen
		OGraph graph = new OGraph();
		knotenMap.forEach((s,knoten)->graph.addVertex(knoten)); //kann wahrscheinlich entfernt werden TODO
		//Lese Anweisungen aus
		for (int i=0; i<tokenList.size();i++) {
			String anweisung = tokenList.get(i);
			if (anweisung.indexOf('-')!=-1) {
				String startknotenName = anweisung.substring(0, anweisung.indexOf('-'));
				EdgeType typ;
				int anfangDesZielKnotens;
				if (anweisung.indexOf("--")!=-1) {
					typ=EdgeType.UNDIRECTED;
					anfangDesZielKnotens=anweisung.indexOf("--");
				}
				else {
					typ=EdgeType.DIRECTED;
					anfangDesZielKnotens=anweisung.indexOf("->");
				}
				//kontrolliere ob ein Wert oder ein Edge Name vorhanden ist:
				
				boolean wertVorhanden=false;
				if (anweisung.indexOf(':')!=-1) {
					wertVorhanden=true;
				}
				boolean nameVorhanden=false;
				if (anweisung.indexOf('(')!=-1) {
					nameVorhanden=true;
				}
				//Name des Zielknotens
				String zielknotenName;
				
				if (nameVorhanden) {
					zielknotenName=anweisung.substring(anfangDesZielKnotens+2, anweisung.indexOf('('));
				}
				else if (wertVorhanden) {
					zielknotenName=anweisung.substring(anfangDesZielKnotens+2, anweisung.indexOf(':'));
				}
				else {
					zielknotenName=anweisung.substring(anfangDesZielKnotens+2, anweisung.length());
				}
				//hole restliche Werte
				String kantenName;
				if (nameVorhanden) {
					kantenName=anweisung.substring(anweisung.indexOf('(')+1, anweisung.indexOf(')'));
				}
				else {
					kantenName="";
				}
				int kantenWert = 0;
				if (wertVorhanden) {
					kantenWert=Integer.parseInt(anweisung.substring(anweisung.indexOf(':'), anweisung.length()));
				}
				//erstelle Kante
				graph.createEdge(knotenMap.get(startknotenName), knotenMap.get(zielknotenName), kantenName, typ, kantenWert);
			}
		}
		
		
		return graph;
	}
	

}
