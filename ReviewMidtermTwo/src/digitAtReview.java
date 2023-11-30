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
public final class digitAtReview {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private digitAtReview() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static int digitAt(NaturalNumber n, int pos) {

        int result = 0;
        int digit = n.divideBy10();

        if (!(n.isZero())) {
            result = digitAt(n, pos);

        }
        n.multiplyBy10(digit);

        return result;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        NaturalNumber test = new NaturalNumber2(12345);
        out.print(digitAt(test, 1));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
