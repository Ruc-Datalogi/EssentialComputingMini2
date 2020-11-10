/*
Class for generating a profile of the person that talked to EVE

 */

public class Profile {
    private String name;
    private int age;
    private String mood;
    private int rememberNumber;
    private int rememberWord;

    Profile() {
        //When we make a profile we do not actually save the info since we can't get it before

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    //We look through our profile to find values that we haven't set yet, then we ask a question based around the missing info.
    public String createQuestionForProfile() {

        return "";
    }

}
