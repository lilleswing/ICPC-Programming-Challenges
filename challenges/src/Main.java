import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new JollyJumper(sc).solve();
    }
}

class JollyJumper {
    private Scanner scanner;
    public JollyJumper(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void solve() {
        final List<Case> cases = parse(scanner);
        for (final Case aCase: cases) {
            solve(aCase);
        }
    }

    private void solve(final Case aCase) {
        System.out.println(aCase.solve());
    }

    private List<Case> parse(final Scanner scanner) {
        final List<Case> cases = new ArrayList<>();
        while(scanner.hasNextInt()) {
            final Case aCase = new Case(scanner.nextLine());
            cases.add(aCase);
        }
        return cases;
    }

    class Case {
        int[] hash = new int[3000];
        String valueString;
        public Case(final String s) {
            this.valueString = s;
        }

        public String solve() {
            final StringTokenizer st = new StringTokenizer(valueString);
            int n = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            for (int i = 1; i < n; i++) {
                int next = Integer.parseInt(st.nextToken());
                int diff = Math.abs(start - next);
                hash[diff] = 1;
                start = next;
            }
            for (int i = 1; i < n; i++) {
                if (hash[i] == 0) {
                    return "Not jolly";
                }
            }
            return "Jolly";
        }
    }

}
