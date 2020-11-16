import java.util.ArrayList;
import java.util.Scanner;

public class core {
    /*
            The core shouldn't be doing much runtime work in itself, instead we use seperate classes and objects to deligate

    */
    static ArrayList<QuestionKey> allKeys;
    static QuestionKey lastKey;
    static QuestionDecomp lastDecomp;
    static int countForQuestions = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UiHandler ui = new UiHandler();


        allKeys = new ArrayList<QuestionKey>();
        LineReader ourLineReader = new LineReader("./src/questions.txt");
        String[] TextData = ourLineReader.openFile();

        for (String s : TextData) {
            processScriptLine(s);
        }
        for (QuestionKey key : allKeys) {
            System.out.println(key.toString());
        }


    }


    //Method for reading each line of the script file.
    static void processScriptLine(String line) {
        String words[] = line.split("\\W+");
        if (line.matches("(.*)key:(.*)")) {
            //System.out.println("Processing line:"+line);
            QuestionKey tempKey = new QuestionKey(words[1]);
            lastKey = tempKey;
            allKeys.add(tempKey);
            //System.out.println("Created and added key with:" + words[1]);
        } else if (line.matches("(.*)decomp(.*)")) {
            //Let's assume we're working on the last added key?
            //TODO make it so we can add the whole regex string for decomp
            QuestionDecomp tempDecomp;
            //Full on spaghetti code to allow empty or wildcard words?
            if (words.length < 3) {
                tempDecomp = new QuestionDecomp("*");
            } else {
                tempDecomp = new QuestionDecomp(words[2]);
            }
            lastDecomp = tempDecomp;
            lastKey.addDecomp(tempDecomp);
            //System.out.println("Is lastkey actually last key?: " + lastKey.equals(allKeys.get(allKeys.size()-1)));
            //System.out.println("Created and added decomp rule: " + words[1]);

        } else if (line.matches("(.*)ans:(.*)")) {
            if (lastDecomp != null) {
                lastDecomp.addAnswer(line);
            } else {
                System.out.println("Tried to add answer to a null lastDecomp");
            }
        }


    }

    static String findAnswerToString(String line) {
        String words[] = line.split("\\W+");
        String commonWords[] = {"What's your name again ?", "How's your family doing ?", "How's school ?"};
        String ans = "";
        int count = 0;

        for (QuestionKey k : allKeys) {
            if (k.hasKeyWord(words)) {
                ans = k.getAnswer(words);
                if (ans.length() > 2) {
                    count++;
                    if(count == 5){
                        count = 0;
                        ans = ans + "feel free to ask me some questions";
                        return ans;
                    }
                    return ans;
                }
            }
        }


        countForQuestions++;
        if (countForQuestions == commonWords.length) {
            System.out.println("hello");
            countForQuestions = 0;
        }

        return commonWords[countForQuestions];
    }


    static class QuestionDecomp {
        ArrayList<String> DecompRegs;
        ArrayList<String> answers;
        int count = -1; // the function increments at the start

        QuestionDecomp(String[] newKeywords) {
            this.DecompRegs = new ArrayList<String>();
            this.answers = new ArrayList<String>();
            for (String s : newKeywords) {
                this.DecompRegs.add(s);
            }
        }

        QuestionDecomp(String Decomp) {
            this.DecompRegs = new ArrayList<String>();
            this.DecompRegs.add(Decomp);
            this.answers = new ArrayList<String>();
        }


        boolean hasDecomp(String[] msg) { // check for decomp
            for (String s : msg) {
                for (String Decomp : DecompRegs) {
                    if (s.equalsIgnoreCase(Decomp)) {
                        return true;
                    }
                }
            }
            return false;
        }

        void addAnswer(String ans) {
            ans = ans.trim(); // removes spaces from the answer
            ans = ans.replace("ans: ", "");
            answers.add(ans);
        }


        String getNextAnswer() {
            count++;
            if (count == answers.size()) {
                count = 0;
            }
            return answers.get(count);
        }


        public String toString() {
            String output = "";
            for (String Decoms : DecompRegs) {
                output += "Dec: [" + Decoms + "]";
            }
            for (String answ : answers) {
                output += "Ans: [" + answ + "]";
            }
            return output;
        }


    }


}
