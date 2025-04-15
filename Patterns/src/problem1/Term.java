package problem1;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Term {

	private String name; // e.g. "Spring"

	private int year; // e.g. 2025

<<<<<<< HEAD
	private Set<CourseInterface> courses = new HashSet<CourseInterface>();
=======
	private Set<Course> courses = new HashSet<Course>();
>>>>>>> parent of 70edab8 (blah)

	public Term(String name, int year) {
		this.name = name;
		this.year = year;
	}

	public boolean addCourse(Course course) {
		return courses.add(course);
	}

	public boolean hasCourse(Course course) {
		return courses.contains(course);
	}

	public String toString() {
		return name + " " + year;
	}

	public void print(PrintStream out) {
		out.println(this + ":");
		for (Course course : courses)
			out.println("  " + course.toStringLong());
	}
	
	public Collection<Course> getCoursesWithCode(String code) {
		List<Course> result = new LinkedList<Course>();
		for (Course course : courses)
			if (code.equals(course.getCode()))
				result.add(course);
		return result;
	}

}
