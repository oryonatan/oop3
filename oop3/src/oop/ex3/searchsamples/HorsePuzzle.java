package oop.ex3.searchsamples;

import oop.ex3.search.*;

/**
 * This example implements board for well known "horse move" puzzle 
 * also known as "open knight tour"
 * http://en.wikipedia.org/wiki/Knight's_tour
 * @author OOP
 */
public class HorsePuzzle {

	/**
	 * This function validates input parameters and executes the puzzle
	 * @param args 3 parameters - X,Y and timeout
	 */
	public static void main(String[] args) {
		long timeOut;
		int xSize,ySize;
		
		/* check valid number of args */
		if (args.length < 3) {
			System.out.println("Arguments should be X Y Time");
			return;
		}
		
		/* check validity of arguments, convert into properties */
		try {
			timeOut = Long.parseLong(args[2]);
			xSize = Integer.parseInt(args[0]);
			ySize = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid string inside arguments - should be X(int) Y(int) Time(long)");
			return;
		}
		if (xSize<=0 || ySize<=0 || timeOut<=1 || xSize>20 || ySize>20) {
			System.out.println("All numbers should be positive and reasonable");
			return;
		}

		/* start the puzzle  */
		// 1) Create board
		HorseBoard board = new HorseBoard(xSize, ySize);
		
		// 2) Create search
		DepthFirstSearch<HorseBoard,HorseMove> theSearch = 
			new MyDepthFirstSearch<HorseBoard,HorseMove>();
		
		// 3) Search board for solution
		HorseBoard solution = theSearch.search(board, xSize*ySize, timeOut);
		
		// 4) Display solution
		System.out.println("Solution:\n" + solution);
		System.out.println("The quality of the obtained solution is "+solution.getQuality());
		System.out.println("This is "+(!solution.isBestSolution()?"not " :"")+"the best possible solution.");
	}
}
