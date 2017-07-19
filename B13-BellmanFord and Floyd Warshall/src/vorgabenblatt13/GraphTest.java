import java.io.IOException;
import java.util.ArrayList;

public class GraphTest {

	/**
	 * Loest das SSSP-Proplem mit Hilfe des Algortihmus von Bellman-Ford.
	 * @param g der Graph
	 * @param source id des Startknotes
	 * @return Array mit Weglaengen; Element i gibt die Laenge eines kuerzesten
	 * Weges von dem Knoten mit der id source zu dem Knoten mit id i an
	 */
	public static double[] sssp(Graph g, int source) {
		throw new UnsupportedOperationException("Aufgabe 13.1 noch nicht bearbeitet!");		
	}


	/**
	 * Loest das APSP-Problem mit Hilfe des Algorithmus von Floyd-Warshall 
	 * @param g der Graph
	 * @return Matrix mit Weglaengen; Element (i,j) gibt die Laenge eines 
	 * kuerzesten Weges von dem Knoten mit der id i zu dem Knoten mit id j an 
	 */
	public static double[][] apsp(Graph g) {
		throw new UnsupportedOperationException("Aufgabe 13.2 noch nicht bearbeitet!");	
	}

	/**
 	 * Realisiert einen APSP-Algorithmus, indem fuer alle Knoten das 
 	 * SSSP-Problem mittels Bellman-Ford geloest wird.
 	 * @param g der Graph
	 * @return Matrix mit Weglaengen; Element (i,j) gibt die Laenge eines 
	 * kuerzesten Weges von dem Knoten mit der id i zu dem Knoten mit id j an 
	 */
	public static double[][] apspBellmanFord(Graph g) {
		// Knoten holen
		ArrayList<Node> nodes = g.getNodes();
		// Tabelle anlegen
		double[][] result = new double[nodes.get(nodes.size()-1).getID()+1][];
		for (Node n : nodes){
			// Bellman-Ford fuer jeden Knoten
			result[n.getID()] = sssp(g, n.getID());
		}
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		if (args.length < 1) {
			System.err.println("Syntax: java GraphTest <filename> [<nodenumber>]");
			System.exit(-1);
		}
		
		Graph g = Graph.fromFile(args[0]);
		if (g==null) {
			System.err.println("Konnte Datei "+args[0]+ " nicht oeffnen oder enthaelt keinen Graphen");
			System.exit(1);
		}
		if (g.getNodes().isEmpty()) {
			System.err.println("Leerer Graph.");
			System.exit(2);
		}
		
		if (args.length == 2) {
			// Fuehre Bellman-Ford-Algorithmus aus
			int nodenumber=-1;
			try {
				nodenumber=Integer.parseInt(args[1]);
			} catch (NumberFormatException ex) {
			}
			if (g.getNode(nodenumber) == null) {
				System.err.println("Ungueltiger Startknoten angegeben: "+args[1]);
				System.exit(1);
			}
			double[] minCost = sssp(g, nodenumber);
			ArrayList<Node> nodes = g.getNodes();
			Node s = g.getNode(nodenumber), e = s;
			double maxDist = 0d;
			for (Node n : nodes){
				if (nodes.size()<= 20){
					System.out.println("Abstand von Knoten " + n.getID() + " zu Knoten " + s.getID() + ": " + minCost[n.getID()]);
				}
				if (minCost[n.getID()] != Double.POSITIVE_INFINITY && minCost[n.getID()] > maxDist){
					maxDist = minCost[n.getID()];
					e = n;
				}
			}
			System.out.println("Der maximale Abstand ist " + maxDist + " fuer Knoten " + e.getID());
		} else {
			// Fuehre Floyd-Warshall-Algorithmus aus
			double[][] minCost=apsp(g);
			ArrayList<Node> nodes = g.getNodes();
			Node s = nodes.get(0), e = s;
			double maxDist = 0d;
			for (Node u : nodes){
				for (Node v : nodes){
					if (nodes.size()<= 10){
						System.out.print((minCost[u.getID()][v.getID()] == Double.POSITIVE_INFINITY? "\u221E": "" +minCost[u.getID()][v.getID()]) + "\t");
					}
					if (minCost[u.getID()][v.getID()] != Double.POSITIVE_INFINITY && minCost[u.getID()][v.getID()] > maxDist){
						maxDist = minCost[u.getID()][v.getID()];
						s = u;
						e = v;
					}
				}
				if (nodes.size()<= 10) System.out.println();
			}
			System.out.println("Der maximale Abstand ist " + maxDist + " fuer das Knotenpaar (" + s.getID() + ", " + e.getID() + ")");
		}
	}
}
