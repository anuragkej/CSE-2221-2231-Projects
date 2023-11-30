import components.statement.Statement;
import components.statement.Statement1;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement call = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(call);
                    s.addToBlock(i, call);
                }

                break;
            }
            case IF: {

                Statement call = new Statement1();

                Statement.Condition c = s.disassembleIf(call);
                count = countOfPrimitiveCalls(call);
                s.assembleIf(c, call);

                break;
            }
            case IF_ELSE: {

                Statement call = new Statement1();
                Statement call2 = new Statement1();

                Statement.Condition c = s.disassembleIfElse(call, call2);
                count = countOfPrimitiveCalls(call)
                        + countOfPrimitiveCalls(call2);

                s.assembleIfElse(c, call, call2);

                break;
            }
            case WHILE: {

                Statement call = s.newInstance();
                Statement.Condition c = s.disassembleWhile(call);

                count = countOfPrimitiveCalls(call);
                s.assembleWhile(c, call);

                break;
            }
            case CALL: {

                String call = s.disassembleCall();
                if (call.equals("move") || call.equals("turnright")
                        || call.equals("turnleft") || call.equals("infect")
                        || call.equals("skip")) {
                    count++;
                }
                s.assembleCall(call);
                break;
            }
            default: {
                // this will never happen...can you explain why?

                //yes because all of the possible cases are already
                //covered with the switch cases
                break;
            }
        }
        return count;
    }

}
