import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int numChild = xt.numberOfChildren();
        int midChildIndex = (numChild - 1) / 2;
        out.println("The label of the middle child is: "
                + xt.child(midChildIndex).label());
        if (xt.child(midChildIndex).isTag()) {
            out.println("The middle child's label is a tag.");
            out.println("The middle child's number of children is: "
                    + xt.child(midChildIndex).numberOfChildren());
        } else {
            out.println("The middle child's label is text.");

        }

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
        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");

        XMLTree results = xml.child(0);
        XMLTree channel = results.child(0);
        out.println(channel.numberOfChildren());
        XMLTree title = channel.child(1);
        XMLTree titleText = title.child(0);
        out.println(titleText.label());
        out.println(xml.child(0).child(0).child(1).child(0).label());
        xml.display();

        XMLTree astronomy = channel.child(10);
        if (astronomy.hasAttribute("sunset")) {
            out.println("astronomy has an attribute named sunset.");
            out.println(astronomy.attributeValue("sunset"));

        }
        if (astronomy.hasAttribute("midday")) {
            out.println("astronomy has an attribute named midday.");
            out.println(astronomy.attributeValue("midday"));

        }

        if (xml.isTag()) {
            out.println("Is a tag");
        } else {
            out.println("Is not a tag");
        }
        out.println("The label of the root node is: " + xml.label());

        printMiddleNode(channel, out);

        in.close();
        out.close();
    }

}
