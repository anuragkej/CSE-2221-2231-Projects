import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Estimating the square root of a double using Newton iteration and also can
 * compute the square root of 0. Also, prompts the user to input the error
 * instead of having it already built into the program. Lastly, it reads a
 * negative double as the user terminating the program.
 *
 * @Anurag Kejriwal
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error that the
     * user provides through input.
     *
     * @param x
     * @param limit
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double limit) {
        double r = x;
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
     *            the method sqrt if the variable is 0 or a positive number,
     *            repeats until user enters a negative number.
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call sqrt as shown
         */

        double y = 0.0;
        while (y >= 0) {

            out.println(
                    "\nEnter a value for the double (Negative number to cancel.) ");
            String a = in.nextLine();

            if (FormatChecker.canParseDouble(a)) {
                y = Double.parseDouble(a);
            }

            if (y >= 0) {

                out.println("Please enter the amount of accuracy: ");
                String lim = in.nextLine();

                double limit = 0.0;
                if (FormatChecker.canParseDouble(lim)) {
                    limit = Double.parseDouble(lim);
                }

                out.print(sqrt(y, limit));

            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
