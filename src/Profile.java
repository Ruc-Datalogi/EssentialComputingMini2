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

    public void setName(String Name){
        this.name=Name;
        System.out.println("Set name to: " +Name);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        System.out.println("Set age to: " + age);
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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
    void processInput(String line){ // process the input
        if(hasQueuedQuestion){ // if theres a question in the queue
            processInput(line,queuedQuestion);
            hasQueuedQuestion=false;
        }
    }
    String getReply(){
        if(hasQueuedReply){ // only if there's a reply in the queue
            return getProfileSummary();
        }
        return "";
    }
    String getProfileSummary(){
        String reply="I seem to think that I know the following:";
        if(getName()!=null){ // only if the program has stored a name
            reply+=" your name is " + name + ",";
        }
        if(getAge()!=0){ // only if the program has stored an age
            reply+=" you're " + getAge() + " years old,";
        }
        if(getOccupation()!=null){ // only if it has stored an occupation
            reply+=" you're currently " + getOccupation();
        }
        return reply;
    }
    void processInput(String line,String questionType){
        if(questionType.equalsIgnoreCase("name")){
            //setting the users name as the last word of the input line
            this.setName(line.replaceAll("^.*?(\\w+)\\W*$", "$1")); // take the last word of the sentence
        }else if(questionType.equalsIgnoreCase("age")){
            this.setAge(Integer.parseInt(line.replaceAll("\\D+",""))); // remove everything that's not a digit
        }else if(questionType.equalsIgnoreCase("occupation=work")){ // if the user is working
            setOccupation("working");
        }else if(questionType.equalsIgnoreCase("occupation=student")){ // if the user is studying
            setOccupation("studying");
        }else if(questionType.equalsIgnoreCase("reply")){
            hasQueuedReply=true; // start the queue
        }
    }

}
