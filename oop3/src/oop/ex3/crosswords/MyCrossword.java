package oop.ex3.crosswords;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import oop.ex3.crosswords.CrosswordStructure.SlotType;
import oop.ex3.search.SearchBoardNode;

public class MyCrossword implements Crossword {

	private static final String ABC = "abcdefghijklmnopqrstuvwxyz";
	private static final boolean ADD = true;
	private static final boolean REMOVE = false;
	// Grid size
	// protected MyCrosswordStructure myStruct;
	protected ArrayList<CrosswordEntry> usedWords = new ArrayList<CrosswordEntry>();
	protected ArrayList<String> unUsedWords = new ArrayList<String>();
	private HashSet<CrosswordPosition> startingTiles = new HashSet<CrosswordPosition>();
	protected ArrayList<String> potentialWords;

	protected MyCrosswordStructure structure;

	// protected ArrayList</*CrosswordPosition*/>[] charPosList = new
	// ArrayList</**/>[26];
	private HashMap<Character, ArrayList<BoardPosition>> charPosList;

	// array to keep board status
	protected int fillboard[][];
	private CrosswordGlossary glossary;
	private int topQuality;

	/**
	 * Ctor for crossword
	 * 
	 */
	public MyCrossword() {
		usedWords = new ArrayList<CrosswordEntry>();
		unUsedWords = new ArrayList<String>();
		startingTiles = new HashSet<CrosswordPosition>();
		initializeCharPosLists();
	}

	/**
	 * Ctor with a giver crossword entries input
	 * 
	 * @param crosswordEntries
	 *            list of enteries to copy
	 */
	public MyCrossword(Collection<CrosswordEntry> crosswordEntries) {
		this.usedWords = new ArrayList<CrosswordEntry>();
		this.usedWords.addAll(crosswordEntries);
	}

