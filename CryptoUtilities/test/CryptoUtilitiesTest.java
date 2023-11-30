import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Anurag Kejriwal
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_15_30() {
        NaturalNumber n = new NaturalNumber2(15);
        NaturalNumber nExpected = new NaturalNumber2(15);
        NaturalNumber m = new NaturalNumber2(30);
        NaturalNumber mExpected = new NaturalNumber2(0);

        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test

    public void testReduceToGCD_333_3() {
        NaturalNumber n = new NaturalNumber2(333);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(3);
        NaturalNumber mExpected = new NaturalNumber2(0);

        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_49_27() {
        NaturalNumber n = new NaturalNumber2(49);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(27);
        NaturalNumber mExpected = new NaturalNumber2(0);

        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_64_256() {
        NaturalNumber n = new NaturalNumber2(64);
        NaturalNumber nExpected = new NaturalNumber2(64);
        NaturalNumber m = new NaturalNumber2(256);
        NaturalNumber mExpected = new NaturalNumber2(0);

        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_45() {
        NaturalNumber n = new NaturalNumber2(45);
        NaturalNumber nExpected = new NaturalNumber2(45);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_36() {
        NaturalNumber n = new NaturalNumber2(36);
        NaturalNumber nExpected = new NaturalNumber2(36);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_7325897532() {
        NaturalNumber n = new NaturalNumber2("7325897532");
        NaturalNumber nExpected = new NaturalNumber2("7325897532");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_151() {
        NaturalNumber n = new NaturalNumber2(151);
        NaturalNumber nExpected = new NaturalNumber2(151);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_903() {
        NaturalNumber n = new NaturalNumber2(903);
        NaturalNumber nExpected = new NaturalNumber2(903);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_642() {
        NaturalNumber n = new NaturalNumber2(642);
        NaturalNumber nExpected = new NaturalNumber2(642);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);

    }

    @Test
    public void testIsEven_843975897453() {
        NaturalNumber n = new NaturalNumber2("843975897453");
        NaturalNumber nExpected = new NaturalNumber2("843975897453");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);

    }

    @Test
    public void testIsEven_MAXMinusOne() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE - 1);
        NaturalNumber nExpected = new NaturalNumber2(Integer.MAX_VALUE - 1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_MAX() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber nExpected = new NaturalNumber2(0);

        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber pExpected = new NaturalNumber2(1);

        NaturalNumber m = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber mExpected = new NaturalNumber2(Integer.MAX_VALUE);

        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_115_0_8() {
        NaturalNumber n = new NaturalNumber2(115);
        NaturalNumber nExpected = new NaturalNumber2(1);

        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);

        NaturalNumber m = new NaturalNumber2(8);
        NaturalNumber mExpected = new NaturalNumber2(8);

        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_11_11_11() {
        NaturalNumber n = new NaturalNumber2(11);
        NaturalNumber nExpected = new NaturalNumber2(0);

        NaturalNumber p = new NaturalNumber2(11);
        NaturalNumber pExpected = new NaturalNumber2(11);

        NaturalNumber m = new NaturalNumber2(11);
        NaturalNumber mExpected = new NaturalNumber2(11);

        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of CompositeWitness
     *
     */
    @Test
    public void testCompositeWitness_6_3() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(6);
        NaturalNumber wExpected = new NaturalNumber2(3);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, result);

    }

    @Test
    public void testCompositeWitness_7_2() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(7);
        NaturalNumber wExpected = new NaturalNumber2(2);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    @Test
    public void testCompositeWitness_80_20() {
        NaturalNumber n = new NaturalNumber2(80);
        NaturalNumber w = new NaturalNumber2(20);
        NaturalNumber nExpected = new NaturalNumber2(80);
        NaturalNumber wExpected = new NaturalNumber2(20);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, result);
    }

    @Test
    public void testCompositeWitness_219_34() {
        NaturalNumber n = new NaturalNumber2(219);
        NaturalNumber w = new NaturalNumber2(34);
        NaturalNumber nExpected = new NaturalNumber2(219);
        NaturalNumber wExpected = new NaturalNumber2(34);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, result);
    }

    @Test
    public void testCompositeWitness_56_3() {
        NaturalNumber n = new NaturalNumber2(56);
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(56);
        NaturalNumber wExpected = new NaturalNumber2(3);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, result);
    }

    @Test
    public void testCompositeWitness_37_2() {
        NaturalNumber n = new NaturalNumber2(37);
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(37);
        NaturalNumber wExpected = new NaturalNumber2(2);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    @Test
    public void testCompositeWitness_217_37() {
        NaturalNumber n = new NaturalNumber2(217);
        NaturalNumber w = new NaturalNumber2(37);
        NaturalNumber nExpected = new NaturalNumber2(217);
        NaturalNumber wExpected = new NaturalNumber2(37);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    @Test
    public void testCompositeWitness_MAX_37() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber w = new NaturalNumber2(37);
        NaturalNumber nExpected = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber wExpected = new NaturalNumber2(37);

        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    /*
     *
     * Tests of isPrime1
     */

    @Test
    public void testIsPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);

        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);

        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime1_97() {
        NaturalNumber n = new NaturalNumber2(97);

        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime1_121() {
        NaturalNumber n = new NaturalNumber2(121);

        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(false, result);
    }

    @Test
    public void testIsPrime1_MAX() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);

        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(true, result);

    }

    @Test
    public void testIsPrime1_66() {
        NaturalNumber n = new NaturalNumber2(66);

        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(false, result);

    }

    @Test
    public void testIsPrime1_11112() {
        NaturalNumber n = new NaturalNumber2(11112);

        boolean result = CryptoUtilities.isPrime1(n);
        assertEquals(false, result);

    }
    /*
     *
     * Tests of isPrime2
     *
     */

    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);

        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);

        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_16() {
        NaturalNumber n = new NaturalNumber2(16);

        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(false, result);
    }

    @Test
    public void testIsPrime2_121() {
        NaturalNumber n = new NaturalNumber2(121);

        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(false, result);
    }

    @Test
    public void testIsPrime2_MAX() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);

        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }
    /*
     *
     * Tests of generateNextLikelyToPrime
     */

    @Test
    public void testGenerateNextLikelyToPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

    @Test
    public void testGenerateNextLikelyToPrime_8() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

    @Test
    public void testGenerateNextLikelyToPrime_14() {
        NaturalNumber n = new NaturalNumber2(14);
        NaturalNumber nExpected = new NaturalNumber2(17);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyToPrime_69() {
        NaturalNumber n = new NaturalNumber2(69);
        NaturalNumber nExpected = new NaturalNumber2(71);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyToPrime_MAXMinusOne() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE - 1);
        NaturalNumber nExpected = new NaturalNumber2("2147483647");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

    @Test
    public void testGenerateNextLikelyToPrime_MAX() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber nExpected = new NaturalNumber2(Integer.MAX_VALUE);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

    @Test
    public void testGenerateNextLikelyToPrime_49857834758973() {
        NaturalNumber n = new NaturalNumber2("49857834758973");
        NaturalNumber nExpected = new NaturalNumber2("49857834758999");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);

    }

}
