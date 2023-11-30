import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Model class.
 *
 * @author Bruce W. Weide * @author Paolo Bucci
 */
public final class AppendUndoModel1 implements AppendUndoModel {
    /**
     * Model variables.
     */
    private String input;
    private Stack<String> output = new Stack1L<>();

    /**
     * Default constructor.
     */
    public AppendUndoModel1() { /*
                                 * Initialize model; both variables start as
                                 * empty strings
                                 */
        this.input = "";
        this.output.push("");
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String input() {
        return this.input;
    }

    @Override
    public Stack<String> output() {
        return this.output;
    }
}