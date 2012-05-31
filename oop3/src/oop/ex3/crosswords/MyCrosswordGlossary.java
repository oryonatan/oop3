package oop.ex3.crosswords;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Basic implementation of CrosswordGlossary interface. Based on HashMap
 * @author OOP
 */
public class MyCrosswordGlossary implements CrosswordGlossary {

	// Holds glossary data, maps each term to its matching definition.
	protected Map<String, String> data = new HashMap<String, String>();
	
	
	// Line separator symbol
	final char SEPARATOR = ':';
	
	/*
	 * (non-Javadoc)
	 * @see oop.ex3.crosswords.CrosswordGlossary#getTermDefinition(java.lang.String)
	 */
	public String getTermDefinition(String term) {
		return this.data.get(term);
	}

	/*
	 * (non-Javadoc)
	 * @see oop.ex3.crosswords.CrosswordGlossary#getTerms()
	 */
	public Set<String> getTerms() {
		return this.data.keySet();
	}

	/*
	 * (non-Javadoc)
	 * @see oop.ex3.crosswords.CrosswordGlossary#load(java.lang.String)
	 */
	public void load(String glossFileName) throws IOException {
		HashSet<String> glosCheck = new HashSet<String>();
		int counter = 1;
		String word, glos;
		Scanner sc = null;
		try {
			// Initialize a scanner to access the file
			sc = new Scanner(new FileReader(glossFileName));
			
			// Go over the lines of the file 
			while (sc.hasNextLine()) {
				
				String entryLine = sc.nextLine();
				
				int separatorPosition = entryLine.indexOf(SEPARATOR);
				if (separatorPosition != -1) { // line doesn'r contain ":"
					
					// Fetch the term of the current line
					word = entryLine.substring(0, separatorPosition);

					// Skip untrue words
					if (word.length() < 2)
						continue;

					// fetch definition of current line
					glos = entryLine.substring(separatorPosition + 1);

					// Adding stars to repetitive glosses for convenience
					// If you implement your dictionary you don't have to do it
					while (glosCheck.contains(glos)) glos+="*";

					glosCheck.add(glos);
				} else {
					// Handling lines that don't contain the ":" separartor.
					// Again, you don't have to do it as you can assume valid input file.
					// If there is no ":", definitions are represented as numbers
					word = entryLine;
					glos = "Dummy" + counter;
				}
				
				//Ignoring repetitive terms
				data.put(word.toLowerCase(), glos);
				counter++;
			}
		} finally {
			if (sc != null)
				sc.close();
		}
	}

}