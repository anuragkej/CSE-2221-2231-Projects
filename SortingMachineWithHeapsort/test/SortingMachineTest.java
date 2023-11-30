import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Anurag Kejriwal & Vibhav Kaluvala
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /*
     * Tests Add kernel method.
     */
    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car",
                "lambo", "porsche");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "car", "lambo", "porsche", "ferrari");
        m.add("ferrari");
        assertEquals(mExpected, m);

    }

    @Test
    public final void testAddNonEmptyMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car",
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "car", "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla", "ferrari");
        m.add("ferrari");
        assertEquals(mExpected, m);

    }

    /*
     * Tests changeToExtractionMode method.
     */
    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        m.changeToExtractionMode();

        assertEquals(mExpected, m);

    }

    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "car");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeNonEmptyMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car",
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "car", "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    /*
     * Tests removeFirst kernel method.
     */
    @Test
    public final void testRemoveFirstToEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "car");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String temp = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals("car", temp);

    }

    @Test
    public final void testRemoveFirstNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "car",
                "lambo", "porsche");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "lambo", "porsche");

        String temp = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals("car", temp);
    }

    @Test
    public final void testRemoveFirstNonEmptyMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "car",
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");

        String temp = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals("car", temp);

    }

    /*
     * Tests isInInsertionMode method.
     */
    @Test
    public final void testIsInInsertionModeTrueEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        boolean check = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(check, true);

    }

    @Test
    public final void testIsInInsertionModeTrueNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car",
                "lambo", "porsche");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "car", "lambo", "porsche");

        boolean check = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(check, true);

    }

    @Test
    public final void testIsInInsertionModeTrueNonEmptyMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car",
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "car", "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");

        boolean check = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(check, true);

    }

    @Test
    public final void testIsInInsertionModeFalseEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        boolean check = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(check, false);

    }

    @Test
    public final void testIsInInsertionModeFalseNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "car",
                "lambo", "porsche");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "car", "lambo", "porsche");

        boolean check = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(check, false);

    }

    @Test
    public final void testIsInInsertionModeFalseNonEmptyMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "car",
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "car", "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");

        boolean check = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(check, false);
    }

    /*
     * Tests size kernel method.
     */
    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(0, m.size());

    }

    @Test
    public final void testSizeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "car");

        assertEquals(mExpected, m);
        assertEquals(1, m.size());

    }

    @Test
    public final void testSizeNonEmptyMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car",
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "car", "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");

        assertEquals(mExpected, m);
        assertEquals(8, m.size());

    }

    /*
     * Tests order method.
     */
    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(ORDER, m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrderNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "car",
                "lambo");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "car", "lambo");

        assertEquals(ORDER, m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrderNonEmptyMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "car",
                "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "car", "lambo", "porsche", "honda", "toyota", "ford", "subaru",
                "tesla");

        assertEquals(ORDER, m.order());
        assertEquals(mExpected, m);

    }

}
