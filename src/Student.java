import java.util.ArrayList;
/**
 * Class Student
 *
 * extends class Person, has some methods that interact with class Subject.
 * each person has a list of certificates and is either enrolled on a course or not.
 */
public class Student extends Person {
    private ArrayList<Integer> certificates;
    private boolean isEnrolled;

    /**
     * Constructor.
     *
     * @param name (required) each student has a name.
     * @param gender (required) each student is either male or  female. Takes value 'M' and 'F'.
     * @param age (required) each student has an age.
     */
    public Student(String name, char gender, int age) {
        super(name, gender, age);
        this.certificates = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param person (required) each student is a person with a certain name, gender and age.
     */
    public Student(Person person){
        this(person.getName(),person.getGender(),person.getAge());
    }

    /**
     * Graduate a course.
     * when a student graduates a course, he obtains a certificate on its subject and is no longer enrolled.
     *
     * @param mySubject the subject of the course that the student graduates.
     */
    public void graduate(Subject mySubject) {
        int id = mySubject.getID();
        certificates.add(id);
        isEnrolled = false;
    }

    /**
     * certificates getter.
     *
     * @return the list of certificates.
     */
    public ArrayList<Integer> getCertificates() {
        return certificates;
    }

    /**
     * Returns weather or not the student has graduated a certain subject.
     *
     * @param mySubject the subject that we are testing.
     * @return <code>true</code> only if the student has graduated the subject.
     */
    public boolean hasCertificate(Subject mySubject) {
        int id = mySubject.getID();
        return certificates.contains(id);
    }

    /**
     * isEnrolled getter.
     *
     * @return whether or not the student is enrolled on a course.
     */
    public boolean isEnrolled() {
        return isEnrolled;
    }

    /**
     * isEnrolled setter.
     *
     * @param isEnrolled sets the value of isEnrolled.
     */
    public void isEnrolled(boolean isEnrolled) {
        this.isEnrolled = isEnrolled;
    }
}
