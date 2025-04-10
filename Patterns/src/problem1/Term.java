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

	private Set<CourseInterface> courses = new HashSet<Course>();

	public Term(String name, int year) {
		this.name = name;
		this.year = year;
	}

	public boolean addCourse(CourseInterface course) {
		return courses.add(course);
	}

	public boolean hasCourse(CourseInterface course) {
		return courses.contains(course);
	}

	public String toString() {
		return name + " " + year;
	}

	public void print(PrintStream out) {
		out.println(this + ":");
		for (CourseInterface course : courses)	
			out.println("  " + course.toStringLong());
	}
	
	public Collection<CourseInterface> getCoursesWithCode(String code) {
		List<CourseInterface> result = new LinkedList<CourseInterface>();
		for (CourseInterface course : courses)
			if (code.equals(course.getCode()))
				result.add(course);
		return result;
	}

}
