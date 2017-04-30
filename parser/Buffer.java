/* Holds a string of input and does things to it */
package parser;

import java.io.*;

public class Buffer {

    String input = "";
    int stringPosition = 0;

    public Buffer() {
        try {
            InputStreamReader consoleInput = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(consoleInput);
            input = in.readLine();
        } catch (Exception ex) {
            System.err.print("Could not read the console input. \n");
        }
    }

    //Buffers input from a string
    public Buffer(String inputText) {
        input = inputText;
    }

    //Buffers input from a file
    public Buffer(FileReader openInput) {
        try {
            BufferedReader wrapInput = new BufferedReader(openInput);
            StringBuilder buildString = new StringBuilder();
            while (wrapInput.ready()) {
                buildString.append(wrapInput.readLine());
            }
            input = buildString.toString();
        } catch (Exception ex) {
            System.err.print("Could not read the file. \n");
        }
    }

    public void skipBlanks() {
        char c = thisChar();
        while ((c == ' ' || c == '\n' || c == '\t') && stringPosition < input.length()) {
            toNextChar();
            c = thisChar();
        }
    }

    public char peek() {
        return input.charAt(stringPosition + 1);
    }

    public char thisChar() {
        if (stringPosition < input.length()) {
            return input.charAt(stringPosition);
        } else {
            return 0;
        }
    }

    public char thisAndToNextChar() {
        this.skipBlanks();
        if (stringPosition < input.length()) {
            char hold = input.charAt(stringPosition);
            ++stringPosition;
            return hold;
        } else {
            return 0;
        }
    }

    public void toNextChar() {
        ++stringPosition;
    }
}
