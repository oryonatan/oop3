package oop.ex3.crosswords;


/**
 * Represents a board position
 * 
 * @author yonatan,yulishap
 * 
 */
public class BoardPosition implements CrosswordPosition,
		Comparable<BoardPosition> {

	public static final boolean VERTICAL = true;
	public static final boolean HORIZONTAL = false;

	private int x, y;
	private boolean isVertical;

	/**
	 * Copy constructor ctor , creates a new position
	 * 
	 * @param from
	 *            source to copy from
	 */
	public BoardPosition(BoardPosition from) {
		this.x = from.getX();
		this.y = from.getY();
	}

	/**
	 * Moves the position left by an offset
	 * 
	 * @param offset
	 *            number of tiles to move
	 */
	public void moveLeft(int offset) {
		this.x -= offset;
	}

	/**
	 * Moves the position up by an offset
	 * 
	 * @param offset
	 *            number of tiles to move
	 */
	public void moveUp(int offset) {
		this.y -= offset;
	}

	/**
	 * Moves the position in a given position
	 * 
	 * @param offset
	 *            number of tiles to move
	 * @param direction
	 *            VERT
	 */
	public void moveInDirection(int offset, boolean direction) {
		if (direction == VERTICAL) {
			moveUp(offset);
		} else {
			moveLeft(offset);
		}
	}

	/**
	 * Ctor for position
	 * 
	 * @param x
	 *            coordinate to set
	 * @param y
	 *            coordinate to set
	 */
	public BoardPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.crosswords.CrosswordPosition#getX()
	 */
	@Override
	public int getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.crosswords.CrosswordPosition#getY()
	 */
	@Override
	public int getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.crosswords.CrosswordPosition#isVertical()
	 */
	@Override
	public boolean isVertical() {
		return isVertical;
	}

	/**
	 * Sets the orientation to vertical/horizontal
	 * 
	 * @param orientation
	 *            VERTICAL for vertical HORIZONTAL for horizontal
	 */
	public void setOrientation(boolean orientation) {
		this.isVertical = orientation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BoardPosition other) {
		// Comparator , comperaing order is y coordinate first and x coordinate
		// second
		int compared = ((Integer) y).compareTo(other.y);
		if (compared == 0)
			compared = ((Integer) x).compareTo(other.x);
		return compared;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		// true if in the same position
		if (compareTo((BoardPosition) other) == 0) {
			return true;
		}
		return false;
	}
}
