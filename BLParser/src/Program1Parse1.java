import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Anurag Kejriwal & Vibhav Kaluvala
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        String instruction = tokens.dequeue();
        Reporter.assertElseFatalError(instruction.equals("INSTRUCTION"),
                "Error: Expected: INSTRUCTION, actually found: " + instruction);

        Sequence<String> s = new Sequence1L<>();
        s.add(0, "move");
        s.add(1, "turnleft");
        s.add(2, "turnright");
        s.add(3, "infect");
        s.add(4, "skip");

        String identifier = tokens.dequeue();
        for (int i = 0; i < s.length(); i++) {
            Reporter.assertElseFatalError(!s.entry(i).equals(identifier),
                    "Error: User Defined Instruction " + identifier
                            + " cannot be the name of the primitive instruction "
                            + s.entry(i));
        }

        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Error: Expected: IS, actually found: " + is);

        body.parseBlock(tokens);

        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: Expected: END, actually found: " + end);

        String endIdentifier = tokens.dequeue();
        Reporter.assertElseFatalError(endIdentifier.equals(identifier),
                "Error: Beginning IDENTIFIER: " + identifier
                        + " does not equal the end IDENTIFIER: "
                        + endIdentifier);

        return identifier;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        String program = tokens.dequeue();
        Reporter.assertElseFatalError(program.equals("PROGRAM"),
                "Error: Expected: PROGRAM, actually found: " + program);

        String identifier = tokens.dequeue();

        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Error: Expected: IS, actually found: " + is);

        Map<String, Statement> ctxt = this.newContext();
        Map<String, Statement> c = this.newContext();

        String instructionOrBegin = tokens.front();

        while (instructionOrBegin.equals("INSTRUCTION")) {
            Statement b = new Statement1();
            String ctxtIdentifier = parseInstruction(tokens, b);
            for (int i = 0; i < ctxt.size(); i++) {
                Map.Pair<String, Statement> pair = ctxt.removeAny();
                Reporter.assertElseFatalError(
                        !pair.key().equals(ctxtIdentifier),
                        "Error: Instruction " + ctxtIdentifier
                                + " cannot be defined again");
            }
            ctxt.add(ctxtIdentifier, b);
            c.add(ctxtIdentifier, b);

            instructionOrBegin = tokens.front();
        }

        String begin = tokens.dequeue();
        Reporter.assertElseFatalError(begin.equals("BEGIN"),
                "Error: Expected: BEGIN, actually found: " + begin);

        Statement pBody = this.newBody();
        pBody.parseBlock(tokens);

        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: Expected: END, actually found: " + end);

        String endIdentifier = tokens.dequeue();
        Reporter.assertElseFatalError(endIdentifier.equals(identifier),
                "Error: Beginning IDENTIFIER: " + identifier
                        + " does not equal the end IDENTIFIER: "
                        + endIdentifier);

        Reporter.assertElseFatalError(
                tokens.front().equals(Tokenizer.END_OF_INPUT),
                "Error: Expected: " + Tokenizer.END_OF_INPUT
                        + " , actually found: " + tokens.front());

        // Need to swap name
        this.setName(identifier);
        this.swapContext(c);
        this.swapBody(pBody);

    }

    /*
     * Main test method -------------------------------------------------------
     */

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
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
