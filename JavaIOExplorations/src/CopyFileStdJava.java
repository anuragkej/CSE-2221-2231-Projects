import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        BufferedReader input;
        try {
            input = new BufferedReader(new FileReader(args[0]));
        } catch (IOException e) {
            System.err.println("Error opening file.");
            in.close();
            return;
        }
        PrintWriter output = null;
        try {
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(args[1])));
        } catch (IOException e) {
            System.err.println("Error creating/accessing output file.");
        }
        String s;
        try {
            s = input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading file.");
        }
        while (s != null) {
            output.println(s);
            //starts reading next line
            try {
                s = input.readLine();
            } catch (IOException e) {
                System.err.println("Error reading file.");
            }
        }
        //close streams
        try {
            input.close();
        } catch (IOException e) {
            System.err.println("Error closing stream.");
        }
        output.close();
    }
}
