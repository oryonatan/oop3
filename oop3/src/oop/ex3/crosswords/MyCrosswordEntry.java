package oop.ex3.crosswords;

public class MyCrosswordEntry implements CrosswordEntry {

	private int overlapping;
	private BoardPosition position;
	private String term;
	private String definition;

	public MyCrosswordEntry(String word, String definition,
			BoardPosition location, int overlapping) {
		this.term = word;
		this.definition = definition;
		this.position = location;
		this.overlapping = overlapping;
	}

	@Override
	public BoardPosition getPosition() {
		return position;
	}

	@Override
	public String getDefinition() {
		return definition;
	}

	@Override
	public String getTerm() {
		return term;
	}

	@Override
	public int getLength() {
		return term.length();

	}

	public int getOverlapping() {
		return overlapping;
	}

}
