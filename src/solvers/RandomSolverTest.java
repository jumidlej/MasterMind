package solvers;

import static org.junit.Assert.*;
import exceptions.AlphabetSizeException;
import exceptions.MasterMindException;
import exceptions.RepeatedTokenException;
import game.MasterMind;

import org.junit.Test;

import answer.Answer;

public class RandomSolverTest {

	@Test
	public void testGenerateRandomShot() throws AlphabetSizeException, RepeatedTokenException {
		//how to test this? You have to see if there is any repetition
		MasterMind game = new MasterMind("1234567890", 3, "RANDOM");
		game.raffleAnswer();
		RandomSolver solver = new RandomSolver();
		for (int i=0; i<720;i++) { //720 = 10*9*8
			String s = solver.generateRandomShot(game.getCharactersSet(), 3);
		}
	}
	
	@Test
	public void testSolve() throws AlphabetSizeException, MasterMindException {
		int puzzleSize = 4;
		MasterMind game = new MasterMind("1234567890", puzzleSize, "RANDOM");
		game.raffleAnswer();
		Answer answer = game.getAnswer();
		Solver solver = game.getSolver();
		int i= solver.solve(game.getCharactersSet(), puzzleSize, answer);
		assertTrue(i < Math.pow(10f, (float) puzzleSize));
	}
	
	@Test
	public void testCorretc() {
		RandomSolver solver = new RandomSolver();
		assertTrue(solver.correct("bb"));
		assertTrue(solver.correct("bbbbbbbbbbbbb"));
		assertTrue(solver.correct("bbbbbbb"));
		assertFalse(solver.correct("bbbbbbbbbbbbbbbbdbbbbbbbbbbbbbbbbbbbbbbbbbb"));
	}

}
