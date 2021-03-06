import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		ArrayList<Elephant> cir = new ArrayList<Elephant>();
		int id = 1;
		while(sc.hasNextInt()) {
			cir.add(new Elephant(sc.nextInt(), sc.nextInt(), id++));
		}
		Collections.sort(cir);
		//System.out.println(cir);
		sol[] solution = new sol[cir.size()];
		for(int i = 0; i < solution.length; i++) {
			solution[i] = new sol();
		}
		//System.out.println(solution.length);
		//System.out.println(cir.size());
		//dp
		for(int i = solution.length-1; i >= 0; i--) {
			int max = 0;
			int maxSpot = 0;
			//solution[i].b.set(i);
			for(int j = i; j < solution.length; j++) {
				//System.out.println(i + " " + j);
				if(cir.get(j).weight == cir.get(i).weight) {
					continue;
				}
				if(cir.get(i).iq > cir.get(j).iq && solution[j].on > max){ 
					max = solution[j].on;
					maxSpot =  j;
				}
			}
			solution[i].b = (BitSet) solution[maxSpot].b.clone();
			solution[i].b.set(i);
			solution[i].on = max+1;
		}
		int max = 0;
		int maxSpot = 0;
		for(int i = 0; i < solution.length; i++) {
			if(solution[i].on >= max) {
				max = solution[i].on;
				maxSpot = i;
			}
		}
		System.out.println(max);
		if(max == 0) {
			return;
		}
		for(int i = 0; i < solution[maxSpot].b.size(); i++) {
			if(solution[maxSpot].b.get(i)) {
				System.out.println(cir.get(i));
			}
		}
	}
}
class sol {
	BitSet b;
	int on;
	public sol() { 
		on = 1;
		b = new BitSet();
	}
	public String toString() {
		return on + "";
	}
}
class Elephant implements Comparable{
	int weight, iq, id;
	public Elephant(int w, int iqq, int idd) {
		this.weight = w;
		this.iq = iqq;
		this.id = idd;
	}
	public int compareTo(Object o) {
		Elephant e = (Elephant)(o);
		return this.weight - e.weight;
	}
	public String toString() {
		return this.id + " " + this.weight + " " + this.iq;
		//return this.id + "";
	}
}
