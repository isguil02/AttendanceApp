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

    public AttendanceApp(){
        section1 = new Course();
        section2 = new Course();
    }//end of default constructor
    private void displayAppHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Attendance App");
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println();
    } //- end of displayAppHeading
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
                seat = Input.getIntRange("Enter " + name + "'s seat number: ", 0, 55);
                course.addStudent(name, seat);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Unable to add Student!");
            }

        }
    } //end of setupStudents
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
                    displayDetailReports();
                    System.out.println();
                    break;

                case 1:
                case 2:

                    if (userInput == 1)
                        courseAttendance(section1);
                    else
                        courseAttendance(section2);

                    System.out.println();
                    displayDetailReports();
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
    private void courseAttendance(Course course) throws Exception {

        int seat;
        Student student;
        //display

        while (true) {
            seat = Input.getIntRange("Enter " + course.getName() + "'s Seat # ", 1, 55);

            student = course.getStudent(seat);

            if (student == null) {
                System.out.println("Invalid #, please try again!");
                continue;
            }

            studentAttendance(student);

            break;
        }

        System.out.println();
        System.out.println(SINGLE_DASH_LINE);

    } //end of courseAttendance
    private void studentAttendance(Student student){

        int type;

        System.out.println();

        System.out.println(SINGLE_DASH_LINE);
        System.out.println("Enter #" + student.getSeat() + " " +student.getName() + "  Attendance");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("0 = OnTime");
        System.out.println("1 = Late");
        System.out.println("2 = Excused");
        System.out.println("3 = Unexcused");

        System.out.println(SINGLE_DASH_LINE);
        type = Input.getIntRange("Enter Status: ", 0, 3);
        System.out.println(SINGLE_DASH_LINE);

        try {
            student.updateStats(type);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to update player's stats!");
        }

        student.displayStats();


    }//end of studentAttendance
    private void displayDetailReports() {
                section1.displaySummaryReport();
                section2.displaySummaryReport();
    } //end of displayAttendanceReport
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
}
