import java.util.ArrayList;
/**
 * Class School.
 * Our School class is where all our Students are taught and our Instructors work.
 *
 * each school has a name and a record of its students, subjects, courses and instructors.
 */
public class School {
    private String name;
    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private ArrayList<Course> courses;
    private ArrayList<Instructor> instructors;

    /**
     * Constructor.
     *
     * @param name the  name of the school.
     */
    public School(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.instructors = new ArrayList<>();
    }

    /**
     * Name getter.
     *
     * @return the school's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     *
     * @param name the name we want to give to the school
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Registers a new student in the school.
     *
     * @param student the student we want to add.
     */
    public void add(Student student) {
        students.add(student);
    }

    /**
     * Removes a student from the list of students.
     *
     * @param student the student we want to remove.
     */
    public void remove(Student student) {
        students.remove(student);
    }

    /**
     * List of students getter.
     *
     * @return the list of students in the school.
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * List of students setter.
     *
     * @param students a list of students that we give to the school.
     */
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Adds a new subject to the school.
     *
     * @param subject the subject we want to add
     */
    public void add(Subject subject) {
        subjects.add(subject);
    }

    /**
     * Removes a subject from the list of subjects.
     *
     * @param subject the subject we want to remove.
     */
    public void remove(Subject subject) {
        subjects.remove(subject);
    }

    /**
     * List of  subjects getter.
     *
     * @return the list of subjects in the school.
     */
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Adds a course to the school.
     *
     * @param course the course we want to add.
     */
    public void add(Course course) {
        courses.add(course);
    }

    /**
     * Removes a course from the list of courses.
     *
     * @param course the course we want to remove.
     */
    public void remove(Course course) {
        courses.remove(course);
    }

    /**
     * List of courses getter.
     *
     * @return the list of courses in the school.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Adds an instructor to the list of instructors.
     *
     * @param instructor the instructor we want to add.
     */
    public void add(Instructor instructor) {
        instructors.add(instructor);
    }

    /**
     * Removes an instructor from the list of instructors.
     *
     * @param instructor the instructor we want to remove.
     */
    public void remove(Instructor instructor) {
        instructors.remove(instructor);
    }

    /**
     * List of instructors getter.
     *
     * @return the list of instructors in the school.
     */
    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    /**
     * List of instructors setter.
     *
     * @param instructors a list of instructors that we give to the school.
     */
    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    /**
     * Returns a subject's description by its id.
     *
     * @param id the subject's id.
     * @return the subject's description.
     */
    public String getSubjectById(int id) {
        for (Subject subject : subjects) if (subject.getID() == id) return subject.getDescription();
        return "";
    }

