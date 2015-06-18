import java.io.FileInputStream;
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
			int people = sc.nextInt();
			ArrayList<Integer> weights = new ArrayList<Integer>();
			for (int peop = 0; peop < people; peop++) {
				weights.add(sc.nextInt());
			}
			System.out.println(solve(weights));
			if (round != rounds - 1) {
				System.out.println();
			}
		}
	}
	private static ArrayList<Integer> getSol(BitSet dp, int goal, int sum) {
		int dist = 0;
		ArrayList<Integer> sol = new ArrayList<Integer>(2);
		while (!dp.get(goal + dist) && !dp.get(goal-dist)) {
			dist++;
		}
		if(dp.get(goal+dist)) {
			sol.add(sum-(goal+dist));
			sol.add(goal+dist);
		} else {
			sol.add(goal-dist);
			sol.add(sum-(goal-dist));
		}
		return sol;
	}
	private static String solve(ArrayList<Integer> weights) {
		if(weights.size() == 0) {
			return "0 0";
		}
		int sum = 0;
		for (Integer i : weights) {
			sum += i;
		}
		// System.out.println("Sum calculated");
		int goal = sum / 2;
		int size = 0;
		if (weights.size() % 2 == 0) {
			size = weights.size() / 2 + 1;
		} else {
			size = weights.size() / 2 + 2;
		}
		BitSet[] dp = new BitSet[size];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new BitSet();
		}
		dp[0].set(0);
		//System.out.println(dp.length);
		for (Integer i : weights) {
			for (int j = dp.length - 1; j >= 1; j--) {
				for (int x = goal; x >= 0; x--) {
					if (dp[j - 1].get(x)) {
						dp[j].set(i + x);
						// System.out.println(j + "," + (i+x));
					}

				}
			}
		}
		ArrayList<Integer> sol = getSol(dp[dp.length-1],goal,sum);

		if (weights.size() % 2 == 1) {
			ArrayList<Integer> sol2 = getSol(dp[dp.length-2],goal,sum);
			if(sol2.get(0) > sol.get(0)) {
				sol = sol2;
			}
		}
		return (sol.get(0) + " " + sol.get(1));
	}
}
