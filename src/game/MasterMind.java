package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import answer.Answer;
import exceptions.AlphabetSizeException;
import exceptions.RepeatedTokenException;
import solvers.CachorroSolver;
import solvers.RandomSolver;
import solvers.Solver;


public class MasterMind {
	
	private static final String RANDOM_SOLVER = "RANDOM";
	private static final String CACHORRO_SOLVER = "CACHORRO";
	
	Set<Character> charactersSet;
	int size;
	private Answer answer;
	private Solver solver;

	/**
	 * This method is the Mastermind Constructor using parameters
	 * @param alphabet
	 * @param puzzleSize
	 * @param solvertype 
	 * @throws AlphabetSizeException 
	 * @throws RepeatedTokenException 
	 */
	public MasterMind(String alphabet, int puzzleSize, String solvertype) throws AlphabetSizeException, RepeatedTokenException {
		size = puzzleSize;
		answer = new Answer(puzzleSize);
		if (alphabet.length()< puzzleSize) {
			throw new AlphabetSizeException();
		}
		createSet(alphabet);
		if (repeatedChar(alphabet)) {
			throw new RepeatedTokenException();
		};
		instanciateSolver(solvertype);
	}

	public Answer getAnswer() {
		return answer;
	}

	
	
	public Solver getSolver() {
		return solver;
	}

	/**
	 * This method instanciates a solver according the specified type
	 * @param solvertype
	 * @return
	 */
	protected void instanciateSolver(String solvertype) {
		if (RANDOM_SOLVER.equals(solvertype)) {
			this.solver = new RandomSolver();
		}
		if (CACHORRO_SOLVER.equals(solvertype)) {
			this.solver = new CachorroSolver();
		}
		//you can include other if's to instanciate different solvers
	}
	
	/**
	 * This method creates the characterSet having all chars in the alphabet
	 * @param alphabet
	 */
	private void createSet(String alphabet) {
		Map <Character, Integer> mapChars = new HashMap<Character, Integer>(); //a Hash Map of chars
        char[] chrs = alphabet.toCharArray();
        for(Character ch:chrs){
            if(mapChars.containsKey(ch)){
                mapChars.put(ch, mapChars.get(ch)+1);
            } else {
                mapChars.put(ch, 1);
            }
        }
        charactersSet = mapChars.keySet();
	}


	public Set<Character> getCharactersSet() {
		return charactersSet;
	}

	/**
	 * This method checks if there are repeated tokens in a given Alphabet
	 * @param alphabet
	 * @return
	 * NOT TESTED!!!! (tested with Constructor test)
	 */
	protected boolean repeatedChar(String alphabet) {
		if (charactersSet.size()<alphabet.length()) //in this case some character must be repeated
			return true;
		return false;
	}

	/**
	 * This method creates the game answer by raffling this.size tokens
	 */
	public void raffleAnswer() {
		ArrayList<Character> chars = new ArrayList<Character>();
		for (char c:charactersSet) {
			chars.add(c);
		}
		Collections.shuffle(chars); //shuffle the chars Arraylist
		for (int i=0;i<this.size; i++) { //to insert the size first itens in the answer
			answer.insert(i, chars.get(i));
		}
	}

	/**
	 * This method performs the soluction of the game and returns the number of trials until the soluction is reached
	 * @return
	 */
	public int solve() {
		answer.setNumberOfTries(0);
		return solver.solve(charactersSet, size, answer);
	}

}
