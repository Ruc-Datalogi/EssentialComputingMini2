public class SynonymCheck {

    String checkForSynonym(String line){
        line = line.replaceAll(" i am ", " i'm ");
        line = line.replaceAll(" im "," i'm ");
        line = line.replaceAll(" you are ", " you're ");
        System.out.println("i change " + line);
        return line;
    }

}
