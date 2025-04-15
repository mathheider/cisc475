package edu.udel.cisc475.numlist;

import java.io.PrintStream;

public class Demo {
	public static void main(String[] args) {
		PrintStream out = System.out;
		ImmutableNumList e = ImmutableNumList.emptyList();
		out.println("The empty list is: " + e);
		assert e.isEmpty();
		NonemptyNumList l3 = e.cons(3);
		out.println("List containing just 3: " + l3);
		assert !l3.isEmpty();
		assert l3.first() == 3;
		assert l3.rest().isEmpty();
		NonemptyNumList l123 = l3.cons(2).cons(1);
		out.println("List l123 consisting of 1, 2, and 3: " + l123);
		assert l123.first() == 1;
		assert ((NonemptyNumList) l123.rest()).first() == 2;
		out.println("Size of l123: " + l123.size());
		NonemptyNumList l923 = l123.set(0, 9);
		out.println("List l923 consisting of 9, 2, and 3: " + l923);
		out.println("List l123 consisting of 1, 2, and 3: " + l123);
		out.println("OK.");
	}
}
