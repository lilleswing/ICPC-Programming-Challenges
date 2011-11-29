#include <stdio.h>
#include <math.h>

int solve(long long l) {
	long long sq = (long long)(sqrt(l));
	return sq*sq==l;
}

int main() {
	long long l;
	scanf("%lld", &l);
	do {
		if(solve(l)) {
			printf("yes\n");
		} else {
			printf("no\n");
		}
		scanf("%lld", &l);
	}while(l!=0);

}
