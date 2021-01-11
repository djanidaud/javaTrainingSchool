/**
 * Class Person.
 *
 * has 3 fields, a constructor and 4 methods.
 * each person has a name,gender and age
*/
public class Person {
    private String name;
    private char gender;
    private int age;

    /**
     * Constructor.
     *
     * @param name each person has a name.
     * @param gender each person has a gender. Can take values 'M' and 'F'.
     * @param age each person has an age.
     */
    public Person(String name, char gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Name getter.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gender getter.
     *
     * @return the gender of the person
     */
    public char getGender() {
        return gender;
    }

    /**
     * Age getter.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Age setter.
     *
     * @param age the age that we want to set to the person
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a string of a person's data.
     *
     * @param format Takes values 'P'(pretty) and 'L'(list).
     * @return a string containing the person's name, gender and age.
     */
    public String getData(char format){
        String gender = this.gender == 'M' ? "male" : "female";

        if(format=='P')
        return name + " is " + age + " years old, " + gender;

        return name + "," + this.gender + "," + age;
    }
}
