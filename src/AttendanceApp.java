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

        userInput = Input.getLine("Enter the AWAY TEAM name: ");
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
                seat = Input.getIntRange("Enter " + name + "'s jersey number: ", 0, 55);
                course.addStudent(name, seat);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Unable to add Student!");
            }

        }
    } //end of setupStudents
    private void mainMenu() throws Exception{

    }//end of mainMenu
    private void courseAttendance(Course course) throws Exception {

    } //end of courseAttendance
    private void studentAttendance(Student student){

    }//end of studentAttendance
    private void displayDetailReport(Course course) {

    } //end of displayAttendanceReport
    public static void main(String[] args) {
    AttendanceApp app = new AttendanceApp();
    app.displayAppHeading();
    try {
        app.setupCourses();
    } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Sorry but this program ended with an error. Please contact Isaiah!");
    }

    Input.sc.close();

    }//end of main
}