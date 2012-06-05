oryonatan
yulishap

Our Files : 
BoardPosition - implementation for the board position interface
charCounter - represents a count of chars in a tile
MyCrossword - our crossword implementation
MyCrosswordEntry - our crossword entery implementation 
MyCrosswordGlossary - our crossword glossary implementation
MyCrosswordStructure - our crossword structure implementation
WordsComperator - comparator for words ordering
MyDepthFirstSearch - our DFS implementation


Design and implementation:
Most of the design and implementation was already given to us. The additions we made are:
* We put an inner class of type BoardIterator that is used by the DFS. The reason why
		its an inner class, is because it uses the potentialWords data structure that is	
		part of the MyCrossword class, and it uses part of the methods of MyCrossword.
		Its only logical that the board knows how to iterate the different moves on itself.
* We used a data structure of type HashMap<Character, ArrayList<BoardPosition>> charPosList
		that suppose to hold all hold the cordinates for every letter that appears in the 
		crossword (every letter leads to an arrayList of positions in the board where its 
		located). It makes it easier to find all the overlapping words situations, and to check if
		a word is possible in such situations.
* in MyCrosswordStructure we used a data structure of type charCounter[][] data that holds all the 
		crossword letters and the number of times they overlap. See Data Structures used at the end.
* For the Iterator of the different next moves we used a TreeSet that orders the different words with comperators
		( by length and lexicographiclly) and a TreeSet for all the different entries possible for every word 
		in the next way:
		For each word ordered ( by length and lexicographiclly) we call the method getWordEntries which 
		calculates all the different entries possible for the given word, puts them in a TreeSet and
		the TreeSet's comperator does the rest. The method returns an interator on that TreeSet and the 
		next-move iterator iterates on these entries till there aren't any more, and than it moves to the next word
		and repeats the call to getWordEntries. It continues to do that till there aren't any words left.


OOP question:

Ordering optimization:
In order to improve run time , instead of iterating over the whole table time after time , we
hold a table (hashset) of all the characters used in the crossword and their positions , this way 
allowing first to try putting a word in places that has overlapping tiles (which should increase score).
Then we iterate over only the free slots , since the word can't be put in any of the rest of the locations 
as they don't contain any letter that appears in the word.

To incorporate more ordering optimizations, if the optimization is a simple one
( such as getting the longest words first, or ordering lexicographically) we use
a comparator called WordsComperator that combines all these simple orders. If we want to
add another one, all we need to do is to write a matching comparator, and let the WordsComperator
call it.

If we want to add a more sophisticated optimization that optimize the different
entries returned ( such as one that returns the entries which have more overlappings and such ) 
We use a comparator called EntryComperator that combines all these different optimizations ( in
our case, we have one optimization ) . These optimizations are comparators that know how to order
the entries themselves ( these entries are from type MyCrosswordEntry, which in addition to
simple coordinates and direction, have a member that indicates how many letters are overlapping)
So in order to add a sophisticated optimization all we need to do is to write a matching comparator, and 
let the EntryComperator call it.

UQ calculation:
To incorporate more ways to calculate UQ we need to make a number of classes 
that implement SearchBoardNode<CrosswordEntry> ( one for each way to calculate UQ ) call them qualityCalculators.
Each of these classes will implement its own way to calculate getQuality and getQualityBound.
Our crossword implementation MyCrossword would implement the Crossword interface ( as it is now )
and extend the different qualityCalculators. So we can easily change between different ways to calculate UQ.


Scoring method:
In order to incorporate other scoring methods we need to change the way we calculate the quality 
for a given crossword. We do it in the way described in the UQ calculation ( Described above ).
For different weights for different letters, we need to have a table 
that remembers for any letter, what its weight ( probably a regular array with 26 cells).
Than to add a method which calculates weight for a given word, and than let the methods
getQuality and getQualityBound call it.


Data Structures used:
In MyCrossword:
We have 6 different data structures:
	HashSet<CrosswordEntry> usedWords: it holds all the entries that already inside the
			crossword. We know that there can't be 2 same entries, thats why we use a structure
			from type HashSet.
	HashSet<String> unUsedWords: it holds all the terms that haven't been used yet.
			We know that there can't be 2 same terms, thats why we use a structure
			from type HashSet.		
	HashSet<String> potentialWords: holds all the terms that can be used after they were filtered
			( by getQualityBound ). Means all the terms that are still possible to put in the crossword.
	MyCrosswordStructure structure: holds the structure of the crossword.
	HashMap<Character, ArrayList<BoardPosition>> charPosList: hold the coordinates for every letter that
			appears in the crossword (every letter leads to an arrayList of positions in the board where 
			its located).
			Makes it easier to find overlapping matches. We want to get all the positions of a letter in O(1),
			that why we use HashMap. (hashing takes O(1)).

in MyCrosswordStructure:
We have 2 different data structures:
	charCounter[][] data: a double array of charCounter which holds all the letters in the
			crossword and amount of overlappings for each letter. ( charCounter has 2 members:
			that character itself, and an overlapping counter ).
	ArrayList<BoardPosition> freeSlots: holds all the free slots available in the crossword.
			Makes it easier to find places where we can put a word.

in MyDepthFirstSearch:
We have 4 ints:
	int highestUQ: The top quality that we can get ( in all the crossword )
	int topQualitySoFar: The best quality so far that we got
	B bestBoard: the best board so far.
	long endTime: the endtime of the search.



Complexity of DFS:
We have already seen in DAST that DFS complexity is O(|V|+|E|) , while linear, it can still take a long time to
cases because the input itself is not linear.
explanation:
Lets assume (worst case) that we have an a*b = T tiles graph
for each tile - we can put a letter in either vertical orientation or horizontal orientation , 
however this still gave only a linear (*2) effect on input size.

Now lets assume there are W words , since in each tile we can put each of the W words,
we get T*W*2 for options for the first move.

For the second move we already used a single slot and a single word , taking down our number of options to 
(T-1)(W-1)*2 , for the third we have (T-2)(W-2)*2 and so on.

For the whole process we got W!*T!*2 options possible meaning that our so called "linear" complexity can 
take a lot of time.