import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		for(int TT = 0; TT < T; TT++) {
			if(TT != 0) {
				System.out.println();
			}
			System.out.println(sc.nextLine());
			int numteams = sc.nextInt();
			sc.nextLine();
			HashMap<String, Team> map = new HashMap<String, Team>();
			for(int i = 0; i < numteams; i++) {
				String temp = sc.nextLine();
				map.put(temp, new Team(temp));
			}
			int games = sc.nextInt();
			sc.nextLine();
			for(int i = 0; i < games; i++) {
				StringTokenizer st = new StringTokenizer(sc.nextLine(), "#||@");
				Team team1 = map.get(st.nextToken());
				int g1 = Integer.parseInt(st.nextToken());
				int g2 = Integer.parseInt(st.nextToken());
				Team team2 = map.get(st.nextToken());
				if(g1 > g2) {
					team1.wins++;
					team1.points += 3;
					team2.losses++;
				} else if(g2 > g1) {
					team2.wins++;
					team2.points += 3;
					team1.losses++;
				} else if(g2 == g1) {
					team1.points++;
					team2.points++;
					team1.ties++;
					team2.ties++;
				}
				team1.goalDiff += g1-g2;
				team2.goalDiff += g2-g1;
				team1.goals += g1;
				team1.goalsAgainst += g2;
				team2.goals += g2;
				team2.goalsAgainst += g1;
				team1.gamesPlayed++;
				team2.gamesPlayed++;
			}
			ArrayList<Team> standings = new ArrayList<Team>();
			standings.addAll(map.values());
			Collections.sort(standings);
			Collections.reverse(standings);
			int rank = 1;
			for(Team t: standings) {
				System.out.println(rank++ + ") " + t);
			}
		}
	}
}

/*
 * Teams are ranked according to these rules (in this order):

   1. Most points earned.
   2. Most wins.
   3. Most goal difference (i.e. goals scored - goals against)
   4. Most goals scored.
   5. Less games played.
   6. Lexicographic order. 
 * 
 */
class Team implements Comparable {
	String name;
	int points, wins, goalDiff, goals, gamesPlayed,ties, losses, goalsAgainst;
	public Team(String s) {
		name = s;
		wins = 0;
		goalDiff = 0;
		goals = 0;
		gamesPlayed = 0;
		ties = 0;
		losses = 0;
		goalsAgainst = 0;
	}
	@Override
	public int compareTo(Object o) {
		Team t = (Team) o;
		int retval;
		retval = this.points - t.points;
		if(retval != 0) {
			return retval;
		}
		retval = this.wins - t.wins;
		if(retval != 0) {
			return retval;
		}
		retval = this.goalDiff - t.goalDiff;
		if(retval != 0) {
			return retval;
		}
		retval = this.goals - t.goals;
		if(retval != 0) {
			return retval;
		}
		retval = t.gamesPlayed - this.gamesPlayed;
		if(retval != 0) {
			return retval;
		}
		return this.name.compareToIgnoreCase(t.name)*-1;
	}
	/*
	 * [a]) Team_name [b]p, [c]g ([d]-[e]-[f]), [g]gd ([h]-[i])

		Where:

    * [a] = team rank
    * [b] = total points earned
    * [c] = games played
    * [d] = wins
    * [e] = ties
    * [f] = losses
    * [g] = goal difference
    * [h] = goals scored
    * [i] = goals against 

There must be a single blank sp
	 */
	public String toString() {
		return name + " " + points +"p, " + this.gamesPlayed +"g ("+this.wins+"-"+this.ties +"-" + this.losses + "), " + this.goalDiff +"gd (" + this.goals+"-" + this.goalsAgainst + ")";
	}
}