import java.util.ArrayList;
import java.util.Scanner;

public class core {
    /*
            The core shouldn't be doing much runtime work in itself, instead we use seperate classes and objects to deligate

         */
    static ArrayList<QuestionKey> allKeys;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

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
            allKeys.add(new QuestionKey(words[1]));
            System.out.println("Created and added key with:" + words[1]);
        } else if (line.matches("(.*)decomb(.*)")) {
            //Let's assume we're working on the last added key?
            //TODO make it so we can add the whole regex string for decomp
            allKeys.get(allKeys.size() - 1).addDecomp(new QuestionDecomp(words[1]));
            System.out.println("Created and added decomp rule: " + words[1]);
        }


    }

    static class QuestionKey {
        ArrayList<String> keyWords;
        ArrayList<QuestionDecomp> decompsForKey;

        QuestionKey(String[] newKeywords) {
            this.decompsForKey = new ArrayList<QuestionDecomp>();
            this.keyWords = new ArrayList<String>();
            for (String s : newKeywords) {
                this.keyWords.add(s);
            }

        }

        QuestionKey(String keyWord) {
            this.keyWords = new ArrayList<String>();
            this.keyWords.add(keyWord);
            this.decompsForKey = new ArrayList<QuestionDecomp>();
        }


        boolean hasKeyWord(String[] line) {
            for (int i = 0; i < line.length; i++) {
                for (String keyWord : this.keyWords) {
                    if (line[i].equalsIgnoreCase(keyWord)) {
                        return true;
                    }
                }
            }
            return false;
        }

        //Add the decomp
        void addDecomp(QuestionDecomp newDecomp) {
            decompsForKey.add(newDecomp);
        }

        String getAnswer(String[] msg) {
            for (QuestionDecomp D : decompsForKey) {
                //System.out.println("For");
                if (D.hasDecomp(msg)) {
                    return D.getFirstAnswer();
                }
            }
            return "Sorry I don't understand";
        }

        public String toString() {
            return "key: [" + keyWords.get(0) + "] ";
        }

    }

    static class QuestionDecomp {
        ArrayList<String> DecompRegs;
        ArrayList<String> answers;

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


        boolean hasDecomp(String[] msg) {
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
            answers.add(ans);
        }

        String getFirstAnswer() {
            return answers.get(0);
        }


    }
}
