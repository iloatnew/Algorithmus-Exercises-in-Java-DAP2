
public class Edge {
	private Node src;
	private Node dst;
	
	public Edge(Node n1, Node n2){
		src = n1;
		dst = n2;
	}
	
	public Node getSrc() {
		return src;
	}
	
	public Node getDst() {
		return dst;
	}
}
