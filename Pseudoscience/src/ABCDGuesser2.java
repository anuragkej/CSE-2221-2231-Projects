import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * asks the user what constant μ should be approximated, and then asks in turn
 * for each of the four personal numbers w, x, y, and z. Then calculate and
 * report the values of the exponents a, b, c, and d that bring the de Jager
 * formula as close as possible to μ, as well as the value of the formula w^a *
 * x^b * y^c * z^d and the relative error of the approximation to the nearest
 * hundredth of one percent.
 *
 * @author Anurag Kejriwal
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double posNum = 0;
        int test = 0;

        while (test == 0) {
            out.println(
                    "Please enter a positive real number to be approximated: ");
            String userInput = in.nextLine();

            if (FormatChecker.canParseDouble(userInput)) {
                posNum = Double.parseDouble(userInput);
                if (posNum > 0) {
                    test++;
                } else {
                    out.println(
                            "This number is not positive, please try again.");
                }

            }

        }
        return posNum;
    }

    /**
     * Takes in 4 parameters and returns total estimate of number the user wants
     * to be approximated using the de Jager Formula.
     *
     * @param val1
     *            evaluation of w to the power of the number in the specified
     *            index of double[] arr
     * @param val2
     *            evaluation of x to the power of the number in the specified
     *            index of double[] arr
     * @param val3
     *            evaluation of y to the power of the number in the specified
     *            index of double[] arr
     * @param val4
     *            evaluation of z to the power of the number in the specified
     *            index of double[] arr
     * @return a double value total, which is the estimate of the number the
     *         user wants us to approximate.
     */
    private static double calcTotal(double val1, double val2, double val3,
            double val4) {

        double total = 0.0;

        total = val1 * val2 * val3 * val4;
        return total;

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double posNumNotOne = 0;
        int test = 0;

        while (test == 0) {
            out.println(
                    "Please enter a personal, positive real number to be used in the "
                            + "calculation that is not equal to 1.0: ");
            String userInput = in.nextLine();

            if (FormatChecker.canParseDouble(userInput)) {
                posNumNotOne = Double.parseDouble(userInput);
                if (posNumNotOne > 0 && posNumNotOne != 1.0) {
                    test++;
                } else {
                    out.println(
                            "This number is not positive or it is equal to 1.0, "
                                    + "please try again.");
                }

            }

        }
        return posNumNotOne;
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
        double error = 0.0;
        final double mult = 100.0;
        double errorMinus = 0.0;
        double lowError = 100.0;
        double val1Minus = 0.0;
        double val2Minus = 0.0;
        double val3Minus = 0.0;
        double val4Minus = 0.0;
        double total = 0.0;

        final double[] arr = { -5, -4, -3, -2, -1, -0.5, (-1.0 / 3.0), -0.25, 0,
                0.25, (1.0 / 3.0), 0.5, 1, 2, 3, 4, 5 };
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double d = 0.0;

        double approxNum = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length; j++) {

                for (int e = 0; e < arr.length; e++) {

                    for (int f = 0; f < arr.length; f++) {
                        if ((i > 0) && (j > 0) && (e > 0) && (f > 0)) {
                            val1Minus = (Math.pow(w, arr[i - 1]));
                            val2Minus = (Math.pow(x, arr[j - 1]));
                            val3Minus = (Math.pow(y, arr[e - 1]));
                            val4Minus = (Math.pow(z, arr[f - 1]));
                        }

                        double val1 = (Math.pow(w, arr[i]));
                        double val2 = (Math.pow(x, arr[j]));
                        double val3 = (Math.pow(y, arr[e]));
                        double val4 = (Math.pow(z, arr[f]));

                        errorMinus = Math
                                .abs((((val1Minus * val2Minus * val3Minus
                                        * val4Minus) - (approxNum)) / approxNum)
                                        * (mult));
                        error = Math.abs(
                                (((val1 * val2 * val3 * val4) - (approxNum))
                                        / approxNum) * (mult));
                        if (error <= errorMinus) {
                            if (error <= lowError) {
                                lowError = error;
                                total = calcTotal(val1, val2, val3, val4);
                                a = arr[i];
                                b = arr[j];
                                c = arr[e];
                                d = arr[f];

                            }
                        }

                    }

                }

            }

        }
        out.println(
                "This is the estimate using the de Jager formula: " + total);
        out.println("The error of the approximation: " + lowError + "%");
        out.println("1st exponent: " + a + " \n2nd exponent: " + b
                + " \n3rd exponent: " + c + " \n4th exponent: " + d);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
