package oop.ex3.crosswords;

public class charCounter {
	private int counter = 0;
	private char charecter ;
	
	public charCounter(char c) {

		this.setCharecter(c);
	}
	public charCounter() {

		this.setCharecter('#');
	}
	
	public int removeChar()
	{
		if (counter > 0)
			counter--;
		if (counter==0)
			setCharecter('_');
		return counter;
	}
	public char getCharecter() {
		return charecter;
	}
	public void setCharecter(char charecter) {
		this.charecter = charecter;
	}
	
	public int update(char c)
	{
		if (c == charecter)
			counter++;
		else
		{
			charecter = c;
			counter = 1;
		}
		return counter;
	}
	
}
