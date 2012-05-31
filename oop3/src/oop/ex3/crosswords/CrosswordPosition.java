package oop.ex3.crosswords;

/**
 * Specifies a basic position of an entry in a crossword.
 * @author OOP
 */
public interface CrosswordPosition {

	/**
	 * @return The x coordinate.
	 */
	public abstract int getX();

	/**
	 * 
	 * @return The Y coordinate.
	 */
	public abstract int getY();

	/**
	 * @return True iff position is a vertical position.
	 */
	public abstract boolean isVertical();

}