import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void testCombinationFarmer() {
        String str1 = "farm";
        String str2 = "farmer";
        String sExpected = "farmer";
        int overlap = 4;
        StringReassembly.combination(str1, str2, overlap);

        assertEquals(sExpected, "farmer");
    }

    @Test
    public void testCombinationCrashing() {
        String str1 = "Crashin";
        String str2 = "ing";
        int overlap = 2;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Crashing", combine);
    }

    @Test
    public void testCombinationDublinshire() {
        String str1 = "Dublins";
        String str2 = "inshire";
        int overlap = 3;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Dublinshire", combine);
    }

    @Test
    public void testCombinationCellulose() {
        String str1 = "Cellul";
        String str2 = "lose";
        int overlap = 1;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Cellulose", combine);
    }

    @Test
    public void testCombinationLamborghini() {
        String str1 = "lambo";
        String str2 = "mborghini";
        String sExpected = "lamborghini";
        int overlap = 3;
        StringReassembly.combination(str1, str2, overlap);

        assertEquals(sExpected, "lamborghini");
    }

    /*
     * @Test public void testLinesFromInput() { SimpleReader input = new
     * SimpleReader1L("data/orders.txt"); String str1 = "farm"; String str2 =
     * "farmer"; String sExpected = "farmer"; int overlap = 4;
     * StringReassembly.combination(str1, str2, overlap);
     *
     * assertEquals(sExpected, "farmer"); }
     */

    @Test
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> set1 = new Set2<>();
        set1.add("farm");
        set1.add("cars");
        set1.add("classes");
        String str1 = "class";

        Set<String> sExpected = new Set2<>();
        sExpected.add("farm");
        sExpected.add("cars");
        sExpected.add("classes");

        StringReassembly.addToSetAvoidingSubstrings(set1, str1);

        assertEquals(sExpected, set1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> set1 = new Set2<>();
        set1.add("porsche");
        set1.add("lamborghini");
        set1.add("ota");
        String str = "toyota";
        Set<String> sExpected = new Set2<>();
        sExpected.add("porsche");
        sExpected.add("lamborghini");
        sExpected.add("toyota");
        StringReassembly.addToSetAvoidingSubstrings(set1, str);
        assertEquals(sExpected, set1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings3() {
        String str1 = "wheels";
        Set<String> set1 = new Set2<>();
        set1.add("garage");
        set1.add("house");

        Set<String> sExpected = new Set2<>();
        sExpected.add("garage");
        sExpected.add("house");
        sExpected.add("wheels");

        StringReassembly.addToSetAvoidingSubstrings(set1, str1);

        assertEquals(sExpected, set1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings4() {
        String str1 = "osu";
        Set<String> set1 = new Set2<>();
        set1.add("cse");
        set1.add("library");

        Set<String> sExpected = new Set2<>();
        sExpected.add("cse");
        sExpected.add("library");
        sExpected.add("osu");

        StringReassembly.addToSetAvoidingSubstrings(set1, str1);

        assertEquals(sExpected, set1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings5() {
        String str1 = "code";
        Set<String> set1 = new Set2<>();
        set1.add("vscode");
        set1.add("eclipse");

        Set<String> sExpected = new Set2<>();
        sExpected.add("vscode");
        sExpected.add("eclipse");

        StringReassembly.addToSetAvoidingSubstrings(set1, str1);

        assertEquals(sExpected, set1);
    }

    /*
     * tests of printWithLineSeparators
     */
    @Test
    public void testLinesFromInput1() {
        SimpleReader input = new SimpleReader1L("test.txt");

        Set<String> sExpected = new Set2<>();
        sExpected.add("I love");
        sExpected.add("cars");
        assertEquals(sExpected, StringReassembly.linesFromInput(input));
        input.close();

    }

    @Test
    public void testLinesFromInput2() {
        SimpleReader input = new SimpleReader1L("test1.txt");

        Set<String> sExpected = new Set2<>();
        sExpected.add("red");
        sExpected.add("blue");
        sExpected.add("flower");

        assertEquals(sExpected, StringReassembly.linesFromInput(input));
        input.close();

    }

    @Test
    public void testLinesFromInput3() {
        SimpleReader input = new SimpleReader1L("test2.txt");

        Set<String> sExpected = new Set2<>();
        sExpected.add("hello");
        sExpected.add("how are");
        sExpected.add("you");
        sExpected.add("whats up");

        assertEquals(sExpected, StringReassembly.linesFromInput(input));
        input.close();
    }

    @Test
    public void testLinesFromInput4() {
        SimpleReader input = new SimpleReader1L("test3.txt");

        Set<String> sExpected = new Set2<>();
        sExpected.add("iph");
        sExpected.add("one");
        sExpected.add("pl");
        sExpected.add("us");
        assertEquals(sExpected, StringReassembly.linesFromInput(input));
        input.close();

    }

    @Test
    public void testLinesFromInput5() {
        SimpleReader input = new SimpleReader1L("test7.txt");

        Set<String> sExpected = new Set2<>();
        sExpected.add("ferrucio lamborghini");
        sExpected.add("enzo ferrari");

        assertEquals(sExpected, StringReassembly.linesFromInput(input));
        input.close();

    }

    @Test
    public void testLinesFromInput6() {
        SimpleReader input = new SimpleReader1L("test9.txt");

        Set<String> sExpected = new Set2<>();
        sExpected.add("macbook");

        assertEquals(sExpected, StringReassembly.linesFromInput(input));
        input.close();

    }

    /*
     * tests of printWithLineSeparators
     */
    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L("test4Output.txt");
        SimpleReader in = new SimpleReader1L("test4.txt");
        SimpleReader newIn = new SimpleReader1L("test4Output.txt");

        String orig = "hello ~how are ~you whats up";
        String expected = "hello " + "\n" + "how are " + "\n" + "you whats up";
        StringReassembly.printWithLineSeparators(orig, out);
        String test = newIn.nextLine();
        String test2 = newIn.nextLine();
        String test3 = newIn.nextLine();
        in.close();
        newIn.close();
        out.close();
        assertEquals(expected, test + "\n" + test2 + "\n" + test3);
    }

    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L("test5Output.txt");
        SimpleReader in = new SimpleReader1L("test5.txt");
        SimpleReader newIn = new SimpleReader1L("test5Output.txt");

        String orig = "cars porsche~ lam~borghini";
        String sExpected = "cars porsche" + "\n" + " lam" + "\n" + "borghini";
        StringReassembly.printWithLineSeparators(orig, out);
        String test = newIn.nextLine();
        String test2 = newIn.nextLine();
        String test3 = newIn.nextLine();
        in.close();
        newIn.close();
        out.close();
        assertEquals(sExpected, test + "\n" + test2 + "\n" + test3);
    }

    @Test
    public void testPrintWithLineSeparators3() {
        SimpleWriter out = new SimpleWriter1L("test6Output.txt");
        SimpleReader in = new SimpleReader1L("test6.txt");
        SimpleReader newIn = new SimpleReader1L("test6Output.txt");

        String orig = "cod~ing this project ~testing";
        String sExpected = "cod" + "\n" + "ing this project " + "\n"
                + "testing";
        StringReassembly.printWithLineSeparators(orig, out);
        String test = newIn.nextLine();
        String test2 = newIn.nextLine();
        String test3 = newIn.nextLine();

        in.close();
        newIn.close();
        out.close();
        assertEquals(sExpected, test + "\n" + test2 + "\n" + test3);

    }

    @Test
    public void testPrintWithLineSeparators4() {
        SimpleWriter out = new SimpleWriter1L("test8Output.txt");
        SimpleReader in = new SimpleReader1L("test8.txt");
        SimpleReader newIn = new SimpleReader1L("test8Output.txt");
        String orig = in.nextLine();
        String sExpected = "hood" + "\n" + "\n" + "hoodie  " + "\n"
                + "school old school" + "\n" + " glasses " + "\n" + "glass";
        StringReassembly.printWithLineSeparators(orig, out);
        String test = newIn.nextLine();
        String test2 = newIn.nextLine();
        String test3 = newIn.nextLine();
        String test4 = newIn.nextLine();
        String test5 = newIn.nextLine();
        String test6 = newIn.nextLine();

        in.close();
        newIn.close();
        out.close();
        assertEquals(sExpected, test + "\n" + test2 + "\n" + test3 + "\n"
                + test4 + "\n" + test5 + "\n" + test6);
    }

}
