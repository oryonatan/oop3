package oop.ex3.crosswords;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * This is a basic implementation of CrosswordStructure. 
 * The shape is stored as list of strings.
 * 
 * @author OOP
 */
public class MyCrosswordStructure implements CrosswordStructure {
	
	// Stores the crossword structure, each element in the list 
	// corresponds to a row of the structure  
	protected List<String> dataList = new ArrayList<String>();
	protected charCounter[][] data;
	HashSet<BoardPosition> freeSlots;

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordStructure#getHeight()
	 */
	public Integer getHeight() {
		return this.dataList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordStructure#getWidth()
	 */
	public Integer getWidth() {
		return (getHeight() != 0) ?  this.dataList.get(0).length() : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordStructure#getSlotType(CrosswordPosition)
	 */
	public SlotType getSlotType(CrosswordPosition pos) {
		
		// check position validity
		if (pos.getX() >= getWidth() || pos.getX() < 0 || 
				pos.getY() >= getHeight() || pos.getY() < 0)
			return SlotType.FRAME_SLOT;
		
		switch (this.data[pos.getY()][pos.getX()].getCharecter()) {
		case '_':
			return SlotType.UNUSED_SLOT;
		case '#':
			return SlotType.FRAME_SLOT;
		default :
			return SlotType.USED_SLOT;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordStructure#load
	 */
	public void load(String textFileName) throws IOException {
		Scanner sc=null;
		try {
			this.dataList = new ArrayList<String>();
			sc = new Scanner(new FileReader(textFileName));
			while (sc.hasNextLine()) {
				this.dataList.add(sc.nextLine());
			}
		} finally {
			if (sc!=null) sc.close();
		}
		
		//Data array
		data = new charCounter[dataList.get(0).length()][dataList.size()];
		int lineNum = 0;
		int charNum; 
		for (String line : dataList){
			charNum = 0;
			for (char c: line.toCharArray()){
				data[lineNum][charNum] = new charCounter(c);
				if (c == '_'){
					freeSlots.add(new BoardPosition(lineNum,charNum));
				}
			}
			charNum++;
		}
		lineNum++;
	}

	public HashSet<BoardPosition> getFreeSlots() {
		return freeSlots;
	}

	public char getSlotContent(BoardPosition pos) {
		return data[pos.getX()][pos.getY()].getCharecter();
	}

	public void addEntry(CrosswordEntry move) {
		
		BoardPosition position = (BoardPosition) move.getPosition();
		int x = position.getX();
		int y = position.getY();
		String word = move.getTerm();
		
		if (position.isVertical())
		{
			for (int i = 0 ; i< word.length(); i++){
				data[x][y + i].update(word.toCharArray()[i]);
				freeSlots.remove(new BoardPosition(x, y+i));
			}
		}
		else {
			for (int i = 0 ; i< word.length(); i++)
			{
				data[x + i][y].update(word.toCharArray()[i]);
				freeSlots.add(new BoardPosition(x+i, y));
			}
		}
		
	}

	public void removeEntry(CrosswordEntry move) {
		BoardPosition position = (BoardPosition) move.getPosition();
		int x = position.getX();
		int y = position.getY();
		String word = move.getTerm();
		
		if (position.isVertical())
		{
			for (int i = 0 ; i< word.length(); i++)
				if (data[x][y + i].removeChar() == 0 ){
					freeSlots.add(new BoardPosition(x, y+i));
				}
		}
		else {
			for (int i = 0 ; i< word.length(); i++)
				if(data[x + i][y].removeChar() == 0){
					freeSlots.add(new BoardPosition(x+i, y));
				}
		}
	}

}
