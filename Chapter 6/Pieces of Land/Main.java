import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] a) {
		Scanner in = new Scanner(System.in);
		int u = in.nextInt();
		for (int i = 0; i < u; i++) {
			BigInteger b = BigInteger.ONE;
			BigInteger c = b.add(b);
			BigInteger d = new BigInteger("24");
			BigInteger n = new BigInteger(in.next());
			BigInteger x = n.subtract(b);
			BigInteger y = x.subtract(b);
			BigInteger z = y.subtract(b);
			b = b.add(n.multiply(x).divide(c));
			b = b.add(n.multiply(x).multiply(y).multiply(z).divide(d));
			System.out.println(b);
		}
	}
	
}
