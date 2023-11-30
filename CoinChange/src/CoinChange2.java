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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {

    }

    private static void Calculate(int amount, SimpleReader in,
            SimpleWriter out) {

        int numDollar = 0;
        int numHalfDollar = 0;
        int numQuarter = 0;
        int numDime = 0;
        int numNickel = 0;
        int numPenny = 0;

        int[] coinDenoms = { numDollar, numHalfDollar, numQuarter, numDime,
                numNickel, numPenny };
        int[] coinCounts = { 100, 50, 25, 10, 5, 1 };

        int i = 0;
        while (i < 6) {

            if (amount % coinDenoms[i] >= 0) {
                coinDenoms[i] = amount / coinCounts[i];
                amount = amount % coinCounts[i];
            }
            i++;

        }
        out.println("Dollars: " + coinDenoms[0]);
        out.println("Half-dollars: " + coinDenoms[1]);
        out.println("Quarters: " + coinDenoms[2]);
        out.println("Dimes: " + coinDenoms[3]);
        out.println("Nickels: " + coinDenoms[4]);
        out.println("Pennies: " + coinDenoms[5]);

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

        int money = 0;

        out.println("Please enter the amount of money you want to "
                + "make change for in cents: ");
        String amt = in.nextLine();

        if (FormatChecker.canParseInt(amt)) {
            money = Integer.parseInt(amt);
        }

        Calculate(money, in, out);

        in.close();
        out.close();
    }

}
