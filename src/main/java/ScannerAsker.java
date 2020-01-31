import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ScannerAsker {
    private final Scanner scanner;
    private final PrintStream out;

    public ScannerAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

//    public ScannerAsker(Scanner scanner, PrintStream out) {
//        this.scanner = scanner;
//        this.out = out;
//    }

    public int askInt(String message) {
        out.println(message);
        return scanner.nextInt();
    }
    public String askString(String message) {
        out.println(message);
        return scanner.next();
    }
}
