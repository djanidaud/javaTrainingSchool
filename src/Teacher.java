/**
 * Class Teacher.
 *
 * extends class Instructor and implements canTeach().
 */
public class Teacher extends Instructor {
    /**
     * Constructor.
     *
     * @param name (required) each teacher has a name.
     * @param gender (required) each teacher is either male or  female. Takes value 'M' and 'F'.
     * @param age (required) each teacher has an age.
     */
    public Teacher(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Constructor.
     *
     * @param person (required) each teacher is a person with a certain name, gender and age.
     */
    public Teacher(Person person) {
        this(person.getName(), person.getGender(), person.getAge());
    }

    /**
     * Implements the canTeach method.
     *
     * @param mySubject we test if the teacher can teach this subject.
     * @return <code>true</code> only if mySubject has a specialty of either 1 or 2.
     */
    public boolean canTeach(Subject mySubject) {
        return mySubject.getSpecialism() < 3;
    }
}
