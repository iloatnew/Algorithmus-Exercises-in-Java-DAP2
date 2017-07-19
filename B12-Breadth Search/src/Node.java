import java.util.ArrayList;

public class Node {
	private int id;
	private ArrayList<Edge> edgeArray;
	public Node(int id) {
		this.id = id;
		edgeArray = new ArrayList<Edge>();
	}
	
	public int getId() {
		return id;
	}
	
	public ArrayList<Edge> getEdge(){
		return edgeArray;
	}
	
	public void addEdge(Node dst) {
		Edge newEdge = new Edge(this, dst);
		edgeArray.add(newEdge);
	}
	
	public boolean equals(Node other) {
		if(other.getId() == this.getId()) {
			return true;
		}
		else {
			return false;
		}
	}
}
