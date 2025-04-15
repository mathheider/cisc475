package edu.udel.cisc475.kwic;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class Input {

	/** Name of input file. */
	String filename;

	/**
	 * Number of characters stored, including the special end-of-word character
	 * '$'.
	 */
	int nchars = 0;

	/**
	 * The characters, "packed 4 to a word". A word is a 32-bit int. Each entry
	 * in this array represents one word. There is a special end-of-word
	 * character '$'.
	 */
	int[] data = null;

	/**
	 * Array of length number of lines plus 1. Lines are numbered starting from
	 * 0. lines[i] is the index of the first character of line i.
	 * lines[numLines] is the total number of characters.
	 */
	int[] lines = null;

	public Input(String filename) {
		this.filename = filename;
	}

	/** Converts an array of 4 ASCII chars into a 32-bit int. */
	private int pack4(char[] chars) {
		int result = 0;
		assert (chars.length == 4);
		for (int i = 0; i < 4; i++) {
			int val = chars[i];
			if (val < 0 || val > 255)
				throw new IllegalArgumentException(
						"Non-ASCII character " + val + ": '" + chars[i] + "'");
			result |= (val << (i * 8));
		}
		return result;
	}

	/** Reads the file and populates the fields of this class. */
	public void execute() throws IOException {
		FileReader fr = new FileReader(filename);
		data = new int[1];
		lines = new int[2];
		lines[0] = 0;
		char[] buf = new char[4];
		boolean inNewWord = false;
		int dataidx = 0, bufidx = 0, lineidx = 1;
		while (true) {
			int nread = fr.read(buf, bufidx, 1);
			if (nread < 0)
				break;
			char c = buf[bufidx];
			if (Character.isWhitespace(c)) {
				if (inNewWord) {
					inNewWord = false;
					buf[bufidx] = '$';
					bufidx++;
				}
				if (c == '\n') {
					if (lineidx == lines.length)
						lines = Arrays.copyOf(lines, 2 * lines.length);
					lines[lineidx] = nchars + bufidx;
					lineidx++;
				}
			} else {
				if (!inNewWord)
					inNewWord = true;
				bufidx++;
			}
			if (bufidx == 4) {
				if (dataidx == data.length)
					data = Arrays.copyOf(data, 2 * data.length);
				data[dataidx] = pack4(buf);
				dataidx++;
				nchars += 4;
				bufidx = 0;
			}
		}
		fr.close();
		if (inNewWord) {
			buf[bufidx] = '$';
			bufidx++;
		}
		if (bufidx > 0) {
			if (dataidx == data.length)
				data = Arrays.copyOf(data, data.length + 1);
			data[dataidx] = pack4(buf);
			dataidx++;
			nchars += bufidx;
		}
		data = Arrays.copyOf(data, dataidx); // trim to length
		lines = Arrays.copyOf(lines, lineidx);
	}

	public void print(PrintStream out) {
		out.println("data length = " + data.length + ", nchars = " + nchars
				+ ".  Data:");
		int remainingChars = nchars;
		for (int i = 0; i < data.length && remainingChars > 0; i++) {
			int val = data[i];
			for (int j = 0; j < 4 && remainingChars > 0; j++) {
				int c = val & 255;
				char a = (char) c;
				out.print(a);
				remainingChars--;
				val >>>= 8;
			}
			out.println();
		}
		out.println("Line starts = " + Arrays.toString(lines));
	}

	public static void main(String[] args) throws IOException {
		String fn = args[0];
		Input in = new Input(fn);
		in.execute();
		in.print(System.out);
	}

}
