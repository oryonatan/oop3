package oop.ex3.crosswords;

import java.util.Comparator;

public class WordsComperator implements Comparator<String> {

	private static final int FIRST_BIGGER = -1;
	private static final int SECOND_BIGGER = 1;

	@Override
	public int compare(String firstWord, String secondWord) {
		if (firstWord.length() > secondWord.length()) {
			return FIRST_BIGGER;
		} else if (secondWord.length() > firstWord.length()) {
			return SECOND_BIGGER;
		} else
			return firstWord.compareToIgnoreCase(secondWord);
	}

}
