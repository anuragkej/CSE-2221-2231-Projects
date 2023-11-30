
import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Program that counts word occurrences in a given input file and outputs an
 * HTML document with a TagCloud of as many words in the input file as the user
 * enters in alphabetical order.
 *
 * @author Anurag Kejriwal & Vibhav Kaluvala
 */
public class TagCloudGenerator {

    /**
     * private static String variable to be used to check possible separators.
     */
    private static String separators = "!.,?/:;@-()[]\t\n\r ";

    /**
     * private static Set variable to be used to check possible separators.
     */
    private static Set<Character> separatorSet = new Set1L<>();

    /**
     * private static int variable to be used to denote the max amount of
     * appearances of a word in the input file.
     */
    private static int maxCount = 0;

    /**
     * private static int variable to be used to denote the min amount of
     * appearances of a word in the input file.
     */
    private static int minCount = Integer.MAX_VALUE;

    /**
     * private static final int variable to be used to denote the largest font
     * size used in the output.
     */
    private static final int FONT_MAX = 37;

    /**
     * {@code ADD_FONT} private static final int variable to be used to greaten
     * the font size.
     */
    private static final int ADD_FONT = 9;

    /**
     * Correctly orders a Map pair based on alphabetical order.
     */
    private static class AlphabeticalOrder
            implements Comparator<Map.Pair<String, Integer>> {
        /*
         * @param p1 first Map Pair of String, Integer to be compared
         *
         * @param p2 second Map Pair of String, Integer to be compared
         */
        @Override
        public int compare(Map.Pair<String, Integer> p1,
                Map.Pair<String, Integer> p2) {

            int result = p1.key().compareTo(p2.key());
            return result;
        }
    }

    /**
     * Correctly orders a Map pair in decreasing order.
     */
    private static class WordCounter
            implements Comparator<Map.Pair<String, Integer>> {
        /*
         * @param p1 first Map Pair of String, Integer to be compared
         *
         * @param p2 second Map Pair of String, Integer to be compared
         */
        @Override
        public int compare(Map.Pair<String, Integer> p1,
                Map.Pair<String, Integer> p2) {

            int result = p2.value().compareTo(p1.value());
            return result;
        }
    }

    /**
     *
     * Takes a string as input and tries to find the first word that starts at
     * the position given as a parameter. Then, the word is determined by if it
     * determines there is a separator given in the Set parameter known as
     * separators. Then, once the method finds a valid separator, it determines
     * the word by using the substring function from the starting position to up
     * until the separator index. If the position is at a separator, only the
     * separator is returned. If there isn't a separator, then the word is the
     * whole string text. Finally, if none of the above conditions are true, the
     * word starting at the original index to the ending position.
     *
     * @param separatorSet
     *            A set of separators
     * @param text
     *            String to be read
     * @param position
     *            The starting position to check in the method
     * @return word, or separator if the position starts at one
     * @requires Text isn't empty. Also, separators is not empty and that
     *           position is less than the length of text
     */

    private static String nextWordOrSeparator(Set<Character> separatorSet,
            String text, int position) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int endPosition = -1;
        String word = " ";

        //reads text trying to find the first occurrence of separator and
        //establishes endPosition

        for (int i = position; i < text.length(); i++) {
            if (separatorSet.contains(text.charAt(i)) && endPosition < 0) {
                endPosition = i;
            }
        }

