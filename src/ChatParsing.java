import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChatParsing {

    /*
        This class is used to parse the user input and accept it
     */
    Scanner in = new Scanner(System.in);

    ArrayList<Word> inputList = new ArrayList<>();

    String[] MeList = {"i'm", "im", "i", "me", "mine", "my"};
    String[] YouList = {"you", "youre", "your", "you're"};
    String[] OurList = {"our", "we", "we're"};
    String[] TheyList = {"they", "them", "their", "theyre", "they're"};
    String[] MeMood = {"good", "bad", "happy", "sad","depressed","joyful","hopeful","excited"};


    ChatParsing() {
        //Constructor
    }

    //TODO gotta make sure the regex for splitting checks for accents, hyphens and so on correctly.
    void parseNextMessage() {
        //taking nextline as string and then using split with a regex pattern match to split it into words
        String[] msg = in.nextLine().split("\\W+");

        for(int i = 0 ; i < msg.length ; i++){
            Word test = new Word(msg[i], WordType.zero);
            inputList.add(i, test);
        }



        if (isMe(msg)) {
        } else if (isYou(msg)) {
            System.out.println("What you say about me??!");
        } else {
            System.out.println("I don't understand your gibberish");
        }






        inputList.clear();
    }


    boolean isMe(String[] s) {
        for (int i = 0; i < s.length; i++) {
            for (int k = 0; k < MeList.length; k++) {
                if (s[i].equalsIgnoreCase(MeList[k])) {
                    return true;
                }
            }
        }

        return false;
    }


    boolean isYou(String[] s) {
        for (int i = 0; i < s.length; i++) {
            for (int k = 0; k < YouList.length; k++) {
                if (s[i].equalsIgnoreCase(YouList[k])) {
                    return true;
                }
            }
        }

        return false;
    }

    void messageWordType(String[] s){

        for(int i = 0 ; i < s.length ; i++){
        if(s[i].equalsIgnoreCase("sad") || s[i].equalsIgnoreCase("depressed" )){

            }

        }

    }


}
