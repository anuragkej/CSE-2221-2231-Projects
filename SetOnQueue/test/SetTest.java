import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Anurag Kejriwal & Vibhav Kaluvala
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Tests add
     */
    @Test
    public final void testAddNonEmpty1() {

        Set<String> s = this.createFromArgsTest("hi", "hello");
        Set<String> sExpected = this.createFromArgsRef("goodbye", "hi",
                "hello");

        s.add("goodbye");

        assertEquals(s, sExpected);
    }

    /**
     * Tests remove
     */
    @Test
    public final void testRemoveNonEmpty() {

        Set<String> s = this.createFromArgsTest("goodbye", "hi", "hello");
        Set<String> sExpected = this.createFromArgsRef("hi", "hello");

        s.remove("goodbye");

        assertEquals(s, sExpected);
    }

    /**
     * Tests removeAny
     */
    @Test
    public final void testRemoveAny() {

        Set<String> s = this.createFromArgsTest("goodbye", "hi", "hello");
        Set<String> sExpected = this.createFromArgsRef("goodbye", "hi",
                "hello");

        String removed = s.removeAny();
        assertTrue(sExpected.contains(removed)
                && s.size() == sExpected.size() - 1);

    }

    /**
     * Tests Contains
     */
    @Test
    public final void testContains() {

        Set<String> s = this.createFromArgsTest("goodbye", "hi", "hello");
        Set<String> sExpected = this.createFromArgsRef("goodbye", "hi",
                "hello");

        String removed = "goodbye";
        assertTrue(sExpected.contains(removed) && s.contains(removed));

    }

    /**
     * Tests Size
     */
    @Test
    public final void testSize() {

        Set<String> s = this.createFromArgsTest("goodbye", "hi", "hello");
        Set<String> sExpected = this.createFromArgsRef("goodbye", "hi",
                "hello");

        int size1 = s.size();
        int size2 = sExpected.size();
        assertTrue(size1 == size2);
    }
}
