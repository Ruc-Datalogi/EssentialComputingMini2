/*
A class representing a key
 */
import java.util.ArrayList;

public class QuestionKey {

    ArrayList<String> keyWords;
    ArrayList<QuestionDecomp> decompsForKey;

    QuestionKey(String keyWord) { // constructor for QuestionKey
        this.keyWords = new ArrayList<String>();
        this.keyWords.add(keyWord);
        this.decompsForKey = new ArrayList<QuestionDecomp>();
    }

    boolean hasKeyWord(String line){ // check for keyword
        for(String K : keyWords){ // check all keywords
            if(line.matches("(.*)" +  K + "(.*)")){ // if the input matches a keyword
                return true;
            }
        }
        return false;
    }

    //Add the decomp
    void addDecomp(QuestionDecomp newDecomp) { // add decomp word to key
        decompsForKey.add(newDecomp);
    }

    String getAnswer(String line){ // get answer for key
        for(QuestionDecomp D : decompsForKey){
            if(D.hasDecomp(line)){
                return D.getNextAnswer();
            }
        }
        return "";
    }

    public String toString() { // to string method for the keywords
        String output = "key: [" + keyWords.get(0) + "] ";
        for (QuestionDecomp Dec : decompsForKey) {
            output += Dec.toString();
        }
        return output;
    }

}