package oop.ex3.search;

import java.util.Iterator;


/**
 * A search board node is a possible solution of a problem, for example
 * a partially completed crossword. The type parameter M stands for 
 * a concrete type that extends SearchMove.
 * 
 * You can assume that concrete SearchBoardNode/SearchMove implementations are
 * developed in pairs and are always compatible.
 * 
 * @author OOP
 * 
 */
public interface SearchBoardNode<M extends SearchMove> {
	
	/**
	 * Returns true if a node object represents a one of the best 
	 * possible solutions of the problem, false otherwise.
	 * 
	 * @return whether this board is a best solution.
	 */
	boolean isBestSolution();

	/**
	 * Creates and returns an iterator on the list of nodes reachable by a
	 * single edge from the current node (in case of crossword - a list of
	 * crosswords with one more word in the grid).
	 * 
	 * @return Iterator object  
	 */
	Iterator<M> getMovesIterator();

	/**
	 * This function allows evaluation of quality of solutions 
	 * (higher values mean better solutions).
	 * 
	 * @return The quality value of this board.
	 */
	int getQuality();

	/**
	 * This function allows estimation of potential 
	 * upper bound on quality of solutions available through zero or 
	 * more doMove() operations from the current board.
	 * 
	 * The returned value have to be always greater or equal to
	 * the best possible quality obtained through doMove operations.
	 * Hence, it is also assumed that doMove operation should never increase 
	 * the upper bound value.
	 * 
	 * @return The upper bound on quality of this board.
	 */
	int getQualityBound();
	
	/**
	 * Performs a move on the board potentially (reversibly) changing 
	 * the current board object.
	 * @param move
	 */
	void doMove(M move);
	
	/**
	 * Restores the object to the state before the last move.
	 * It is assumed that a sequence of undoMove operations always
	 * reflects in the correct order the sequence of doMove operations.
	 * Hence undoMove is always supplied with the most recent un-undoed move.
	 * (You don't have to check this assumption)
	 * @param move
	 */
	void undoMove(M move);

	/**
	 * Creates a stand-alone copy of the current board.
	 * The returned copy should not be affected by subsequent doMove/undoMove
	 * operations on the current board.
	 * @return a copy of this board.
	 */
	public SearchBoardNode<M> getCopy();
}
