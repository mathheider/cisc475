package edu.udel.cisc475.kwic;

import java.io.PrintStream;

public class Output {

	/**
	 * Number of characters stored, including the special end-of-word character
	 * '$'.
	 */
	int nchars;

	/**
	 * The characters, "packed 4 to a word". A word is a 32-bit int. Each entry
	 * in this array represents one word. There is a special end-of-word
	 * character '$'.
	 */
	int[] data;

	/**
	 * Array of length number of lines plus 1. Lines are numbered starting from
	 * 0. lines[i] is the index of the first character of line i.
	 * lines[numLines] is the total number of characters.
	 */
	int[] lines;

	/**
	 * The circular shifts from class CircularShift.
	 */
	LAPair[] shifts;

	public Output(int nchars, int[] data, int[] lines, LAPair[] shifts) {
		this.nchars = nchars;
		this.data = data;
		this.lines = lines;
		this.shifts = shifts;
	}

	private char getChar(int i) {
		return (char) ((data[i / 4] >>> (8 * (i % 4))) & 255);
	}

	void print(PrintStream out, LAPair lap) {
		int addr = lap.addr;
		int start = lines[lap.lineno], stop = lines[lap.lineno + 1];
		while (true) {
			char c = getChar(addr);
			if (c == '$')
				out.print(" ");
			else
				out.print(c);
			addr++;
			if (addr == stop)
				addr = start; // cycle back to beginning
			if (addr == lap.addr)
				break;
		}
		out.println();
	}

	public void execute(PrintStream out) {
		for (int i = 0; i < shifts.length; i++)
			print(out, shifts[i]);
	}

}
