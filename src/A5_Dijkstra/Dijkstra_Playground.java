package A5_Dijkstra;

public class Dijkstra_Playground {
	public static void main(String[] args) {

		// thorough testing is your responsibility
		//
		// you may wish to create methods like
		// -- print
		// -- sort
		// -- random fill
		// -- etc.
		// in order to convince yourself your code is producing
		// the correct behavior
		exTest();
	}

	public static void exTest() {
		DiGraph d = new DiGraph();

		d.addNode(1, "a");
		d.addNode(2, "b");
		d.addNode(3, "c");
		d.addNode(4, "d");
		d.addNode(5, "e");
		d.addNode(6, "f");
		d.addNode(7, "g");

		d.addEdge(0, "a", "b", 4, null);
		d.addEdge(1, "a", "c", 2, null);
		d.addEdge(2, "a", "g", 3, null);
		d.addEdge(3, "b", "d", 3, null);
		d.addEdge(4, "b", "g", 2, null);
		d.addEdge(5, "c", "b", 1, null);
		d.addEdge(6, "c", "g", 1, null);
		d.addEdge(7, "d", "e", 2, null);
		d.addEdge(8, "d", "f", 1, null);
		d.addEdge(9, "f", "e", 2, null);
		d.addEdge(10, "g", "d", 1, null);
		d.addEdge(11, "g", "f", 2, null);

		ShortestPathInfo[] s = d.shortestPath("a");

		for (int i = 0; i < s.length; i++) {
			System.out.println("ShortestPath[" + i + "]: " + s[i]);
		}

		// d.delEdge("f", "s");
		// d.addNode(1, "f");
		// d.addNode(3, "s");
		// d.addEdge(0, "f", "s", 0, null);
		// d.delEdge("f", "s");
		// d.delEdge("f", "s");
		// d.addEdge(0, "f", "s", 0, null);
		// d.delEdge("f", "s");
		//
		// System.out.println(d.numEdges());

		// d.addNode(1, "A");
		// d.addNode(2, "B");
		// d.addNode(3, "C");
		// d.addNode(4, "D");
		// d.addNode(5, "E");
		// d.addNode(6, "F");
		// d.addNode(7, "G");
		//
		// d.addEdge(1, "A", "B", 2, null);
		// d.addEdge(2, "A", "D", 1, null);
		// d.addEdge(3, "B", "D", 3, null);
		// d.addEdge(4, "B", "E", 1, null);
		// d.addEdge(5, "E", "G", 6, null);
		// d.addEdge(6, "D", "E", 3, null);
		// d.addEdge(7, "D", "G", 4, null);
		// d.addEdge(8, "D", "F", 8, null);
		// d.addEdge(9, "D", "C", 2, null);
		// d.addEdge(10, "G", "F", 1, null);
		// d.addEdge(11, "C", "A", 4, null);
		// d.addEdge(12, "C", "F", 5, null);

		// d.addEdge(2, "A", "B", 2, null);
		// d.addEdge(1, "A", "D", 1, null);
		// d.addEdge(3, "B", "D", 3, null);
		// d.addEdge(1, "B", "E", 1, null);
		// d.addEdge(6, "E", "G", 6, null);
		// d.addEdge(3, "D", "E", 3, null);
		// d.addEdge(4, "D", "G", 4, null);
		// d.addEdge(8, "D", "F", 8, null);
		// d.addEdge(2, "D", "C", 2, null);
		// d.addEdge(1, "G", "F", 1, null);
		// d.addEdge(4, "C", "A", 4, null);
		// d.addEdge(5, "C", "F", 5, null);

		// d.addEdge(2, "A", "B", 1, null);
		// d.addEdge(1, "A", "D", 2, null);
		// d.addEdge(3, "B", "D", 3, null);
		// d.addEdge(1, "B", "E", 4, null);
		// d.addEdge(6, "E", "G", 5, null);
		// d.addEdge(3, "D", "E", 6, null);
		// d.addEdge(4, "D", "G", 7, null);
		// d.addEdge(8, "D", "F", 8, null);
		// d.addEdge(2, "D", "C", 9, null);
		// d.addEdge(1, "G", "F", 10, null);
		// d.addEdge(4, "C", "A", 11, null);
		// d.addEdge(5, "C", "F", 12, null);

		// System.out.println("numEdges: " + d.numEdges());
		// System.out.println("numNodes: " + d.numNodes());
		//
		// ShortestPathInfo[] s = d.shortestPath("A");
		//
		// for (int i = 0; i < s.length; i++) {
		// System.out.println("ShortestPath[" + i + "]: " + s[i]);
		// }
		//
		// d.addNode(1, "f");
		// d.addNode(3, "s");
		// d.addNode(7, "t");
		// d.addNode(0, "fo");
		// d.addNode(4, "fi");
		// d.addNode(6, "si");
		// d.addEdge(0, "f", "s", 0, null);
		// d.addEdge(1, "f", "si", 0, null);
		// d.addEdge(2, "s", "t", 0, null);
		// d.addEdge(3, "fo", "fi", 0, null);
		// d.addEdge(4, "fi", "si", 0, null);
		// System.out.println("numEdges: " + d.numEdges());
		// System.out.println("numNodes: " + d.numNodes());
		//
		// ShortestPathInfo[] s = d.shortestPath("s");
		//
		// for (int i = 0; i < s.length; i++) {
		// System.out.println("ShortestPath[" + i + "]: " + s[i]);
		// }

		// d.addNode(1, "f");
		// d.addNode(3, "s");
		// d.addNode(7, "t");
		// d.addNode(0, "fo");
		// d.delNode("fi");
		// d.delNode("si");
		// d.addEdge(0, "f", "s", 1, "e1");
		// d.addEdge(2, "s", "t", 1, "e2");
		// d.delEdge("f", "s");
		//
		// System.out.println(d.numEdges());

	}
}
