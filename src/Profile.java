/*
Class for the profile which stores the name, age, occupation

 */

public class Profile {
    String name;
    int age;
    String occupation;
    boolean hasQueuedReply=false;

    Profile(){
    }

    // setters and getters
    public void setName(String Name){
        this.name=Name;
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
    boolean hasAge(){
        return getAge()!=0;
    }
    public void setAge(int age) {
        this.age = age;
    }


    public String getOccupation() {
        return this.occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    boolean hasOccupation(){
        return getOccupation()!=null;
    }


    String getReply(){
        if(hasQueuedReply){
            return getProfileSummary();
        }
        return "";
    }

    String getProfileSummary(){ // get the profile of the user
        String reply="I seem to think that I know the following:";
        if(hasName()){ // if the program has stored a name
            reply+=" your name is " + name + ",";
        }
        if(hasAge()){ // if the program has stored an age of the user
            reply+=" you're " + getAge() + " years old,";
        }
        if(hasOccupation()){ // if the program has stored an occupation
            reply+=" you're currently " + getOccupation();
        }
        return reply;
    }
    void processInput(String line,String questionType){ // proccess in the input
        if(questionType.equalsIgnoreCase("name")){
            //setting the users name as the last word of the input line
            this.setName(line.replaceAll("^.*?(\\w+)\\W*$", "$1")); // regex and replace from stackoverflow
        }else if(questionType.equalsIgnoreCase("age")){
            String newLine= line.replaceAll("\\D+",""); // remove everything expect numbers
            if(newLine.length()>0) { //Let's not try and parse an int from an empty string
                int parsedAge = Integer.parseInt(newLine);
                this.setAge(parsedAge);
            }
        }else if(questionType.equalsIgnoreCase("occupation=work")){ // setters
            setOccupation("working");
        }else if(questionType.equalsIgnoreCase("occupation=student")){
            setOccupation("studying");
        }else if(questionType.equalsIgnoreCase("reply")){
            hasQueuedReply=true;
        }
    }

}
