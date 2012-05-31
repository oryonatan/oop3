package oop.ex3.crosswords;

import oop.ex3.search.SearchMove;

/**
 * A crossword entry is a word at a given position in a crossword.
 * 
 * @author OOP
 */
public interface CrosswordEntry extends SearchMove {

	/**
	 * Returns the X/Y/Vertical position of an entry
	 * @return the position
	 */
	public CrosswordPosition getPosition();
	
	/**
	 * Returns the corresponding definition of the entry.
	 * 
	 * @return the corresponding glossary definition.
	 */
	public String getDefinition();

	/**
	 * Returns the corresponding word of the entry.
	 * 
	 * @return the corresponding term.
	 */
	public String getTerm();
		

	/**
	 * Retrieves length of the entry (redundant convenience, may be calculated
	 * through getTerm()).
	 * 
	 * @return number of letters of the term.
	 */
	public int getLength();
		
}
