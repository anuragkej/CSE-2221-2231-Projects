import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Anurag Kejriwal
 *
 */
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        out.print(n + ", ");
        while (n != 1 && n > 0) {

            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            if (n != 1) {
                out.print(n + ", ");
            } else {
                out.print(n);
            }

        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        int y = 0;

        out.println("Please enter a positive integer: ");
        String x = in.nextLine();

        if (FormatChecker.canParseInt(x)) {
            y = Integer.parseInt(x);
        }

        generateSeries(y, out);

        in.close();
        out.close();
    }

}
