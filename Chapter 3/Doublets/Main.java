import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		String s;
		HashSet<String> dict = new HashSet<String>();
		while (!(s = sc.nextLine()).equals("")) {
			dict.add(s);
		}
		Graph<String> g = new Graph<String>();
		for (String str : dict) {
			g.addNode(new Node<String>(str));
		}
		fillEdges(g, dict);
		boolean first = true;
		while (sc.hasNext()) {
			if(!first) {
				System.out.println();
			}
			String vars[] = sc.nextLine().split(" ");
			g.dj(vars[0]);
			String[] path = g.getNode(vars[1]).path.split(":");
			if (path.length == 1) {
				System.out.println("No solution.");
			} else {
				for (int i = 1; i < path.length; i++) {
					System.out.println(path[i]);
				}
				System.out.println(vars[1]);
			}
			// System.out.println(g.getNode(vars[1]).path);
			if(first) {
				first = false;
			}

		}
	}

	private static void fillEdges(Graph<String> g, HashSet<String> dict) {
		for (String s : dict) {
			for (int i = 0; i < s.length(); i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					String temp = s.substring(0, i) + c
							+ s.substring(i + 1, s.length());
					if (dict.contains(temp) && !temp.equals(s)) {
						// System.out.println(s+":" + temp);
						g.addEdge(g.getNode(s), g.getNode(temp), 1);
					}
				}
			}
		}

	}

}

class Graph<Key> {
	ArrayList<Node<Key>> nodes;
	HashSet<Edge> edges;
	HashMap<Key, Node<Key>> getNodeHash;
	int cost;

	public Graph() {
		nodes = new ArrayList<Node<Key>>();
		edges = new HashSet<Edge>();
		getNodeHash = new HashMap<Key, Node<Key>>();
		cost = 0;
	}

	public boolean addNodeNoEdges(Node<Key> n) {
		Node temp = new Node(n.value);
		nodes.add(temp);
		getNodeHash.put(n.value, temp);
		return true;
	}

	public boolean addNode(Node<Key> n) {
		getNodeHash.put(n.value, n);
		nodes.add(n);
		for (Edge k : n.edges) {
			edges.add(k);
		}
		return true;
	}

	public Node<Key> getNode(Key k) {
		return getNodeHash.get(k);
	}

	public boolean addEdge(Node<Key> a, Node<Key> b, int w) {
		Edge e = new Edge(a, b, w);
		edges.add(e);
		a.edges.add(e);
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean dj(Key k) {
		Node<Key> start = getNode(k);
		for (Node<Key> n : nodes) {
			n.distance = Integer.MAX_VALUE;
			n.visited = false;
			n.path = "";
		}
		start.distance = 0;
		ArrayList<Node<Key>> touched = new ArrayList<Node<Key>>();
		do {
			for (Edge e : start.edges) {
				if (!e.to.visited) {
					if (!touched.contains(e.to) && !e.to.visited) {
						touched.add(e.to);
					}
					int dist = start.distance + e.weight;
					if (dist < e.to.distance) {
						e.to.distance = dist;
						e.to.path = e.from.path + ":" + e.from.value;
					}
				}
			}
			start.visited = true;
			touched.remove(start);
			// System.out.println(touched);
		} while (!touched.isEmpty()
				&& !(start = (Node<Key>) Collections.min(touched)).visited);
		return true;
	}

	public Graph prim(Key k) {
		Graph<Key> min = new Graph<Key>();
		Node<Key> start = getNode(k);
		Edge minEdge;
		min.addNodeNoEdges(start);
		// Heap<Edge> possEdges = new Heap<Edge>();
		PriorityQueue<Edge> possEdges = new PriorityQueue<Edge>();
		// LinkedList<Edge> possEdges = new LinkedList<Edge>();
		for (Node<Key> n : this.nodes) {
			n.visited = false;
		}
		start.distance = 0;

		do {
			start.visited = true;
			for (Edge e : start.edges) {
				if (!e.to.visited) {
					possEdges.add(e);
				}
			}
			for (Iterator<Edge> i = possEdges.iterator(); i.hasNext();) {
				Edge e = i.next();
				if (e.to.visited) {
					i.remove();
				}
			}
			if (possEdges.isEmpty()) {
				return min;
			}
			minEdge = possEdges.poll();
			min.addNodeNoEdges(minEdge.to);
			min.addEdge(minEdge.from, minEdge.to, minEdge.weight);
			min.cost += minEdge.weight;
			minEdge.to.distance = minEdge.from.distance + minEdge.weight;
			start = minEdge.to;

		} while (!possEdges.isEmpty());

		return min;
	}

	public int totalCost() {
		int sum = 0;
		for (Edge e : this.edges) {
			sum += e.weight;
		}
		return sum;
	}
}

class Edge implements Comparable {
	public Node from, to;
	public int weight;

	public Edge(Node a, Node b) {
		from = a;
		to = b;
		weight = 1;
	}

	public Edge(Node a, Node b, int w) {
		from = a;
		to = b;
		weight = w;
	}

	public Node getFrom() {
		return from;
	}

	public void setFrom(Node from) {
		this.from = from;
	}

	public Node getTo() {
		return to;
	}

	public void setTo(Node to) {
		this.to = to;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Edge e) {
		if (from.equals(e.from) && to.equals(e.to)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "From:" + from + " To:" + to;
	}

	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof Edge) {
			Edge temp = (Edge) (arg0);
			// return (this.from.distance+this.weight) -
			// (temp.from.distance+temp.weight);
			return this.weight - temp.weight;
		}
		return 0;
	}
}

class Node<Key> implements Comparable {
	public Key value;
	public ArrayList<Edge> edges;
	public int distance;
	boolean visited;
	public String path;

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
