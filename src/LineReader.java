/*
    This class is for reading our script file
    we took a lot of code from https://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
    It takes an input string for the path and outputs the different lines in a string array
*/

import java.io.*;

public class LineReader {
    private String path;

    public String[] openFile() { // class for reading the different lines from the textfile
        String[] failedGet = new String[]{"-1"};
        String[] textData; // the string array of all lines

        try { // we might not find the file so we use a try catch with IOexception
            FileReader fr = new FileReader(path); // reads single char
            BufferedReader textReader = new BufferedReader(fr); // for reading a whole line

            int numberOfLines = readLines();
            textData = new String[numberOfLines];

            for (int i = 0; i < numberOfLines; i++) {
                textData[i] = textReader.readLine();
            }

            textReader.close();
            return textData;
        } catch (IOException e) { //
            e.getMessage();
        }


        System.out.println("Wrong path"); // wrong path
        System.out.println(failedGet[0]);
        return failedGet;
    }

    public LineReader(String file_path) { // constructor for file path
        path = file_path;
    }

    private int readLines() throws IOException { // for getting the amount of lines to read from the textfile
        FileReader file_lines = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_lines);

        String aLine;
        int numberOfLines = 0;

        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }

}
