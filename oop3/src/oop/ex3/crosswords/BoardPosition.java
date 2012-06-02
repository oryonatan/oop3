package oop.ex3.crosswords;

public class BoardPosition implements CrosswordPosition , Comparable<BoardPosition> {

	private int x, y;
	private boolean isVertical;
	
	public BoardPosition(BoardPosition from){
		this.x = from.getX();
		this.y = from.getY();
	}
	
	public void moveLeft(int offset) throws OutOfBoardException{
		if (x - offset < 0 ){
			throw new OutOfBoardException();
		}
		this.x -=offset;
	}
	public void moveUp(int offset) throws OutOfBoardException{
		if (y - offset < 0 ){
			throw new OutOfBoardException();
		}
		this.y -=offset;
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
	

}
