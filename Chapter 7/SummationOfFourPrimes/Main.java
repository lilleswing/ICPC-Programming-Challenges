import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
	static final int max =10000000;
	public static ArrayList<Integer> sieve() {
		BitSet b = new BitSet(max);
		int top = max/2;
		int j;
		for(int i = 2; i < top; i++) {
			if(!b.get(i)) {
				int k = 2;
				while((j=i*k) < max) {
					b.set(j);
					k++;
				}
			}
		}
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i < max; i++) {
			if(!b.get(i)) {
				primes.add(i);
			}
		}
		return primes;
	}
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Integer> primes = sieve();
		HashSet<Integer> pset = new HashSet<Integer>(primes);
		Scanner sc = new Scanner(System.in);
		int[] vals = new int[4];
		while(sc.hasNextInt()) {
			int num = sc.nextInt();
			if(num < 8) {
				System.out.println("Impossible.");
			} else {
				int p3=0;
				if(num % 2 == 1) {
					num = num -5;
					p3=3;	
				} else {
					num = num - 4;
					p3 = 2;
				}
				int i = 0;
				while(!pset.contains(num-primes.get(i))) {
					i++;
				}
				System.out.printf("%d %d %d 2\n", primes.get(i), num-primes.get(i), p3);
			}
		}
		
	}
}
