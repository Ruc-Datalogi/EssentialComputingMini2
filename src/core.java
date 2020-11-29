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

    // first we define our different variables
    static ArrayList<QuestionKey> allKeys; // Arraylist of all keys
    static QuestionKey lastKey; // a temporary key
    static QuestionDecomp lastDecomp; // a temporary decomposition
    static int countForQuestions = -1; // if EVE doesnt understand she outputs question
    static String[] questionStartes = {"What's Your name again?", "What is your occupation?", "I'm confused, Dogs or cats? Which one do you prefer?", "Are you okay?", "Hmm, mind if i change the subject?", "Okay, so i got to know, "};
    static Profile userProfile;
    static SynonymCheck synonymChecker = new SynonymCheck();

    public static void main(String[] args) {

        UiHandler ui = new UiHandler(); // for the ui
        userProfile = new Profile(); // the profile
        allKeys = new ArrayList<QuestionKey>(); // initializing the arraylist for all the keys
        LineReader ourLineReader = new LineReader("./src/questions.txt"); // creating the linereader object with the filepath of our questions script
        String[] TextData = ourLineReader.openFile(); // for reading the script file

        for (String s : TextData) { // generating the different arraylist of keywords decomp and ans
            processScriptLine(s);
        }

        /* for debugging the keys we have used this
        for (QuestionKey key : allKeys) {
            System.out.println(key.toString());
        }
        */

    }

    //Method for reading each line of the script file.
    static void processScriptLine(String line) {
        String words[] = line.split("\\s+"); // \\s+ means we split the words at every space
        if (line.matches("(.*)key:(.*)")) { // check for if the line contains the word key and if so add to the keyList
            QuestionKey tempKey = new QuestionKey(words[1]); // create the temporary key and uses the second word because the first word is key:
            lastKey = tempKey; // using the temp key to add it to the arraylist
            allKeys.add(tempKey); // add the key to the arraylist
        } else if (line.matches("(.*)decomp:(.*)")) { // check for decomp and add to decompList
            QuestionDecomp tempDecomp; // a temporary decomp
            words[2] = words[2].replaceAll("\\*", ""); // remove * from the word
            tempDecomp = new QuestionDecomp(words[2]);
            lastDecomp = tempDecomp; // for adding the decomp in the arraylist
            lastKey.addDecomp(tempDecomp);
        } else if (line.matches("(.*)ans:(.*)")) { // lastly add the answers
            if (lastDecomp != null) { // check if the it contains an answer
                lastDecomp.addAnswer(line);
            } else {
                System.out.println("Tried to add answer to a null last Decomp"); // debugging
            }
        } else if (line.matches("(.*)profile:(.*)")) { // if the keywords contains an answer
            line = line.replaceAll("(.*)profile: ", ""); // remove the *profile* from the line
            lastDecomp.setProfileQuestionType(line); // add the profile question type to the decomp
            // System.out.println("Set the decomps profilequestion to " + line + " for D: " + lastDecomp.DecompRegs.get(0)); // debugging
        }

    }

    static String findAnswerToString(String userInput) { // a function for finding the answer to the input
        userInput = userInput.toLowerCase(); // to make the lowercase so we don't have to check for
        userInput = synonymChecker.checkForSynonym(userInput); // checking for synonyms and replacing
        String ans = "";

        for (QuestionKey k : allKeys) { // iterating over all the keys
            if (k.hasKeyWord(userInput)) { // checks if the input string contains a keyword
                ans = k.getAnswer(userInput); // get the answer
                if (ans.length() > 1) { // to make sure we have an answer with something in it
                    if (userProfile.hasQueuedReply) { // Overwriting the answer to the queued reply if we find any
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

        if (userProfile.hasName() == true && questionStartes[countForQuestions] == "What's Your name again?") { // check if the it already has stored a name
            countForQuestions++;
        }

        if (userProfile.hasOccupation() == true && questionStartes[countForQuestions] == "What is your occupation?") { // check if it already knows the occupation
            countForQuestions++;
        }

        return questionStartes[countForQuestions];
    }


}
