        /*
        This program is by Mikkel Elmelund Esbersen, Sebastian Nørager, and Martin List Syberg
        and it is the examination mini-project for the Essential Computing course at Roskilde University
         */

import java.util.ArrayList;

public class core {
    /*
            The core shouldn't be doing much runtime work in itself, instead we use seperate classes and objects to deligate
            the work.

    */
    static ArrayList<QuestionKey> allKeys; // Arraylist of all keys
    static QuestionKey lastKey; //
    static QuestionDecomp lastDecomp;
    static int countForQuestions = -1; // if EVE doesnt understand she outputs question
    static String[] questionStartes = {"What's Your name again?", "What is your occupation?", "I'm confused, Dogs or cats? Which one do you prefer?", "Are you okay?", "Hmm, mind if i change the subject?", "Okay, so i got to know, "};
    static Profile userProfile;

    public static void main(String[] args) {

        UiHandler ui = new UiHandler();

        allKeys = new ArrayList<QuestionKey>();
        LineReader ourLineReader = new LineReader("./src/questions.txt");
        String[] TextData = ourLineReader.openFile();

        for (String s : TextData) { // generating the different arraylist of keywords decomp and ans
            processScriptLine(s);
        }
        userProfile=new Profile();

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
        } else if (line.matches("(.*)ans:(.*)")) { // lastly add the answers
            if (lastDecomp != null) {
                lastDecomp.addAnswer(line);
            } else {
                System.out.println("Tried to add answer to a null last Decomp"); // debugging
            }
        }else if(line.matches("(.*)profile:(.*)")){
            line=line.replaceAll("(.*)profile: ","");
            lastDecomp.setProfileQuestionType(line);
            System.out.println("Set the decomps profilequestion to " + line + " for D: " + lastDecomp.DecompRegs.get(0));
        }

    }

    static String findAnswerToString(String line) {
        line = line.toLowerCase();
        SynonymCheck sC = new SynonymCheck(); // checking for synonyms and replacing
        line = sC.checkForSynonym(line);


        //System.out.println("I insert " + line);
        String words[] = line.split(" "); // splitting the words by " "

        String ans = "";

        for (QuestionKey k : allKeys) {
            if (k.hasKeyWord(line)) {
                ans = k.getAnswer(line);
                if (ans.length() > 1) { // to make sure we have an answer with something in it
                    if(userProfile.hasQueuedReply){ //Overwriting the answer to the queued reply if we find any
                        return userProfile.getReply();
                    }
                    return ans;
                }
            }
        }






        if (countForQuestions == questionStartes.length - 1) { // to avoid out of bounds
            countForQuestions = 0;
        }
        countForQuestions++;

        if(userProfile.hasName() == true && questionStartes[countForQuestions] == "What's Your name again?"){ // check if the it already has stored a name
            countForQuestions++;
        }

        if(userProfile.hasOccupation() == true && questionStartes[countForQuestions] == "What is your occupation?"){ // check if it already knows the occupation
            countForQuestions++;
        }

        return questionStartes[countForQuestions];
    }


}
