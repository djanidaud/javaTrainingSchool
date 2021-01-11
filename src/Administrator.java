import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * Class Administrator.
 * Controls a school and runs the simulation.
 *
 * Each administrator has a school.
 */
public class Administrator {
    private School school;
    private String[] namesData = {"Alex", "Charlie", "Eden", "Harley", "Reese", "Blake", "Morgan", "Karter", "Remi",
            "Rory", "Taylor", "Adrian", "Joe"};

    /**
     * Constructor.
     *
     * @param school the school that the administrator runs.
     */
    public Administrator(School school) {
        this.school = school;
    }

    /**
     * Main method that reads the configuration file.
     *
     * @param args its 1st element is the filename and its 2nd is the number of days the simulation is to be run
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        boolean isWrongFormat = false;
        String line = "";
        Administrator admin = new Administrator(new School(""));

        try {
            File file = new File(args[0]);
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException | IndexOutOfBoundsException re) {
            System.err.println("File not found. Try with another one");
        }

        /**
         * If the user has not specified the number of days he wants the simulation to be run,
         *  then the default value is 1.
         */
        int daysToRun = args.length == 1 ? 1 : Integer.parseInt(args[1]);

        /**
         * We start reading the file line by line.
         *
         * Firstly, we split each line by ":", which gives us an array with 2 elements: class name and class fields.
         * We then test the class name and create class fields depending on the case we have entered.
         *
         * If the class is a person, then we extract the information from the fields first,
         * and then test for the specific class name.
         *
         * If the class name is not either school,subject or some sort of person,
         * then the configuration file is invalid.
         *
         * Finally, if the format is valid, we run the simulation for the specified number of days.
         */
        while (true) {
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] splitedLine = line.split(":");

