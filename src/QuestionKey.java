import java.util.ArrayList;

public class QuestionKey {

    ArrayList<String> keyWords;
    ArrayList<QuestionDecomp> decompsForKey;

    QuestionKey(String[] newKeywords) { //
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

    boolean hasKeyWord(String line){
        for(String K : keyWords){
            if(line.matches("(.*)" + K + "(.*)")){
                return true;
            }
        }
        return false;
    }

    boolean hasKeyWord(String[] line) { // check the input line for keyword
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
    void addDecomp(QuestionDecomp newDecomp) { // add decomp word to key
        decompsForKey.add(newDecomp);
    }

    String getAnswer(String line){
        for(QuestionDecomp D : decompsForKey){
            if(D.hasDecomp(line)){
                return D.getNextAnswer();
            }
        }
        return "";
    }

    String getAnswer(String[] msg) { // get the next answer for the input
        for (QuestionDecomp D : decompsForKey) {
            //System.out.println("For");
            if (D.hasDecomp(msg)) {
               return D.getNextAnswer();
            }
        }
        return "Sorry I don't understand";
    }


    public String toString() { // to string method for the keywords
        String output = "key: [" + keyWords.get(0) + "] ";
        for (QuestionDecomp Dec : decompsForKey) {
            output += Dec.toString();
        }
        return output;
    }

}