import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Anurag Kejriwal
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    private static void checkPassword(String s, SimpleWriter out) {

        int countLower = 0;
        int countDigit = 0;
        int countUpper = 0;

        for (int i = 0; i < 1; i++) {

            if (containsLowerCaseLetter(s)) {
                countLower++;
            }

            if (containsDigit(s)) {
                countDigit++;
            }

            if (containsUpperCaseLetter(s)) {
                countUpper++;
            }

        }

        if ((countLower > 0 && countDigit > 0)
                || (countLower > 0 && countUpper > 0)
                || (countDigit > 0 && countUpper > 0)) {
            if (s.length() >= 8) {
                out.println("Satisfies the length criteria.");
            }
            if (s.length() < 8) {
                out.println("Does not satisfy the length criteria.");
            }
            if (countLower > 0) {
                out.println("Satisfies the lower case character criteria.");
            }
            if (countLower == 0) {
                out.println(
                        "Does not satisfy the lower case character criteria.");
            }

            if (countDigit > 0) {
                out.println("Satisfies the digit criteria.");
            }

            if (countDigit == 0) {
                out.println("Does not satisfy the digit criteria.");
            }
            if (countUpper > 0) {
                out.println("Satisfies the upper case character criteria.");
            }
            if (countUpper == 0) {
                out.println(
                        "Does not satisfy the upper case character criteria.");
            }

        } else {
            out.println("Password rejected. Please try again.");
        }
    }

    private static boolean containsLowerCaseLetter(String s) {

        int i;
        int j = 0;
        for (i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                j++;
            }
        }
        return (j > 0);
    }

    private static boolean containsDigit(String s) {
        int i;
        int j = 0;
        for (i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                j++;
            }
        }
        return (j > 0);
    }

    private static boolean containsUpperCaseLetter(String s) {

        int i;
        int j = 0;
        for (i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                j++;
            }
        }
        return (j > 0);
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

        out.print("Enter a password: ");
        String password = in.nextLine();

        checkPassword(password, out);

        in.close();
        out.close();
    }

}
