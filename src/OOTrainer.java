/**
 * Class OOTrainer.
 *
 * extends class Instructor and implements canTeach().
 */
public class OOTrainer extends Teacher {
    /**
     * Constructor.
     *
     * @param name (required) each trainer has a name.
     * @param gender (required) each trainer is either male or  female. Takes value 'M' and 'F'.
     * @param age (required) each trainer has an age.
     */
    public OOTrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Constructor.
     *
     * @param person (required) each trainer is a person with a certain name, gender and age.
     */
    public OOTrainer(Person person) {
        this(person.getName(), person.getGender(), person.getAge());
    }

    /**
     * Implements the canTeach method.
     *
     * @param mySubject we test if the trainer can teach this subject.
     * @return <code>true</code> only if mySubject has a specialty of either 1, 2 or 3.
     */
    public boolean canTeach(Subject mySubject) {
        return mySubject.getSpecialism() < 4;
    }
}
