package oop.ex3.crosswords;

/**
 * Char counter object to represent number of chars in a location from different
 * words
 * 
 * @author yulishap,yonatan
 * 
 */
public class charCounter {
	private int counter = 0;
	private char charecter;

	/**
	 * Ctor for char counter
	 * 
	 * @param c
	 *            the char to count
	 */
	public charCounter(char c) {
		this.setCharecter(c);
	}

	/**
	 * Default constructor , sets the tile to #
	 * 
	 */
	public charCounter() {
		this.setCharecter('#');
	}

	/**
	 * Removes 1 from the counter , if 0 is reached , put '_' as the char
	 * instead (marking free space)
	 * 
	 * @return the count
	 */
	public int removeChar() {
		if (counter > 0)
			counter--;
		if (counter == 0)
			setCharecter('_');
		return counter;
	}

	/**
	 * Gets the char in the counter
	 * 
	 * @return the char
	 */
	public char getCharecter() {
		return charecter;
	}

	/**
	 * Changes the char in the counter
	 * 
	 * @param charecter
	 *            the char to set
	 */
	public void setCharecter(char charecter) {
		this.charecter = charecter;
	}

	/**
	 * Updates char counter
	 * 
	 * @param c
	 *            the char to update
	 * @return counter of chars
	 */
	public int update(char c) {
		if (c == charecter)
			counter++;
		else {
			charecter = c;
			counter = 1;
		}
		return counter;
	}

}
