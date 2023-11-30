import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     *
     *          private static void generateSeries(NaturalNumber n, SimpleWriter
     *          out) { /*out.print(n + ", "); int oneInt = 1; int twoInt = 2;
     *          int threeInt = 3; int zeroInt = 0;
     *
     *          NaturalNumber n2 = new NaturalNumber2(); NaturalNumber zero =
     *          new NaturalNumber2(zeroInt); NaturalNumber one = new
     *          NaturalNumber2(oneInt); NaturalNumber two = new
     *          NaturalNumber2(twoInt); NaturalNumber three = new
     *          NaturalNumber2(threeInt); NaturalNumber copy = new
     *          NaturalNumber2(n);
     *
     *          NaturalNumber result2 = copy.divide(two);
     *
     *          while (n.compareTo(one) != 0 && n.compareTo(zero) > 0) {
     *
     *          if (result2.compareTo(zero) == 0) { n.divide(two); } else {
     *          n.multiply(three); n.add(one); }
     *
     *          if (n.compareTo(one) < 0 && n.compareTo(zero) > 0) { out.print(n
     *          + ", "); } else { out.print(n); }
     *
     *          copy.copyFrom(n2);
     *
     *          }
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        out.print(n + ", ");
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber three = new NaturalNumber2(3);
        NaturalNumber copy = new NaturalNumber2(n);
        NaturalNumber copy2 = new NaturalNumber2(n);
        NaturalNumber max = new NaturalNumber2(n);
        int count = 0;
        while (copy.compareTo(one) != 0 && copy.compareTo(zero) > 0) {
            NaturalNumber comp = copy.divide(two);
            if (comp.compareTo(one) != 0) {
                copy2.divide(two);
            } else {
                copy2.multiply(three);
                copy2.add(one);
            }
            if (copy2.compareTo(one) != 0) {
                out.print(copy2 + ", ");
            } else {
                out.print(copy2);
            }
            copy.copyFrom(copy2);
            count++;

            if (max.compareTo(copy2) < 0) {
                max.copyFrom(copy2);
            }
        }
        out.println("\ncount: " + count);
        out.println("max: " + max);
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
        out.println(
                "Please enter a number you want to compute the Hailstone series for. ");
        int input = in.nextInteger();
        NaturalNumber n = new NaturalNumber2(input);

        generateSeries(n, out);
        out.print("n: " + n);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
