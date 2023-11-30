import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
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
        EmailAccount email = new EmailAccount1("", "");
        out.println(
                "Type in a full name for an Email address to be generated, or hit enter to close program.");
        String fullName = in.nextLine();
        while (!in.nextLine().equals("")) {
            out.println(
                    "Type in a full name for an Email address to be generated, or hit enter to close program.");
            fullName = in.nextLine();
            String firstName = "";
            String lastName = "";
            for (int i = 0; i < fullName.length(); i++) {
                if (fullName.substring(i, i + 1).equals("")) {
                    firstName = fullName.substring(0, i);
                    lastName = fullName.substring(i, fullName.length() - 1);
                }
            }

            email = new EmailAccount1(firstName, lastName);
        }
        /*
         * Should print: Brutus Buckeye
         */
        out.println(email.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(email.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(email);
        in.close();
        out.close();
    }

}
