package edu.udel.cisc475.kwic;

import java.io.PrintStream;

public class Output {

	private LineStorage shifts;

	private Alphabetizer alpha;

	public Output(LineStorage shifts, Alphabetizer alpha) {
		this.shifts = shifts;
		this.alpha = alpha;
	}

	public void print(PrintStream out) {
		int n = shifts.numLines();
		for (int i = 0; i < n; i++)
			out.println(shifts.getLineAsString(alpha.ith(i)));
	}
}
