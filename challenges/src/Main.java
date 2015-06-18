import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    }
}

class JollyJumper {
    private Scanner scanner;
    public JollyJumper(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void solve() {
        final List<Case> cases = parse(scanner);

    }

    private List<Case> parse(final Scanner scanner) {
        final List<Case> cases = new ArrayList<>();
        final Case aCase = new Case();
        return cases;
    }

    class Case {
        int[] hash = new int[3000];
        String values;
    }

}
