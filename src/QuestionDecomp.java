import java.util.ArrayList;

public class QuestionDecomp {
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
                System.out.println(DecompRegs);
                if(Decomp.equals("")){ // if the keyword does not require a decomp word
                    return true;
                }
                if (s.equalsIgnoreCase(Decomp)) { // check for decomp word in sentence
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

    String getFirstAnswer(){
        return answers.get(0);
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
