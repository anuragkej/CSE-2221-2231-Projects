import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length

    @Test
    public final void testPushFromEmpty() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("Hi");

        s.push("Hi");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPushFromNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Hello");
        Stack<String> sExpected = this.createFromArgsRef("Hi", "Hello");

        s.push("Hi");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPopToEmpty() {
        Stack<String> s = this.createFromArgsTest("Hi");
        Stack<String> sExpected = this.createFromArgsRef();

        String ans = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Hi", ans);
    }

    @Test
    public final void testPopToNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Hi", "Hello");
        Stack<String> sExpected = this.createFromArgsRef("Hello");

        String ans = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Hi", ans);
    }

    @Test
    public final void testLength() {
        Stack<String> s = this.createFromArgsTest("Hi");

        int l = s.length();

        assertEquals(1, l);
    }

    @Test
    public final void testTop() {
        Stack<String> s = this.createFromArgsTest("Hi");

        String top = "Hi";

        assertEquals(s.top(), top);
    }

}
