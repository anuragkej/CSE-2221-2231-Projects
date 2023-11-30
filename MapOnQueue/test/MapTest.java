import components.map.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import components.map.MapSecondary;
import components.queue.Queue;
import components.queue.Queue1L;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public final void testConstructor() {
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddNotEmpty() {
        Map<String, String> s = this.createFromArgsTest(“hi”, “bye”);
        Map<String, String> sExpected = this.createFromArgsRef(“hi”, “bye”, “goodbye”);

        s.add(“goodbye”);
        boolean result = false;
        for (Pair<String, String> x : sExpected) {
            if (s.hasKey(x.key()) && s.hasValue(x.value())
                    && s.key(x.value()).equals(x.key())) {
                result = true;
            }
        }

        assertEquals(result, true);
    }

    @Test
    public final void testRemove() {
        Map<String, String> s = this.createFromArgsTest((“hi”, “bye”, “goodbye"));
        Map<String, String> sExpected = this.createFromArgsRef(“bye”, “goodbye”);

        s.remove("hi");
        assertTrue(!s.hasKey("hi") && s.equals(sExpected));

    }

    @Test
    public final void testRemoveAny() {
        Map<String, String> s = this.createFromArgsTest(“hi”, “bye”, “goodbye”);
        int sizeBefore = s.size();
        s.removeAny();
        int sizeAfter = s.size();
        assertEquals(sizeAfter, sizeBefore - 1);
    }

    @Test
    public final void testValue() {
        Map<String, String> s = this.createFromArgsTest(“hi”, “bye”, “goodbye”, “hello”);
        String test = s.value("hi");
        String test2 = s.value("goodbye");
        assertTrue(test.equals("bye") && test2.equals("hello"));
    }

    @Test
    public final void testHasKey() {
        Map<String, String> s = this.createFromArgsTest(“hi”, “bye”, “goodbye”, “hello”);
        assertTrue(s.hasKey("hi") && !s.hasKey("bye"));
    }

    @Test
    public final void testSize() {
        Map<String, String> s = this.createFromArgsTest(“hi”, “bye”, “goodbye”, “hello”);
        int sizeTest = s.size();
        int sizeTest2 = 2;
        assertEquals(sizeTest, sizeTest2);
    }

}
