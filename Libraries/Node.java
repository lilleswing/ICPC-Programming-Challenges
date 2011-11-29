package Resources;
import java.util.ArrayList;


public class Node<Key> implements Comparable {
	public Key value;
	public ArrayList<Edge> edges;
	public int distance;
	boolean visited;
	String path;

	public Node(Key k) {
		value = k;
		edges = new ArrayList<Edge>();
		distance = 0;
		visited = false;
		path = "";
	}

	public boolean addNeighbor(Node<Key> n, int weight) {
		return edges.add(new Edge(this, n, weight));
	}

	public boolean addAllNeighbors(ArrayList<Node<Key>> n) {
		// return Neighbors.addAll(n);
		for (Node<Key> k : n) {
			this.edges.add(new Edge(this, k));
		}
		return false;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public boolean equals(Node<Key> n) {
		return this.value.equals(n.value);
	}

	public int compareTo(Object o) {
		if (this.visited) {
			return -1;
		}
		if (o instanceof Node) {
			Node<Key> n = (Node<Key>) (o);
			if (n.visited) {
				return 1;
			}
			return this.distance - n.distance;
		}
		return 0;
	}

	public String toString() {
		return value.toString();
	}
}
