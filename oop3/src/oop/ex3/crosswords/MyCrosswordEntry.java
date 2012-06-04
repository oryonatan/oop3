package oop.ex3.crosswords;

/**Class for crossword entery
 * @author yonatan,yulishap
 *
 */
public class MyCrosswordEntry implements CrosswordEntry {

	private int overlapping;
	private BoardPosition position;
	private String term;
	private String definition;

	/**Ctor for entery
	 * @param term the term 
	 * @param definition the definition
	 * @param location the location of the word
	 * @param overlapping overlapping count
	 */
	public MyCrosswordEntry(String term, String definition,
			BoardPosition location, int overlapping) {
		this.term = term;
		this.definition = definition;
		this.position = location;
		this.overlapping = overlapping;
	}

	/* (non-Javadoc)
	 * @see oop.ex3.crosswords.CrosswordEntry#getPosition()
	 */
	@Override
	public BoardPosition getPosition() {
		return position;
	}

	/* (non-Javadoc)
	 * @see oop.ex3.crosswords.CrosswordEntry#getDefinition()
	 */
	@Override
	public String getDefinition() {
		return definition;
	}

	/* (non-Javadoc)
	 * @see oop.ex3.crosswords.CrosswordEntry#getTerm()
	 */
	@Override
	public String getTerm() {
		return term;
	}

	/* (non-Javadoc)
	 * @see oop.ex3.crosswords.CrosswordEntry#getLength()
	 */
	@Override
	public int getLength() {
		return term.length();

	}

	/**Gets the number of overlapping chars 
	 * @return number of chars
	 */
	public int getOverlapping() {
		return overlapping;
	}

}