        if (endPosition == position) {
            word = text.substring(position, endPosition + 1);
        } else if (endPosition == -1) {
            word = text.substring(position);
        } else {
            word = text.substring(position, endPosition);
        }
        return word;

    }

    /**
     * Reads and processes input file of type SimpleReader and assigns the word
     * to a key and its count to a value in a Map which is returned by the
     * method.
     *
     * @param fileIn
     *            the input text file of type SimpleReader
     * @return wordsAndCount the Map which stores the words and the number of
     *         times they appear in the input file
     * @ensures fileIn words = Map's words and number of times they appear
     */
    private static Map<String, Integer> readMap(SimpleReader fileIn) {

        for (int i = 0; i < separators.length(); i++) {
            separatorSet.add(separators.charAt(i));
        }
        Map<String, Integer> wordsAndCount = new Map1L<String, Integer>();

        int position = 0;

        while (!fileIn.atEOS()) {
            String line = fileIn.nextLine();
            line = line.toLowerCase();
            position = 0;

            while (position < line.length()) {
                String word = nextWordOrSeparator(separatorSet, line, position);

                if (!separatorSet.contains(word.charAt(0))) {

                    if (!wordsAndCount.hasKey(word)) {
                        wordsAndCount.add(word, 1);
                    } else {
                        int wordValue = wordsAndCount.value(word);
                        wordValue++;
                        wordsAndCount.replaceValue(word, wordValue);
                    }
                }
                position += word.length();
            }
        }
        return wordsAndCount;
    }

    /**
     * Sorts through the Map using the comparator.
     *
     * @param wordsAndCount
     *            the Map containing all the words and its occurrences
     * @param comparator
     *            the Comparator that helps to compare words alphabetically
     * @param wordCount
     *            the number of words in tag cloud defined by user
     * @updates wordsAndCount
     * @ensures Map of words is in order based on the comparator and returns
     *          SortingMachine variable.
     * @return SortingMachine with sorted elements of Map pairs that consist of
     *         String, Integers.
     *
     */

    private static SortingMachine<Map.Pair<String, Integer>> sortWord(
            Map<String, Integer> wordsAndCount,
            Comparator<Map.Pair<String, Integer>> comparator, int wordCount) {

        SortingMachine<Map.Pair<String, Integer>> sort = new SortingMachine1L<>(
                comparator);
        Map<String, Integer> temp = wordsAndCount.newInstance();

        int size = wordsAndCount.size();
        for (int i = 0; i < size; i++) {
            Map.Pair<String, Integer> pair = wordsAndCount.removeAny();
            if (pair.value() > maxCount) {
                maxCount = pair.value();
            } else if (pair.value() < minCount) {
                minCount = pair.value();
            }
            sort.add(pair);
            temp.add(pair.key(), pair.value());
        }
        wordsAndCount.transferFrom(temp);
        return sort;
    }

    /**
     * Creates font sizes for each word and outputs the word and size into the
     * output file.
     *
     * @param out
     *            outputs to the file
     * @param decreaseOrder
     *            the SortingMachine that sorted the elements from the text file
     *            in decreasing order of values
     * @param sortedOrder
     *            the SortingMachine that sorted the elements from the text file
     *            in alphabetical order
     * @param numWords
     *            the number of words supposed to be in the tag cloud provided
     *            by the user
     * @clears decreaseOrder, sortedOrder
     * @ensures sortedOrder will be outputted with proper font sizes increasing
     *          with the number of occurrences of the word
     *
     */
    private static void createFontSize(SimpleWriter out,
            SortingMachine<Map.Pair<String, Integer>> decreaseOrder,
            SortingMachine<Map.Pair<String, Integer>> sortedOrder,
            int numWords) {

        decreaseOrder.changeToExtractionMode();
        int size = decreaseOrder.size();
        for (int i = 0; i < size && i < numWords; i++) {
            Map.Pair<String, Integer> pair = decreaseOrder.removeFirst();
            sortedOrder.add(pair);
        }

        int sortedSize = sortedOrder.size();
        sortedOrder.changeToExtractionMode();

        for (int i = 0; i < sortedSize; i++) {
            int font = 0;
            Map.Pair<String, Integer> pair = sortedOrder.removeFirst();
            int val = pair.value();
            if (val > minCount) {
                font = (FONT_MAX * (val - minCount) / (maxCount - minCount))
                        + ADD_FONT;
            }
            out.println("<span style=\"cursor:default\" class=\"f"
                    + Integer.toString(font) + "\" title=\"count: "
                    + pair.value() + "\">" + pair.key() + "</span>\n");
        }
    }

    /**
     * Main method.
     *
     * Creates HTML page that pulls from a Map of words and its number of
     * occurrences and generates a TagCloud.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        SimpleReader userInput = new SimpleReader1L();
        SimpleWriter userOutput = new SimpleWriter1L();
        userOutput.print("Enter the input file name: ");
        String input = userInput.nextLine();
        userOutput.print("Enter the output file name: ");
        String output = userInput.nextLine();

        userOutput.print(
                "Enter the number of words (as an integer) to be included "
                        + "in the generated tag cloud: ");
        int n = userInput.nextInteger();

        SimpleReader in = new SimpleReader1L(input);
        SimpleWriter out = new SimpleWriter1L(output);

        Map<String, Integer> wordsAndCount = readMap(in);
        Comparator<Map.Pair<String, Integer>> wordCounter = new WordCounter();
        SortingMachine<Map.Pair<String, Integer>> decreaseOrder = sortWord(
                wordsAndCount, wordCounter, n);

        Comparator<Map.Pair<String, Integer>> alphabet = new AlphabeticalOrder();
        SortingMachine<Map.Pair<String, Integer>> sortedOrder = new SortingMachine1L<>(
                alphabet);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + "Top " + n + " words in " + input + "</title>");
        out.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/"
                        + "assignments/projects/tag-cloud-generator/data/"
                        + "tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");

        out.println("</head>");
        out.println("<body>");

        out.println("<h2>Top " + n + " words in " + input + "</h2>");
        out.println("<hr />");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");

        createFontSize(out, decreaseOrder, sortedOrder, n);

        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        userInput.close();
        userOutput.close();
        in.close();
        out.close();

    }

}
