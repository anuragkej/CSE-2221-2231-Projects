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
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer also computes and outputs the maximum value of the series and
     * also computes and outputs the length of the series. Lastly, repeatedly
     * asks the user whether they wish to calculate another series.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int count = 1;
        int max = n;
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
            if (n > max) {
                max = n;
            }

            count++;

        }
        out.print("\n\nLength: " + count);
        out.print("\n\nMaximum: " + max);

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

        String test = "y";
        while (test.equals("y")) {

            int y = 0;

            out.println("Please enter a positive integer: ");
            String x = in.nextLine();

            if (FormatChecker.canParseInt(x)) {
                y = Integer.parseInt(x);
            }

            generateSeries(y, out);
            out.println(
                    "\nDo you wish to calculate another series? (Enter y for yes). ");
            test = in.nextLine();

        }

        in.close();
        out.close();
    }

}
