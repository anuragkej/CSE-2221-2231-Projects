import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    private static Map<String, Integer> emailMap = new HashMap<>();
    private String firstName;
    private String lastName;
    private String emailAddress;

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;

        //if else needed to check if last name already exists.
        if (emailMap.containsKey(lastName.toLowerCase())) {
            int lastNameVal = emailMap.get(lastName.toLowerCase());
            lastNameVal++;
            this.emailAddress = lastName.toLowerCase() + "." + lastNameVal
                    + "@osu.edu";
            emailMap.replace(lastName.toLowerCase(), lastNameVal);
        } else {
            emailMap.put(lastName.toLowerCase(), 1);
            this.emailAddress = lastName.toLowerCase() + ".1@osu.edu";
        }
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {
        String name = this.firstName + " " + this.lastName;
        return name;

    }

    @Override
    public String emailAddress() {
        return this.emailAddress;
    }

    @Override
    public String toString() {
        String result = "Name: " + this.firstName + " " + this.lastName
                + ", Email: " + this.emailAddress;
        return result;
    }

}
