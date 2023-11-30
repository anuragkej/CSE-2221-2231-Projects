import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Anurag Kejriwal & Vibhav Kaluvala
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test for first constructor
     */
    @Test
    public final void testConstructorOne() {
        NaturalNumber nTest = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nTest, nExpected);
    }

    /*
     * Test for second constructor with 0 as input
     */
    @Test
    public final void testConstructorTwoZero() {
        int i = 0;
        NaturalNumber nTest = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);

        assertEquals(nTest, nExpected);
    }

    /*
     * Test for second constructor with positive integer
     */
    @Test
    public final void testConstructorTwoPos() {
        int i = 5;
        NaturalNumber nTest = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);

        assertEquals(nTest, nExpected);
    }

    /*
     * Test for second constructor with MAX INT value
     */
    @Test
    public final void testConstructorTwoMaxInt() {
        int i = Integer.MAX_VALUE;
        NaturalNumber nTest = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);

        assertEquals(nTest, nExpected);
    }

    /*
     * Test for third constructor with String: "0"
     */
    @Test
    public final void testConstructorThreeZero() {
        String s = "0";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);

        assertEquals(nTest, nExpected);
    }

    /*
     * Test for third constructor with String of large number
     */
    @Test
    public final void testConstructorThreeBigNum() {
        String s = "32746";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);

        assertEquals(nTest, nExpected);
    }

    /*
     * Test for third constructor with String of small number
     */
    @Test
    public final void testConstructorThreeSmallNum() {
        String s = "4";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);

        assertEquals(nTest, nExpected);
    }

    /*
     * Test for fourth constructor with naturalNumber 0
     */
    @Test
    public final void testConstructorFourZero() {
        String n = "0";
        NaturalNumber test = this.constructorTest(n);
        NaturalNumber test2 = this.constructorRef(n);

        NaturalNumber nTest = this.constructorTest(test);
        NaturalNumber nExpected = this.constructorRef(test2);

        assertEquals(test, test2);
        assertEquals(nTest, nExpected);
    }

    /*
     * Test for fourth constructor with a large naturalNumber
     */
    @Test
    public final void testConstructorFourBigNum() {
        String n = "278436";
        NaturalNumber test = this.constructorTest(n);
        NaturalNumber test2 = this.constructorRef(n);

        NaturalNumber nTest = this.constructorTest(test);
        NaturalNumber nExpected = this.constructorRef(test2);

        assertEquals(test, test2);
        assertEquals(nTest, nExpected);
    }

    /*
     * Test for fourth constructor with a small naturalNumber
     */
    @Test
    public final void testConstructorFourSmallNum() {
        String n = "3";
        NaturalNumber test = this.constructorTest(n);
        NaturalNumber test2 = this.constructorRef(n);

        NaturalNumber nTest = this.constructorTest(test);
        NaturalNumber nExpected = this.constructorRef(test2);

        assertEquals(test, test2);
        assertEquals(nTest, nExpected);
    }

    /*
     * Test multiplyBy10 with 0
     */
    @Test
    public final void testMultiplyBy10Zero() {

        int k = 6;
        NaturalNumber nTest = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(k);

        nTest.multiplyBy10(k);

        assertEquals(nExpected, nTest);
    }

    /*
     * Test multiplyBy10 with large number
     */
    @Test
    public final void testMultiplyBy10BigNum() {
        String s = "435435";
        int k = 6;
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef("4354356");

        nTest.multiplyBy10(k);
        assertEquals(nExpected, nTest);
    }

    /*
     * Test multiplyBy10 with small number
     */
    @Test
    public final void testMultiplyBy10SmallNum() {
        String s = "43";
        int k = 6;
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef("436");

        nTest.multiplyBy10(k);
        assertEquals(nExpected, nTest);
    }

    /*
     * Test multiplyBy10 with MAX INT
     */
    @Test
    public final void testMultiplyBy10MaxInt() {
        String s = "983427823947892347";
        int k = 6;
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef("9834278239478923476");

        nTest.multiplyBy10(k);
        assertEquals(nExpected, nTest);
    }

    /*
     * Test divideBy10 with 0
     */
    @Test
    public final void testDivideBy10Zero() {
        NaturalNumber nTest = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        int remainder = nTest.divideBy10();

        assertEquals(nTest, nExpected);
        assertEquals(remainder, 0);
    }

    /*
     * Test divideBy10 with large number
     */
    @Test
    public final void testDivideBy10BigNum() {
        String s = "435435";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef("43543");
        int remainder = nTest.divideBy10();

        assertEquals(nTest, nExpected);
        assertEquals(remainder, 5);
    }

    /*
     * Test divideBy10 with small number
     */
    @Test
    public final void testDivideBy10SmallNum() {
        String s = "43";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef("4");
        int remainder = nTest.divideBy10();

        assertEquals(nTest, nExpected);
        assertEquals(remainder, 3);
    }

    /*
     * Test divideBy10 with MAX INT
     */
    @Test
    public final void testDivideBy10MaxIntPlus() {
        String s = "983427823947892347";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef("98342782394789234");
        int remainder = nTest.divideBy10();

        assertEquals(nTest, nExpected);
        assertEquals(remainder, 7);
    }

    /*
     * Test isZero with true
     */
    @Test
    public final void testIsZeroTrue() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        boolean x = n.isZero();

        assertEquals(x, true);
        assertEquals(n, nExpected);
    }

    /*
     * Test isZero with false
     */
    @Test
    public final void testIsZeroFalse() {
        String s = "5534534";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        boolean x = n.isZero();

        assertEquals(x, false);
        assertEquals(n, nExpected);
    }

}
