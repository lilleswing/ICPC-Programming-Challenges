package Resources;

public class Edge implements Comparable {
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
			return this.weight - temp.weight;
		}
		return 0;
	}
}
