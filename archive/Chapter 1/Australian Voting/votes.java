import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class votes {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("in.in"));
		out.println("20\n");
		for(int i = 0; i < 20; i++) {
			out.println("20");
			ArrayList<Integer> votes = new ArrayList<Integer>();
			for(int j = 0; j < 20; j++) {
				out.println("~" + j);
				votes.add(j+1);
			}
			
			for(int j = 0; j < 1000; j++) {
				Collections.shuffle(votes);
				out.print(votes.get(0));
				for(int k = 1; k < votes.size(); k++) {
					out.print(" "+votes.get(k));
				}
				out.println();
			}
			out.println();
		}
		out.close();
	}
}
