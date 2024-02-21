import java.util.ArrayList;
import java.util.List;

public class AttendanceApp {

	private final static String DOUBLE_LINE = "==================================================";
	private final static String SINGLE_LINE = "--------------------------------------------------";

	Course section1;
	Course section2;

	public AttendanceApp() {
		section1 = new Course();
		section2 = new Course();
	}

    private void displayAppHeading() {
    	
		System.out.println(DOUBLE_LINE);
		System.out.println("Welcome to the Attendance App");
		System.out.println(DOUBLE_LINE);
		System.out.println();
		
    }

    private void setupCourses() throws Exception {

		String courseName = "Unknown";

		courseName = Input.getLine("Enter Section 1's course name: ");
		this.section1.setName(courseName);
		this.setupStudents(this.section1);

		System.out.println();

		courseName = Input.getLine("Enter Section 2's course name: ");
		this.section2.setName(courseName);
		this.setupStudents(this.section2);
    }
    
    private void setupStudents(Course course) {
    	String courseName = course.getName();
    	String studentName = null;
    	int studentSeat = 0;

    	while (true) {
			System.out.println();
			studentName = Input.getLine("Enter " + courseName + " student's name or 'q' to quit: ");
			
			if (studentName.equals("q"))
				return;
			
			try {
				studentSeat = Input.getIntRange("Enter " + studentName + " seat number: ", 0, 55);
				course.addStudent(studentSeat, studentName);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Unable to add student!");
			}
			
    	}
		    	
    }
    
    private void takeAttendance() throws Exception {
    	
    	boolean keepLooping = true;
    	int userInput = 0;
    	
    	System.out.println();
    	System.out.println(DOUBLE_LINE);    	
    	System.out.println("Recording Attendance!");
    	System.out.println(DOUBLE_LINE);
    	System.out.println();
    	
    	while (keepLooping) {
    		
    		System.out.println(SINGLE_LINE);
    		System.out.println("Main Menu");
    		System.out.println(SINGLE_LINE);
    		
    		System.out.println("0 = End Attendance App");
    		System.out.println("1 = Take " + section1.getName() + " Attendance");
    		System.out.println("2 = Take " + section2.getName() + " Attendance");
    		System.out.println("3 = Display All Attendance Report");
    		
    		System.out.println(SINGLE_LINE);
    		userInput = Input.getIntRange("Menu Choice: ", 0, 3);
    		System.out.println(SINGLE_LINE);
    		
    		System.out.println();
    		
    		switch (userInput) {
    		case 0:
    			keepLooping = false;
        		System.out.println();
        		break;
        		
    		case 1:
				this.takeAttendance(section1);
				break;
				
    		case 2:
   				this.takeAttendance(section2);
        		break;
        		
    		case 3:
    			this.displayDetailReports();
    			break;
    			
    		default:
    			System.out.println("Invalid menu choice = " + userInput);
    			
    		} 
    	}

    }
    
    private void takeAttendance(Course course) throws Exception {

    	int studentSeat = 0;
    	Student student;
    	
		while (true) {
			studentSeat = Input.getIntRange("Enter " + course.getName() + "'s Student Seat or 0 to quit: ", 0, 55);

			if (studentSeat == 0)
				break;

			student = course.getStudent(studentSeat);
			
			if (student == null) {
				System.out.println("Invalid seat, please try again!");
				continue;
			}
			
			this.updateStudent(student);
			
		}
			
		System.out.println();
		System.out.println(SINGLE_LINE);
		course.displaySummaryReport();
		System.out.println(SINGLE_LINE);
		System.out.println();

	}
    
    private void updateStudent(Student student) throws Exception {
    	int status = 0;

		System.out.println();
		
		System.out.println(SINGLE_LINE);
		System.out.println("Enter #" + student.getSeat() + " " + student.getName() + " Attendance");
		System.out.println(SINGLE_LINE);
		
		System.out.println("1 = Absent");
		System.out.println("2 = Late");
		System.out.println("3 = Excused");
		System.out.println("4 = On Time");
		
		System.out.println(SINGLE_LINE);
		status = Input.getIntRange("Enter Status: ", 1, 4);
		System.out.println(SINGLE_LINE);
		
		switch(status) {
			case 1:
				student.absent();
				break;
			case 2:
				student.late();
				break;
			case 3:
				student.excused();
				break;
			case 4:
				student.onTime();
				break;
			default:
				throw new Exception("Invalid attendance status!");
		}

		student.displayAttendance();
		System.out.println();
    }
    
    private void displayDetailReports() {

    	this.section1.displayDetailReport();
    	this.section2.displayDetailReport();

    }

	public static void main(String[] args) throws Exception {

		AttendanceApp app = new AttendanceApp();
		
		app.displayAppHeading();
		
		try {
			app.setupCourses();
			app.takeAttendance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry but this program ended with an error. Please contact Princess Debbie!");
		}
		
		Input.sc.close();
		
	} 
	
} 