            if(splitedLine.length != 2) isWrongFormat=true;
            else
            switch (splitedLine[0]) {
                case "school":
                    String schoolName = splitedLine[1];
                    admin.getSchool().setName(schoolName);
                    break;
                case "subject":
                    String[] splitedComma = splitedLine[1].split(",");

                    if(splitedComma.length==4) {
                        String description = splitedComma[0];
                        int id = Integer.parseInt(splitedComma[1]);
                        int specialisation = Integer.parseInt(splitedComma[2]);
                        int duration = Integer.parseInt(splitedComma[3]);

                        admin.getSchool().add(new Subject(id, specialisation, duration, description));
                    }
                    else isWrongFormat = true;
                    break;
                case "student":
                case "Teacher":
                case "Demonstrator":
                case "OOTrainer":
                case "GUITrainer":
                    splitedComma = splitedLine[1].split(",");

                    if(splitedComma.length==3) {
                        String name = splitedComma[0];
                        char gender = splitedComma[1].charAt(0);
                        int age = Integer.parseInt(splitedComma[2]);

                        switch (splitedLine[0]) {
                            case "student":
                                admin.getSchool().add(new Student(name, gender, age));
                                break;
                            case "Teacher":
                                admin.getSchool().add(new Teacher(name, gender, age));
                                break;
                            case "Demonstrator":
                                admin.getSchool().add(new Demonstrator(name, gender, age));
                                break;
                            case "OOTrainer":
                                admin.getSchool().add(new OOTrainer(name, gender, age));
                                break;
                            case "GUITrainer":
                                admin.getSchool().add(new GUITrainer(name, gender, age));
                                break;
                        }
                    } else isWrongFormat = true;
                    break;
                default:
                    isWrongFormat = true;
            }
        }
        if (isWrongFormat) System.err.println("Invalid configuration file. Try with another one.");
        else admin.run(daysToRun);
    }

    /**
     * School getter.
     *
     * @return the school that the administrator runs.
     */
    public School getSchool() {
        return school;
    }

    /**
     * Generates a random person.
     *
     * We are using unisex names so they can be assigned to both males and females.
     * There is a 50-50 chance of determining the person's gender.
     * The age of the person is also random and varies between 18 and 47.
     *
     * @return returns a random person.
     */
    public Person getRandomPerson(){
        Random random = new Random();
        String randomName = namesData[random.nextInt(namesData.length)];
        char gender = random.nextInt(2) == 0 ? 'M' : 'F';
        int randomAge = random.nextInt(30) + 18;
        return new Person(randomName,gender,randomAge);
    }

    /**
     * A method that runs the simulation.
     * Adds new students/instructors each day, calls a dayAtSchool() and removes students/instructors.
     *
     * Firstly, we generate a random number from 0 to 2 that determines the number of students to join the school.
     *
     * Secondly, each day a new instructor might join the school.
     * We generate some random numbers so that there is a 20% chance of a teacher joining,
     * 10% for a demonstrator and 5% for both GUITrainer and OOTrainer.
     *
     * After that, we call the aDayAtSchool() method.
     *
     * Finally, the chances of a free instructor and of a student to quit are 20% and 5% respectively.
     * All students who have graduated all of the subjects also leave the school.
     */
    public void run() {
        Random random = new Random();
        ArrayList<Subject> subjects = school.getSubjects();
        ArrayList<Student> students = school.getStudents();
        ArrayList<Instructor> instructors = school.getInstructors();

        int newStudentsNumber = random.nextInt(3);
        for (int i = 0; i < newStudentsNumber; i++) school.add(new Student(getRandomPerson()));

        if(random.nextInt(5)==0)  school.add(new Teacher(getRandomPerson()));
        if(random.nextInt(10)==0) school.add(new Demonstrator(getRandomPerson()));
        if(random.nextInt(20)==0) school.add(new OOTrainer(getRandomPerson()));
        if(random.nextInt(20)==0) school.add(new GUITrainer(getRandomPerson()));

        school.aDayAtSchool();

        /** We create duplicates of the list of instructors and list of students in the school.*/
        ArrayList<Instructor> newInstructorList = new ArrayList<>(instructors);
        ArrayList<Student> newStudentList = new ArrayList<>(students);

        /** If the instructor is willing to quit and free, we remove him from the duplicated list. */
        for (Instructor instructor : instructors) {
            boolean willQuit = random.nextInt(5) == 0;
            boolean isFree = instructor.getAssignedCourse() == null;

            if (isFree && willQuit) newInstructorList.remove(instructor);
        }

        /** If the student is willing to quit and free OR has graduated, we remove him from the duplicated list. */
        for (Student student : students) {
            boolean willQuit = random.nextInt(20) == 0;
            boolean hasAllCertificates = subjects.size() == student.getCertificates().size();
            boolean isFree = !student.isEnrolled();

            if (hasAllCertificates || (willQuit && isFree)) newStudentList.remove(student);
        }

        /** Now we update the original lists of instructors/students. */
        school.setInstructors(newInstructorList);
        school.setStudents(newStudentList);
    }

    /**
     * Overloaded method run().
     *
     * @param numberOfDays the number of days the simulation is to be run.
     */
    public void run(int numberOfDays) {
        for (int i = 1; i <= numberOfDays; i++) {
            System.out.println("---------------------------- Day " + i + " ----------------------------");
            run();
            System.out.println(school.toString());

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        saveProgress();
    }

    /**
     * Extension.
     * Asks the user if he wants to save the simulation's progress after it has finished.
     *
     * If the user agrees, we try to save his progress by creating String fileContent.
     * We then transfer everything in the configuration file format.
     */
    public void saveProgress() {
        System.out.println("Would you like to save your simulation's current progress? Type Yes/No.");

        String answer;
        Scanner myScanner = new Scanner(System.in);
        answer = myScanner.nextLine();

        switch (answer.toLowerCase()) {
            case "yes":
                try {
                    FileWriter locFile = new FileWriter("configuration.txt");

                    String fileContent = "school:" + school.getName() + "\n";


                    for (Subject subject : school.getSubjects()) {
                        String desc = subject.getDescription();
                        int id = subject.getID();
                        int specialism = subject.getSpecialism();
                        int duration = subject.getDuration();

                        fileContent += "subject:" + desc + "," + id + "," + specialism + "," + duration + "\n";
                    }

                    for (Student student : school.getStudents())
                        fileContent += "student:" + student.getData('L') + "\n";


                    for (Instructor instructor : school.getInstructors()){
                        boolean isGUITrainer = instructor instanceof GUITrainer;
                        boolean isOOTrainer = instructor instanceof OOTrainer;
                        boolean isDemonstrator = instructor instanceof Demonstrator;
                        boolean isTeacher = !isGUITrainer && !isOOTrainer && !isDemonstrator;

                        if (isTeacher) fileContent += "Teacher:";
                        if (isDemonstrator) fileContent += "Demonstrator:";
                        if (isOOTrainer) fileContent += "OOTrainer:";
                        if (isGUITrainer) fileContent += "GUITrainer:";

                        fileContent += instructor.getData('L') + "\n";
                    }
                    locFile.write(fileContent);
                    System.out.println("Simulation saved as 'configuration.txt'.");
                    locFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "no":
                System.out.println("End of simulation.");
                break;
            default:
                /** If the user has typed something different than 'yes' or 'no', start all over again. */
                System.err.println("Invalid answer, please try again.");
                saveProgress();
        }
    }
}
