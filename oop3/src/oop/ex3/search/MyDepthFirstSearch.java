package oop.ex3.search;

import java.util.Iterator;

/**
 * Recursive DFS for traversing over Searchbode and finding best solution
 * 
 * @author oryonatan,yulishap
 * 
 * @param <B>
 *            extends SearchBoardNode<M> the source board.
 * @param <M>
 *            move - board move type
 */
public class MyDepthFirstSearch<B extends SearchBoardNode<M>, M extends SearchMove>
		implements DepthFirstSearch<B, M> {

	private int highestUQ;
	private int topQualitySoFar = 0;
	private B bestBoard;
	private long endTime;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oop.ex3.search.DepthFirstSearch#search(oop.ex3.search.SearchBoardNode,
	 * int, long)
	 */
	@Override
	public B search(B startBoard, int maxDepth, long timeOut) {
		// Search the board with DFS , using the recursive explore method to
		// traverse.
		// best board is saved in the bestBoard parameter , and later returned
		// to caller.
		endTime = System.currentTimeMillis() + timeOut;
		highestUQ = startBoard.getQualityBound();
		Iterator<M> iter = startBoard.getMovesIterator();
		// While there is time left , and there are more moves
		while (iter.hasNext() && System.currentTimeMillis() < endTime) {
			M move = iter.next();
			startBoard.doMove(move);
			// Recurse in
			explore(startBoard, maxDepth);
			// If found the top quality in a node return best board found.
			if (topQualitySoFar == highestUQ) {
				// Set best board
				return bestBoard;
			}
			// Go back , try next move
			startBoard.undoMove(move);
		}
		// If best possible board was not found - return best so far.
		return bestBoard;
	}

	/**
	 * Recursive DFS explore method , to detect best possible board at a given
	 * time frame
	 * 
	 * @param startBoard
	 *            board to recurse on
	 * @param depthLeft
	 *            - max depth possible in recursion
	 */
	@SuppressWarnings("unchecked")
	public void explore(B startBoard, int depthLeft) {
		int curQuality = startBoard.getQuality();
		// If found the best board so far - set it as best board
		if (topQualitySoFar < curQuality) {
			topQualitySoFar = curQuality;
			bestBoard = (B) startBoard.getCopy();
		}
		// If found best solution - return it
		if (topQualitySoFar == highestUQ) {
			// Set best board
			return;
		}

		if (curQuality == highestUQ) {
			return; // Best board found
		}// else if board is of maximum quality that is lower then best quality
			// found so far - stop recursing
		if (startBoard.getQualityBound() <= topQualitySoFar) {
			return;
		}

		Iterator<M> iter = startBoard.getMovesIterator();
		if (depthLeft <= 0)
			return;
		while (iter.hasNext() && System.currentTimeMillis() < endTime) {
			M move = iter.next();
			startBoard.doMove(move);
			explore(startBoard, depthLeft - 1);
			startBoard.undoMove(move);
		}
	}

}
