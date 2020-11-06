import java.util.ArrayList;

public class Word {
    private String word;
    private WordType wordType;




    public Word(String word, WordType wordType) {
        this.word = word;
        this.wordType = wordType;
    }


    public WordType getWordType(){
        return this.wordType;
    }


    public void setWordType(WordType wordType) {
        this.wordType = wordType;
    }

    public boolean equalsWord(Word f){
        if(this.word == f.word){
            this.wordType = f.wordType;
            return true;
        }
        return false;
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
