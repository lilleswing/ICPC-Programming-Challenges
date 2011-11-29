#include <iostream>
#include <vector>
#include <string>
using namespace std;
#define MAX 1000000
vector<long long> v(MAX);

unsigned long long hail(long long m) {
	if(m < MAX && v.at(m) != 0) {
		return v.at(m);	
	}
	if(m == 1) {
		v.at(m) = 1;
		return 1;
	}
	int temp=0;
	if(m%2 == 0) {
		temp = 1+hail(m/2);
	}else {
		temp = 1+hail(m*3+1);
	}
	if(m < MAX) {
		v.at(m) = temp;
	}
	return temp;
}

int main() {
	for(int x = 0; x < MAX; x++) {
		v.at(x) = 0;
	}
	int n, m;
	for(int x = 1; x < MAX; x++) {
		hail(x);	
	}
	while(cin>>n>>m) {
		cout << n << " " << m << " ";
		if(m < n) {
			int temp = n;
			n = m;
			m = temp;	
		}
		int max = 0;
		long long temp;
		for(int x = n; x <= m; x++) {
			temp = hail(x);
			if(temp > max) {
				max = temp;
			}
		}
		cout << max << "\n";
	}
	return 0;	
}
