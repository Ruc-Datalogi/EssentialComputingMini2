public class Profile {
    String name;
    int age;
    String occupation;
    boolean male;
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
    void processInput(String line,String questionType){
        if(questionType.equalsIgnoreCase("name")){
            //setting the users name as the last word of the input line
            this.setName(line.replaceAll("^.*?(\\w+)\\W*$", "$1"));
        }else if(questionType.equalsIgnoreCase("age")){
            this.setAge(Integer.parseInt(line.replaceAll("\\D+","")));
        }else if(questionType=="sex"){

        }
    }
}
