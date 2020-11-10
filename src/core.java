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
        processScriptLine("key: because");
        LineReader ourLineReader = new LineReader("./src/questions.txt");
        ourLineReader.openFile();


        String[] questions = {
                "Hello I am Eve, nice to meet you!",
                "How is the weather", "Ahh nice, what's your name",
                "That's beautiful name, how old are you?",
                "Hmm interesting, so what's your occupation"
        };
        QuestionKey nameKey = new QuestionKey("name");
        QuestionDecomp nameDecomp = new QuestionDecomp("your");
        QuestionDecomp theirName = new QuestionDecomp("my");
        theirName.addAnswer("That's a beautiful name");
        nameDecomp.addAnswer("My name is Eva");
        nameKey.addDecomp(nameDecomp);
        nameKey.addDecomp(theirName);

        QuestionKey sadKey = new QuestionKey("sad");
        String[] imStrings = {"I'm","im","I am"};
        QuestionDecomp meSad = new QuestionDecomp(imStrings);
        meSad.addAnswer("I'm very sorry to hear that");
        sadKey.addDecomp(meSad);




        //ChatParsing myChatParser = new ChatParsing();
        for (int i = 0; i < 2; i++) {
            String line = in.nextLine();
            String[] msg = line.split("\\W+");
            if (nameKey.hasKeyWord(msg)) {
                System.out.println(nameKey.getAnswer(msg));
            }
            if (sadKey.hasKeyWord(msg)){
                System.out.println(sadKey.getAnswer(msg));
            }

            //System.out.println(questions[i]);
            //myChatParser.parseNextMessage();

        }
    }

    //Method for reading each line of the script file.
    static void processScriptLine(String line){
        String words[]=line.split("\\W+");
        if(line.matches("(.*)key:(.*)")){
            allKeys.add(new QuestionKey(words[1]));
        }else if(line.matches("(.*)decomb(.*)")){
            //Let's assume we're working on the last added key?
            //TODO make it so we can add the whole regex string for decomp
            allKeys.get(allKeys.size()-1).addDecomp(new QuestionDecomp(words[1]));
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


    }

    static class QuestionDecomp {
        ArrayList<String> DecompRegs;
        ArrayList<String> answers;

        QuestionDecomp(String[] newKeywords) {
            this.DecompRegs = new ArrayList<String>();
            this.answers = new ArrayList<String>();
            for(String s : newKeywords){
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
                for(String Decomp : DecompRegs){
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
