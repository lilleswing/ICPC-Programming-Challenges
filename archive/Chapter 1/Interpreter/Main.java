import java.io.*;
import java.util.*;

class Main {
	static int[] ram;
	static int[] reg;
	static int pc;

	public static void main(String[] args) throws FileNotFoundException {
		boolean first = true;
		ram = new int[1000];
		reg = new int[10];
		//System.setIn(new FileInputStream("in.in"));
		int caseNum, cases;
		Scanner sc = new Scanner(System.in);

		cases = Integer.parseInt(sc.nextLine());
		sc.nextLine();
		for (caseNum = 0; caseNum < cases; caseNum++) {
			Arrays.fill(ram, 0);
			Arrays.fill(reg, 0);
			pc = 0;
			String s;
			while (sc.hasNextLine()) {
				s = sc.nextLine();
				if (s.equals("")) {
					break;
				}
				// System.out.println(s);
				ram[pc++] = Integer.parseInt(s);
			}
			// display();
			pc = 0;
			int count = 0;
			boolean solved = false;
			while (!solved) {
				count++;
				solved = move();
			}

			if (!first) {
				System.out.println();
			}
			System.out.println(count);
			if (first) {
				first = false;
			}
		}
		System.exit(0);
	}

	static boolean move() {
		int i = ram[pc];
		int d, n;
		d = (i / 10) % 10;
		n = i % 10;
		pc++;
		// 100 means halt
		if (i == 100) {
			// System.out.println(i);
			return true;
		}
		// 2dn means set register d to n (between 0 and 9)
		if (i / 100 == 2) {
			reg[d] = n % 1000;
			return false;
		}
		// 3dn means add n to register d
		if (i / 100 == 3) {
			reg[d] = (reg[d] + n) % 1000;
			return false;
		}
		// 4dn means multiply register d by n
		if (i / 100 == 4) {
			reg[d] = (reg[d] * n) % 1000;
			return false;
		}
		// 5dn means set register d to the value of register n
		if (i / 100 == 5) {
			reg[d] = reg[n] % 1000;
			return false;
		}

		// * 6dn means add the value of register n to register d
		if (i / 100 == 6) {
			reg[d] = (reg[d] + reg[n]) % 1000;
			return false;
		}

		// 7ds means multiply register d by the value of register s
		if (i / 100 == 7) {
			reg[d] = (reg[d] * reg[n]) % 1000;
			return false;
		}
		// 8ds means set register d to the value in RAM whose address is in
		// register s
		if (i / 100 == 8) {
			reg[d] = ram[reg[n]] % 1000;
			return false;
		}
		// 9ds means set the value in RAM whose address is in register s to the
		// value of register d
		if (i / 100 == 9) {
			ram[reg[n]] = reg[d] % 1000;
			return false;
		}
		// 0ds means goto the location in register d unless register n contains
		// 0
		if (i / 100 == 0) {
			if (reg[n] != 0) {
				pc = reg[d] % 1000;
			}
			return false;
		}

		return false;
	}
}
