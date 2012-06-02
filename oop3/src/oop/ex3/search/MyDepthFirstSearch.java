package oop.ex3.search;

import java.util.Iterator;

public class MyDepthFirstSearch<B extends SearchBoardNode<M>, M extends SearchMove>
		implements DepthFirstSearch<B, M> {

	private int topUQ;
	private int topQualitySoFar = 0;
	private B bestBoard;
	private long endTime;
	
	@Override
	public B search(B startBoard, int maxDepth, long timeOut) {

//		endTime = System.currentTimeMillis() + timeOut;		
		endTime =  Long.MAX_VALUE;
		topUQ = startBoard.getQualityBound();
		Iterator<M> iter = startBoard.getMovesIterator();
		while (iter.hasNext() && System.currentTimeMillis() < endTime){
			M move = iter.next();
			startBoard.doMove(move);
			explore(startBoard, maxDepth);
			if (topQualitySoFar == topUQ) {
				return bestBoard;
			}
			startBoard.undoMove(move);
		}
		return bestBoard;
	}		

	public void explore(B startBoard, int depthLeft) {
		int curQuality = startBoard.getQuality();
		if (topQualitySoFar < curQuality){
			topQualitySoFar = curQuality;
			bestBoard = (B) startBoard.getCopy();
		}
		if (topQualitySoFar == topUQ) {
			return;
		}
		
		if (curQuality == topUQ) {
			return ; // Best board found
		}// else v
		if (startBoard.getQualityBound() <= topQualitySoFar) {
			return;
		}
		
		Iterator<M> iter = startBoard.getMovesIterator();
		if (depthLeft <= 0) return;
		while (iter.hasNext() && System.currentTimeMillis() < endTime ) {
			M move = iter.next();
			startBoard.doMove(move);
			explore(startBoard, depthLeft-1);
			startBoard.undoMove(move);
		}




	}

}
