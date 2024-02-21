public class Student {

	private int seat;
	
	private String name;

	private int absent;

	private int excused;

	private int late;
	
	private int onTime;
	
	private Student(){
		this.seat = 0;
		this.name = "Unknown";
		this.absent = 0;
		this.excused = 0;
		this.late = 0;
		this.onTime = 0;
	}
	
	public Student(int seat) throws Exception {
		this();
		this.setSeat(seat);
	}

	public Student(int seat, String name) throws Exception {
		this();
		this.setName(name);
		this.setSeat(seat);
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

	public int getSeat() {
		return this.seat;
	}

	public void setSeat(int id) throws Exception {
		if (id < 0)
			throw new Exception("Student ID can not be negative.");
		
		this.seat = id;
	}

	public int getAbsent() {
		return absent;
	}

	public void absent() {
		this.absent++;
	}

	public int getExcused() {
		return this.excused;
	}

	public void excused() {
		this.excused++;
	}

	public int getLate() {
		return this.late;
	}

	public void late() {
		this.late++;
	}

	public int getOnTime() {
		return this.onTime;
	}

	public void onTime() {
		this.onTime++;
	}

	public void displayAttendance(){
		System.out.print(this.name);
		System.out.print(" Absent=" + this.getAbsent());
		System.out.print(" Excused=" + this.getExcused());
		System.out.print(" Late=" + this.getLate());
		System.out.println(" OnTime=" + this.getOnTime());
	}

	@Override
    public boolean equals(Object object) {
        
        if(!(object instanceof Student))
            return false;
        
        Student other = (Student)object;
        
       	if (this.seat == other.getSeat())
       		return true;
       	else
       		return false;
    }
	
	@Override 
	public String toString(){
		return this.seat + " " + this.name;
	}
	
}
