import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Anurag Kejriwal & Vibhav Kaluvala
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
    /*
     * Tests default constructor
     */
    public final void testConstructor1() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();

        assertEquals(m, mExpected);
    }

    @Test
    /*
     * Tests constructor
     */
    public final void testConstructor2() {
        Map<String, String> m = this.createFromArgsTest("hi", "bye", "hello",
                "bonjour");
        Map<String, String> mExpected = this.createFromArgsRef("hi", "bye",
                "hello", "bonjour");

        assertEquals(m.equals(mExpected), true);
    }

    /*
     * Tests add method
     */
    @Test
    public final void testAddNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye");
        Map<String, String> sExpected = this.createFromArgsRef("hi", "bye",
                "goodbye", "hello");

        s.add("goodbye", "hello");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddEmpty() {
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("hi", "bye");

        s.add("hi", "bye");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddNonEmptyMany() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        Map<String, String> sExpected = this.createFromArgsRef("hi", "bye",
                "goodbye", "hello", "bonjour", "bonsoir");

        s.add("bonjour", "bonsoir");

        assertEquals(s, sExpected);
    }

    /*
     * Tests remove method
     */
    @Test
    public final void testRemoveNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        Map<String, String> sExpected = this.createFromArgsRef("hi", "bye");

        s.remove("goodbye");

        assertEquals(s.hasKey("goodbye"), false);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testRemoveEmpty() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye");
        Map<String, String> sExpected = this.createFromArgsRef();

        s.remove("hi");

        assertEquals(s.hasKey("hi"), false);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testRemoveNonEmptyMany() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello", "bonjour", "bonsoir");
        Map<String, String> sExpected = this.createFromArgsRef("hi", "bye",
                "goodbye", "hello");

        s.remove("bonjour");

        assertEquals(s.hasKey("bonjour"), false);
        assertEquals(s, sExpected);
    }

    /*
     * Tests removeAny method
     */
    @Test
    public final void testRemoveAny() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        int sizeBefore = s.size();
        s.removeAny();
        int sizeAfter = s.size();
        assertEquals(sizeAfter, sizeBefore - 1);
    }

    /*
     * Tests value method
     */
    @Test
    public final void testValue() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        assertEquals("bye", s.value("hi"));
        assertEquals("hello", s.value("goodbye"));
    }

    /*
     * Tests hasKey method
     */
    @Test
    public final void testHasKey1() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        assertEquals(s.hasKey("hi"), true);
    }

    @Test
    public final void testHasKey2() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        assertEquals(s.hasKey("goodbye"), true);
    }

    @Test
    public final void testHasKey3() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        assertEquals(s.hasKey("bye"), false);
    }

    /*
     * Tests size method
     */
    @Test
    public final void testSize1() {
        Map<String, String> s = this.createFromArgsTest("hi", "bye", "goodbye",
                "hello");
        int sizeTest = 2;
        assertEquals(sizeTest, s.size());
    }

    @Test
    public final void testSize2() {
        Map<String, String> s = this.createFromArgsTest();
        int sizeTest = 0;
        assertEquals(sizeTest, s.size());
    }

}
