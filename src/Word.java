import java.util.ArrayList;

public class Word {
    private String word;
    private WordType wordType;




    public void setWord(String word) {
        this.word = word;
    }

    public void setWordType(WordType wordType) {
        this.wordType = wordType;
    }

    public WordType getWordType(Word s){
        return wordType;
    }

    public String getWord() {
        return word;
    }

    void printWords(ArrayList<Word> s){
        for(int i = 0 ; i < s.size() ; i++){
            System.out.println(s.get(i).getWord());
        }

    }

}
