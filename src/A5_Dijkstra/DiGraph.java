package A5_Dijkstra;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DiGraph implements DiGraph_Interface {

	ConcurrentHashMap<String, Node> nodes = new ConcurrentHashMap<String, Node>();
	// Concurrent HashMap that can change as it is being iterated over
	Set<Long> nIDs = new HashSet<Long>(); // to determine if the ID num for the
											// Nodes has been used
	Set<Long> eIDs = new HashSet<Long>(); // to determine if the ID num for the
											// Edges has been used
	int _nodes; // nodes counter
	int _edges; // edges counter

	// in here go all your data and methods for the graph

	public DiGraph() { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
		_nodes = 0;
		_edges = 0;
	}

	public int getNodes() {
		return _nodes;
	}

	public int getEdges() {
		return _edges;
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if (nodes.put(label, new Node(idNum, label)) != null) {
			// the put method returns null if the mapping already exists - then
			// return false
			return false;
		} else if (!nIDs.add(new Long(idNum))) {
			// checks to see if the ID num has already been used, would be false
			// then must remove the mapping set above
			nodes.remove(label);
			return false;
		} else {
			// else it increments nodes because the methods in the if statements
			// have added the node and the id
			_nodes++;
			return true;
		}
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (nodes.get(sLabel) == null || nodes.get(dLabel) == null) {
			// if either the source or the destination nodes are not in the
			// mapping - return false
			return false;
		} else if (nodes.get(sLabel).destinations.containsKey(nodes.get(dLabel))) {
			// the source must contain a mapping of an edge to the destination
			// node, or else return false
			return false;
		} else if (!eIDs.add(idNum)) {
			// if the addition of the add edge id is false - return false
			// because the id has been used
			return false;
		} else {
			// increment edges and put the <Node, Edge> association in the
			// destination map of the source node
			nodes.get(sLabel).destinations.put(nodes.get(dLabel), new Edge(weight, sLabel, dLabel, idNum, eLabel));
			_edges++;
			return true;
		}
	}

	@Override
	public boolean delNode(String label) {
		// if the key is not in the mapping, return false
		if (!nodes.containsKey(label)) {
			return false;
		} else if (!nIDs.remove(nodes.get(label).id)) {
			// if the removal of the id returns false, return false on the
			// delete
			return false;
		} else if (nodes.remove(label) == null) {
			// take the mapping out of the map - if it is null return false
			return false;
		} else {
			// decrement nodes if all the above happens correctly
			_nodes--;
			return true;
		}
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if (!nodes.containsKey(sLabel) || !nodes.containsKey(dLabel)) {
			// if the array doesnt contain either node - return false
			return false;
		} else if (!nodes.get(sLabel).destinations.containsKey(nodes.get(dLabel))) {
			// if the source does not contain the mapping to the destination -
			// return false
			return false;
		} else if (!eIDs.remove(nodes.get(sLabel).destinations.get(nodes.get(dLabel)).id)) {
			// if the removal of the id number of the edge between the source
			// and the destination node is false - return false
			return false;
		} else {
			// simply remove the destination node from the source destinations
			// mapping by using the label of the destination node and decrement
			// edge number
			nodes.get(sLabel).destinations.remove(nodes.get(dLabel));
			_edges--;
			return true;
		}
	}

	@Override
	public long numNodes() {
		return _nodes;
	}

	@Override
	public long numEdges() {
		return _edges;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		if (!nodes.containsKey(label)) {
			// if the node is not in the map - throw an exception
			throw new RuntimeException("label is not in the DiGraph");
		}

		if (label == null) {
			// if the parameter is null throw an exception
			throw new RuntimeException("label cannot be null");
		}
		// parameter is the vertex you begin at

		ShortestPathInfo[] output = new ShortestPathInfo[_nodes];
		// the length of this array is the number of nodes
		int i = 0;
		// index variable
		MinBinHeap heap = new MinBinHeap();

		Collection<Node> allNodes = nodes.values();
		// this creates an iterable set of all the nodes in the mapping

		for (Node n : allNodes) {
			// must go through and reset all of these first in case there are
			// shortestpath calls back to back
			n.handled = false;
			n.dist = (long) Double.NaN;
			n.path = null;
		}

		long length = 0;
		EntryPair tempOB; // long and String
		String tempLabel;
		long tempL;
		Node tempN; // temp node
		Node tempENode; // temp edge node
		Node sNode = nodes.get(label); // source node

		heap.insert(new EntryPair(length, label));
		// the entry pairs are added based on a long and a string - a weight and
		// a label

		sNode.dist = length;
		sNode.path = null;

		while (heap.size() > 0) {
			tempOB = heap.getMin();
			heap.delMin();
			// pop off the priority que and deal with it
			tempLabel = tempOB.label;
			tempN = nodes.get(tempLabel);
			length = tempOB.priority;
			// the length is the weight of the thing popped off now
			tempN.dist = length;
			sNode.path = tempN;

			if (tempN.handled) {
				// if it is handled already we wanna skip to the next pop off
				// the que
				continue;
			}

			output[i] = new ShortestPathInfo(tempLabel, tempN.dist);
			i++;
			// add this object to the output and increment the index variable

			tempN.handled = true;
			// change the handled to true

			Collection<Edge> edges = tempN.destinations.values();
			// creates an iterable set of all the edges of the temp node (or the
			// popped off node)

			for (Edge e : edges) {
				tempENode = nodes.get(e.dlabel);
				// only if we havent handled it yet
				if (!tempENode.handled) {
					// update the length with the weight of the edge
					tempL = length + e.weight;
					if (tempL < tempENode.dist || tempENode.dist == (long) Double.NaN) {
						// if the length is less than the distance or if it is
						// the first time going through (Double.NaN)
						tempENode.dist = tempL;
						tempENode.path = tempN;
						heap.insert(new EntryPair(tempL, e.dlabel));
						// add the edge node onto the heap and go back and cycle
						// through
					}
				}
			}

		}

		// check if nodes have been handled - if not - add them w a -1 dist
		// this is used for all the nodes that have no path from the source
		// label
		// the i will be at the correct spot because it was only incremented
		// when a shortestpathinfo object was added
		for (Node n : allNodes) {
			if (!nodes.get(n.label).handled) {
				output[i] = new ShortestPathInfo(n.label, -1);
				i++;
			}
		}

		return output;
	}

	// rest of your code to implement the various operations

	public static class Node {
		long id;
		String label;
		ConcurrentHashMap<Node, Edge> destinations = new ConcurrentHashMap<Node, Edge>();
		// maps the desintation node to the edge that links this node with the
		// edge node
		boolean handled; // for shortest path
		long dist; // used for shortest path
		Node path;

		public Node(long idNum, String label) {
			id = idNum;
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	public static class Edge {
		long id;
		long weight;
		String slabel;
		String dlabel;
		String label;

		public Edge(long weight, String sLabel, String dLabel, long idNum, String eLabel) {
			id = idNum;
			this.weight = weight;
			this.slabel = sLabel;
			this.dlabel = dLabel;
			label = eLabel;
		}

		public String getLabel() {
			return label;
		}

		public String getSourceLabel() {
			return slabel;
		}

		public String getDestinationLabel() {
			return dlabel;
		}

	}

}
