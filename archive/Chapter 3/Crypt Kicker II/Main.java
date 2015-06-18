import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static String check = "the quick brown fox jumps over the lazy dog";
	static String[] checkVars = check.split(" ");

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int trials = sc.nextInt();
		sc.nextLine();
		sc.nextLine();
		for (int tri = 0; tri < trials; tri++) {
			if(tri != 0) {
				System.out.println();
			}
			ArrayList<String> message = new ArrayList<String>();
			String s;
			while (sc.hasNext() && !(s = sc.nextLine()).equals("")) {
				//System.out.println(s);
				message.add(s);
			}
			HashMap<Character, Character> map = findLine(message);
			if (map.isEmpty()) {
				System.out.println("No solution.");
			} else {
				for (String str : message) {
					for (int i = 0; i < str.length(); i++) {
						if (map.containsKey(str.charAt(i))) {
							System.out.print(map.get(str.charAt(i)));
						} else {
							System.out.print(str.charAt(i));
						}
					}
					System.out.println();
				}
			}
		}
	}

	private static HashMap<Character, Character> findLine(
			ArrayList<String> message) {

		HashMap<Character, Character> cipher = new HashMap<Character, Character>();
		for (String s : message) {
			if (s.length() == check.length()) {
				String[] vars = s.split(" ");
				boolean sol = true;
				for (int i = 0; i < vars.length; i++) {
					if (vars[i].length() != checkVars[i].length()) {
						sol = false;
					}
				}
				if (sol) {
					cipher.clear();
					for (int i = 0; i < s.length(); i++) {
						if (!cipher.containsKey(s.charAt(i))) {
							cipher.put(s.charAt(i), check.charAt(i));
						} else {
							if (!cipher.get(s.charAt(i))
									.equals(check.charAt(i))) {
								sol = false;
							}
						}
					}
				}
				if (sol) {
					return cipher;
				} else {
					cipher.clear();
				}
			}
		}
		return cipher;
	}
}
