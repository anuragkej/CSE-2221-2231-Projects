import static org.junit.Assert.assertEquals;

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
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    @Test
    public final void testConstructor1() {

        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddEmpty() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("Hi", "Hello");

        s.add("Hi");
        s.add("Hello");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddNonEmpty1() {
        Set<String> s = this.createFromArgsTest("Hello", "Bye");
        Set<String> sExpected = this.createFromArgsRef("Hello", "Bye", "Car");
        s.add("Car");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddNonEmpty2() {
        Set<String> s = this.createFromArgsTest("Hello", "Bye", "Car");
        Set<String> sExpected = this.createFromArgsRef("Hello", "Bye", "Car",
                "lamborghini", "porsche", "ferrari");

        s.add("lamborghini");
        s.add("porsche");
        s.add("ferrari");

        assertEquals(sExpected, s);

    }

    @Test
    public final void testAddNonEmptyMany() {
        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan", "jeep", "suv", "audi", "bmw", "mercedes");
        Set<String> sExpected = this.createFromArgsRef("Car", "lambo",
                "porsche", "sedan", "jeep", "suv", "audi", "bmw", "mercedes",
                "toyota");

        s.add("toyota");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveEmpty() {

        Set<String> s = this.createFromArgsTest("Car");
        Set<String> sExpected = this.createFromArgsRef();
        String strExpected = "Car";

        String str = s.remove("Car");

        assertEquals(sExpected, s);
        assertEquals(strExpected, str);

    }

    @Test
    public final void testRemoveNonEmpty1() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan");
        Set<String> sExpected = this.createFromArgsRef("Car", "lambo",
                "porsche");
        String strExpected = "sedan";

        String str = s.remove("sedan");

        assertEquals(sExpected, s);
        assertEquals(strExpected, str);

    }

    @Test
    public final void testRemoveNonEmpty2() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan");
        Set<String> sExpected = this.createFromArgsRef("Car", "porsche",
                "sedan");
        String strExpected = "lambo";

        String str = s.remove("lambo");

        assertEquals(sExpected, s);
        assertEquals(strExpected, str);

    }

    @Test
    public final void testRemoveNonEmpty3() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan");
        Set<String> sExpected = this.createFromArgsRef("lambo", "porsche",
                "sedan");
        String strExpected = "Car";

        String str = s.remove("Car");

        assertEquals(sExpected, s);
        assertEquals(strExpected, str);

    }

    @Test
    public final void testRemoveNonEmptyMany() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan", "jeep", "suv", "audi", "bmw", "mercedes");
        Set<String> sExpected = this.createFromArgsRef("Car", "lambo",
                "porsche", "sedan", "jeep", "suv", "audi", "mercedes");
        String strExpected = "bmw";

        String str = s.remove("bmw");

        assertEquals(sExpected, s);
        assertEquals(strExpected, str);

    }

    @Test
    public final void testRemoveAnyToEmpty() {

        Set<String> s = this.createFromArgsTest("Car");
        int sizeBefore = s.size();

        s.removeAny();

        int sizeAfter = s.size();

        assertEquals(sizeAfter, sizeBefore - 1);

    }

    @Test
    public final void testRemoveAnyNonEmpty() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan");
        int sizeBefore = s.size();
        s.removeAny();
        int sizeAfter = s.size();

        assertEquals(sizeAfter, sizeBefore - 1);

    }

    @Test
    public final void testContainsEmpty() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        String str = "Car";
        boolean x = s.contains(str);

        assertEquals(false, x);
        assertEquals(sExpected, s);

    }

    @Test
    public final void testContainsNonEmptyTrue() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan");
        Set<String> sExpected = this.createFromArgsRef("Car", "lambo",
                "porsche", "sedan");
        String str = "Car";
        boolean x = s.contains(str);

        assertEquals(true, x);
        assertEquals(sExpected, s);

    }

    @Test
    public final void testContainsNonEmptyFalse() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan");
        Set<String> sExpected = this.createFromArgsRef("Car", "lambo",
                "porsche", "sedan");
        String str = "hello";
        boolean x = s.contains(str);

        assertEquals(false, x);
        assertEquals(sExpected, s);

    }

    @Test
    public final void testSizeEmpty() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        int size = s.size();

        assertEquals(0, size);
        assertEquals(sExpected, s);

    }

    @Test
    public final void testSizeNonEmpty() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan");
        Set<String> sExpected = this.createFromArgsRef("Car", "lambo",
                "porsche", "sedan");

        int size = s.size();

        assertEquals(4, size);
        assertEquals(sExpected, s);

    }

    @Test
    public final void testSizeNonEmptyMany() {

        Set<String> s = this.createFromArgsTest("Car", "lambo", "porsche",
                "sedan", "jeep", "suv", "audi", "bmw", "mercedes");
        Set<String> sExpected = this.createFromArgsRef("Car", "lambo",
                "porsche", "sedan", "jeep", "suv", "audi", "bmw", "mercedes");

        int size = s.size();

        assertEquals(9, size);
        assertEquals(sExpected, s);

    }

}
