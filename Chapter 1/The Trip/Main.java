/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;
import java.util.*;

class Main implements Runnable {
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

	public static void main(String args[]) throws FileNotFoundException // entry point from OS
	{
		//System.setIn(new FileInputStream("in.in"));

		Main myWork = new Main(); // Construct the bootloader
		myWork.run(); // execute
	}

	public void run() {
		new myStuff().run();
	}
}

class myStuff implements Runnable {
	int parse(String s) {
		String[] vals = s.split("\\.");
		return 100 * Integer.parseInt(vals[0]) + Integer.parseInt(vals[1]);
	}

	public void run() {
		int people = Integer.parseInt(Main.ReadLn(1000));
		while (people != 0) {
			int sum = 0;
			ArrayList<Integer> spent = new ArrayList<Integer>(people);
			for (int i = 0; i < people; i++) {
				int temp = parse(Main.ReadLn(10000));
				sum += temp;
				spent.add(temp);
			}
			Collections.sort(spent);
			Collections.reverse(spent);
			ArrayList<Integer> average = calcAverage(spent);
			int diff1 = calcDiff(average, spent);
			display(diff1/2);
			people = Integer.parseInt(Main.ReadLn(1000));
		}
	}
	public int min(int a, int b) {
		if(a < b) {
			return a;
		}
		return b;
	}
	public boolean display(int i) {
		System.out.println("$" + i/100 + "." + String.format("%02d",i%100));
		return true;
	}
	public int calcDiff(ArrayList<Integer> a, ArrayList<Integer> s) {
		int sum = 0;
		for(int i = 0; i < a.size(); i++) {
			sum += Math.abs(a.get(i) - s.get(i));
		}
		return sum;
	}

	public ArrayList<Integer> calcAverage(ArrayList<Integer> a) {
		long l = 0;
		for (int i : a) {
			l += i;
		}
		int remain = (int) (l%a.size());
		int average = (int) (l / a.size());
		ArrayList<Integer> spend = new ArrayList<Integer>(a);
		Collections.fill(spend, average);
		for(int i = 0; i < remain; i++) {
			spend.set(i,spend.get(i)+1);
		}
		return spend;
	}

}

