package oop.ex3.crosswords;

public class charCounter {
	private int counter = 0;
	private char charecter ;
	
	public charCounter(char c) {
		// TODO Auto-generated constructor stub
		this.setCharecter(c);
	}
	public charCounter() {
		// TODO Auto-generated constructor stub
		this.setCharecter('#');
	}
	
	public void removeChar()
	{
		if (counter > 0)
			counter--;
		else
			setCharecter('_');
	}
	public char getCharecter() {
		return charecter;
	}
	public void setCharecter(char charecter) {
		this.charecter = charecter;
	}
	
	public void update(char c)
	{
		if (c == charecter)
			counter++;
		else
		{
			charecter = c;
			counter = 1;
		}
	}
}
