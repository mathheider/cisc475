package edu.udel.cisc475.kwic;

import java.io.IOException;
import java.io.PrintStream;

public class MasterControl {

	public final static void main(String[] args) throws IOException {
		PrintStream out = System.out;
		String fn = args[0];

		out.println("Result of Input:");
		Input in = new Input(fn);
		LineStorage ls1 = in.getWords();
		ls1.print(out);
		out.println();

		out.println("Result of CircularShifter:");
		CircularShifter cs = new CircularShifter(ls1);
		LineStorage ls2 = cs.csSetup();
		ls2.print(out);
		out.println();

		out.println("Result of Alphabetizer:");
		Alphabetizer alpha = new Alphabetizer(ls2);
		alpha.alph();
		alpha.print(out);
		out.println();

		out.println("Result of Output:");
		Output op = new Output(ls2, alpha);
		op.print(out);
	}
}
