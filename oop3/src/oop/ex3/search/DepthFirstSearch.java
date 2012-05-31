package oop.ex3.search;

/**
 * This interface contains the method for a search. The type parameter B stands
 * for a concrete type of SearchBoardNode.
 * 
 * An implementation must work with ANY class that implements the 
 * SearchBoardNode interface (including the supplied example), and not only with
 * implementations of the Crossword interface.
 * 
 * @author OOP
 */
public interface DepthFirstSearch<B extends SearchBoardNode<M>, M extends SearchMove> {
	
	/**
	 * Note: This method assumes that the graph is a tree, so it doesn't have to
	 * check for loops.
	 * 
	 * Performs a Depth-First search, up to depth maxDepth, and using at most
	 * timeOut ms (milliseconds), starting from the given startBoard.
	 * 
	 * @param startBoard
	 *            - the starting board of the search.
	 * @param maxDepth
	 *            - Maximal depth of your search. maxDepth 0 means that you
	 *            check only the given board; maxDepth 1 means that you check
	 *            only startBoard and its children, etc.
	 * @param timeOut
	 *            - time in milliseconds as estimated by java Date object.
	 * 
	 * @return SearchBoardNode with highest non-negative quality() found within
	 *        the given time (timeOut) and depth (maxDepth).
	 *        If all of the observed quality values are negative, returns null.
	 */
	public B search(B startBoard, int maxDepth, long timeOut);

}
