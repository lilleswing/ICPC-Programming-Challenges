import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	static String ReadLn(int maxLength) { // utility function to read from
		// stdin,
		// Provided by Programming-challenges, edit for style only
		byte line[] = new byte[maxLength];
		int length = 0;
		int input = -1;
		try {
			while (length < maxLength) {// Read untill maxlength
				input = System.in.read();
				if ((input < 0) || (input == '\n'))
					break; // or untill end of line ninput
				line[length++] += input;
			}

			if ((input < 0) && (length == 0))
				return null; // eof
			return new String(line, 0, length);
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		//System.setIn(new FileInputStream("in.txt"));

		ArrayList<String> a = new ArrayList<String>();
		String s;
		while ((s = ReadLn(1000)) != null) {
			a.add(s);
		}
		int line = 0;
		int fields = 1;
		String printed = "";
		while(!a.get(line).equals("0 0")) {
			String[] vals = a.get(line++).split(" ");
			//System.out.println(vals[0]);
			int r = Integer.parseInt(vals[0]);
			int c = Integer.parseInt(vals[1]);
			char[][] board = new char[r][c];
			for(int i = 0; i < r; i++) {
				String temp = a.get(line++);
				for(int j = 0; j < c; j++) {
					board[i][j] = temp.charAt(j);
				}
			}
			printed += "Field #" + fields+":\n";
			fields++;
			solution sol = new solution(r,c,board);
			printed += sol.solve();
			printed += "\n";
		}
		System.out.println(printed.substring(0,printed.length()-2));
	}
}

class solution implements Runnable {
	int row, col;
	char[][] board;
	public solution(int r, int c, char[][] b) {
		row = r;
		col = c;
		board = b;
	}
	public String solve() {
		int[][] sol = new int[row+100][col+100];
		int rowSpot = 50;
		int colSpot = 50;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(board[i][j] == '*') {
					sol[rowSpot + i][colSpot+j] = 100;
					for(int x = -1; x <= 1; x++) {
						for(int y = -1; y <= 1; y++) {
							sol[rowSpot+i+x][colSpot+j+y]++;
						}
					}
				}
			}
		}
		String returned = "";
		for(int i = rowSpot; i < rowSpot +row; i++) {
			for(int j = colSpot; j < colSpot + col; j++) {
				if(sol[i][j] < 50) {
					returned += sol[i][j];
				} else {
					returned += "*";
				}
			}
			returned += "\n";
		}
		return returned;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

