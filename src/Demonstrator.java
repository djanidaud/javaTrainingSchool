/**
 * Class Demonstrator.
 *
 * extends class Instructor and implements canTeach().
 */
public class Demonstrator extends Instructor {
    /**
     * Constructor.
     *
     * @param name (required) each demonstrator has a name.
     * @param gender (required) each demonstrator is either male or  female. Takes value 'M' and 'F'.
     * @param age (required) each demonstrator has an age.
     */
    public Demonstrator(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Constructor.
     *
     * @param person (required) each demonstrator is a person with a certain name, gender and age.
     */
    public Demonstrator(Person person) {
        this(person.getName(),person.getGender(), person.getAge());
    }

    /**
     * Implements the canTeach method.
     *
     * @param mySubject we test if the demonstrator can teach this subject.
     * @return <code>true</code> only if mySubject has a specialty of 2.
     */
    public boolean canTeach(Subject mySubject) {
        return mySubject.getSpecialism() == 2;
    }
}
