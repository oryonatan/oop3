package oop.ex3.searchsamples;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import oop.ex3.search.*;

/**
 * This example implements board for well known "horse move" puzzle 
 * also known as "open knight tour"
 * http://en.wikipedia.org/wiki/Knight's_tour
 * 
 * It keeps board status in array and creates iterator for new moves.
 * @author OOP
 */
class HorseBoard implements SearchBoardNode<HorseMove> {
	/* properties */

	// List of all moves done so far 
	protected List<HorseMove> movesRecord = new LinkedList<HorseMove>();
	
	// Grid size
	protected int sizeX, sizeY;
	
	// array to keep board status
	protected int fillboard[][];
	protected int quality = 0;

	/**
	 * Constructor.
	 * Creates a board by dimensions
	 **/
	public HorseBoard(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.fillboard = new int[sizeX][sizeY];
	}

	// Returns true if done N x M moves for board of size N x M.
	// Note that for some board sizes, it's impossible to fill the entire
	// board, meaning that in such cases the method always returns false. 
	@Override
	public boolean isBestSolution() {
		return (this.quality == this.sizeX * this.sizeY);
	}

	// Applies a move to the board and stores it in record
	@Override
	public void doMove(HorseMove move) {
		// Update quality
		this.quality = move.getNumber();
		
		// Updated fillboard
		this.fillboard[move.getX()][move.getY()] = this.quality;
		
		// Add move to the first position
		this.movesRecord.add(0, move);
	}

	// Undoes the most recent move and removes it from list
	// Note that the in general the method assumes that it is supplied with the
	// most recent move that should be un-undoed.
	// In this specific example, we keep track of the moves so we can use the 
	// kept record. 
	@Override
	public void undoMove(HorseMove move) {
		// Get most recent move
		HorseMove lastMove = this.movesRecord.get(0);

		// Check if move is valid
		assert(move == lastMove);
		
		// Restore board
		this.fillboard[lastMove.getX()][lastMove.getY()] = 0;
		
		// Update record
		this.movesRecord.remove(0);
		this.quality--;
	}

	

	// Returns an iterator for current board's moves.
	// For crossword you can either do it this way or design iterator
	// which creates moves "on the fly" when requested.
	@Override
	public Iterator<HorseMove> getMovesIterator() {
		
		List<HorseMove> movesList;
		
		if (this.movesRecord.isEmpty()) {
			movesList = createInitialMovesList();
		} else {
			movesList = createMovesList(this.movesRecord.get(0));
		}
		
		return movesList.iterator();
	}

	// Overriding default method for printing
	// You don't have to do it for crosswords
	// but you may want to do it for debugging
	public String toString() {
		String s = "";
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				s += String.format("%5d", this.fillboard[i][j]);
				;
			}
			s += "\n";
		}
		return s;
 	}

	/* start internal (protected) methods) */
	protected List<HorseMove> createMovesList(HorseMove fromMove) {
		List<HorseMove> movesList = new LinkedList<HorseMove>();
		// List of all possible advancement pairs for chess knight
		int[][] movesDirections = new int[][] { { -2, -1 }, { -2, 1 },
				{ 2, -1 }, { 2, 1 }, { -1, 2 }, { 1, 2 }, { -1, -2 }, { 1, -2 } };

		for (int i = 0; i < movesDirections.length; i++) {
			int newDirX = movesDirections[i][0] + fromMove.getX();
			int newDirY = movesDirections[i][1] + fromMove.getY();
			int newNumber = fromMove.getNumber() + 1;
			
			// Check if move is legal and add it to list
			if (newDirX >= 0 && newDirX < this.sizeX && newDirY >= 0
					&& newDirY < this.sizeY && this.fillboard[newDirX][newDirY] == 0)
				movesList.add(new HorseMove(newDirX, newDirY, newNumber));
		}
		return movesList;
	}

	
	protected List<HorseMove> createInitialMovesList() {
		List<HorseMove> movesList = new LinkedList<HorseMove>();
		movesList.add(new HorseMove(0, 0, 1));
		return movesList;
	}
	
	@Override
	public int getQuality() {
		return this.quality;
	}

	@Override
	public int getQualityBound() {
		return this.sizeX * this.sizeY;
	}

	@Override
	public SearchBoardNode<HorseMove> getCopy() {
		HorseBoard board = new HorseBoard(this.sizeX,this.sizeY);
		board.fillboard = Arrays.copyOf(this.fillboard, this.fillboard.length);
		
		for (int i = 0; i < this.fillboard.length; i++) {
			board.fillboard[i] = 
				Arrays.copyOf(this.fillboard[i], this.fillboard[i].length);
			
		}
		
		board.quality = this.quality;
		return board;
	}
}
