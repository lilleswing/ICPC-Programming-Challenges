#include <stdio.h>
#include <string.h>
#define primeMax 65000
unsigned char sieve[primeMax];

int setSieve() {
	memset(sieve, 0, sizeof(sieve));
	int i, j, k;
	for(i=2; i < primeMax; i++) {
		if(!sieve[i]) {
			k = 2;
			while((j=i*k) < primeMax) {
				sieve[j] = 1;
				k++;
			}
		}
	}
}
long long modpow(long long base, long long exponent, long long modulus) {
	long long result = 1;
	base = base % modulus;
	while(exponent > 0) {
		if(exponent & 1) {
			result = (result * base) % modulus;
		}
		exponent = exponent >> 1;
		base = (base * base) % modulus;
	}
	return result;
}

int solve(long long n) {
	long long t = 2;
	while(t < n) {
		long long sol = modpow(t,n,n);
		if(sol != t) {
			//printf("%lld,%lld,%lld\n", t,n,sol);
			return 0;
		}
		t++;
	}
	return 1;
}

int test() {
	setSieve();
	long long l;
	for(l = 1; l < 65000; l++) {
		if(!sieve[l] || !solve(l)) {
		} else {
			printf("%lld\n", l);
		}
	}
}

int main() {
	//test();
	
	long long l;
	setSieve();
	scanf("%lld", &l); 
	do{
		if(!sieve[l] || !solve(l)) {
			printf("%lld is normal.\n", l);
		}
		else if(solve(l)) {
			printf("The number %lld is a Carmichael number.\n", l);
		} 
		scanf("%lld", &l);
	}while(l != 0);
	return 0;
	
}
