package problem1;

public class Course {
	private String code = ""; // e.g., "CISC"

	private int number = 0; // e.g., 475

	private String name = ""; // e.g. "Software Engineering"

	private String instructor = "";

	private String building = "";

	private int room = 0; // room number

	private int capacity = 0; // max number of students

	public Course() {
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
		return code + number + ": " + name;
	}

	public String toStringLong() {
		return toString() + "[instructor=" + instructor + ", room=" + building
				+ " " + room + ", capacity=" + capacity + "]";
	}
}
