import java.util.ArrayList;
import java.util.List;


/**
 * Use to keep track of a basketball Course that is made up with multiple Students along with the Course's stats.
 * @author dejohns2
 * @since 2025.02.22
 * @version 1.0 beta
 * @see <a href="https://github.com/dejohns2/JavaSection3_BB_Scoreboard_Spring2025.git">GitHub Repository</a>
 */
public class Course {


    /**
     * The course's name.
     */
    private String name;


    /** The course's students. */
    private final List<Student> students;


    /** Sets the course's name to "Unknown", and assigns students to an empty new ArrayList */
    public Course() {
        name = "Unknown";
        students = new ArrayList<>();
    }


    /**
     * Calls the default constructor, and then set's the course's name using the setter for data validation.
     * @param name the course's name
     * @throw Exception if the setName fails due to a blank name
     */
    public Course(String name) throws Exception {
        this();
        setName(name);
    }


    /**
     * Gets the course's name.
     * @return The course's name.
     */
    public String getName() { return name; }


    /**
     * Set the courses's name.
     * @param name the courses's name
     * @throws Exception if the course's name is blank (whitespace or empty)<br>
     * Error Example: Student name can not be blank.
     */
    public void setName(String name) throws Exception {
        name = name.trim(); // remove leading and trailing whitespace


        //isBlank checks for both empty or whitespace
        if (name.isBlank())
            throw new Exception("Student name can not be blank.");


        this.name = name;
    } // end of setName method

    /**
     * Get a Student by their seat number using the ArrayList.indexOf method<br>
     * If the indexOf method returns -1 then this method returns null otherwise,<br>
     * it returns the Student object associated with the seat number.
     * @param seat The Student's seat number.
     * @return If a Student is found, it will return the Student object otherwise a null value.
     * @throws Exception Creating a student with a invalid seat number could throw an error
     */
    public Student getStudent(int seat) throws Exception {


        int index = students.indexOf(new Student(seat));


        if (index == -1)
            return null;
        else
            return students.get(index);


    }


    /**
     * Add a student to the Course, by using the overload constructor that allows setting their name and seat number too.<br>
     * This method will verify that the seat number is not already used by another student by calling the Course.getStudent method.<br>
     * and if it is, then it will throw an exception back to the calling method
     * @param name The Student's name.
     * @param seat The Student's seat number.
     * @throws Exception Seat number # already assigned.
     */
    public void addStudent(String name, int seat) throws Exception {
        Student student = getStudent(seat);
        if(student == null) {
            students.add(new Student(seat, name));
        } else {
            throw new Exception("Seat #" + seat + " already assigned to " + student.getName() + "!");
        }
    }



    /**
     * Get the total number of points for the entire Course by calling the Student.getPoints method.
     * Uses a for loop to sum all the course's students points by calling the student's getPoints
     * method.
     * @return The Courses's points.
     */
    public int getCoursePoints() {
        int totalPoints = 0;


        for (Student student : students) {
            totalPoints += student.getPoints();
        }

        return totalPoints;
    }

    /**
     * Get the total number of fouls for the entire Course using the Student.getFouls method.
     * Uses a for loop to sum all the course's students fouls by calling the student's getFouls
     * method.
     * @return The total number of fouls for the Course.
     */
    public int getCourseFouls() {
        int totalFouls = 0;


        for (Student student : students) {
            totalFouls += student.getFouls();
        }

        return totalFouls;
    }


    /**
     * Display the Course's summary stats using the Course.getCourseFouls and getCoursePoints methods.<br>
     * Example: <br>
     * <pre>Course Wildcats Fouls=4 Points=23</pre>
     */
    public void displayCourseStats() {
        System.out.println("Course " + name + " Fouls=" + getCourseFouls() + " Points=" + getCoursePoints());
    }

    /**
     * Displays each Student's detail stats for the entire Course using the Student's getter methods.<br>
     * This method uses the printf method for proper stats alignment. Example:<br>
     * <pre>
     * Seat Name            Fouls 1pt 2pt 3pt Total
     * ====== =============== ===== === === === =====
     *    10  Billy               1   2   3   1    10
     *    24  Tammy               0   0   2   0     4
     * </pre>
     */
    public void displayDetailStats() {


        Student student;


        displayCourseStats();


        System.out.println("Seat Name            Fouls 1pt 2pt 3pt Total");
        System.out.println("====== =============== ===== === === === =====");


        for (Student value : students) {


            student = value;


            System.out.printf("%6d %-15s %5d %3d %3d %3d %5d\n",
                    student.getSeat(),
                    student.getName(),
                    student.getFouls(),
                    student.getFieldGoals_1pt(),
                    student.getFieldGoals_2pt(),
                    student.getFieldGoals_3pt(),
                    student.getPoints());
        }

        System.out.println();
    }


    /**
     * Returns the course's name.
     * @return Course name.
     */
    @Override
    public String toString(){
        return name;
    }
}


