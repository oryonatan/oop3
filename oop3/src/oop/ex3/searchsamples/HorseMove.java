package oop.ex3.searchsamples;

import oop.ex3.search.SearchMove;


/**
 * This class which represents a single move for HorseBoard-based puzzle.
 * It keeps 3 fields: x, y and move number.
 *
 * @author OOP
 */
class HorseMove implements SearchMove{
	/* fields */
	protected int x;
	protected int y;
	protected int moveNumber;

	HorseMove(int x, int y, int moveNumber) {
		this.x = x;
		this.y = y;
		this.moveNumber = moveNumber;
	}

	public int getNumber() {
		return this.moveNumber;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
