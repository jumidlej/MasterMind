package solvers;

import java.util.Set;

import answer.Answer;
import game.MasterMind;

public interface Solver {
	
	/**
	 * This method performs the Mastermind soluction
	 * @param answer 
	 * @param size 
	 * @param charactersSet 
	 * @return
	 */
	public int solve(Set<Character> charactersSet, int size, Answer answer);

}
