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
	private static final boolean VERTICAL = true;
	private static final boolean HORIZONTAL = false;
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

	public MyCrossword() {
		usedWords = new ArrayList<CrosswordEntry>();
		unUsedWords = new ArrayList<String>();
		startingTiles = new HashSet<CrosswordPosition>();
		initializeCharPosList();
	}

	public MyCrossword(Collection<CrosswordEntry> crosswordEntries) {
		this.usedWords = new ArrayList<CrosswordEntry>();
		this.usedWords.addAll(crosswordEntries);
	}

	private void initializeCharPosList() {
		charPosList = new HashMap<Character, ArrayList<BoardPosition>>();
		for (char c : ABC.toCharArray()) {
			charPosList.put(c, new ArrayList<BoardPosition>());
		}
	}

	@Override
	public boolean isBestSolution() {
		if (getQuality() == topQuality)
			return true;
		return false;
	}

	@Override
	public Iterator<CrosswordEntry> getMovesIterator() {
		return new BoardIterator();
	}

	@Override
	public int getQuality() {
		int charCounter = 0;
		for (CrosswordEntry word : usedWords) {
			charCounter += word.getTerm().length();
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
		structure.addEntry(move);
		unUsedWords.remove(move.getTerm());
		usedWords.add(move);
		startingTiles.add(move.getPosition());
		updateCharPositionList(move, ADD);
	}

	@Override
	public void undoMove(CrosswordEntry move) {
		updateCharPositionList(move, REMOVE);
		structure.removeEntry(move);
		unUsedWords.add(move.getTerm());
		usedWords.remove(move);
		startingTiles.remove(move.getPosition());

	}

	private void updateCharPositionList(CrosswordEntry move, boolean ifAdd) {
		// TODO Auto-generated method stub

		BoardPosition pos = new BoardPosition((BoardPosition) move.getPosition());
		String word = move.getTerm();
		boolean direction = pos.isVertical();

		for (char c : word.toCharArray()) {
			if (ifAdd)
				charPosList.get(c).add(pos);
			else
				charPosList.get(c).remove(pos);

				pos.moveInDirection(-1, direction);

		}

	}

	@Override
	public SearchBoardNode<CrosswordEntry> getCopy() {
		return new MyCrossword(getCrosswordEntries());
	}

	@Override
	public void attachGlossary(CrosswordGlossary glossary) {
		this.glossary = glossary;
		this.topQuality = countChars(glossary.getTerms());
		unUsedWords.addAll(glossary.getTerms());
	}

	private int countChars(Set<String> terms) {
		int counter = 0;
		for (String term : terms) {
			counter += term.length();
		}
		return counter;
	}

	@Override
	public void attachStructure(CrosswordStructure structure) {
		this.structure = (MyCrosswordStructure) structure;
	}

	@Override
	public Collection<CrosswordEntry> getCrosswordEntries() {
		return usedWords;
	}

	private boolean possibleWord(String word) {
		int offset = 0;
		for (char c : word.toCharArray()) {
			ArrayList<BoardPosition> positions = charPosList.get(c);
			if (null != positions) {
				for (BoardPosition pos : positions) {
					if (wordPossibleInPos(word, pos, offset)) {
						return true;
					}
					offset++;
				}
			}
		}
		// iterate over the rest of the board
		for (BoardPosition pos : structure.getFreeSlots()) {
			if (wordPossibleInPos(word, pos, 0)) {
				return true;
			}
		}
		return false;
	}

	private MyCrosswordEntry wordPossibleInPos(String word, BoardPosition pos,
			int offset, boolean direction) {

		BoardPosition curPosition = new BoardPosition(pos);
		int i = 0;
			curPosition
					.moveInDirection(offset - (word.length() - 1), direction);
			if (structure.getSlotType(curPosition) == SlotType.FRAME_SLOT)
				return null;

			int overlaping = 0;

			for (i = word.length() - 1; i >= 0; i--) {
				if (structure.getSlotType(curPosition) == SlotType.UNUSED_SLOT) {
					curPosition.moveInDirection(1, direction);
					continue;
				} else if (structure.getSlotContent(curPosition) == 
						word.toCharArray()[i]) {
					curPosition.moveInDirection(1, direction);
					overlaping += 1;
					continue;
				}
				return null;
			}
			curPosition.moveInDirection(-1, direction);
			if (i < 0) {
				curPosition.setVertical(direction);
				if (startingTiles.contains(curPosition))
					return null;
				return new MyCrosswordEntry(word,
						glossary.getTermDefinition(word), curPosition,
						overlaping);
			}

		return null;
	}

	private boolean wordPossibleInPos(String word, BoardPosition pos, int offset) {
		if (null != wordPossibleInPos(word, pos, offset, VERTICAL)) {
			return true;
		}
		if (null != wordPossibleInPos(word, pos, offset, HORIZONTAL)) {
			return true;
		}
		return false;
	}

	private class BoardIterator implements Iterator<CrosswordEntry> {

		// TreeSet<String> potentialWords;
		Iterator<String> wordIterator;
		Iterator<MyCrosswordEntry> entries;
		private String currentWord;

		BoardIterator() {
			TreeSet<String> wordsTreeSet = new TreeSet<String>(
					new WordsComperator());
			wordsTreeSet.addAll(potentialWords);
			wordIterator = wordsTreeSet.iterator();
			if (wordIterator.hasNext())
			{
				currentWord = wordIterator.next();
				entries = getWordEntries();
			}
		}

		@Override
		public boolean hasNext() {
			if (entries == null)
				return false;
			if (entries.hasNext())
				return true;
			if (wordIterator.hasNext()) {
				currentWord = wordIterator.next();
				entries = getWordEntries();
				return true;
			}
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public CrosswordEntry next() {
			if (entries == null)
				return null;
			if (entries.hasNext()) {
				return entries.next();
			} else if (wordIterator.hasNext()) {
				currentWord = wordIterator.next();
				entries = getWordEntries();
				return next();
			} else {
				return null;
			}

		}

		private Iterator<MyCrosswordEntry> getWordEntries() {

			TreeSet<MyCrosswordEntry> entriesTree = new TreeSet<MyCrosswordEntry>(
					new EntryComperator());
			int offset = 0;
			MyCrosswordEntry entry;

			String word = currentWord;
			for (char c : word.toCharArray()) {
				ArrayList<BoardPosition> positions = charPosList.get(c);
				if (null != positions) {
					for (BoardPosition pos : positions) {
						entry = wordPossibleInPos(word, pos, offset, VERTICAL);
						if (null != entry) {
							entriesTree.add(entry);
						}
						entry = wordPossibleInPos(word, pos, offset, HORIZONTAL);
						if (null != entry) {
							entriesTree.add(entry);
						}
						offset++;
					}
				}
			}
			// iterate over the rest of the board
			for (BoardPosition pos : structure.getFreeSlots()) {
				entry = wordPossibleInPos(word, pos, offset, VERTICAL);
				if (null != entry) {
					entriesTree.add(entry);
				}
				entry = wordPossibleInPos(word, pos, offset, HORIZONTAL);
				if (null != entry) {
					entriesTree.add(entry);
				}
			}

			return entriesTree.iterator();
		}

		@Override
		public void remove() {
			entries.remove();
		}
	}
}
