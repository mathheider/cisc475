package edu.udel.cisc475.kwic;

import java.io.IOException;
import java.io.PrintStream;

public class MasterControl {

	public final static void main(String[] args) throws IOException {
		PrintStream out = System.out;
		String fn = args[0];

		out.println("Result of Phase 1 (Input):");
		Input in = new Input(fn);
		in.execute();
		in.print(out);
		out.println();

		out.println("Result of Phase 2 (CircularShift):");
		CircularShift cs = new CircularShift(in.nchars, in.data, in.lines);
		cs.execute();
		cs.print(out);

		out.println("Result of Phase 3 (Alphabetizing):");
		Alphabetizing alpha = new Alphabetizing(cs.nchars, cs.data, cs.lines,
				cs.shifts);
		alpha.execute();
		alpha.print(out);

		out.println("Result of Phase 4 (Output):");
		Output op = new Output(alpha.nchars, alpha.data, alpha.lines,
				alpha.shifts);
		op.execute(out);
	}
}
