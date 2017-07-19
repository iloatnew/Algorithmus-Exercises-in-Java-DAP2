import java.util.ArrayList;
import java.util.LinkedList;

public class BreitenSuche {
	
	public static void bfs(Graph graph,int id ) {
		LinkedList<Node> QList = new LinkedList<Node>();
		if(graph.contains(id)) {
			// Initializierung 0=white 1=grey 2=black
			ArrayList<Integer> color = new ArrayList<Integer>(); 
			ArrayList<Integer> distance = new ArrayList<Integer>();
			for(Node node : graph.getNodeArray()) {
				while(color.size()<=node.getId()) {
					color.add(0);
					distance.add(Integer.MAX_VALUE);
				}
			}
			QList.add(graph.getNode(id));
			color.set(id,1);
			distance.set(id, 0);
			//
			while(QList.size()>0) {
				Node u = QList.getFirst();
				// for each v aus Adj[u] do
				for(Edge edge : u.getEdge()) {
					Node v = edge.getDst();
					// if color[v] = white then
					if(color.get(v.getId())==0) {
						color.set(v.getId(), 1);
						// d[v] = d[u] + 1
						distance.set(v.getId(), distance.get(u.getId())+1);
						QList.add(v);
					}
				}
				QList.removeFirst();
			}
			System.out.println("------------------------------------------------------");
			System.out.println("BreitenSuche für Knoten "+ id);
			for(int i=0;i<color.size();i++) {
				if(color.get(i)==1) {
					System.out.println("Knoten "+i+" has distance "+distance.get(i));
				}
			}
		}
		else {
			System.out.println("node do not exist");
		}
	}
	
	public static void main(String[] args) {
		//  /home/wentao/workspace/B12/src/BspGraphKlein.graph
		try {
			Graph graph = new Graph();
			String direct = args[0];
			int node = Integer.parseInt(args[1]);
			graph = Graph.fromFile(direct);
			bfs(graph, node);
		}
		catch(NumberFormatException e){
			System.out.println("not a number");
			System.exit(0);
		}	
	}
}
