import java.util.ArrayList;

public class QuestionKey {

    ArrayList<String> keyWords;
    ArrayList<core.QuestionDecomp> decompsForKey;

    QuestionKey(String[] newKeywords) {
        this.decompsForKey = new ArrayList<core.QuestionDecomp>();
        this.keyWords = new ArrayList<String>();
        for (String s : newKeywords) {
            this.keyWords.add(s);
        }

    }

    QuestionKey(String keyWord) {
        this.keyWords = new ArrayList<String>();
        this.keyWords.add(keyWord);
        this.decompsForKey = new ArrayList<core.QuestionDecomp>();
    }


    boolean hasKeyWord(String[] line) {
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
    void addDecomp(core.QuestionDecomp newDecomp) {
        decompsForKey.add(newDecomp);
    }

    String getAnswer(String[] msg) {
        for (core.QuestionDecomp D : decompsForKey) {
            //System.out.println("For");
            if (D.hasDecomp(msg)) {
                return D.getNextAnswer();
            }
        }
        return "Sorry I don't understand";
    }

    public String toString() {
        String output = "key: [" + keyWords.get(0) + "] ";
        for (core.QuestionDecomp Dec : decompsForKey) {
            output += Dec.toString();
        }
        return output;
    }

}

