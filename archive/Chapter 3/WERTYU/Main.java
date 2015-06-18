import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		
		String mString = "wqewretrytuyiuoiposadsfdgfhgjhkjlkxzcxvcbvnbmn1`213243546576879809-0=-[p;l,m.,';/.][\\]".toUpperCase();
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		Scanner sc = new Scanner(System.in);

		for(int x = 0; x < mString.length(); x+=2) {
			map.put(mString.charAt(x), mString.charAt(x+1));
		}
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if(map.containsKey(c)) {
					System.out.print(map.get(c));
				} else {
					System.out.print(c);
				}
			}
			System.out.println();
		}
	}
}
