import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {
        //use recursion
        //restores exp

        assert exp != null : "Violation of: exp is not null";
        NaturalNumber result = new NaturalNumber2(0);
        NaturalNumber zero = new NaturalNumber2(0);

        String labelExp = exp.label();

        if (!(exp.numberOfChildren() == 0)) {

            NaturalNumber val1 = evaluate(exp.child(0));
            NaturalNumber val2 = evaluate(exp.child(1));
            if (!(val1.compareTo(zero) < 0) || (val2.compareTo(zero) < 0)) {
                if (labelExp.equals("plus")) {
                    result.copyFrom(val1);
                    result.add(val2);

                } else if (labelExp.equals("minus")) {
                    if (val2.compareTo(val1) > 0) {
                        //reports error because NaturalNumbers cannot be negative.
                        Reporter.fatalErrorToConsole(
                                "Error: Can't subtract number by a larger number.");
                    } else {
                        //if inputs are valid then it subtracts
                        result.copyFrom(val1);
                        result.subtract(val2);
                    }
                } else if (labelExp.equals("times")) {
                    //multiplies inputs
                    result.copyFrom(val1);
                    result.multiply(val2);

                } else if (labelExp.equals("divide")) {
                    //divides if safe
                    if (!(val2.isZero())) {
                        result.copyFrom(val1);
                        result.divide(val2);
                    } else {
                        //reports error because NaturalNumbers cannot divide by 0 since val2 is equal to 0.
                        Reporter.fatalErrorToConsole(
                                "Error: can't divide by zero.");
                    }
                }
            }

        } else {
            result = new NaturalNumber2(exp.attributeValue("value"));
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