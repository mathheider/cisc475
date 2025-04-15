package edu.udel.cisc475.kwic;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * An implementation of {@link LineStorage} in which the entire object is stored
 * in-memory in a structured way. A word is represented as a {@link String}. A
 * line is represented as an {@link ArrayList} of {@link String}. The line
 * storage object is represented as an {@link ArrayList} of {@link ArrayList} of
 * {@link String}.
 */
public class CommonLineStorage implements LineStorage {

	/**
	 * For each line, the sequence of words in that line.
	 */
	private ArrayList<ArrayList<String>> theWords = new ArrayList<>();

	/**
	 * Creates a new empty text.
	 */
	public CommonLineStorage() {
	}

	@Override
	public int numLines() {
		return theWords.size();
	}

	@Override
	public int numWordsInLine(int line) {
		return theWords.get(line).size();
	}

	@Override
	public int numCharsInWord(int line, int word) {
		return theWords.get(line).get(word).length();
	}

	@Override
	public char getChar(int line, int word, int c) {
		return theWords.get(line).get(word).charAt(c);
	}

	@Override
	public String getWord(int line, int word) {
		return theWords.get(line).get(word);
	}

	@Override
	public String getLineAsString(int line) {
		StringBuffer buf = new StringBuffer();
		for (String word : theWords.get(line)) {
			buf.append(word);
			buf.append(" ");
		}
		return buf.toString();
	}

	@Override
	public int addLine() {
		theWords.add(new ArrayList<>());
		return theWords.size() - 1;
	}

	@Override
	public int addWord(int line, String str) {
		ArrayList<String> wordsInLine = theWords.get(line);
		wordsInLine.add(str);
		return wordsInLine.size() - 1;
	}

	// etc.

	@Override
	public void print(PrintStream out) {
		for (ArrayList<String> line : theWords) {
			for (String word : line) {
				out.print(word);
				out.print(" ");
			}
			out.println();
		}
	}
}
