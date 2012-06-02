package oop.ex3.crosswords;

public class BoardPosition implements CrosswordPosition , Comparable<BoardPosition> {

	private static final boolean VERTICAL = true;
	private static final boolean HORIZONTAL = false;
	private int x, y;
	private boolean isVertical;
	
	public BoardPosition(BoardPosition from){
		this.x = from.getX();
		this.y = from.getY();
	}
	
	public void moveLeft(int offset)  {

		this.x -=offset;
	}
	public void moveUp(int offset)  {

		this.y -=offset;
	}
	
	public void moveInDirection(int offset,boolean direction){
		if (direction == VERTICAL){
			moveUp(offset);
		}
		else{
			moveLeft(offset);
		}
	}
	
	
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
		return isVertical;
	}

	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}

	@Override
	public int compareTo(BoardPosition other) {
		int compared = ((Integer)y).compareTo(other.y);
		 if (compared == 0) 
			 compared = ((Integer)x).compareTo(other.x);
		return compared ;
	}
	
	public boolean equals(BoardPosition other){
		if(compareTo(other) == 0){
			return true;
		}
		return false;
	}

}
