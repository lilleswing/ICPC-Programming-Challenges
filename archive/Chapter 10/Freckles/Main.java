import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int rounds = sc.nextInt();
		for(int round = 0; round < rounds; round++) {
			int nPoints = sc.nextInt();
			ArrayList<Point> points = new ArrayList<Point>();
			for(int i = 0; i < nPoints; i++) {
				points.add(new Point(sc.nextDouble(), sc.nextDouble()));
			}
			System.out.printf("%.2f\n", solve(points));
			if(round != rounds-1) {
				System.out.println();
			}
		}
	}

	private static double solve(ArrayList<Point> points) {
		Graph<Integer> g = new Graph<Integer>();
		for(int i = 0; i < points.size(); i++) {
			g.addNode(new Node<Integer>(i));
		}
		for(int i = 0; i < points.size(); i++) {
			for(int j = 0; j < points.size(); j++) {
				if(i != j) {
					g.addEdge(g.getNode(i), g.getNode(j), points.get(i).distance(points.get(j)));
				}
			}
		}
		Graph<Integer> sol = g.prim(0);
		double sum = 0;
		for(Edge e: sol.edges) {
			sum += e.weight;
		}
		return sum;
	}
}
class Point {
	double x,y;
	public Point(double a, double b) {
		x = a;
		y = b;
	}
	public double distance(Point b) {
		double dx = b.x-x;
		double dy = b.y-y;
		return Math.sqrt(dx*dx+dy*dy);
	}
}

class Graph<Key> {
	ArrayList<Node<Key>> nodes;
	HashSet<Edge> edges;
	HashMap<Key, Node<Key>> getNodeHash;

	public Graph() {
		nodes = new ArrayList<Node<Key>>();
		edges = new HashSet<Edge>();
		getNodeHash = new HashMap<Key, Node<Key>>();
	}

	public boolean addNodeNoEdges(Node<Key> n) {
		Node<Key> temp = new Node<Key>(n.value);
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

	public boolean addEdge(Node<Key> a, Node<Key> b, double w) {
		Edge e = new Edge(a, b, w);
		edges.add(e);
		a.edges.add(e);
		return true;
	}
	/*
	public int[][][] Floyd_Warshall() {
		int[][][] path = new int[2][nodes.size()][nodes.size()];
		for(int i = 0; i < nodes.size(); i++) {
			Arrays.fill(path[1][i], -1);
		}
		for(int i = 0; i < nodes.size(); i++) {
			for(int j = 0; j < nodes.size() ;j++) {
				if(i==j) {
					path[0][i][j] = 0;
				} else {
					path[0][i][j] = edgeCost(nodes.get(i), nodes.get(j));
				}
			}
		}
		for(int k = 0; k < path.length; k++) {
			for(int i = 0; i < path.length; i++) {
				for(int j = 0; j < path.length; j++) {
					if(path[0][i][k] + path[0][k][j] < path[0][i][j]) {
						path[0][i][j] = path[0][i][k]+path[0][k][j];
						path[1][i][j] = k;
					}
				}
			}
		}
		return path;
	}
	public String getPath(int[][][] path,int i,int j) {
		if(path[0][i][j] == Integer.MAX_VALUE) {
			return "no path";
		}
		int intermediate = path[1][i][j];
		if(intermediate==-1) {
			return ",";
		} else {
			return getPath(path, i,intermediate) + "," + nodes.get(i) + "," + getPath(path, intermediate,j); 
		}
	}
	private int edgeCost(Node<Key> node, Node<Key> node2) {
		ArrayList<Edge> ed = node.edges;
		for(Edge e: ed) {
			if(e.to.equals(node2)) {
				return e.weight;
			}
		}
		return Integer.MAX_VALUE;
	}
	
	@SuppressWarnings("unchecked")
	public boolean dj(Key k) {
		Node<Key> start = getNode(k);
		for (Node<Key> n : nodes) {
			n.distance = Integer.MAX_VALUE;
			n.visited = false;
		}
		start.distance = 0;
		ArrayList<Node<Key>> touched = new ArrayList<Node<Key>>();
		do {
			for (Edge e : start.edges) {
				if (!e.to.visited) {
					if (!touched.contains(e.to)&& !e.to.visited) {
						touched.add(e.to);
					}
					int dist = start.distance + e.weight;
					if (dist < e.to.distance) {
						e.to.distance = dist;
						e.to.path = e.from.path + ":" + e.from.toString();
					}
				}
			}
			start.visited = true;
			touched.remove(start);
			//System.out.println(touched);
		} while (!touched.isEmpty() && !(start = Collections.min(touched)).visited);
		return true;
	}
	*/

	public Graph<Key> prim(Key k) {
		Graph<Key> min = new Graph<Key>();
		Node<Key> start = getNode(k);
		Edge minEdge;
		min.addNodeNoEdges(start);
		PriorityQueue<Edge> possEdges = new PriorityQueue<Edge>();
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
	public double weight;

	public Edge(Node a, Node b) {
		from = a;
		to = b;
		weight = 1;
	}

	public Edge(Node a, Node b, double w) {
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
			return (int)(this.weight - temp.weight);
		}
		return 0;
	}
}
class Node<Key> implements Comparable {
	public Key value;
	public ArrayList<Edge> edges;
	public double distance;
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
			return (int)(this.distance - n.distance);
		}
		return 0;
	}

	public String toString() {
		return value.toString();
	}
}
