public class core {
    /*
        The core shouldn't be doing much runtime work in itself, instead we use seperate classes and objects to deligate

     */
    public static void main(String[] args) {
       // String[] questions = {"Hello I am Eve, nice to meet you!", "How is the weather", "Ahh nice, what's your name", "That's beautiful name, how old are you?", "Hmm interesting, so what's your occupation"};
        Word test1 = new Word("hello",WordType.zero);
        Word test2 = new Word("hello",WordType.moodWord);
        System.out.println(test1.equalsWord(test2));
        System.out.println(test1.getWordType());

        AllWords test = new AllWords();
        System.out.println(test.getiWords());

        System.out.println("Hello I am Eve, nice to meet you!");
        System.out.println("How are you doing today ?");
        ChatParsing myChatParser = new ChatParsing();
        for (int i = 0; i < 5; i++) {
            myChatParser.parseNextMessage();
        }
        /*
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            myChatParser.parseNextMessage();

        }

         */
    }
}
