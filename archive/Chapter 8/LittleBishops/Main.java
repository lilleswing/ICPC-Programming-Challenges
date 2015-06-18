import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {
	static int globCount = 0;
	public static void main(String[] args) throws FileNotFoundException {
		//Correct Way to do it
		/*
		System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			if(n==0 && k==0) {
				return;
			}
			System.out.printf("%d\n", dfsHelper(n,k));
		}
		*/
		//Generates pre-computed values when in.in has all possible values of n, k. less then 64*8 values
		/*
		System.setIn(new FileInputStream("in.in"));
		System.setOut(new PrintStream("out.out"));
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			if(n==0 && k==0) {
				return;
			}
			System.out.printf("\t\tarr[%d][%d] = %d\n;", n,k, dfsHelper(n,k));
		}
		*/
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int[][] precompute = new int[9][65];
		fill(precompute);
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			if(n==0 && k==0) {
				return;
			}
			System.out.println(precompute[n][k]);
		}
	}
	public static void fill(int[][] arr) {
		arr[1][0] = 1;
		arr[1][1] = 1;
		arr[2][0] = 1;
		arr[2][1] = 4;
		arr[2][2] = 4;
		arr[2][3] = 0;
		arr[2][4] = 0;
		arr[3][0] = 1;
		arr[3][1] = 9;
		arr[3][2] = 26;
		arr[3][3] = 26;
		arr[3][4] = 8;
		arr[3][5] = 0;
		arr[3][6] = 0;
		arr[3][7] = 0;
		arr[3][8] = 0;
		arr[3][9] = 0;
		arr[4][0] = 1;
		arr[4][1] = 16;
		arr[4][2] = 92;
		arr[4][3] = 232;
		arr[4][4] = 260;
		arr[4][5] = 112;
		arr[4][6] = 16;
		arr[4][7] = 0;
		arr[4][8] = 0;
		arr[4][9] = 0;
		arr[4][10] = 0;
		arr[4][11] = 0;
		arr[4][12] = 0;
		arr[4][13] = 0;
		arr[4][14] = 0;
		arr[4][15] = 0;
		arr[4][16] = 0;
		arr[5][0] = 1;
		arr[5][1] = 25;
		arr[5][2] = 240;
		arr[5][3] = 1124;
		arr[5][4] = 2728;
		arr[5][5] = 3368;
		arr[5][6] = 1960;
		arr[5][7] = 440;
		arr[5][8] = 32;
		arr[5][9] = 0;
		arr[5][10] = 0;
		arr[5][11] = 0;
		arr[5][12] = 0;
		arr[5][13] = 0;
		arr[5][14] = 0;
		arr[5][15] = 0;
		arr[5][16] = 0;
		arr[5][17] = 0;
		arr[5][18] = 0;
		arr[5][19] = 0;
		arr[5][20] = 0;
		arr[5][21] = 0;
		arr[5][22] = 0;
		arr[5][23] = 0;
		arr[5][24] = 0;
		arr[5][25] = 0;
		arr[6][0] = 1;
		arr[6][1] = 36;
		arr[6][2] = 520;
		arr[6][3] = 3896;
		arr[6][4] = 16428;
		arr[6][5] = 39680;
		arr[6][6] = 53744;
		arr[6][7] = 38368;
		arr[6][8] = 12944;
		arr[6][9] = 1600;
		arr[6][10] = 64;
		arr[6][11] = 0;
		arr[6][12] = 0;
		arr[6][13] = 0;
		arr[6][14] = 0;
		arr[6][15] = 0;
		arr[6][16] = 0;
		arr[6][17] = 0;
		arr[6][18] = 0;
		arr[6][19] = 0;
		arr[6][20] = 0;
		arr[6][21] = 0;
		arr[6][22] = 0;
		arr[6][23] = 0;
		arr[6][24] = 0;
		arr[6][25] = 0;
		arr[6][26] = 0;
		arr[6][27] = 0;
		arr[6][28] = 0;
		arr[6][29] = 0;
		arr[6][30] = 0;
		arr[6][31] = 0;
		arr[6][32] = 0;
		arr[6][33] = 0;
		arr[6][34] = 0;
		arr[6][35] = 0;
		arr[6][36] = 0;
		arr[7][0] = 1;
		arr[7][1] = 49;
		arr[7][2] = 994;
		arr[7][3] = 10894;
		arr[7][4] = 70792;
		arr[7][5] = 282248;
		arr[7][6] = 692320;
		arr[7][7] = 1022320;
		arr[7][8] = 867328;
		arr[7][9] = 389312;
		arr[7][10] = 81184;
		arr[7][11] = 5792;
		arr[7][12] = 128;
		arr[7][13] = 0;
		arr[7][14] = 0;
		arr[7][15] = 0;
		arr[7][16] = 0;
		arr[7][17] = 0;
		arr[7][18] = 0;
		arr[7][19] = 0;
		arr[7][20] = 0;
		arr[7][21] = 0;
		arr[7][22] = 0;
		arr[7][23] = 0;
		arr[7][24] = 0;
		arr[7][25] = 0;
		arr[7][26] = 0;
		arr[7][27] = 0;
		arr[7][28] = 0;
		arr[7][29] = 0;
		arr[7][30] = 0;
		arr[7][31] = 0;
		arr[7][32] = 0;
		arr[7][33] = 0;
		arr[7][34] = 0;
		arr[7][35] = 0;
		arr[7][36] = 0;
		arr[7][37] = 0;
		arr[7][38] = 0;
		arr[7][39] = 0;
		arr[7][40] = 0;
		arr[7][41] = 0;
		arr[7][42] = 0;
		arr[7][43] = 0;
		arr[7][44] = 0;
		arr[7][45] = 0;
		arr[7][46] = 0;
		arr[7][47] = 0;
		arr[7][48] = 0;
		arr[7][49] = 0;
		arr[8][0] = 1;
		arr[8][1] = 64;
		arr[8][2] = 1736;
		arr[8][3] = 26192;
		arr[8][4] = 242856;
		arr[8][5] = 1444928;
		arr[8][6] = 5599888;
		arr[8][7] = 14082528;
		arr[8][8] = 22522960;
		arr[8][9] = 22057472;
		arr[8][10] = 12448832;
		arr[8][11] = 3672448;
		arr[8][12] = 489536;
		arr[8][13] = 20224;
		arr[8][14] = 256;
		arr[8][15] = 0;
		arr[8][16] = 0;
		arr[8][17] = 0;
		arr[8][18] = 0;
		arr[8][19] = 0;
		arr[8][20] = 0;
		arr[8][21] = 0;
		arr[8][22] = 0;
		arr[8][23] = 0;
		arr[8][24] = 0;
		arr[8][25] = 0;
		arr[8][26] = 0;
		arr[8][27] = 0;
		arr[8][28] = 0;
		arr[8][29] = 0;
		arr[8][30] = 0;
		arr[8][31] = 0;
		arr[8][32] = 0;
		arr[8][33] = 0;
		arr[8][34] = 0;
		arr[8][35] = 0;
		arr[8][36] = 0;
		arr[8][37] = 0;
		arr[8][38] = 0;
		arr[8][39] = 0;
		arr[8][40] = 0;
		arr[8][41] = 0;
		arr[8][42] = 0;
		arr[8][43] = 0;
		arr[8][44] = 0;
		arr[8][45] = 0;
		arr[8][46] = 0;
		arr[8][47] = 0;
		arr[8][48] = 0;
		arr[8][49] = 0;
		arr[8][50] = 0;
		arr[8][51] = 0;
		arr[8][52] = 0;
		arr[8][53] = 0;
		arr[8][54] = 0;
		arr[8][55] = 0;
		arr[8][56] = 0;
		arr[8][57] = 0;
		arr[8][58] = 0;
		arr[8][59] = 0;
		arr[8][60] = 0;
		arr[8][61] = 0;
		arr[8][62] = 0;
		arr[8][63] = 0;
		arr[8][64] = 0;

	}
	private static int dfsHelper(int n, int k) {
		Point p[] = new Point[k];
		for(int i = 0; i < p.length; i++) {
			p[i] = new Point(-1,-1);
		}
		int count = dfs(0,0,p, k-1, n);
		//System.out.println("count = " + count);
		return count;
	}

	private static int dfs(int i, int j, Point[] p, int b, int len) {
		//System.out.println(i + "," + j);
		if(b==-1) {
			/*
			System.out.print(globCount++ + "\t");
			for(int x = 0; x < p.length; x++) {
				System.out.print(p[x].x+ "," + p[x].y+"\t");
			}
			System.out.println();
			*/
			return 1;
		}if(i==len && j==0) {
			return 0;
		}
		int nJ = j+1;
		int nI;
		if(nJ==len) {
			nJ = 0;
			nI = i+1;
		} else {
			nI = i;
		}
		int count = 0;
		if(check(p, i, j)) {
			p[b].set(i,j);
			p[b].valid = true;

			//System.out.println();
			count += dfs(nI, nJ, p, b-1, len);
			p[b].valid = false;
			//System.out.println("Check Passed");
		}
		count += dfs(nI, nJ,p,b,len);

		return count;
	}

	private static boolean check(Point[] p, int i, int j) {
		int angle1 = i-j;
		int angle2 = i+j;
		for(int x = 0; x < p.length; x++) {
			if(p[x].valid) {
				//System.out.println("WOW MOM");
				if(angle1 == p[x].angle1) {
					//System.out.println("fail1");
					return false;
				}
				if(angle2 == p[x].angle2) {
					//System.out.println("fail2");
					return false;
				}
			}
		}
		return true;
	}
}
class Point {
	int x, y, angle1, angle2;
	boolean valid;
	public Point(int a, int b) {
		valid = false;
		x = a;
		y = b;
		angle1 = x-y;
		angle2 = x+y;
	}
	public void set(int a, int b) {
		x = a;
		y = b;
		angle1 = x-y;
		angle2 = x+y;
	}
}
