import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Anurag Kejriwal
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */

    private static int evaluate(XMLTree exp) {
        //use recursion
        //restores exp
        //no loop needed
        assert exp != null : "Violation of: exp is not null";
        int result = 0;

        String labelExp = exp.label();

        if (!(exp.numberOfChildren() == 0)) {

            int val1 = evaluate(exp.child(0));
            int val2 = evaluate(exp.child(1));

            if (labelExp.equals("plus")) {
                //adds values
                result = val1 + val2;

            } else if (labelExp.equals("minus")) {
                //reports error because int cannot be negative.
                if (val2 > val1) {
                    Reporter.fatalErrorToConsole(
                            "Error: Can't subtract number by a larger number.");
                } else {
                    //subtracts values
                    result = val1 - val2;
                }
            } else if (labelExp.equals("times")) {
                //multiplies values
                result = val1 * val2;

            } else if (labelExp.equals("divide")) {
                //divides values as long as second value isn't 0
                if (val2 != 0) {
                    result = val1 / val2;
                } else {
                    //reports error because int cannot divide by 0 since val2 is equal to 0.
                    Reporter.fatalErrorToConsole(
                            "Error: can't divide by zero.");
                }
            }

        } else {

            result = Integer.parseInt(exp.attributeValue("value"));

        }

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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}