    /**
     * Overwritten toString().
     *
     * @return a string containing a detailed report of the relationships between the objects in the school.
     */
    @Override
    public String toString() {
        /** We declare the following strings: */
        String lines = "-------------------------------------------------- \n";
        String schoolName = "-------------------" + name + "-------------------- \n";
        String subjectsInfo = "The number of subjects in this school is " + subjects.size() + "\n";
        String coursesInfo = "The number of courses in this school is " + courses.size() + "\n";
        String studentsInfo = "The number of students in this school is " + students.size() + "\n";
        String instructorsInfo = "The number of instructors in this school is " + instructors.size() + "\n";

        /** We start filling subjectsInfo with information about each subject. */
        for (Subject subject : subjects)
            subjectsInfo += "The subject " + subject.getDescription() + " with id " + subject.getID() +
                    " has a duration of " + subject.getDuration() + " days and a specialism level of "
                    + subject.getSpecialism() + "\n";

        /**
         * We start filling coursesInfo with information about each course.
         *
         * Firs of all, we test if this course (has started/is about to start),
         * and sets String status an appropriate value.
         * We don't consider the case when courseStatus is 0 because if it does,
         * it will be removed by aDayAtSchool().
         *
         * We also collect all the students that are in the course and store them in String studentInCourse.
         *
         * Finally, We add everything we have learned about this course in one sentence.
         */
        for (Course course : courses) {
            int courseStatus = course.getStatus();
            String studentInCourse = "";
            String instructorStatus = course.hasInstructor() ? " has an instructor" : " does not have an instructor";
            String status = courseStatus < 0 ? (" and starts in " + -courseStatus + " days") :
                    (" and finishes in " + courseStatus + " days");

            for (Student student : course.getStudents()) studentInCourse += " " + student.getName();

            coursesInfo += "The " + course.getSubject().getDescription() + " course" + instructorStatus + status +
                    ". It has " + course.getSize() + " students:" + studentInCourse + ".\n";
        }

        /**
         * We start filling studentsInfo with information about each student.
         *
         * First of all, we collect the subjects that each student has graduated.
         *
         * Secondly, we tests if the student has any certificates at all.
         *
         * We also use the getData() method to get a pretty-string of the student's data.
         *
         * Finally, we add everything we have learned about this student in one sentence.
         */
        for (Student student : students) {
            String certificatesInfo = "";

            for (int certificateId : student.getCertificates()) certificatesInfo += " " + getSubjectById(certificateId);

            certificatesInfo = certificatesInfo.equals("") ? " and has 0 certificates" :
                    " and has certificates on the following subjects:" + certificatesInfo;

            studentsInfo += student.getData('P') + certificatesInfo + ".\n";
        }

        /**
         * We start filling instructorsInfo with information about each instructor.
         *
         * First of all, we get this instructor's assigned course.
         *
         * Secondly, we use the getData method to get a pretty-string of this instructor's data.
         *
         * Finally, we add everything we have learned about this instructor in one sentence.
         */
        for (Instructor instructor : instructors) {
            String assignedCourse = instructor.getAssignedCourse() == null ? " and currently does not teach anything" :
                    (" and teaches " + instructor.getAssignedCourse().getSubject().getDescription());

            instructorsInfo += instructor.getData('P') + assignedCourse + ".\n";
        }

        return schoolName + subjectsInfo + lines + coursesInfo + lines + studentsInfo + lines + instructorsInfo;
    }

    /**
     * This method creates new courses for subjects, adds instructors and students to these courses,
     * calls aDayPasses() on each course and then removes all courses that are either cancelled or have finished.
     */
    public void aDayAtSchool() {
        /** Creates a course for any subject that does not have an open-for-registration course */
        for (Subject subject : subjects) {
            boolean hasCourse = false;

            for (Course course : courses) {
                Subject currentSubject = course.getSubject();
                boolean hasOpenCourse = subject.equals(currentSubject) && course.getStatus() < 0 && course.getSize()<3;

                if (hasOpenCourse) hasCourse = true;
            }
            if (!hasCourse) add(new Course(subject, 2));
        }

        /** Creates a copy of the list of courses */
        ArrayList<Course> newCoursesList = new ArrayList<>(courses);

        /**
         * We start looping through each course.
         *
         * First of all, if the course doesn't have an instructor, we start looking for a free one.
         *
         * Secondly, we enroll any free student that doesn't have a certificate on this course.
         *
         * We then call aDayPasses() on each course.
         *
         * Finally, we remove the cancelled or finished courses from newCoursesList.
         */
        for (Course course : courses) {
            if (!course.hasInstructor())
                for (Instructor instructor : instructors)
                    if (course.setInstructor(instructor)) break;

            for (Student student : students) {
                boolean hasCertificate = student.hasCertificate(course.getSubject());
                if (!student.isEnrolled() && !hasCertificate) course.enrolStudent(student);
            }

            course.aDayPasses();

            if (course.isCancelled() || course.getStatus() == 0) newCoursesList.remove(course);
        }

        /** We update the list of courses so that the finished/cancelled courses aren't there. */
        courses = newCoursesList;
    }
}
