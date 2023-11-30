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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {

    }

    private static void Calculate(int amount, SimpleReader in,
            SimpleWriter out) {

        int dollar = 0;
        int halfDollar = 0;
        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        int penny = 0;

        if (amount % 100 >= 0) {
            dollar = amount / 100;
            amount = amount % 100;
        }

        if (amount % 50 >= 0) {
            halfDollar = amount / 50;
            amount = amount % 50;
        }
        if (amount % 25 >= 0) {
            quarter = amount / 25;
            amount = amount % 25;
        }
        if (amount % 10 >= 0) {
            dime = amount / 10;
            amount = amount % 10;
        }
        if (amount % 5 >= 0) {
            nickel = amount / 5;
            amount = amount % 5;
        }
        if (amount % 1 >= 0) {
            penny = amount / 1;
            amount = amount % 1;
        }

        out.println("Dollars: " + dollar);
        out.println("Half-dollars: " + halfDollar);
        out.println("Quarters: " + quarter);
        out.println("Dimes: " + dime);
        out.println("Nickels: " + nickel);
        out.println("Pennies: " + penny);

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

        out.println("Please enter the amount of money you want to make "
                + "change for in cents: ");
        String amt = in.nextLine();

        if (FormatChecker.canParseInt(amt)) {
            money = Integer.parseInt(amt);
        }

        Calculate(money, in, out);

        in.close();
        out.close();
    }

}
