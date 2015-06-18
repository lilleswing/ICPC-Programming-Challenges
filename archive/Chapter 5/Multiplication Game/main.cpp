#include <stdio.h>
#include <string.h>
#include <math.h>

unsigned int n;

// 2 3 5 7
char w[33][23][15][13];

char getwin( int two,int three,int five,int seven ){
	if ( w[two][three][five][seven] != -1 )
		return w[two][three][five][seven];

	// base case

	long double x=1.0;

	x*=pow( 2.0, two );
	x*=pow( 3.0, three );
	x*=pow( 5.0, five );
	x*=pow( 7.0, seven );

	if ( x+0.00000001 >= (long double)n ) return 0;

		// recursive case :)
		int k=0;
		k+=!getwin(two+1,three,five,seven); // 2
		k+=!getwin(two,three+1,five,seven); // 3
		k+=!getwin(two+2,three,five,seven); // 4
		k+=!getwin(two,three,five+1,seven); // 5
		k+=!getwin(two+1,three+1,five,seven); // 6
		k+=!getwin(two,three,five,seven+1); // 7
		k+=!getwin(two+3,three,five,seven); // 8
		k+=!getwin(two,three+2,five,seven); // 9

	return w[two][three][five][seven]= k>0;
}

int main(){
	//FILE *in=fopen( "mult.in", "r" );
	int i,j,k;

	while ( 1==scanf("%u", &n ) ){
		memset( w, -1, sizeof(w) );
		if ( getwin(0,0,0,0) ) {
			printf( "Stan wins.\n" );
		}else {
			printf( "Ollie wins.\n" );
		}
	}
}
