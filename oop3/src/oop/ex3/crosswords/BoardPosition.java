package oop.ex3.crosswords;

public class BoardPosition implements CrosswordPosition {

	private int x, y;

	public BoardPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public boolean isVertical() {
		return false;
	}

}
