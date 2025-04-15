package edu.udel.cisc475.kwic;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

public class Alphabetizing {

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
	 * The circular shifts.
	 */
	LAPair[] shifts;

	public Alphabetizing(int nchars, int[] data, int[] lines, LAPair[] shifts) {
		this.nchars = nchars;
		this.data = data;
		this.lines = lines;
		this.shifts = shifts;
	}

	private char getChar(int i) {
		return (char) ((data[i / 4] >>> (8 * (i % 4))) & 255);
	}

	class LAPComparator implements Comparator<LAPair> {
		/**
		 * Returns -1 if the circular shift represented by lap1 occurs before
		 * lap2, 0 if they are exactly equal, and +1 if lap2 occurs before lap1.
		 */
		public int compare(LAPair lap1, LAPair lap2) {
			int addr1 = lap1.addr, addr2 = lap2.addr;
			int start1 = lines[lap1.lineno], stop1 = lines[lap1.lineno + 1];
			int start2 = lines[lap2.lineno], stop2 = lines[lap2.lineno + 1];

			while (true) {
				char c1 = getChar(addr1), c2 = getChar(addr2);
				if (c1 < c2)
					return -1;
				if (c1 > c2)
					return 1;
				addr1++;
				addr2++;
				if (addr1 == stop1)
					addr1 = start1; // cycle back to beginning
				if (addr2 == stop2)
					addr2 = start2; // cycle back to beginning
				if (addr1 == lap1.addr && addr2 == lap2.addr)
					return 0; // strings are identical
				if (addr1 == lap1.addr)
					return -1; // lap1 is a prefix of lap2
				if (addr2 == lap2.addr)
					return 1; // lap2 is a prefix of lap1
			}
		}
	}

	/** Sorts shifts */
	public void execute() {
		Arrays.sort(shifts, new LAPComparator());
	}

	public void print(PrintStream out) {
		for (int i = 0; i < shifts.length; i++)
			out.println(shifts[i]);
	}
}
