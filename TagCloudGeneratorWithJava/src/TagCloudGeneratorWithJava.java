import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Program that counts word occurrences in a given input file and outputs an
 * HTML document with a TagCloud of as many words in the input file as the user
 * enters in alphabetical order.
 *
 * @author Anurag Kejriwal & Vibhav Kaluvala
 */
public class TagCloudGeneratorWithJava {

    /**
     * private static String variable to be used to check possible separators.
     */
    private static String separators = "!.,?/:;@-()[]\t\n\r ";

    /**
     * private static Set variable to be used to check possible separators.
     */
    private static Set<Character> separatorSet = new HashSet<>();

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
            implements Comparator<Map.Entry<String, Integer>> {
        /*
         * @param p1 first Map Pair of String, Integer to be compared
         *
         * @param p2 second Map Pair of String, Integer to be compared
         */
        @Override
        public int compare(Map.Entry<String, Integer> p1,
                Map.Entry<String, Integer> p2) {

            int result = p1.getKey().compareTo(p2.getKey());
            if (result == 0) {
                result = p2.getValue().compareTo(p1.getValue());
            }
            return result;
        }
    }

    /**
     * Correctly orders a Map pair in decreasing order.
     */
    private static class WordCounter
            implements Comparator<Map.Entry<String, Integer>> {
        /*
         * @param p1 first Map Pair of String, Integer to be compared
         *
         * @param p2 second Map Pair of String, Integer to be compared
         */
        @Override
        public int compare(Map.Entry<String, Integer> p1,
                Map.Entry<String, Integer> p2) {

            int result = p2.getValue().compareTo(p1.getValue());
            if (result == 0) {
                result = p1.getKey().compareTo(p2.getKey());
            }
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
     * @param input
     *            the input text file of type BufferedReader
     * @return wordsAndCount the Map which stores the words and the number of
     *         times they appear in the input file
     * @ensures fileIn words = Map's words and number of times they appear
     */
    private static Map<String, Integer> readMap(BufferedReader input) {

        for (int i = 0; i < separators.length(); i++) {
            separatorSet.add(separators.charAt(i));
        }
        Map<String, Integer> wordsAndCount = new HashMap<>();

        int position = 0;

        try {
            String line = input.readLine();
            while (line != null) {
                line = line.toLowerCase();
                position = 0;

                while (position < line.length()) {
                    String word = nextWordOrSeparator(separatorSet, line,
                            position);

                    if (!separatorSet.contains(word.charAt(0))) {
                        if (!wordsAndCount.containsKey(word)) {
                            wordsAndCount.put(word, 1);
                        } else {
                            int wordValue = wordsAndCount.get(word);
                            wordValue++;
                            wordsAndCount.replace(word, wordValue);
                        }
                    }
                    position += word.length();
                }
                line = input.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from input file.");
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
     * @updates wordsAndCount
     * @ensures Map of words is in order based on the comparator and returns
     *          SortingMachine variable.
     * @return SortingMachine with sorted elements of Map pairs that consist of
     *         String, Integers.
     *
     */

    private static List<Map.Entry<String, Integer>> sortWord(
            Map<String, Integer> wordsAndCount,
            Comparator<Map.Entry<String, Integer>> comparator) {

        List<Map.Entry<String, Integer>> list = new LinkedList<>();

        for (Map.Entry<String, Integer> pair : wordsAndCount.entrySet()) {
            if (pair.getValue() > maxCount) {
                maxCount = pair.getValue();
            } else if (pair.getValue() < minCount) {
                minCount = pair.getValue();
            }
            list.add(pair);
        }
        list.sort(comparator);
        return list;
    }

    /**
     * Creates font sizes for each word and outputs the word and size into the
     * output file.
     *
     * @param output
     *            outputs to the file
     * @param decreaseOrder
     *            the SortingMachine that sorted the elements from the text file
     *            in decreasing order of values
     * @param sortedOrder
     *            the SortingMachine that sorted the elements from the text file
     *            in alphabetical order
     * @param n
     *            the number of words supposed to be in the tag cloud provided
     *            by the user
     * @clears decreaseOrder, sortedOrder
     * @ensures sortedOrder will be outputted with proper font sizes increasing
     *          with the number of occurrences of the word
     *
     */
    private static void createFontSize(PrintWriter output,
            List<Map.Entry<String, Integer>> decreaseOrder,
            List<Map.Entry<String, Integer>> sortedOrder, int n) {

        Comparator<Map.Entry<String, Integer>> alphabet = new AlphabeticalOrder();

        int i = 0;
        Iterator<Map.Entry<String, Integer>> it = decreaseOrder.iterator();
        while (it.hasNext() && i < n) {
            sortedOrder.add(it.next());
            i++;
        }

        sortedOrder.sort(alphabet);

        Iterator<Map.Entry<String, Integer>> iterator = sortedOrder.iterator();
        while (iterator.hasNext()) {
            int font = 0;
            Map.Entry<String, Integer> pair = iterator.next();
            int val = pair.getValue();
            if (val > minCount) {
                font = (FONT_MAX * (val - minCount) / (maxCount - minCount))
                        + ADD_FONT;
            }
            output.println("<span style=\"cursor:default\" class=\"f"
                    + Integer.toString(font) + "\" title=\"count: "
                    + pair.getValue() + "\">" + pair.getKey() + "</span>\n");
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

        Scanner in = new Scanner(System.in);

        System.out.print("Enter the input file name: ");
        String fileIn = in.nextLine();
        System.out.print("Enter the output file name: ");
        String fileOut = in.nextLine();

        BufferedReader input;
        try {
            input = new BufferedReader(new FileReader(fileIn));
        } catch (IOException e) {
            System.err.println("Error opening file.");
            in.close();
            return;
        }

        PrintWriter output;
        try {
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(fileOut)));
        } catch (IOException e) {
            System.err.println("Error creating output file.");
            in.close();
            try {
                input.close();
            } catch (IOException f) {
                System.err.println("Error closing input file.");
            }
            return;
        }

        System.out.print(
                "Enter the number of words (as an integer) to be included "
                        + "in the generated tag cloud: ");
        int n = in.nextInt();

        Map<String, Integer> wordsAndCount = readMap(input);
        Comparator<Map.Entry<String, Integer>> wordCounter = new WordCounter();
        List<Map.Entry<String, Integer>> decreaseOrder = sortWord(wordsAndCount,
                wordCounter);

        List<Map.Entry<String, Integer>> sortedOrder = new LinkedList<>();

        output.println("<html>");
        output.println("<head>");
        output.println(
                "<title>" + "Top " + n + " words in " + fileIn + "</title>");
        output.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/"
                        + "assignments/projects/tag-cloud-generator/data/"
                        + "tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");

        output.println("</head>");
        output.println("<body>");

        output.println("<h2>Top " + n + " words in " + fileIn + "</h2>");
        output.println("<hr />");
        output.println("<div class=\"cdiv\">");
        output.println("<p class=\"cbox\">");

        createFontSize(output, decreaseOrder, sortedOrder, n);

        output.println("</p>");
        output.println("</div>");
        output.println("</body>");
        output.println("</html>");

        output.close();

        in.close();

        try {
            input.close();
        } catch (IOException e) {
            System.err.println("Error closing input file.");
        }

    }

}
