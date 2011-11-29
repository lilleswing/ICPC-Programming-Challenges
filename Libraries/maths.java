package Resources;

import java.math.BigInteger;

public class maths {
	public static BigInteger binomial(int N,int  K) {
		BigInteger[][] binomial = new BigInteger[N+1][K+1];
		for(int k = 1; k <=K; k++) binomial[0][k] = BigInteger.ZERO;
		for(int n = 0; n <=N; n++) binomial[n][0] = BigInteger.ONE;
		for(int n = 1; n <= N; n++) {
			for(int k = 1; k <= K; k++) {
				binomial[n][k] = binomial[n-1][k-1].add(binomial[n-1][k]);
			}
		}
		return binomial[N][K];
	}
}
