/*
A class for the decomposition words.
 */

import java.util.ArrayList;

public class QuestionDecomp {
    ArrayList<String> DecompRegs;
    ArrayList<String> answers;
    boolean hasProfileQuestionType = false; //By default we don't have a question. We use this value as it's more efficient to check a boolean than compare a string length
    String profileQuestionType;
    int count = -1; // the function increments at the start so we start at -1

    QuestionDecomp(String Decomp) { // constructor for QuestionDecomp
        this.DecompRegs = new ArrayList<String>();
        this.DecompRegs.add(Decomp);
        this.answers = new ArrayList<String>();
    }

    void setProfileQuestionType(String questionType) { // for checking if it is profile question
        profileQuestionType = questionType;
        hasProfileQuestionType = true;
    }

    //Checking if any of our decomp filters can be found in the line.
    boolean hasDecomp(String line) { // check for decomp word

        if (hasProfileQuestionType) { // if the decomp word contains a profile question type
            //System.out.println("Processing input for questionType" + profileQuestionType);
            core.userProfile.processInput(line, profileQuestionType);
        }
        //Iterating through each decomp and checking the against the line.
        for (String D : DecompRegs) {
            if (line.matches("(.*)" + D + "(.*)")) {
                //Once a match is found we return true
                return true;
            }
        }
        return false;
    }

    void addAnswer(String ans) { // add answer
        ans = ans.trim(); // removes spaces from the answer
        ans = ans.replace("ans: ", ""); // remove ans: from the string
        answers.add(ans);
    }


    String getNextAnswer() { // if there is multiple answers
        count++;
        if (count == answers.size()) {
            count = 0;
        }
        return answers.get(count);
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
