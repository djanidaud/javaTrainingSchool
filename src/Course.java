import java.util.ArrayList;

/**
 * Class Course.
 *
 * Each course has a subject, a number of days until it starts, a duration based on the subjects's duration,
 * some students, an instructor and a boolean variable that tracks weather or not the course is cancelled.
 */
public class Course {
    private Subject subject;
    private int daysUntilStarts;
    private int daysToRun;
    private ArrayList<Student> studentList;
    private Instructor instructor;

    /**
     * Constructor.
     *
     * @param subject the subject that the course is based on.
     * @param daysUntilStarts the number of days until the course starts.
     */
    public Course(Subject subject, int daysUntilStarts) {
        this.subject = subject;
        this.daysUntilStarts = daysUntilStarts;
        this.daysToRun = subject.getDuration();
        this.studentList = new ArrayList<>();
    }

    /**
     * Subject getter.
     *
     * @return course's subject.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Returns the status of the  course.
     *
     * @return Can returns a negative int, that represents the number of days until the course starts, a positive int that
     * represents the number of days until the course finished or 0 if the course has already finished.
     */
    public int getStatus() {
        if (daysUntilStarts > 0) return -daysUntilStarts;
        else if (daysToRun > 0) return daysToRun;
        return 0;
    }

    /**
     * Every time a day passes the course goes through some changes:
     *
     * If the course has already started, decrease the number of days until it finishes by 1
     * else decrease the number of days until it starts by 1.
     *
     * Secondly, if the course has just finished, unassign its instructor and graduate all of its students.
     * */
    public void aDayPasses() {
        if (daysUntilStarts == 0) daysToRun--;
        else daysUntilStarts--;

        if (daysToRun == 0) {
            for (Student student : studentList) if (!student.hasCertificate(subject)) student.graduate(subject);
            clearCourse();
        }
    }

    /** Unassigns the instructor and removes all of the students in the class. */
    public void clearCourse(){
        if (instructor != null) instructor.unassignCourse();
        instructor = null;
        studentList.clear();
    }

    /**
     * Tries to enrol a student, fails if he is too late to enroll or course is full.
     *
     * @param myStudent the student we want to enroll.
     * @return <code>true</code> if the enrollment was successful, that is if the course isn't full and hasn't started.
     */
    public boolean enrolStudent(Student myStudent) {
        if (studentList.size() == 3 || daysUntilStarts == 0) return false;

        studentList.add(myStudent);
        myStudent.isEnrolled(true);
        return true;
    }

    /**
     * Gives the size of the studentList.
     *
     * @return the number of students in this course.
     */
    public int getSize() {
        return studentList.size();
    }

    /**
     * Gives an array version of studentList.
     *
     * @return an array, containing the students in the course.
     */
    public Student[] getStudents() {
        Student[] studentArr = new Student[studentList.size()];
        studentArr = studentList.toArray(studentArr);

        return studentArr;
    }

    /**
     * Tries to assign an instructor to the course.
     *
     * @param instructor the instructor we want to assign.
     * @return <code>true</code> if the instructor can teach the course and is free.
     */
    public boolean setInstructor(Instructor instructor) {
        if (instructor.canTeach(subject) && instructor.getAssignedCourse() == null) {
            this.instructor = instructor;
            instructor.assignCourse(this);
            return true;
        }
        return false;
    }

    /**
     * Tests if the course has an instructor.
     *
     * @return <code>true</code> if if the course has an actual instructor, who has the same assigned course as this one.
     */
    public boolean hasInstructor() {
        return instructor != null && instructor.getAssignedCourse() == this;
    }

    /**
     * Tests if the course is cancelled.
     *
     * @return <code>true</code> if the course is just about to start but has no students or an instructor.
     */
    public boolean isCancelled() {
        if (daysUntilStarts == 0 && (instructor == null || studentList.size() == 0)) {
            clearCourse();
            return true;
        }
        return false;
    }
}
