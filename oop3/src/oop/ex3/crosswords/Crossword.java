package oop.ex3.crosswords;

import oop.ex3.search.SearchBoardNode;

import java.util.Collection;

/**
 * An empty Crossword consists of: 
 * - a glossary (loaded from a given glossary file)
 * - a shape (loaded from a given shape file)
 * 
 * In addition, the partially or fully "filled" Crossword  
 * should be able to return a list of crossword entries (which are entries 
 * that are filled in this crossword object).
 * 
 * You can assume (and don't have to check) that calls to SearchBoard's 
 * interface methods and calls to getCrosswordEntries() will always come
 * after calls to attachGlossary() and attachStructure() methods, and
 * that attachGlossary() and attachStructure() will be called only once
 * per crossword.
 * 
 * @author OOP
 */
public interface Crossword extends SearchBoardNode<CrosswordEntry>  {

	/**
	 * Initializes all structures associated with crossword glossary 
	 * Assumes valid and non-NULL glossary object.
	 * 
	 * @param glossary - the glossary object
	 * 					  (generated according to a text file)
	 */
	public void attachGlossary(CrosswordGlossary glossary);

	/**
	 * Initializes all data structures associated with crossword structure. 
	 * Assumes valid and non-NULL shape object
	 * 
	 * @param shape - the structure object (generated according to a text file).
	 */
	public void attachStructure(CrosswordStructure structure);

	/**
	 * Retrieves the list of filled crossword entries associated with this 
	 * Crossword.The set of filled entries should satisfy both of 
	 * the exercise requirements. 
	 * 
	 * There is no requirement on the order of the returned collection
	 * of entries.
	 * 
	 * @return Collection of filled entries.
	 */
	Collection<CrosswordEntry> getCrosswordEntries();	
}
