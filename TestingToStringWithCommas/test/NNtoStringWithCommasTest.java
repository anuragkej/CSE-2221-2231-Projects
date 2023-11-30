import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    @Test

    public void testToStringWithCommas50000() {
        NaturalNumber n = new NaturalNumber2("50000");
        String test1 = "50,000";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas432() {
        NaturalNumber n = new NaturalNumber2("432");
        String test1 = "432";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas9832467896983468326() {
        NaturalNumber n = new NaturalNumber2("9832467896983468326");
        String test1 = "9,832,467,896,983,468,326";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas0() {
        NaturalNumber n = new NaturalNumber2("0");
        String test1 = "0";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas35872346() {
        NaturalNumber n = new NaturalNumber2("35872346");
        String test1 = "35,872,346";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas41033() {
        NaturalNumber n = new NaturalNumber2("41033");
        String test1 = "41,033";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas111() {
        NaturalNumber n = new NaturalNumber2("111");
        String test1 = "111";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas73() {
        NaturalNumber n = new NaturalNumber2("73");
        String test1 = "73";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);

    }

    @Test

    public void testToStringWithCommas1636333() {
        NaturalNumber n = new NaturalNumber2("1636333");
        String test1 = "1,636,333";
        String result = redirectToMethodUnderTest(n);

        assertEquals(test1, result);
        assertEquals(n, test1);

    }

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

}
