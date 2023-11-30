import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Anurag Kejriwal
 *
 */
public final class Oddity {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Oddity() {
    }

    /**
     * Iterates through an array of values and prints output of odd if the
     * remainder of a certain index of the array divided by 2 is equal to 1 and
     * even otherwise.
     *
     *
     * @param args
     *            the command line arguments
     */

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final int[] values = { 8, -4, 3, 0, -5 };
        int i = 0;
        while (i < values.length) {
            int remainder = values[i] % 2;
            if (remainder == 1 || remainder == -1) {
                out.println("odd");
            } else {
                out.println("even");
            }
            i = i + 1;
        }
        out.close();
    }
    /*
     * public static void main(String[] args) { SimpleWriter out = new
     * SimpleWriter1L(); double x = 456.0; double y = (100.0 * 4.56);
     * 
     * if (x == y || x (double) == math.round(y)) { out.println("equal"); } else
     * { out.println("not equal"); } out.close(); }
     * 
     * public static void main(String[] args) { SimpleWriter out = new
     * SimpleWriter1L(); final int num = 1000; final int microsPerDay = (24 * 60
     * 60 * 1000 * 1000); final int millisPerDay = 24 * 60 * 60 * 1000; if
     * (microsPerDay > Integer.MAX_VALUE){ out.println(num); } else {
     * out.println( microsPerDay / millisPerDay); }
     *
     * out.close(); }
     * 
     * 
     * public static void main(String[] args) { SimpleWriter out = new
     * SimpleWriter1L(); out.println(12345 + 54321); out.close(); }
     */
}
