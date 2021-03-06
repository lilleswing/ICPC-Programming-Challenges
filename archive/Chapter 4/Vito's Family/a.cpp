 #include <algorithm>
 #include <deque>
 #include <fstream>
 #include <iostream>
 #include <list>
 #include <map>
 #include <queue>
 #include <set>
 #include <stack>
 #include <string>
 #include <vector>

 using namespace std;

 int min_dist_sum(int *first, int *last)
 {
   int *median = first + (last - first) / 2;
   nth_element(first, median, last);
   int sum = 0;
   for (; first < median; ++first)
     sum += *median - *first;
   for (; first < last; ++first)
     sum += *first - *median;
   return sum;
 }
           
 int main(int argc, char *argv[])
 {

   int ncases;
   cin >> ncases;
   while (ncases-- > 0) {
     int r;
     cin >> r;
     int s[500];
     for (int i = 0; i < r; ++i)
       cin >> s[i];
     cout << min_dist_sum(s, s + r) << '\n';
   }

   return 0;
 }

