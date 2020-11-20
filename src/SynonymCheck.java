/*
    Class for changing synonym words
 */

public class SynonymCheck {

    String checkForSynonym(String line){ // check for synonyms or misspelling
        line = line.replaceAll(" i am ", "i'm");
        line = line.replaceAll(" im ","i'm");
        line = line.replaceAll(" you are ", "you're");
        line = line.replaceAll(" feeling ", "doing");
        System.out.println("i change " + line);
        return line;
    }

}
