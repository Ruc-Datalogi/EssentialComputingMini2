import java.util.ArrayList;

public class AllWords {

    private ArrayList<Word> swearWords = new ArrayList<>();
    private ArrayList<Word> moodWords = new ArrayList<>();
    private ArrayList<Word> meWords = new ArrayList<>();
    private ArrayList<Word> youWords = new ArrayList<>();

    public AllWords() {
        makelist();
    }

    private void makelist() {
        Word fuck = new Word("fuck", WordType.swearWord);
        Word fuckyou = new Word("fuck you", WordType.swearWord);
        Word shit = new Word("shit", WordType.swearWord);

        swearWords.add(fuck);
        swearWords.add(fuckyou);
        swearWords.add(shit);

        Word sad = new Word("sad", WordType.moodWord);
        Word happy = new Word("happy", WordType.moodWord);
        Word depressed = new Word("depressed", WordType.moodWord);
        Word stressed = new Word("stressed", WordType.moodWord);

        moodWords.add(sad);
        moodWords.add(happy);
        moodWords.add(depressed);
        moodWords.add(stressed);

        Word i = new Word("i", WordType.meWord);
        Word im = new Word("im", WordType.meWord);
        Word imm = new Word("i'm", WordType.meWord);
        Word my = new Word("my", WordType.meWord);

    }

    public boolean checkForIWord(ArrayList<Word> f) {
        for (int i = 0; i < f.size(); i++) {

        }

        return false;
    }
}
