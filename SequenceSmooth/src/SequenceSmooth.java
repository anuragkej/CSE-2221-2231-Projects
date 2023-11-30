import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @requires |s1| >= 1
     * @returns Sequence<Integer> s2
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

//        if (s1.length() == 1) {
//            tempSeq.clear();
//        } else {
//            for (int i = 0; i < s1.length() - 1; i++) {
//                int entry1 = s1.entry(i);
//                int entry2 = s1.entry(i + 1);
//                int avg = (entry1 + entry2) / 2;
//                tempSeq.add(i, avg);
//            }
//        }
//        return tempSeq;
//    }
        Sequence<Integer> tempSeq = new Sequence1L<Integer>();

        if (tempSeq.length() < s1.length() - 1) {
            int entry1 = s1.remove(0);
            tempSeq = smooth(s1);
            int entry2 = s1.remove(0);
            int avg = (entry1 + entry2) / 2;
            if (tempSeq.length() > 0) {
                tempSeq.add(tempSeq.length() - 1, avg);
            } else {
                tempSeq.add(0, avg);
            }
        }
        return tempSeq;
    }

}