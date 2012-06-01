package oop.ex3.crosswords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import oop.ex3.crosswords.CrosswordStructure.SlotType;
import oop.ex3.search.SearchBoardNode;

public class MyCrossword implements Crossword {

	
	// Grid size
	//protected MyCrosswordStructure myStruct;
	protected ArrayList<String> usedWords;
	protected ArrayList<String> unUsedWords;
	
	protected ArrayList<String> potentialWords;
	
	protected MyCrosswordStructure structure;
	
	
	//protected ArrayList</*CrosswordPosition*/>[] charPosList = new ArrayList</**/>[26];
	private HashMap<Character, ArrayList<BoardPosition>> charPosList = 
			new HashMap<Character, ArrayList<BoardPosition>>() ;  
	
	
	// array to keep board status
	protected int fillboard[][];
	
	@Override
	public boolean isBestSolution() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<CrosswordEntry> getMovesIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getQuality() {
		int charCounter = 0;
		for(String word : usedWords){
			charCounter+=word.length();
		}
		return charCounter;
	}

	@Override
	public int getQualityBound() {
		potentialWords = new ArrayList<String>();
		int counter  = getQuality();
		for (String word : unUsedWords){
			if (possibleWord(word))
				potentialWords.add(word);
				counter+=word.length();
		}
		return counter;
	}

	@Override
	public void doMove(CrosswordEntry move) {
		// TODO Auto-generated method stub

	}

	@Override
	public void undoMove(CrosswordEntry move) {
		// TODO Auto-generated method stub

	}

	@Override
	public SearchBoardNode<CrosswordEntry> getCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attachGlossary(CrosswordGlossary glossary) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachStructure(CrosswordStructure structure) {
		this.structure= (MyCrosswordStructure)structure;
	}

	@Override
	public Collection<CrosswordEntry> getCrosswordEntries() {


		
		// TODO Auto-generated method stub
		return null;
	}

	private boolean possibleWord(String word){
		int offset = 0;
		for (char c : word.toCharArray()){
			ArrayList<BoardPosition> positions = charPosList.get(c);
			for (BoardPosition pos : positions){
				if (wordPossibleInPos(word, pos)){
					return true;
				}
				offset++;
			}
		}
		//iterate over the rest of the board
		for (BoardPosition pos: structure.getFreeSlots()){
			if (wordPossibleInPos(word, pos)){
				return true;
			}
		}
		return false;
	}
	
	
	private boolean wordPossibleInPos(String word,BoardPosition pos){
		// check vertical
		
		int i =pos.getX();
		while (structure.getSlotType(pos) == SlotType.UNUSED_SLOT|| structure.getSlotContent(pos)== word.toCharArray()[i-pos.getX()]){
			i++;
		}
		if (i == pos.getX() + word.length())
			return true;
		
		/// check horizontal
		i =pos.getY();
		while (structure.getSlotType(pos) == SlotType.UNUSED_SLOT|| structure.getSlotContent(pos)== word.toCharArray()[i-pos.getY()]){
			i++;
		}
		if (i == pos.getY() + word.length())
			return true;
		return false;
		
		
	}
}
