package oop.ex3.crosswords;

import java.io.IOException;

/**
 * A crossword structure represents a rectangular form of a crossword.
 * Each cell in the form can be either:
 * a) FRAME_SLOT - the crossword background ("structure space").
 * or:
 * b) UNUSED_SLOT - slot which is not a frame slot and doesn't contain 
 *                  any crossword entry 
 * 
 * @author OOP
 */
public interface CrosswordStructure {

	/**
	 * Loads and initializes the structure from a text file. 
	 * Each line in the file is a sequence of #'s and _'s. 
	 * The file format described in the exercise pdf.
	 * 
	 * @param textFileName the name of the text file.
	 * @throws IOException if file reading has failed.
	 */
	void load(String textFileName) throws IOException;


	/**
	 * Retrieves the slot type of a specific entry.
	 * 
	 * @param pos the position of the entry
	 * @return The cell type; FRAME_SLOT returned for any slot outside
	 *         the bounds.
	 */
	SlotType getSlotType(CrosswordPosition pos);

	/**
	 * Possible slot types.
	 */
	enum SlotType {
		FRAME_SLOT, UNUSED_SLOT
	};

	/**
	 * Returns number of columns (x axis) in the crossword.
	 * 
	 * @return Number of columns.
	 */
	Integer getWidth();

	/**
	 * Returns number of rows (y axis) in the crossword.
	 * 
	 * @return Number of rows.
	 */
	Integer getHeight();
}
