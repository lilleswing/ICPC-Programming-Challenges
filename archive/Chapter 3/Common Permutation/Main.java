import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			ArrayList<Character> sol = new ArrayList<Character>();
			String a = sc.nextLine();
			if(!sc.hasNextLine()) {
				break;
			}
			String b = sc.nextLine();
			for(int i = 0; i < a.length(); i++) {
				if(b.contains(a.substring(i,i+1))) {
					
					b = b.replaceFirst(a.substring(i,i+1), "!");
					//System.out.println(b);
					sol.add(a.charAt(i));
				}
			}
			Collections.sort(sol);
			for(int i = 0; i < sol.size(); i++) {
				System.out.print(sol.get(i));
			}
			System.out.println();
		}
	}
}
