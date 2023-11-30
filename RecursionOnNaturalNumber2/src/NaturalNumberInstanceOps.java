import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Extension of {@code NaturalNumber2} with secondary operations implemented as
 * instance methods: add, subtract, and power.
 *
 * @author Put your name here
 *
 */
public final class NaturalNumberInstanceOps extends NaturalNumber2 {

    /**
     * No-argument constructor.
     */
    public NaturalNumberInstanceOps() {
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumberInstanceOps(int i) {
        super(i);
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumberInstanceOps(String s) {
        super(s);
    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumberInstanceOps(NaturalNumber n) {
        super(n);
    }

    @Override
    public void add(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        /**
         * @decreases n
         */
        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();
        if (!n.isZero()) {
            this.add(n);
        }
        thisLowDigit += nLowDigit;
        if (thisLowDigit >= RADIX) {
            thisLowDigit -= RADIX;
            this.increment();
        }
        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);
    }

    @Override
    //Subtracts n from this.

    public void subtract(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        assert this.compareTo(n) >= 0 : "Violation of: this >= n";

        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();

        //run-time error if test case has the last digit on n2 == 9

        if (!n.isZero()) {
            this.subtract(n);
        }
        if (thisLowDigit >= nLowDigit) {
            thisLowDigit -= nLowDigit;
        } else if (thisLowDigit == 0) {
            if (!this.isZero()) {
                thisLowDigit += 9;
                this.clear();
            }
        } else if (nLowDigit == 9) {
            thisLowDigit = 0;
        }

        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);

    }

    @Override
    public void power(int p) {
        assert p >= 0 : "Violation of: p >= 0";
        NaturalNumber one = new NaturalNumber2(1);
        int p1 = p;

        if (p % 2 == 0 && p > 1) {
            p1 /= 2;
            this.power(p1 / 2);
            this.power(p1);
        }

        //else if (p % 2 == 1) {
        //      this.power(p);
        //  }

        else if (p1 == 0) {
            this.copyFrom(one);
        }

    }

}
