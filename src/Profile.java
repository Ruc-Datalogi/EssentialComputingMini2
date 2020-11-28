/*
Class for the profile which stores the name, age, occupation

 */

public class Profile {
    String name;
    int age;
    String occupation;
    boolean male;

    boolean hasQueuedQuestion=false;
    String queuedQuestion;
    boolean hasQueuedReply=false;
    Profile(){
    }

    // setters and getters
    public void setName(String Name){
        this.name=Name;
        System.out.println("Set name to: " +Name);
    }

    public String getName() {
        return name;
    }
    boolean hasName(){
        return getName()!=null;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        System.out.println("Set age to: " + age);
    }
    boolean hasAge(){
        return getAge()!=0;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    boolean hasOccupation(){
        return getOccupation()!=null;
    }

    public boolean isMale() {
        return male;
    }
    public boolean isFemale(){
        return !male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    void processInput(String line){ // for processing the input
        if(hasQueuedQuestion){ // if theres a question in the queue
            processInput(line,queuedQuestion);
            hasQueuedQuestion=false;
        }
    }

    String getReply(){
        if(hasQueuedReply){
            return getProfileSummary();
        }
        return "";
    }

    String getProfileSummary(){ // get the profile of the user
        String reply="I seem to think that I know the following:";
        if(hasName()){
            reply+=" your name is " + name + ",";
        }
        if(hasAge()){
            reply+=" you're " + getAge() + " years old,";
        }
        if(hasOccupation()){
            reply+=" you're currently " + getOccupation();
        }
        return reply;
    }
    void processInput(String line,String questionType){ // proccess in the input
        if(questionType.equalsIgnoreCase("name")){
            //setting the users name as the last word of the input line
            this.setName(line.replaceAll("^.*?(\\w+)\\W*$", "$1"));
        }else if(questionType.equalsIgnoreCase("age")){
            String newLine= line.replaceAll("\\D+","");
            if(newLine.length()>0) { //Let's not try and parse an int from an empty string
                int parsedAge = Integer.parseInt(newLine);
                this.setAge(parsedAge);
            }
        }else if(questionType.equalsIgnoreCase("occupation=work")){
            setOccupation("working");
        }else if(questionType.equalsIgnoreCase("occupation=student")){
            setOccupation("studying");
        }else if(questionType.equalsIgnoreCase("reply")){
            hasQueuedReply=true;
        }
    }

}
