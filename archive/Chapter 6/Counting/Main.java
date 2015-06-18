import java.math.BigInteger;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger[] lookUp = new BigInteger[1001];
		lookUp[1] = new BigInteger("2");
		lookUp[2] = new BigInteger("5");
		lookUp[3] = new BigInteger("13");
		for(int i = 4; i < lookUp.length; i++) {
			lookUp[i] = lookUp[i-1].multiply(lookUp[1]).add(lookUp[i-2]).add(lookUp[i-3]);
		}
		while(sc.hasNextInt()) {
			System.out.println(lookUp[sc.nextInt()]);
		}
	}
}
