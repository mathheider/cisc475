public interface CourseInterface {
    void setCode(String code);
    public String getCode();
    public int getNumber();
    public String getName();
    public String getInstructor();
    public String getBuilding();
    public int getRoom();
    public int getCapacity();
    private String code; // e.g., "CISC
	private int number; // e.g., 475
	private String name; // e.g. "Software Engineering"
	private String instructor;
	private String building;
	private int room; // room number

	private int capacity; // 
}
