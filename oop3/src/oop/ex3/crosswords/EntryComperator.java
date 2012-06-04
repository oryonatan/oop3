package oop.ex3.crosswords;

import java.util.Comparator;

/**
 * Comparator for crossword entries
 * 
 * @author yonatan,yulishap
 * 
 */
public class EntryComperator implements Comparator<MyCrosswordEntry> {

	private static final int FIRST_BIGGER = 1;
	private static final int SECOND_BIGGER = -1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(MyCrosswordEntry firstEntry, MyCrosswordEntry secondEntry) {
		// First compare number of overlapping letters , then orientation , then
		// position
		int compared = compareOverlapping(firstEntry, secondEntry);
		if (compared == 0) {
			compared = compareOrientation(firstEntry, secondEntry);
		}
		if (compared == 0) {
			return firstEntry.getPosition()
					.compareTo(secondEntry.getPosition());
		}
		return compared;
	}

	/**
	 * Compares the orientation of two entries
	 * 
	 * @param firstEntry
	 *            the first entry
	 * @param secondEntry
	 *            the second entry
	 * @return 1 if the first one is vertical and the second is horizontal -1 if
	 *         the other way around 0 if they are of the same orientation
	 */
	private int compareOrientation(MyCrosswordEntry firstEntry,
			MyCrosswordEntry secondEntry) {
		boolean firstIsVertical = firstEntry.getPosition().isVertical();
		boolean secondIsVertical = secondEntry.getPosition().isVertical();
		if (firstIsVertical && !secondIsVertical) {
			return FIRST_BIGGER;
		}
		if (!firstIsVertical && secondIsVertical) {
			return SECOND_BIGGER;
		}
		return 0;
	}

	/**
	 * Compares number of overlapping letters
	 * 
	 * @param firstEntry
	 *            the first entry
	 * @param secondEntry
	 *            the second entry
	 * @return 1 if the first is bigger ,-1 if the second is bigger , 0 if they
	 *         are the same
	 */
	private int compareOverlapping(MyCrosswordEntry firstEntry,
			MyCrosswordEntry secondEntry) {
		int firstOlCount = firstEntry.getOverlapping();
		int secondOlCount = secondEntry.getOverlapping();
		if (firstOlCount > secondOlCount) {
			return FIRST_BIGGER;
		}
		if (firstOlCount < secondOlCount) {
			return SECOND_BIGGER;
		}
		return 0;
	}

}
