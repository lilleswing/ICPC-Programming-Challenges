/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
	static int hash[];
	public static void main(String[] args) throws FileNotFoundException {
		hash = new int[3000];
		//System.setIn(new FileInputStream("temp.txt"));
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			boolean jolly = true;
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int n = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			Arrays.fill(hash,0);
			
			for(int i = 1; i < n; i++) {
				int next = Integer.parseInt(st.nextToken());
				int diff = Math.abs(start - next);
				if(hash[diff] == 1) {
					jolly = false;
					break;
				}
				hash[diff] = 1;	
				start = next;
			}
			for(int i = 1; i < n; i++) {
				//System.out.print(hash[i]+ ",");
				if(hash[i] == 0) {
					jolly = false;
				}
			}
			if(jolly) {
				System.out.println("Jolly");
			} else {
				System.out.println("Not jolly");
			}
		}
	}
}
