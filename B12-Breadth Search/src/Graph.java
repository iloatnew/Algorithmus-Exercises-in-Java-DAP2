import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Graph {
	private ArrayList<Node> nodeArray;
	
	public Graph() {
		nodeArray = new ArrayList<Node>();
	}
	
	public boolean contains(int id) {
		for(Node node : nodeArray) {
			if(id == node.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Node> getNodeArray(){
		return nodeArray;
	}
	
	public void addNode (int id) {
		if(!contains(id)) {
			nodeArray.add(new Node(id));
		}
	}
	
	public Node getNode(int id) {
		for(Node node : nodeArray) {
			if(id == node.getId()) {
				return node;
			}
		}
		return null;
	}
	
	public void addEdge(int src, int dst) {
		if(contains(src) && contains(dst)) {
			// in both direction
			getNode(src).addEdge(getNode(dst));
			getNode(dst).addEdge(getNode(src));
		}
		else {
			System.out.println("nicht Vorhanden");
		}
	}
	
	public static Graph fromFile(String filepath){
		Graph newGraph = new Graph();
		try {
			RandomAccessFile file = new RandomAccessFile(filepath,"r");
			String zeile = file.readLine();
			// read every line from the file
			while(zeile!=null) {
				StringTokenizer st = new StringTokenizer(zeile,",");
				// get two numbers as the id of the node
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());
				// add two nodes
				newGraph.addNode(src);
				newGraph.addNode(dst);
				// add the edge in both directions between two nodes
				newGraph.addEdge(src, dst);
				// go to next line
				zeile = file.readLine();
			}
			file.close();
			for(Node node : newGraph.nodeArray) {
				// get the node
				System.out.println("Node "+ node.getId()+" has Edge ");
				for(Edge edge : node.getEdge()) {
					// get the edge, which contains this node
					System.out.print(edge.getSrc().getId()+","+ edge.getDst().getId()+" ; ");
				}
				System.out.println();
			}
			return newGraph;
		}
		catch(IOException e) {
			System.out.println("readline failed");
			System.exit(0);
		}
		catch(NumberFormatException e){
			System.out.println("not a number");
			System.exit(0);
		}	
		return null;
	}
	
	public static void main(String args[]) {
		Graph graph = fromFile("/home/wentao/workspace/B12/src/BspGraphKlein.graph");
	}
}
