import java.util.ArrayList;
import java.util.List;

/**
 * Use to keep track of a basketball Course that is made up with multiple Students along with the Course's stats.
 * @author Isguil02
 * @since 2025.02.22
 * @version 1.0 beta
 * @see <a href="https://github.com/isguil02/AttendanceAppV2">GitHub Repository</a>
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
            throw new Exception("Seat: " + seat + " is already assigned to " + student.getName() + "!");
        }
    }

    /**
     * Displays the Course's summary stats for the entire Course using the Student's getter methods.<br>
     * This method uses the printf method for proper stats alignment. Example:<br>
     * <pre>
     * 9:00 am OnTime=10 Late=5 Excused=2 Unexcused=3
     * </pre>
     */
    public void displaySummaryReport() {
        Student student;
        int totalOnTimes = 0;
        int totalLate = 0;
        int totalExcused = 0;
        int totalUnexcused = 0;
        for (Student value : students) {
            student = value;
            totalOnTimes+= student.getOnTime();
            totalLate+= student.getLate();
            totalExcused+= student.getExcused();
            totalUnexcused+= student.getUnexcused();
        }
        System.out.println(name + " OnTime=" + totalOnTimes + " Late=" + totalLate + " Excused=" + totalExcused + " Unexcused=" + totalUnexcused);
    }


    /**
     * Displays the Course's detailed stats for the entire Course using the Student's getter methods.<br>
     * This method uses the printf method for proper stats alignment. Example:<br>
     * <pre>
     * Seat Name            OnTime Late Excused Unexcused
     * ==== =============== ====== ==== ======= =========
     *    1 Joe                  1    0       0         0
     *    2 Billy                0    1       0         0
     * </pre>
     */
    public void displayDetailReport() {
        Student student;

        displaySummaryReport();

        System.out.println("Seat Name            OnTime Late Excused Unexcused");
        System.out.println("==== =============== ====== ==== ======= =========");

        for (Student value : students) {

            student = value;

            System.out.printf("%4d %-15s %6d %4d %7d %9d \n",
                    student.getSeat(),
                    student.getName(),
                    student.getOnTime(),
                    student.getLate(),
                    student.getExcused(),
                    student.getUnexcused());
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




