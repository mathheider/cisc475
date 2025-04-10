public interface CourseInterface {
    void setCode(String code);
    public String getCode();
    public void setNumber(int number);
    public int getNumber();
    public void setName(String name);
    public String getName();
    public void setInstructor(String instructor);
    public String getInstructor();
    public void setBuilding(String building);
    public String getBuilding();
    public void setRoom(int room);
    public int getRoom();
    public void setCapacity(int capacity);
    public int getCapacity();
    public String toString();
    public String toStringLong();
    private String code; // e.g., "CISC
	private int number; // e.g., 475
	private String name; // e.g. "Software Engineering"
	private String instructor;
	private String building;
	private int room; // room number
	private int capacity; // 
}
