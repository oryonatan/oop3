package oop.ex3.crosswords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import oop.ex3.crosswords.CrosswordStructure.SlotType;
import oop.ex3.search.SearchBoardNode;

public class MyCrossword implements Crossword {

	private static final boolean VERTICAL = true;
	private static final boolean HORIZONTAL = false;
	// Grid size
	// protected MyCrosswordStructure myStruct;
	protected ArrayList<String> usedWords;
	protected ArrayList<String> unUsedWords;

	protected ArrayList<String> potentialWords;

	protected MyCrosswordStructure structure;

	// protected ArrayList</*CrosswordPosition*/>[] charPosList = new
	// ArrayList</**/>[26];
	private HashMap<Character, ArrayList<BoardPosition>> charPosList = new HashMap<Character, ArrayList<BoardPosition>>();

	// array to keep board status
	protected int fillboard[][];
	private CrosswordGlossary glossary;

	@Override
	public boolean isBestSolution() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<CrosswordEntry> getMovesIterator() {
		// create iterator , get potential words and structure
		return null;
	}

	@Override
	public int getQuality() {
		int charCounter = 0;
		for (String word : usedWords) {
			charCounter += word.length();
		}
		return charCounter;
	}

	@Override
	public int getQualityBound() {
		potentialWords = new ArrayList<String>();
		int counter = getQuality();
		for (String word : unUsedWords) {
			if (possibleWord(word))
				potentialWords.add(word);
			counter += word.length();
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
		this.glossary = glossary;

	}

	@Override
	public void attachStructure(CrosswordStructure structure) {
		this.structure = (MyCrosswordStructure) structure;
	}

	@Override
	public Collection<CrosswordEntry> getCrosswordEntries() {

		// TODO Auto-generated method stub
		return null;
	}

	private boolean possibleWord(String word) {
		int offset = 0;
		for (char c : word.toCharArray()) {
			ArrayList<BoardPosition> positions = charPosList.get(c);
			for (BoardPosition pos : positions) {
				if (null != wordPossibleInPos(word, pos, offset)) {
					return true;
				}
				offset++;
			}
		}
		// iterate over the rest of the board
		for (BoardPosition pos : structure.getFreeSlots()) {
			if (null !=  wordPossibleInPos(word, pos, 0)) {
				return true;
			}
		}
		return false;
	}

	private MyCrosswordEntry wordPossibleInPos(String word, BoardPosition pos, int offset) {

		BoardPosition curHorizontal = new BoardPosition(pos);
		int i=0;
		try {
			curHorizontal.moveLeft( offset - word.length());

			// / check horizontal
			int overlaping = 0; 
			
			for (i = word.length()-1 ; i>=0 ;i--)
			{
				if (structure.getSlotType(curHorizontal) == SlotType.UNUSED_SLOT)
				{
					curHorizontal.moveLeft(1);
					continue;
				}
				else if (structure.getSlotContent(curHorizontal) == word.toCharArray()[i])
				{
				 	curHorizontal.moveLeft(1);
				 	overlaping+=1;
				}
				break;
			}
			if (i == 0)
				curHorizontal.setVertical(HORIZONTAL);
				return new MyCrosswordEntry(word,glossary.getTermDefinition(word),curHorizontal,overlaping);
		}
		catch (OutOfBoardException e) {
			
		}
			
		BoardPosition curVertical = new BoardPosition(pos);
		try {
			curVertical.moveUp( offset - word.length());

			// / check horizontal
			int overlaping = 0; 
			
			for (i = word.length()-1 ; i>=0 ;i--)
			{
				if (structure.getSlotType(curVertical) == SlotType.UNUSED_SLOT)
				{
					curHorizontal.moveUp(1);
					continue;
				}
				else if (structure.getSlotContent(curVertical) == word.toCharArray()[i])
				{
				 	curHorizontal.moveUp(1);
				 	overlaping+=1;
				}
				break;
			}
			if (i == 0)
				curHorizontal.setVertical(VERTICAL);
				return new MyCrosswordEntry(word,glossary.getTermDefinition(word),curVertical,overlaping);
		}
		catch (OutOfBoardException e) {
			
		}
		return null;
	}

			
			
			
			
//			/*while (structure.getSlotType(curHorizontal) == SlotType.UNUSED_SLOT
//					|| (ifOverlapping = 
//						(structure.getSlotContent(curHorizontal) == word.toCharArray()[i]))) {
//				curHorizontal.moveLeft(-1);
//				i++;
//				if (ifOverlapping)
//					overlaping+=1;
//			}
//			if (i == word.length())
//				return new MyCrosswordEntry(curHorizontal,overlaping);
//
//		} catch (OutOfBoardException e) {
//			// do nothing
//		}
//
//		// check vertical
//
//		BoardPosition curVertical = new BoardPosition(pos);
//		try {
//			curVertical.moveUp(offset);
//			i = 0;
//			while (structure.getSlotType(curVertical) == SlotType.UNUSED_SLOT
//					|| structure.getSlotContent(curVertical) == word
//							.toCharArray()[i]) {
//				curVertical.moveUp(-1);
//				i++;
//			}
//			if (i == word.length())
//				return new MyCrosswordEntry(curVertical.moveLeft(i),overlaping);
//		} catch (OutOfBoardException e) {
//			// do nothing
//		}
//
//		return null;
//	}*/
		
		
	private class BoardIterator implements Iterator<CrosswordEntry> {

		//TreeSet<String> potentialWords;
		Iterator<String> wordIterator;
		Iterator<CrosswordEntry> entries;
		BoardIterator()
		{
			TreeSet<String> wordsTreeSet = new TreeSet<String>(new WordsComperator());
			wordsTreeSet.addAll(potentialWords);
			wordIterator = wordsTreeSet.iterator();
			entries = getWordEntries(); 
		}
		@Override
		public boolean hasNext() {
			
			if (entries.hasNext())
				return true;
			if(wordIterator.hasNext())			{
				entries = getWordEntries();
				return hasNext();
			}
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public CrosswordEntry next() {
			
			if (entries.hasNext())
			{
				return entries.next();
			}
			else if(wordIterator.hasNext())			{
				entries = getWordEntries();
				return next();
			}
			else
			{
				return null;
			}
				
		}

		private Iterator<CrosswordEntry> getWordEntries() {
			String word = wordIterator.next();
			TreeSet<MyCrosswordEntry> entriesTree = new TreeSet<MyCrosswordEntry>(new EntryComperator());
			
			return null;
		}
		
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
		
	}
}
