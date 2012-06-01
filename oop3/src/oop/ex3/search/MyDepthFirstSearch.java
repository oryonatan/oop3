package oop.ex3.search;

import java.util.Iterator;

public class MyDepthFirstSearch<B extends SearchBoardNode<M>, M extends SearchMove>
		implements DepthFirstSearch<B, M> {

	private int topUQ;
	private int topQualitySoFar = 0;
	private B bestBoard;
	
	@Override
	public B search(B startBoard, int maxDepth, long timeOut) {
		// DEBUG
		boolean isDebug = java.lang.management.ManagementFactory
				.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("-agentlib:jdwp") > 0;
		if (isDebug) {
			timeOut = Long.MAX_VALUE;
		}
		// DEBUG
//		long initialTime = System.currentTimeMillis();		
		topUQ = startBoard.getQualityBound();
		Iterator<M> iter = startBoard.getMovesIterator();
		while (iter.hasNext()){
			M move = iter.next();
			startBoard.doMove(move);
			explore(startBoard, maxDepth, timeOut);
			if (topQualitySoFar == topUQ) {
				return bestBoard;
			}
			startBoard.undoMove(move);
		}
		return bestBoard;
	}		

	public void explore(B startBoard, int maxDepth, long timeOut) {
		int curQuality = startBoard.getQuality();
		if (topQualitySoFar < curQuality){
			topQualitySoFar = curQuality;
			bestBoard = (B) startBoard.getCopy();
		}
		
		if (curQuality == topUQ) {
			return ; // Best board found
		}// else v
		if (startBoard.getQualityBound() <= topQualitySoFar) {
			return;
		}
		
		Iterator<M> iter = startBoard.getMovesIterator();
		while (iter.hasNext()) {
			M move = iter.next();
			startBoard.doMove(move);
			explore(startBoard, maxDepth, timeOut);
			startBoard.undoMove(move);
		}
		return;

	}

}
