package edu.udel.cisc475.kwic;

import java.io.PrintStream;
import java.util.Arrays;

public class CircularShift {

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
	 * The circular shifts. The output of this class.
	 */
	LAPair[] shifts = null;

	public CircularShift(int nchars, int[] data, int[] lines) {
		this.nchars = nchars;
		this.data = data;
		this.lines = lines;
	}

	private char getChar(int i) {
		return (char) ((data[i / 4] >>> (8 * (i % 4))) & 255);
	}

	public void execute() {
		int nshifts = 0;
		shifts = new LAPair[2];
		for (int i = 0; i < lines.length - 1; i++) {
			int start = lines[i], stop = lines[i + 1], wc = 0;
			for (int j = start; j < stop; j++)
				if (getChar(j) == '$')
					wc++;
			// wc is the number of words on line i.
			// We will add wc entries to shifts.
			// First, ensure shifts is big enough...
			while (shifts.length < nshifts + wc)
				shifts = Arrays.copyOf(shifts, 2 * shifts.length);
			if (wc > 0) {
				shifts[nshifts] = new LAPair(i, start);
				nshifts++;
			}
			for (int j = start + 1; j < stop - 1; j++) {
				if (getChar(j) == '$') {
					shifts[nshifts] = new LAPair(i, j + 1);
					nshifts++;
				}
			}
		}
		shifts = Arrays.copyOf(shifts, nshifts);
	}

	public void print(PrintStream out) {
		for (int i = 0; i < shifts.length; i++)
			out.println(shifts[i]);
	}

}

class LAPair {
	int lineno;
	int addr;
	public LAPair(int lineno, int addr) {
		this.lineno = lineno;
		this.addr = addr;
	}

	public String toString() {
		return "[" + lineno + ", " + addr + "]";
	}
}
