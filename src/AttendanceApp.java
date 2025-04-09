/**
 * Class Attendance Application
 *
 * @author Isaiah Guilliatt
 * @version 1.0 beta
 * @since 2025.02.21
 * @see <a href="https://github.com/isguil02/AttendanceAppV2">GitHub Repository</a>
 */
public class AttendanceApp {
    /** The class 1 attendance. */
    private final Course section1;

    /** The class 2 attendance. */
    private final Course section2;

    /** Standard double dash line for display output */
    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

    /** Standard single dash line for display output */
    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    /** Default constructor that instantiate both courses */
    public AttendanceApp(){
        section1 = new Course();
        section2 = new Course();
    }//end of default constructor

    /**
     *  Displays the Attendance App header. Example:<br>
     *  <pre>
     *  =========================================
     *  Welcome to the Attendance App
     *  =========================================
     *  </pre>
     */
    private void displayAppHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Attendance App");
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println();
    } //- end of displayAppHeading

    /**
     * Sets the courses names and calls setupStudents to set up each course's students. Example:<br>
     * <pre>
     * Enter Section 1 Class name: 9:00 am
     * -----------------------------------------
     * Enter the Section 2's course name: 10:00 am
     * </pre>
     * @throws Exception if the setters throw an error back due to data validation.
     */
    private void setupCourses() throws Exception {
        String userInput;

        userInput = Input.getLine("Enter Section 1 Class name: ");
        section1.setName(userInput);
        setupStudents(section1);

        System.out.println();
        System.out.println(SINGLE_DASH_LINE);
        System.out.println();

        userInput = Input.getLine("Enter the Section 2's course name: ");
        section2.setName(userInput);
        setupStudents(section2);
    }//end of setupCourses

    /**
     * Sets up the course's students.<br>
     * Calls Input.getLine to get the student's name<br>
     * Calls Input.getIntRange to get the student's seat number between 1 and 55<br>
     * Example:<br>
     * <pre>
     * Enter 9:00 am student's name or 'q' to quit: Bob
     * Enter Bob's seat number: 10
     * </pre>
     * @param course The course to set up students for.
     */
    private void setupStudents(Course course) {
        String courseName = course.getName();
        String name;
        int seat;

        while (true) {
            System.out.println();
            name = Input.getLine("Enter " + courseName + " student's name or 'q' to quit: ");

            if (name.equals("q"))
                return;

            try {
                seat = Input.getIntRange("Enter " + name + "'s seat number: ", 1, 55);
                course.addStudent(name, seat);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Unable to add Student!");
            }

        }
    } //end of setupStudents

    /**
     * Displays the main menu and handles user input for the menu options. Example:<br>
     * <pre>
     * --------------------------------------------------
     * Main Menu
     * --------------------------------------------------
     * 0 = End Attendance App
     * 1 = Take 9:00 am 's Attendance
     * 2 = Take 10:00 am 's Attendance
     * 3 = Display All Attendance Reports
     * --------------------------------------------------
     * Menu Choice:
     * </pre>
     * @throws Exception if the setupCourses or courseAttendance methods throw an error
     */
    private void mainMenu() throws Exception {


        boolean playGame = true;
        int userInput;

        System.out.println();
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Recording Attendance!");
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println();


        while (playGame) {

            System.out.println(SINGLE_DASH_LINE);
            System.out.println("Main Menu");
            System.out.println(SINGLE_DASH_LINE);

            System.out.println("0 = End Attendance App");
            System.out.println("1 = Take " + section1.getName() + " 's Attendance");
            System.out.println("2 = Take " + section2.getName() + " 's Attendance");
            System.out.println("3 = Display All Attendance Reports");

            System.out.println(SINGLE_DASH_LINE);
            userInput = Input.getIntRange("Menu Choice: ", 0, 3);
            System.out.println(SINGLE_DASH_LINE);

            System.out.println();

            switch (userInput) {
                case 0:
                    playGame = false;
                    System.out.println();
                    break;

                case 1:
                case 2:

                    if (userInput == 1)
                        courseAttendance(section1);
                    else
                        courseAttendance(section2);

                    System.out.println();

                    System.out.println();
                    break;

                case 3:
                    displayDetailReports();
                    break;

                default:
                    System.out.println("Invalid menu choice = " + userInput);

            } // end of switch
        }
    }//end of mainMenu

    /**
     * Update the select course's attendance.<br>
     * Calls Input.getIntRange using range from 1 and 55.<br>
     * Example: <br>
     * <pre>
     * Enter 9:00 am's Student Seat or 0 to quit: user input
     * </pre>
     * @param course The course to update attendance for.
     * @throws Exception could throw an invalid seat error
     */
    private void courseAttendance(Course course) throws Exception {

        int seat;
        Student student;
        //display

        while (true) {
            seat = Input.getIntRange("Enter " + course.getName() + "'s Student Seat or 0 to quit: ", 0, 55);
            if (seat == 0)
                break;
            student = course.getStudent(seat);

            if (student == null) {
                System.out.println("Invalid seat, please try again!");
                continue;
            }

            studentAttendance(student);

            System.out.println();
        }

        System.out.println();
        System.out.println(SINGLE_DASH_LINE);
        course.displaySummaryReport();
        System.out.println(SINGLE_DASH_LINE);
    } //end of courseAttendance

    /**
     * Displays the attendance for a student. Example:<br>
     * <pre>
     * --------------------------------------------------
     * Enter #1 Joe Attendance
     * --------------------------------------------------
     * 1 = On Time
     * 2 = Late
     * 3 = Excused
     * 4 = Unexcused
     * --------------------------------------------------
     * Enter Status: 3
     * --------------------------------------------------
     * Seat #1 Joe OnTime=0 Late=0 Excused=1 Unexcused=0
     *
     * Enter 10:00 am's Student Seat or 0 to quit: 2
     *
     * --------------------------------------------------
     * </pre>
     * @param student The student to display attendance for.
     */
    private void studentAttendance(Student student){

        int type;

        System.out.println();

        System.out.println(SINGLE_DASH_LINE);
        System.out.println("Enter #" + student.getSeat() + " " +student.getName() + " Attendance");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("1 = On Time");
        System.out.println("2 = Late");
        System.out.println("3 = Excused");
        System.out.println("4 = Unexcused");

        System.out.println(SINGLE_DASH_LINE);
        type = Input.getIntRange("Enter Status: ", 1, 4);
        System.out.println(SINGLE_DASH_LINE);

        try {
            student.updateAttendance(type-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to update player's stats!");
        }

        student.displayAttendance();
        System.out.println();


    }//end of studentAttendance

    /**
     * Displays the detail reports for both courses.<br>
     * Calls Course.displayDetailReport for each course.
     */
    private void displayDetailReports() {
                section1.displayDetailReport();
                section2.displayDetailReport();
    } //end of displayAttendanceReport

    /**
     * Main method that creates the AttendanceApp object and then
     * setups up the courses and displays the main menu.
     * <br>
     * 1) creates a new AttendanceApp<br>
     * 2) calls the displayAppHeading method<br>
     * 3) using a try-catch calls setupCourses and mainMenu methods<br>
     * <br>
     * @param args No command line input args are used for this application
     */
    public static void main(String[] args) {
    AttendanceApp app = new AttendanceApp();
    app.displayAppHeading();
    try {
        app.setupCourses();
        app.mainMenu();
    } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Sorry but this program ended with an error. Please contact Isaiah!");
    }

    Input.sc.close();

    }//end of main
} //end of AttendanceApp
