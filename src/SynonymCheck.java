/*
    Class for changing synonym words
 */

public class SynonymCheck {

    String checkForSynonym(String line){ // check for synonyms or misspelling
        line = line.replaceAll(" i am ", "i'm");
        line = line.replaceAll(" im ","i'm");
        line = line.replaceAll(" you are ", "you're");
        line = line.replaceAll(" feeling ", "doing");
        line = line.replaceAll(" thats ", "that's");
        line = line.replaceAll(" its ", "it's");
        line = line.replaceAll(" ive ", "i've");
        line = line.replaceAll(" that is ", "that's");
        line = line.replaceAll(" youre ", "you're");
        line = line.replaceAll(" you are ", "you're");
        System.out.println("i change " + line);
        return line;
    }

}
