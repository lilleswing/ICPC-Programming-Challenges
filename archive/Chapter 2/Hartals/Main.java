import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.Scanner;



public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in); 
		int rounds = sc.nextInt();
		for(int round = 0; round < rounds; round++) {
			int days = sc.nextInt();
			int parties = sc.nextInt();
			//System.out.println(parties);
			int[] h = new int[parties];
			for(int i = 0; i < h.length; i++) {
				h[i] = sc.nextInt();
			}
			BitSet b = new BitSet(days+1);
			for(int i = 0; i < h.length; i++) {
				int k = h[i];
				int j = 1;
				while(k*j < days+1) {
					b.set(k*j);
					//System.out.println("setting");
					j++;
				}
			}
			int count = 0;
			for(int i = 1; i <days+1; i++) {
				if(i % 7== 6 || i % 7 == 0) {
					continue;
				}
				if(b.get(i)) {
					count++;
				}
			}
			//System.out.println(b.size());
			System.out.println(count);
		}
	}
}
