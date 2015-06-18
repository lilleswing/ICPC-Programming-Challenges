#include <stdio.h>
#include <stdlib.h>

int main() {
	long long n;
	long long p;
	long long div[2];
	div[0] = 9;
	div[1] = 2;
	int divnum = 0;
	int count;
	while(1==scanf("%lld", &n) ){
		count = 0;
		p = 1;
		while(p < n) {
			p = p * div[divnum];
			divnum = (divnum+1)%2;
			count++;
		}
		if(count % 2 == 1) {
			printf("Stan wins.\n");
		} else {
			printf("Ollie wins.\n");
		}
	}
	return 0;
}
