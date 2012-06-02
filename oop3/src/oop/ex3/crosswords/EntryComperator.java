package oop.ex3.crosswords;

import java.util.Comparator;

public class EntryComperator implements Comparator<MyCrosswordEntry> {

	private static final int FIRST_BIGGER = 1;
	private static final int SECOND_BIGGER = -1;

	@Override
	public int compare(MyCrosswordEntry firstEntry, MyCrosswordEntry secondEntry) {
		
		int compared = compareOverlapping(firstEntry,secondEntry);
		if (compared == 0 ){
			compared = compareOrientation(firstEntry,secondEntry);
		}
		if(compared == 0){
			return firstEntry.getPosition().compareTo(secondEntry.getPosition());
		}
		return compared;
	}

	private int compareOrientation(MyCrosswordEntry firstEntry,
			MyCrosswordEntry secondEntry) {
		if(firstEntry.getPosition().isVertical() && !secondEntry.getPosition().isVertical()){
			return FIRST_BIGGER;
		}
		if(!firstEntry.getPosition().isVertical() && secondEntry.getPosition().isVertical()){
			return SECOND_BIGGER;
		}		
		return 0;
	}

	private int compareOverlapping(MyCrosswordEntry firstEntry,
			MyCrosswordEntry secondEntry) {
		if (firstEntry.getOverlapping() > secondEntry.getOverlapping()){
			return FIRST_BIGGER;
		}
		if (firstEntry.getOverlapping()< secondEntry.getOverlapping()){
			return SECOND_BIGGER;
		}
		return 0;
	}
	
}
