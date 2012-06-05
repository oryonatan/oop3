
Design:
Most of the design was already given to us. The additions we made are:
* We put an inner class of type BoardIterator that is used by the DFS. The reason why
		its an inner class, is because it uses the potentialWords data structure that is	
		part of the MyCrossword class, and it uses part of the methods of MyCrossword.
		Its only logical that the board knows how to iterate the different moves on itself.
* We used a data structure of type HashMap<Character, ArrayList<BoardPosition>> charPosList
		that suppose to hold all hold the cordinateds for every letter that appears in the 
		crossword (every letter leads to an arrayList of positions in the board where its 
		located). It makes it easier to find all the overlapping words situations, and to check if
		a word is possible in such situations.

* in MyCrosswordStructure we used 


OOP question:

ordering optimization:
To incoparate more ordering optimizations, if the optimization is a simple one
( such as getting the longest words first, or ordering lexicographiclly) we use
a comperator called WordsComperator that combines all these simple orders. If we want to
add another one, all we need to do is to write a matching comperator, and let the WordsComperator
call it.

If we want to add a more sophisticated optimization that optimize the different
entries returned ( such as one that returns the entries which have more overlappings and such ) 
We use a comperator called EntryComperator that combines all these different optimizations ( in
our case, we have one optimization ) . These optimizations are comperators that know how to order
the entries themselves ( these entries are from type MyCrosswordEntry, which in addition to
simple cordinates and direction, have a member that inidicate how many letters are overlapping)
So in order to add a sophisticated optimization all we need to do is to write a matching comperator, and 
let the EntryComperator call it.

UQ calculation:
???

scoring method:
In order to incoparate other scoring methods we need to change the way we calculate the quality 
for a given crossword. For differnet weights for different letters, we need to have a table 
that remmebers for any letter, what its weight ( probably a regular array with 26 cells).
Than to add a method which calculates weight for a given word, and than let the methods
getQuality and getQualityBound call it.


Data Stuctures used:
In MyCrossword:
We have 6 different data stuctures:
	HashSet<CrosswordEntry> usedWords: it holds all the entries that already inside the
			crossword. We know that there cant be 2 same entries, thats why we use a stucture
			from type HashSet.
	HashSet<String> unUsedWords: it holds all the terms that haven't been used yet.
			We know that there cant be 2 same terms, thats why we use a stucture
			from type HashSet.		
	startingTiles::??? we dont need it
	HashSet<String> potentialWords: holds all the terms that can be used after they were filtered
			( by getQualityBound ). Means all the terms that are still possible to put in the crossword.
	MyCrosswordStructure structure: holds the structure of the crossword.
	HashMap<Character, ArrayList???<BoardPosition>> charPosList: hold the cordinateds for every letter that
			appears in the crossword (every letter leads to an arrayList of positions in the board where its located).
			Makes it easier to find overlapping matches. We want to get all the positions of a letter in O(1),
			that why we use HashMap. (hashing takes O(1)).
			
in MyCrosswordStructure:
We have 2??? different data structures:
	charCounter[][] data: a double array of charCounter which holds all the letters in the
			crossword and amount of overlappings for each letter. ( charCounter has 2 members:
			that charecter itself, and an overlapping counter ).
	ArrayList<BoardPosition> freeSlots: holds all the free slots available in the crossword.
			Makes it easier to find places where we can put a word.

in MyDepthFirstSearch:
We have 4 ints:
	int highestUQ: The top quality that we can get ( in all the crossword )
	int topQualitySoFar: The best quality so far that we got
	B bestBoard: the best board so far.
	long endTime: the endtime of the search.
	
	

Complexity of DFS:

	