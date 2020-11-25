package answer;

public class Answer {
	
	int puzzleSize, numberOfTries;
	char[] tokensArray;
	

	/**
	 * Constructor
	 * @param puzzleSize
	 */
	public Answer(int puzzleSize) {
		numberOfTries = 0;
		this.puzzleSize = puzzleSize;
		tokensArray = new char[puzzleSize];
	}

	/**
	 * This method inserts a given token in a given position
	 * @param pos
	 * @param token
	 */
	public void insert(int pos, Character token) {
		tokensArray[pos] = token;
	}

	
	public char[] getTokensArray() {
		return tokensArray;
	}

	/**
	 * This method returns the set of b, w and o characters in a string
	 * where 
	 * b means a correct token in the correct place
	 * w means a correct token, but misplaced and
	 * o means tokens that are not correct. 
	 * Moreover, it increments the number of tries counter
	 * @param shot
	 * @return
	 */
	public String analyze(String shot) {
		String listO="", listW="", listB = "";
		for (int i=0;i<this.puzzleSize;i++) {
			char c = shot.charAt(i);
			String r = tokenIn(c, tokensArray, i);
			if ("b".equals(r)) {
				listB = listB.concat(r);
			}
			if ("w".equals(r)) {
				listW = listW.concat(r);
			}
			if ("o".equals(r)) {
				listO = listO.concat(r);
			}
		}
		this.numberOfTries ++;
		return listB.concat(listW.concat(listO));
	}

	/**
	 * This method checks if a char is in the char array
	 * and returns "b" if it is in the correct position,
	 * "w" if it is in wrong position or "o" otherwise
	 * @param c
	 * @param tokens
	 * @param pos 
	 * @return
	 */
	protected String tokenIn(char c, char[] tokens, int pos) {
		
		int numberOfB=0, numberOfW=0, numperOfO=0;
		
		for (int i=0; i< this.puzzleSize; i++) {
			if (c==tokens[pos]) { //correct token in correct place 
				return "b";
			}
			else {
				for (int j=0; j< this.puzzleSize; j++) {
					if (c==tokens[j]) { //misplaced token
						return "w";
					}
				}
			}
		}
		return "o";
	}

	/**
	 * This method generates a String having sequences of "b", "w" and "o" 
	 * according parameters
	 * NOT TESTED!
	 * @param numberOfB
	 * @param numberOfW
	 * @param numberOfO
	 * @return
	 */
	private String generateAnalisis(int numberOfB, int numberOfW, int numberOfO) {
		String s = "";
		for (int i=0;i<numberOfB;i++) {
			s = s.concat("b");
		}
		for (int i=0;i<numberOfW;i++) {
			s = s.concat("w");
		}
		for (int i=0;i<numberOfO;i++) {
			s = s.concat("o");
		}
		return s;
	}

	public int getNumberOfTries() {
		return numberOfTries;
	}

	public void setNumberOfTries(int numberOfTries) {
		this.numberOfTries = numberOfTries;
	}
}
