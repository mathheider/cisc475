package edu.udel.cisc475.kwic;

import java.io.PrintStream;

/**
 * <p>
 * A LineStorage object provides a structured view of some text object. In this
 * view, the text consists of a sequence of lines. Each line consists of a
 * sequence of words. Each word consists of a sequence of characters. This
 * interface provides methods to get and modify the text object using this view.
 * </p>
 * 
 * <p>
 * This is an example of a Java interface. It specifies methods and their
 * signatures, but does not provide implementations for them. Multiple classes
 * may implement this interface by providing definitions for these methods.
 * </p>
 */
public interface LineStorage {

	/**
	 * Get the number of lines in the text.
	 * 
	 * @return number of lines
	 */
	int numLines();

	/**
	 * Gets the number of words in a line.
	 * 
	 * @param line
	 *                 The ID number of a line, numbered from 0
	 * @return the number of words in that line
	 */
	int numWordsInLine(int line);

	/**
	 * Gets the number of characters in a word.
	 * 
	 * @param line
	 *                 the ID number of the line (numbered from 0)
	 * @param word
	 *                 the ID number of the word, numbered from 0 within that
	 *                 line
	 * @return the number of characters in the word
	 */
	int numCharsInWord(int line, int word);

	/**
	 * Gets a character in a word.
	 * 
	 * @param line
	 *                 the ID number of the line (numbered from 0)
	 * @param word
	 *                 the ID number of the word (numbered from 0 within the
	 *                 line)
	 * @param c
	 *                 the ID number of the character (numbered from 0 within
	 *                 the word)
	 * @return the character
	 */
	char getChar(int line, int word, int c);

	/**
	 * Gets a complete word as a string.
	 * 
	 * @param line
	 *                 the ID number of the line (numbered from 0)
	 * @param word
	 *                 the ID number of the word (numbered from 0 within the
	 *                 line)
	 * @return the word
	 */
	String getWord(int line, int word);

	/**
	 * Gets an entire line as a string.
	 * 
	 * @param line
	 *                 the ID number of the line (numbered from 0)
	 * @return the line as a string, with a single space separating each word
	 */
	String getLineAsString(int line);

	/**
	 * Adds a line to the text object.
	 * 
	 * @return the ID number of the new line
	 */
	int addLine();

	/**
	 * Adds a word to a line.
	 * 
	 * @return the ID number of the new word (words are numbered from 0 within
	 *         the line)
	 */
	int addWord(int line, String str);

	// etc.

	/**
	 * Prints the entire text object. Each line appears on one line. Words are
	 * separated by a single space character.
	 * 
	 * @param out
	 *                the stream to which to print, e.g., System.out
	 */
	void print(PrintStream out);
}
