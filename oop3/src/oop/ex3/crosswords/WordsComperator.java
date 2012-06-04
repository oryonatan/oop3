package oop.ex3.crosswords;

import java.util.Comparator;

/**
 * Comperator for words
 * 
 * @author yonatan,yulishap
 * 
 */
public class WordsComperator implements Comparator<String> {

	private static final int FIRST_BIGGER = -1;
	private static final int SECOND_BIGGER = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String firstWord, String secondWord) {
		// Return 1 if first is longer , -1 if second is longer , unless they
		// are of the same length and in this case they are compared as strings
		int firstLength = firstWord.length();
		int secondLength = secondWord.length();
		if (firstLength > secondLength) {
			return FIRST_BIGGER;
		} else if (secondLength > firstLength) {
			return SECOND_BIGGER;
		} else
			return firstWord.compareToIgnoreCase(secondWord);
	}

}
