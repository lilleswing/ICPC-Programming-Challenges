#include <stdio.h>
int twoWaySwap(int *a, int *b, int c, int d) {
	*a = c;
	*b = d;
}

int extended_gcd(int a,int b, int * r) {
	int x = 0, y = 1, lastx = 1, lasty = 0, quotient, temp;
	while(b != 0) {
		//printf("%d,%d\n",a,b);
		quotient = a/b;
		twoWaySwap(&a,&b,b,a%b);
		twoWaySwap(&x,&lastx,lastx-quotient*x,x);
		twoWaySwap(&y,&lasty,lasty-quotient*y,y);
	}
	r[0] = lastx;
	r[1] = lasty;
	r[2] = a;
	return 1;	
}
int main() {
	int s[3];
	int a, b;
	scanf("%d %d", &a, &b);
	do {
		extended_gcd(a, b, s);
		printf("%d %d %d\n",s[0],s[1],s[2]);
	}while(scanf("%d %d", &a, &b) != EOF);
	return 0;
}
