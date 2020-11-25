import java.util.ArrayList;

public class core {
    /*
            The core shouldn't be doing much runtime work in itself, instead we use seperate classes and objects to deligate

    */
    static ArrayList<QuestionKey> allKeys; // Arraylist of all keys
    static QuestionKey lastKey; //
    static QuestionDecomp lastDecomp;
    static int countForQuestions = -1; // if EVE doesnt understand she outputs question
    static String[] questionStartes = {"What's Your name again", "What is your occupation", "Dogs or cats?"};

    public static void main(String[] args) {
        UiHandler ui = new UiHandler();

        allKeys = new ArrayList<QuestionKey>();
        LineReader ourLineReader = new LineReader("./src/questions.txt");
        String[] TextData = ourLineReader.openFile();

        for (String s : TextData) { // generating the different arraylist of keywords decomp and ans
            processScriptLine(s);
        }

        for (QuestionKey key : allKeys) { // debugging
            System.out.println(key.toString());
        }

    }

    //Method for reading each line of the script file.
    static void processScriptLine(String line) {
        String words[] = line.split("\\s+");
        if (line.matches("(.*)key:(.*)")) { // check for if the line contains the word key and if so add to the keyList
            QuestionKey tempKey = new QuestionKey(words[1]);
            lastKey = tempKey;
            allKeys.add(tempKey);
            //System.out.println("Created and added key with:" + words[1]);
        } else if (line.matches("(.*)decomp:(.*)")) { // check for decomp and add to decompList
            //TODO make it so we can add the whole regex string for decomp
            QuestionDecomp tempDecomp;
            words[2] = words[2].replaceAll("\\*", ""); // remove * from the word
            if (words.length < 3) {
                tempDecomp = new QuestionDecomp("*");
            } else {
                tempDecomp = new QuestionDecomp(words[2]);
            }
            lastDecomp = tempDecomp;
            lastKey.addDecomp(tempDecomp);
            //System.out.println("Is lastkey actually last key?: " + lastKey.equals(allKeys.get(allKeys.size()-1)));
            //System.out.println("Created and added decomp rule: " + words[1]);

        } else if (line.matches("(.*)ans:(.*)")) { // lastly add the answers
            if (lastDecomp != null) {
                lastDecomp.addAnswer(line);
            } else {
                System.out.println("Tried to add answer to a null last Decomp"); // debugging
            }
        }

    }

    static String findAnswerToString(String line) {
        line = line.toLowerCase();
        SynonymCheck sC = new SynonymCheck(); // checking for synonyms and replacing
        line = sC.checkForSynonym(line);
        String words[] = line.split(" "); // splitting the words by " "

        String ans = "";
        int count = 0;

        for (QuestionKey k : allKeys) {
            if (k.hasKeyWord(words)) {
                ans = k.getAnswer(words);
                return ans;
            }
        }

        if (countForQuestions == questionStartes.length - 1) { // to avoid out of bounds
            countForQuestions = 0;
        }
        countForQuestions++;

        return questionStartes[countForQuestions];
    }


}
