import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int rounds = sc.nextInt();
		for (int round = 0; round < rounds; round++) {
			System.out.println("Case #" + (round + 1));
			int beads = sc.nextInt();
			ArrayList<Bead> b = new ArrayList<Bead>();
			for (int i = 0; i < beads; i++) {
				b.add(new Bead(sc.nextInt(), sc.nextInt()));
			}
			solve(b);
			if (round != rounds - 1) {
				System.out.println();
			}
		}
	}

	private static void display(int loc, int[] order, ArrayList<Bead> b) {
		System.out.println("Beads So Far Are");
		for (int i = 0; i < loc; i++) {
			System.out.println(b.get(order[i]));
		}
	}

	private static boolean dfs(int loc, BitSet used, int[] order,
			ArrayList<Bead> b) {
		//display(loc, order, b);
		// System.out.println(loc);
		if (loc == b.size()) {
			if (b.get(order[0]).col[0] == b.get(order[order.length - 1]).col[1]) {
				return true;
			}
			return false;
		}
		if (loc == 0) {
			for (int i = 0; i < b.size(); i++) {
				for (int j = 0; j < 2; j++) {
					if (!used.get(i) && check(b.get(order[loc - 1]), b.get(i))) {
						used.set(i);
						order[loc] = i;
						boolean solved = dfs(loc + 1, used, order, b);
						if (solved) {
							return true;
						}
						order[loc] = 0;
						used.clear(i);
					}
					b.get(i).flip();
				}
			}
		} else {
			for (int i = 0; i < b.size(); i++) {
				for (int j = 0; j < 2; j++) {
					if (!used.get(i) && check(b.get(order[loc - 1]), b.get(i))) {
						used.set(i);
						order[loc] = i;
						boolean solved = dfs(loc + 1, used, order, b);
						if (solved) {
							return true;
						}
						order[loc] = 0;
						used.clear(i);
					}
					b.get(i).flip();
				}
			}
		}
		return false;
	}

	private static boolean check(Bead bead0, Bead bead1) {
		return bead0.col[1] == bead1.col[0];
	}

	private static void solve(ArrayList<Bead> b) {
		BitSet used = new BitSet(b.size());
		//System.out.println(b);
		int[] order = new int[b.size()];
		if (dfs(0, used, order, b)) {
			for (int i = 0; i < order.length; i++) {
				System.out.println(b.get(order[i]));
			}
		} else {
			System.out.println("some beads may be lost");
		}

	}
}

class Bead {
	int col[];

	public Bead(int a, int b) {
		col = new int[2];
		col[0] = a;
		col[1] = b;
	}

	public void flip() {
		int temp = col[0];
		col[0] = col[1];
		col[1] = temp;
	}

	public String toString() {
		return col[0] + " " + col[1];
	}
}
