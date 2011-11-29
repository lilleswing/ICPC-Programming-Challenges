import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int trials = sc.nextInt();
		sc.nextLine();
		sc.nextLine();
		for (int tri = 0; tri < trials; tri++) {
			if (tri != 0) {
				System.out.println();
			}
			String str;
			LinkedList<String> files = new LinkedList<String>();
			while (sc.hasNext() && !(str = sc.nextLine()).equals("")) {
				files.add(str);
			}
			Collections.sort(files, new StringComp());
			int size = files.peekFirst().length() + files.peekLast().length();
			int[] solutions = new int[files.size()];
			//System.out.println(files);
			solve(0,solutions,files, size, new boolean[files.size()]);
			System.out.println(files.get(solutions[0]) + files.get(solutions[1]));
			
			for(int i = 0; i < solutions.length; i++) {
				//System.out.print(solutions[i] + ",");
			}
			//System.out.println();
			
		}
	}
	public static boolean solve(int spot, int[] index, LinkedList<String> files, int size, boolean[] used) {
		if(spot == index.length) {
			return true;
		}
		if(spot >= 2 && spot % 2 == 0) {
			String currString = files.get(index[0]) + files.get(index[1]);
			if(currString.length() != size) {
				return false;
			}
			for(int i = 3; i < spot; i+=2) {
				String temp = files.get(index[i-1]) + files.get(index[i]);
				if(!currString.equals(temp)) {
					return false;
				}
			}
		}
		for(int i = 0; i < files.size(); i++) {
			if(!used[i]) {
				used[i] = true;
				index[spot] = i;
				boolean works = solve(spot+1, index, files, size, used);
				if(works) {
					return true;
				}
				used[i] = false;
				index[spot] = 0;
			}
		}
		return false;
	}

}
class StringComp implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}

}