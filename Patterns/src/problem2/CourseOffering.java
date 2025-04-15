package problem2;

<<<<<<< HEAD
public class CourseOffering {
	private Course course; // course being offered
=======
public class CourseOffering extends Course {
>>>>>>> parent of 70edab8 (blah)

	private Term term; // term in which this offering takes place

	private String instructor = "";

	private String building = "";

	private int room = 0; // room number

	private int capacity = 0; // max number of students

	public CourseOffering() {
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Term getTerm() {
		return term;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuilding() {
		return building;
	}

	public void setRoom(int room) {
		this.room = room;
	}
	public int getRoom() {
		return room;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public String toString() {
		return super.toString() + "[term=" + term + ", instructor=" + instructor
				+ ", room=" + building + " " + room + ", capacity=" + capacity
				+ "]";
	}

}
