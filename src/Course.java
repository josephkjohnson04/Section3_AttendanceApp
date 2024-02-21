import java.util.ArrayList;
import java.util.List;

public class Course {

	private String name;
	private List<Student> allStudents;

	public Course() {
		this.name = "Unknown";
		allStudents = new ArrayList<Student>();
	}
	
	public Course(String name) throws Exception {
		this();
		this.setName(name);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) throws Exception {
		name = name.trim();

		if (name.isEmpty())
			throw new Exception("Student name can not be blank.");

		this.name = name;
	}
	
	public Student getStudent(int seat) throws Exception {
		
		int index = this.allStudents.indexOf(new Student(seat));
		
		if (index == -1)
			return null;
		else
			return this.allStudents.get(index);
	}

	public void addStudent(int seat, String name) throws Exception {
		Student student = this.getStudent(seat);

		if (student == null) {
			this.allStudents.add(new Student(seat, name));
		} else {
			throw new Exception("Seat: " + seat + " is already assigned to this course " + student.getName() + "!");
		}
	}

	public int getTotalAbsent() {
		int totalAbsent = 0;
	
		for (int i = 0; i < this.allStudents.size(); i++) {
			totalAbsent += this.allStudents.get(i).getAbsent();
		}
		
		return totalAbsent;
	}

	public int getTotalExcused() {
		int totalExcused = 0;

		for (int i = 0; i < this.allStudents.size(); i++) {
			totalExcused += this.allStudents.get(i).getExcused();
		}

		return totalExcused;
	}

	public int getTotalLate() {
		int totalLate = 0;

		for (int i = 0; i < this.allStudents.size(); i++) {
			totalLate += this.allStudents.get(i).getLate();
		}

		return totalLate;
	}

	public int getTotalOnTime() {
		int totalOnTime = 0;

		for (int i = 0; i < this.allStudents.size(); i++) {
			totalOnTime += this.allStudents.get(i).getOnTime();
		}

		return totalOnTime;
	}

	public void displaySummaryReport() {
		System.out.printf(this.name);
		System.out.print(" Absent=" + this.getTotalAbsent());
		System.out.print(" Excused=" + this.getTotalExcused());
		System.out.print(" Late=" + this.getTotalLate());
		System.out.println(" OnTime=" + this.getTotalOnTime());
	}

	public void displayDetailReport() {

		this.displaySummaryReport();
		System.out.println();
		System.out.println("Seat   Name            Absent Excused Late OnTime");
		System.out.println("====== =============== ====== ======= ==== ======");
		
    	for(int i = 0; i < allStudents.size(); i++) {
    		System.out.printf("%6d %-15s %6d %4d %7d %6d\n",
    				this.allStudents.get(i).getSeat(),
					this.allStudents.get(i).getName(),
					this.allStudents.get(i).getAbsent(),
					this.allStudents.get(i).getExcused(),
					this.allStudents.get(i).getLate(),
					this.allStudents.get(i).getOnTime());
    	}
    	
    	System.out.println();
	}

	@Override
	public String toString(){
		return this.name;
	}

} 
