/**
 * Abstract class Instructor.
 *
 * extends Person and some methods that interact with class Course.
 * assignedCourse: each instructor has one assigned Course.
*/
public abstract class Instructor extends Person {
    private Course assignedCourse;

    /**
     * Constructor.
     *
     * @param name (required) each instructor has a name.
     * @param gender (required) each instructor is either male or  female. Takes value 'M' and 'F'.
     * @param age (required) each instructor has an age.
     */
    public Instructor(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Assigns a course to the instructor.
     *
     * @param myCourse the course we are assigning.
     */
    public void assignCourse(Course myCourse) {
        assignedCourse = myCourse;
    }

    /**
     * Assigned course getter
     *
     * @return the assigned course
     */
    public Course getAssignedCourse() {
        return assignedCourse;
    }

    /** Unassigns the course. */
    public void unassignCourse() {
        assignedCourse = null;
    }

    /**
     * Abstract method that tests if the instructor can teach a subject
     *
     * @param mySubject we test if the instructor can teach this subject.
     * @return <code>true</code> only if mySubject has the specialism of the instructor.
     */
    public abstract boolean canTeach(Subject mySubject);
}
