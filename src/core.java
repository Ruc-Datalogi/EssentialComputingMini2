import java.util.ArrayList;
import java.util.Scanner;

public class core {
    /*
            The core shouldn't be doing much runtime work in itself, instead we use seperate classes and objects to deligate

         */
    static ArrayList<QuestionKey> allKeys;
    static QuestionKey lastKey;
    static QuestionDecomp lastDecomp;

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
        UiHandler ui=new UiHandler();


    }


    //Method for reading each line of the script file.
    static void processScriptLine(String line) {
        String words[] = line.split("\\W+");
        if (line.matches("(.*)key:(.*)")) {
            //System.out.println("Processing line:"+line);
            QuestionKey tempKey=new QuestionKey(words[1]);
            lastKey=tempKey;
            allKeys.add(tempKey);
            //System.out.println("Created and added key with:" + words[1]);
        } else if (line.matches("(.*)decomp(.*)")) {
            //Let's assume we're working on the last added key?
            //TODO make it so we can add the whole regex string for decomp
            QuestionDecomp tempDecomp;
            //Full on spaghetti code to allow empty or wildcard words?
            if(words.length<3){
                tempDecomp=new QuestionDecomp("*");
            }else{
                tempDecomp=new QuestionDecomp(words[2]);
            }
            lastDecomp=tempDecomp;
            lastKey.addDecomp(tempDecomp);
            //System.out.println("Is lastkey actually last key?: " + lastKey.equals(allKeys.get(allKeys.size()-1)));
            //System.out.println("Created and added decomp rule: " + words[1]);

        }else if (line.matches("(.*)ans:(.*)")){
            if (lastDecomp!=null) {
                lastDecomp.addAnswer(line);
            }else{
                System.out.println("Tried to add answer to a null lastDecomp");
            }
        }



    }
    static String findAnswerToString(String line){
        String words[] = line.split("\\W+");
        String ans="";
        for(QuestionKey k : allKeys){
            ans=k.getAnswer(words);
            if(ans.length()>2){
                return ans;

            }
        }
        return ans;
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
            String output="key: [" + keyWords.get(0) + "] " ;
            for(QuestionDecomp Dec:decompsForKey){
                output+=Dec.toString();
            }
            return output;
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
        public String toString(){
            String output="";
            for (String Decoms : DecompRegs){
                output+="Dec: [" + Decoms + "]";
            }
            for(String answ : answers){
                output+="Ans: [" + answ + "]";
            }
            return output;
        }


    }
}
