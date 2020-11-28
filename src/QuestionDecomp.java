import java.util.ArrayList;

public class QuestionDecomp {
    ArrayList<String> DecompRegs;
    ArrayList<String> answers;
    boolean hasProfileQuestionType=false; //By default we don't have a question. We use this value as it's more efficient to check a boolean than compare a string length
    String profileQuestionType;
    int count = -1; // the function increments at the start so we start at -1

    QuestionDecomp(String[] newKeywords) { // unused constructor for handling arrays
        this.DecompRegs = new ArrayList<String>();
        this.answers = new ArrayList<String>();
        for (String s : newKeywords) {
            this.DecompRegs.add(s);
        }
    }

    QuestionDecomp(String Decomp) { // constructor for QuestionDecomp
        this.DecompRegs = new ArrayList<String>();
        this.DecompRegs.add(Decomp);
        this.answers = new ArrayList<String>();
    }

    void setProfileQuestionType(String questionType){ // for checking if it is profile question
        profileQuestionType=questionType;
        hasProfileQuestionType=true;
    }

    //Checking if any of our decomp filters can be found in the line.
    boolean hasDecomp(String line){
        if(hasProfileQuestionType){
            System.out.println("Processing input for questionType" + profileQuestionType);
            core.userProfile.processInput(line,profileQuestionType);
        }
        //Iterating through each decomp and checking the against the line.
        for(String D : DecompRegs){
            if (line.matches("(.*)" + D+ "(.*)")){
                //Once a match is found we return true
                return true;
            }
        }
        return false;
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

    void addAnswer(String ans) { // add answer
        ans = ans.trim(); // removes spaces from the answer
        ans = ans.replace("ans: ", "");
        answers.add(ans);
    }


    String getNextAnswer() { // if there is multiple answers
        count++;
        if (count == answers.size()) {
            count = 0;
        }
        return answers.get(count);
    }

    String getFirstAnswer(){
        return answers.get(0);
    }

    public String toString() { // to string method for debugging
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