	/**
	 * Intializes char position lists
	 * 
	 */
	private void initializeCharPosLists() {
		charPosList = new HashMap<Character, ArrayList<BoardPosition>>();
		for (char c : ABC.toCharArray()) {
			charPosList.put(c, new ArrayList<BoardPosition>());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.search.SearchBoardNode#isBestSolution()
	 */
	@Override
	public boolean isBestSolution() {
		if (getQuality() == topQuality)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.search.SearchBoardNode#getMovesIterator()
	 */
	@Override
	public Iterator<CrosswordEntry> getMovesIterator() {
		return new BoardIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.search.SearchBoardNode#getQuality()
	 */
	@Override
	public int getQuality() {
		int charCounter = 0;
		for (CrosswordEntry word : usedWords) {
			charCounter += word.getTerm().length();
		}
		return charCounter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.search.SearchBoardNode#getQualityBound()
	 */
	@Override
	public int getQualityBound() {
		// Iterate over all the words and find which one is potetially usable
		potentialWords = new ArrayList<String>();
		int counter = getQuality();
		for (String word : unUsedWords) {
			if (possibleWord(word))
				potentialWords.add(word);
			counter += word.length();
		}
		return counter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.search.SearchBoardNode#doMove(oop.ex3.search.SearchMove)
	 */
	@Override
	public void doMove(CrosswordEntry move) {
		structure.addEntry(move);
		unUsedWords.remove(move.getTerm());
		usedWords.add(move);
		startingTiles.add(move.getPosition());
		updateCharPositionList(move, ADD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.search.SearchBoardNode#undoMove(oop.ex3.search.SearchMove)
	 */
	@Override
	public void undoMove(CrosswordEntry move) {
		updateCharPositionList(move, REMOVE);
		structure.removeEntry(move);
		unUsedWords.add(move.getTerm());
		usedWords.remove(move);
		startingTiles.remove(move.getPosition());

	}

	/**
	 * Update char position optimization table
	 * 
	 * @param move
	 *            the move to add
	 * @param ifAdd
	 *            true if adding , false if removeing
	 */
	private void updateCharPositionList(CrosswordEntry move, boolean ifAdd) {
		BoardPosition pos = new BoardPosition(
				(BoardPosition) move.getPosition());
		String word = move.getTerm();
		boolean direction = move.getPosition().isVertical();
		// Invcrease/decrease each char counter
		for (char c : word.toCharArray()) {
			if (ifAdd)
				charPosList.get(c).add(new BoardPosition(pos));
			else
				charPosList.get(c).remove(new BoardPosition(pos));

			pos.moveInDirection(-1, direction);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.search.SearchBoardNode#getCopy()
	 */
	@Override
	public SearchBoardNode<CrosswordEntry> getCopy() {
		return new MyCrossword(getCrosswordEntries());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.crosswords.Crossword#attachGlossary(oop.ex3.crosswords.
	 * CrosswordGlossary)
	 */
	@Override
	public void attachGlossary(CrosswordGlossary glossary) {
		this.glossary = glossary;
		this.topQuality = countChars(glossary.getTerms());
		unUsedWords.addAll(glossary.getTerms());
	}

	/**
	 * Coint chars in a list of terms, return length
	 * 
	 * @param terms
	 *            list of terms
	 * @return counter of chars
	 */
	private int countChars(Set<String> terms) {
		int counter = 0;
		for (String term : terms) {
			counter += term.length();
		}
		return counter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.crosswords.Crossword#attachStructure(oop.ex3.crosswords.
	 * CrosswordStructure)
	 */
	@Override
	public void attachStructure(CrosswordStructure structure) {
		this.structure = (MyCrosswordStructure) structure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex3.crosswords.Crossword#getCrosswordEntries()
	 */
	@Override
	public Collection<CrosswordEntry> getCrosswordEntries() {
		return usedWords;
	}

	/**
	 * Checks if it is possible to add a word
	 * 
	 * @param word
	 *            the word to check
	 * @return true if it is possible
	 */
	private boolean possibleWord(String word) {
		int offset = 0;
		for (char c : word.toCharArray()) {
			// First look in the optimization table
			ArrayList<BoardPosition> positions = charPosList.get(c);
			if (null != positions) {
				for (BoardPosition pos : positions) {
					if (wordPossibleInPos(word, new BoardPosition(pos), offset)) {
						return true;
					}

				}
			}
			offset++;
		}
		// If not found in the optimization table , look over rest of the free
		// tiles in the board
		for (BoardPosition pos : structure.getFreeSlots()) {
			if (wordPossibleInPos(word, pos, 0)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if it is possible to put a word in a position
	 * 
	 * @param word
	 *            the word to add
	 * @param pos
	 *            the position to add the word in
	 * @param offset
	 *            offset of the position from beginning of the word
	 * @param direction
	 *            the orientation of the word
	 * @return crossword entry of the word
	 */
	private MyCrosswordEntry wordPossibleInPos(String word, BoardPosition pos,
			int offset, boolean direction) {
		BoardPosition curPosition = new BoardPosition(pos);
		int i = 0;
		curPosition.moveInDirection(offset - (word.length() - 1), direction);
		// If the slot is a frem slot , fail
		if (structure.getSlotType(curPosition) == SlotType.FRAME_SLOT) {
			return null;
		}

		int overlaping = 0;
		// Check each letter , if the same letter is found in a given location ,
		// increase overlapping counter
		for (i = word.length() - 1; i >= 0; i--) {
			if (structure.getSlotType(curPosition) == SlotType.FRAME_SLOT)
				return null;
			else if (structure.getSlotType(curPosition) == SlotType.UNUSED_SLOT) {
				curPosition.moveInDirection(1, direction);
				continue;
			} else if (structure.getSlotContent(curPosition) == word
					.toCharArray()[i]) {
				curPosition.moveInDirection(1, direction);
				overlaping += 1;
				continue;
			}
			return null;
		}
		curPosition.moveInDirection(-1, direction);
		if (i < 0) {
			curPosition.setOrientation(direction);
			if (startingTiles.contains(curPosition)) {
				return null;
			}
			// if the word is succesfully inseartable - return an entry of
			// setting the word in the location
			return new MyCrosswordEntry(word, glossary.getTermDefinition(word),
					curPosition, overlaping);
		}

		return null;
	}

	/**
	 * Checks if it possible to put a word on a location , not checking
	 * direction
	 * 
	 * @param word
	 *            the word to put
	 * @param pos
	 *            the position to put the word in
	 * @param offset
	 *            the offsset from start of the word in which the position
	 *            should appear
	 * @return true if it is possible to put the word in the location , false
	 *         overwise
	 */
	private boolean wordPossibleInPos(String word, BoardPosition pos, int offset) {
		if (null != wordPossibleInPos(word, pos, offset, BoardPosition.VERTICAL)) {
			return true;
		}
		if (null != wordPossibleInPos(word, pos, offset,
				BoardPosition.HORIZONTAL)) {
			return true;
		}
		return false;
	}

	/**
	 * Inner class - iterator over entries
	 * 
	 * @author yonatan,yulishap
	 * 
	 */
	private class BoardIterator implements Iterator<CrosswordEntry> {
		Iterator<String> wordIterator;
		Iterator<MyCrosswordEntry> entries;
		private String currentWord;

		/**
		 * Ctor for class
		 * 
		 */
		BoardIterator() {
			TreeSet<String> wordsTreeSet = new TreeSet<String>(
					new WordsComperator());
			wordsTreeSet.addAll(potentialWords);
			wordIterator = wordsTreeSet.iterator();
			// If there are words - start creation of entries
			if (wordIterator.hasNext()) {
				currentWord = wordIterator.next();
				entries = getWordEntries();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (entries == null) {
				return false;
			}
			if (entries.hasNext()) {
				return true;
			}
			// If word iterator has nexts - try recreation of the terms list for
			// the next word
			if (wordIterator.hasNext()) {
				currentWord = wordIterator.next();
				entries = getWordEntries();
				return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public CrosswordEntry next() {
			if (entries == null)
				return null;
			if (entries.hasNext()) {
				return entries.next();
			} // If word iterator has nexts - try recreation of the terms list
				// for the next word
			else if (wordIterator.hasNext()) {
				currentWord = wordIterator.next();
				entries = getWordEntries();
				return next();
			} else {
				return null;
			}

		}

		/**
		 * Iterator , orders by the specified order
		 * 
		 * @return the iterator
		 */
		private Iterator<MyCrosswordEntry> getWordEntries() {
			TreeSet<MyCrosswordEntry> entriesTree = new TreeSet<MyCrosswordEntry>(
					new EntryComperator());
			int offset = 0;
			MyCrosswordEntry entry;

			String word = currentWord;
			// Iterates over the optimization table, and try to put the word in
			// the locations
			for (char c : word.toCharArray()) {
				ArrayList<BoardPosition> positions = charPosList.get(c);
				if (null != positions) {
					for (BoardPosition pos : positions) {
						entry = wordPossibleInPos(word, pos, offset,
								BoardPosition.VERTICAL);
						if (null != entry) {
							entriesTree.add(entry);
						}
						entry = wordPossibleInPos(word, pos, offset,
								BoardPosition.HORIZONTAL);
						if (null != entry) {
							entriesTree.add(entry);
						}

					}
					offset++;
				}
			}
			// iterate over the rest of the board
			for (BoardPosition pos : structure.getFreeSlots()) {
				entry = wordPossibleInPos(word, pos, 0, BoardPosition.VERTICAL);
				if (null != entry) {
					entriesTree.add(entry);
				}
				entry = wordPossibleInPos(word, pos, 0,
						BoardPosition.HORIZONTAL);
				if (null != entry) {
					entriesTree.add(entry);
				}
			}
			return entriesTree.iterator();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			entries.remove();
		}
	}
}
