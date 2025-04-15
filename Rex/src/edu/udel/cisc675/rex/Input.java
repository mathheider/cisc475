package edu.udel.cisc675.rex;
import java.io.FileReader;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;

/* Module Input: reads the file and stores the sequence of characters
   read in an array of char.  To use this class: instantiate it with
   the filename, then call method execute(), then you can read the
   field chars. */
public class Input {

	/* The name of the original file containing the exam (in) */
	String filename;

	/* The contents of the file, as an array of char (out) */
	char[] chars;

	/*
	 * Constructs a new instance with given filename. Sets the filename field
	 * and does nothing else
	 */
	public Input(String filename) {
		this.filename = filename;
	}

	/* Opens the file, reads it, and constructs chars. */
	public void execute() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(filename);
		int off = 0; // number of chars read
		chars = new char[2]; // start at 2 but double each time cap reached
		while (true) {
			int nread = fr.read(chars, off, chars.length - off);
			if (nread < 0)
				break;
			off += nread;
			if (off == chars.length) // double buffer size
				chars = Arrays.copyOf(chars, 2 * chars.length);
		}
		fr.close();
		chars = Arrays.copyOf(chars, off); // trim to exact size of data
	}
}
