package oop.ex3.crosswords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
	
	
	protected ArrayList</*CrosswordPosition*/>[] charPosList = new ArrayList</**/>[26];
	
	
	
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
		for (char c : word.toCharArray())
			ArrayList<E> positions = charPosList[c - 'a' + 1];
		
			for /*סוג הפוזישן שלנו*/pos 
			wordPossibleInPos(word, )
			offset++;
		
		return false;
	}
	
	
	private boolean wordPossibleInPos(String word,int x , int y ){
		// check vertical
		
		int i =x;
		while (structure.getSlotType(/*crossword position*/ ) == SlotType.UNUSED_SLOT|| structure.getSlotContent(/*crossword position*/ )== word.toCharArray()[i-x]){
			i++;
		}
		if (i == x + word.length())
			return true;
		
		/// check horizontal
		i =y;
		while (structure.getSlotType(/*crossword position*/ ) == SlotType.UNUSED_SLOT|| structure.getSlotContent(/*crossword position*/ )== word.toCharArray()[i-y]){
			i++;
		}
		if (i == y + word.length())
			return true;
		
		return false;
		
		
	}
}
