import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program that counts word occurrences in a given input file and outputs an
 * HTML document with a table of the words and counts listed in alphabetical
 * order.
 *
 * @author Anurag Kejriwal
 */
public class WordCounter {

    /**
     * Correctly orders a queue alphabetically.
     *
     *
     */
    private static class CompareTo implements Comparator<String> {
        /*
         * @param s1 first string to be compared
         *
         * @param s2 second string to be compared
         *
         */
        @Override
        public int compare(String s1, String s2) {
            int result;
            if (s1.toLowerCase().equals(s2.toLowerCase())) {
                result = s1.compareTo(s2);
            } else {
                result = s1.toLowerCase().compareTo(s2.toLowerCase());
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
     * @param separators
     *            A set of separators
     * @param text
     *            String to be read
     * @param position
     *            The starting position to check in the method
     * @return word, or separator if the position starts at one
     * @requires Text isn't empty. Also, separators is not empty and that
     *           position is less than the length of text
     */

    private static String nextWordOrSeparator(Set<Character> separators,
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
            if (separators.contains(text.charAt(i)) && endPosition < 0) {
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
    public static Map<String, Integer> processWord(SimpleReader fileIn) {

        Map<String, Integer> wordsAndCount = new Map1L<String, Integer>();

        int position = 0;

        //creates set with separators
        Set<Character> separators = new Set1L<>();

        //adding possible separators to set
        separators.add(' ');
        separators.add('!');
        separators.add('.');
        separators.add(',');
        separators.add('?');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('@');
        separators.add('-');

        //Loops until all lines have been read from input text file
        while (!fileIn.atEOS()) {
            String line = fileIn.nextLine();
            position = 0;

            while (position < line.length()) {
                String word = nextWordOrSeparator(separators, line, position);

                if (!separators.contains(word.charAt(0))) {

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
     * Sorts through the Map using Queue's sorting functionality.
     *
     * @param wordsAndCount
     *            the Map containing all the words and its occurrences
     * @param comparator
     *            the Comparator that helps to compare words alphabetically
     * @param wordQueue
     *            the Queue that will contain all the words sorted.
     * @updates wordQueue, wordsAndCount
     * @ensures Map of words is in order based on the Queue wordQueue.
     *
     *
     */

    public static void sortWord(Map<String, Integer> wordsAndCount,
            Comparator<String> comparator, Queue<String> wordQueue) {

        Map<String, Integer> tempMap = wordsAndCount.newInstance();
        Queue<String> tempQueue = new Queue1L<String>();

        for (int i = 0; i < wordQueue.length(); i++) {
            String removed = wordQueue.dequeue();
            tempQueue.enqueue(removed);
        }

        int size = wordsAndCount.size();
        for (int i = 0; i < size; i++) {

            Map.Pair<String, Integer> tempPair = wordsAndCount.removeAny();

            tempMap.add(tempPair.key(), tempPair.value());
            wordQueue.enqueue(tempPair.key());
        }

        wordQueue.sort(comparator);

        int length = wordQueue.length();
        for (int i = 0; i < length; i++) {
            String tempKey = wordQueue.dequeue();
            Map.Pair<String, Integer> pair = tempMap.remove(tempKey);
            wordsAndCount.add(pair.key(), pair.value());
            tempQueue.enqueue(tempKey);
        }
        wordQueue.transferFrom(tempQueue);
    }

    /**
     * Main method.
     *
     * Creates HTML page that pulls from a Map of words and its number of
     * occurrences.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        //asks user for input file name and output file name
        SimpleReader userInput = new SimpleReader1L();
        SimpleWriter userOutput = new SimpleWriter1L();
        userOutput.print("Enter the input file name: ");
        String input = userInput.nextLine();
        userOutput.print("Enter the output file name: ");
        String output = userInput.nextLine();

        SimpleReader in = new SimpleReader1L(input);
        SimpleWriter out = new SimpleWriter1L(output);
        Comparator<String> comparator = new CompareTo();
        Queue<String> wordQueue = new Queue1L<String>();

        Map<String, Integer> wordsAndCount = processWord(in);
        sortWord(wordsAndCount, comparator, wordQueue);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + "Words and their respective counts in " + input
                + "</title>");

        out.println("<body>");

        //Header
        out.println("<h1>" + "Words and their respective counts in " + input
                + "</h1>");
        out.println("<hr />");

        out.println("<table border=\"5\">");
        out.println("<tr>");
        out.println("<th>Word</th>");
        out.println("<th>Count</th>");
        out.println("</tr>");

        int size = wordsAndCount.size();
        for (int i = 0; i < size; i++) {
            if (wordQueue.length() > 0) {
                Map.Pair<String, Integer> tempPair = wordsAndCount
                        .remove(wordQueue.dequeue());
                out.println("<tr>");
                out.println("<td>" + tempPair.key() + "</td>");
                out.println("<td>" + tempPair.value() + "</td>");
                out.println("</tr>");
            }
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        userInput.close();
        userOutput.close();
        in.close();
        out.close();

    }

}
