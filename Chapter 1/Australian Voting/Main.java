import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int cases = sc.nextInt();
		boolean first = true;
		for(int round = 1; round <= cases; round++) {
			int people = sc.nextInt();
			sc.nextLine();
			String[] candidates = new String[people+1];
			for(int i = 1; i <= people; i++) {
				candidates[i] = sc.nextLine();
			}
			String s;
			LinkedList<LinkedList<Integer>> ballots = new LinkedList<LinkedList<Integer>>();
			
			while(sc.hasNextLine() && !(s= sc.nextLine()).equals("")) {
				StringTokenizer st = new StringTokenizer(s);
				LinkedList<Integer> temp = new LinkedList<Integer>();
				while(st.hasMoreTokens()) {
					temp.add(Integer.parseInt(st.nextToken()));
				}
				ballots.add(temp);
			}
			
			if(!first) {
				System.out.println();
			}
			solve(ballots, people, candidates);
			if(first) {
				first = false;
			}
		}
	}

	private static int solve(LinkedList<LinkedList<Integer>> ballots, int people, String[] cand) {
		int[] currBallots = new int[people+1];
		
		HashSet<Integer> eliminated = new HashSet<Integer>();
		int[] winLoss = {0,0};
		int votes = ballots.size();
		int size = -1;
		//System.out.println("votes = " + votes);
		do {
			if(size >= eliminated.size()) {
				return 0;
			} else {
				size = eliminated.size();
			}
			//System.out.println(winLoss[0]);
			//eliminated.add(winLoss[0]);
			//currBallots.clear();
			Arrays.fill(currBallots,0);
			for(LinkedList<Integer> a : ballots) {
				for(Iterator<Integer> i=a.iterator(); i.hasNext();) {
					int temp = i.next();
					if(!eliminated.contains(temp)) {
						//System.out.println(i);
						currBallots[temp]++;
						break;
					} else {
						i.remove();
					}
				}
			}
			//System.out.println(currBallots);
		} while(!winner(currBallots,winLoss,votes, eliminated));
		if(winLoss[1] == -1) {
		for(int i = 1; i <= people; i++) {
			if(!eliminated.contains(i)) {
				System.out.println(cand[i]);
			}
		}
		} else {
			System.out.println(cand[winLoss[1]]);
		}
		return winLoss[1];
	}

	private static boolean winner(int[] currBallots, int[] winLoss, int votes, HashSet<Integer> h) {
		
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, maxSpot = 0;;
		ArrayList<Integer> minSpot = new ArrayList<Integer>();
		for(int i = 0; i < currBallots.length; i++) {
			if(!h.contains(i) && currBallots[i] < min) {
				min = currBallots[i];
				minSpot.clear();
				minSpot.add(i);
			}
			else if(currBallots[i] == min) {
				minSpot.add(i);
			}
			if(currBallots[i] > max) {
				max = currBallots[i];
				maxSpot = i;
			}
		}
		if(max * 2 > votes) {
			winLoss[1] = maxSpot;
			return true;
		}
		if(minSpot.contains(maxSpot)) {
			winLoss[1] = -1;
			
			return true;
		}
		//System.out.println(minSpot);
		h.addAll(minSpot);
		//System.out.println(winLoss[0]);
		return false;
	}

	
}
