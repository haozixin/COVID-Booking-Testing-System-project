package engine;


import java.util.Scanner;

/**
 * Class that manages I/O for the system
 */
public class Display  {

    private Scanner keyboard = new Scanner(System.in);


    /**
     * Prints a String and then terminates the line.
     * @param s the string to print
     */
    public void println(String s) {
        System.out.println(s);
    }

    /**
     * Read a char from the keyboard.
     *
     * @return the first char of the next entered string.
     */
    public char readChar() {
        String s = keyboard.next();
        return s.charAt(0);
    }

}