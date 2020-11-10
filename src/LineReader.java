/*
    This class is for reading our script file
    we took a lot of inspiration from https://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
    It takes an input string for the path
*/

import java.io.*;

public class LineReader {
    private String path;

    public String[] openFile() {
        String[] failedGet = new String[]{"-1"};
        String[] textData;

        try {
            FileReader fr = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fr);

            int numberOfLines = readLines();
            textData = new String[numberOfLines];

            for (int i = 0; i < numberOfLines; i++) {
                textData[i] = textReader.readLine();
            }

            textReader.close();

            for(int i = 0 ; i < numberOfLines ;i++){
                System.out.println(textData[i]);
            }

            return textData;
        } catch (IOException e) {
            e.getMessage();
        }


        System.out.println("Wrong path ");
        for(int i = 0 ; i < failedGet.length ; i++){
            System.out.print(failedGet[i]);
        }
        return failedGet;
    }

    public LineReader(String file_path) {
        path = file_path;
    }

    private int readLines() throws IOException {
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
