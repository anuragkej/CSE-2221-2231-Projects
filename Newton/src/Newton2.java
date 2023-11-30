import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Estimating the square root of a double using Newton iteration and also can
 * compute the square root of 0.
 *
 * @Anurag Kejriwal
 *
 */
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01% and
     * also accepts the input of 0.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        final double limit = 0.0001;

        if (r != 0) {
            while ((Math.abs(r * r - x) / x) > limit * limit) {

                r = (r + (x / r)) / 2;
            }
        } else {
            r = 0;
        }

        return r;
    }

    /**
     * Main method.
     *
     * @param args
     *            Parses user input from String to double and passes it through
     *            the method sqrt and repeats until user enters an input other
     *            than "y".
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call sqrt as shown
         */

        String test = "y";
        while (test.equals("y")) {

            out.println("Please enter a positive double: ");
            String a = in.nextLine();
            double y = 0;

            if (FormatChecker.canParseDouble(a)) {
                y = Double.parseDouble(a);
            }

            out.print(sqrt(y));
            out.println(
                    "\nDo you wish to calculate another series? (Enter y for yes). ");
            test = in.nextLine();
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
