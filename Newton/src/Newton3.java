import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Estimating the square root of a double using Newton iteration and also can
 * compute the square root of 0. Also, prompts the user to input the error
 * instead of having it already built into the program.
 *
 * @author Put your name here
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     * @param limit
     *
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
            out.println("Please enter the amount of accuracy: ");
            String lim = in.nextLine();

            double y = 0;
            double limit = 0.0;
            if (FormatChecker.canParseDouble(a)
                    && FormatChecker.canParseDouble(lim)) {
                y = Double.parseDouble(a);
                limit = Double.parseDouble(lim);
            }

            out.print(sqrt(y, limit));
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
