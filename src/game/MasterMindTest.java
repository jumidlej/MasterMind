package game;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import solvers.RandomSolver;

import answer.Answer;

import exceptions.AlphabetSizeException;
import exceptions.MasterMindException;
import exceptions.RepeatedTokenException;

public class MasterMindTest {

	@Test
	public void testMasterMind() {
		//We must to test if an wrong alphabet lenght triggers an Alphabet Size exception
		boolean correctError = false;
		MasterMind game;
		try {
			game = new MasterMind("abc", 5, "RANDOM");
		} catch (AlphabetSizeException e) {
			correctError = true;
		} catch (RepeatedTokenException e) {
			correctError = false;
		}
		assertTrue(correctError);
		
		//Now we must to test if a list having repeated chars triggers an Repeated Token Error
		correctError = false;
		try {
			game = new MasterMind("abcdeefg", 4, "RANDOM");
		} catch (AlphabetSizeException e) {
			correctError = false;
		} catch (RepeatedTokenException e) {
			correctError = true;
		}
		assertTrue(correctError);
	}
	
	@Test
	public void testCreateSet() throws AlphabetSizeException, MasterMindException {
		MasterMind game = new MasterMind("abcde", 3, "RANDOM");
		Set charSet = game.getCharactersSet();
		assertTrue(charSet.contains('a'));
		assertTrue(charSet.contains('b'));
		assertTrue(charSet.contains('c'));
		assertTrue(charSet.contains('d'));
		assertTrue(charSet.contains('e'));
		assertTrue(charSet.size()==5);
		
		game = new MasterMind("0123456789", 4, "RANDOM");
		charSet = game.getCharactersSet();
		assertTrue(charSet.contains('0'));
		assertTrue(charSet.contains('1'));
		assertTrue(charSet.contains('2'));
		assertTrue(charSet.contains('3'));
		assertTrue(charSet.contains('4'));
		assertTrue(charSet.contains('5'));
		assertTrue(charSet.contains('6'));
		assertTrue(charSet.contains('7'));
		assertTrue(charSet.contains('8'));
		assertTrue(charSet.contains('9'));
		assertTrue(charSet.size()==10);
	}
	
	@Test
	public void testRaffleAnswer() throws AlphabetSizeException, RepeatedTokenException {
		for (int t=0;t<10000;t++) { //a random process must be correct if it works a lot of times
			MasterMind game = new MasterMind("0123456789", 3, "RANDOM");
			game.raffleAnswer();
			Answer answer = game.getAnswer();
			char[] tokens = answer.getTokensArray();
			//testing if any token between 0 and 9 is in answer[0]
			boolean isInArray = false;
			for (int i=0;i<10;i++) {
				char c = Character.forDigit(i, 10);
				if (tokens[0]==c) {
					isInArray = true;
				}
			}
			//testing if any token between 0 and 9 is in answer[1]
			assertTrue(isInArray);
			isInArray = false;
			for (int i=0;i<10;i++) {
				char c = Character.forDigit(i, 10);
				if (tokens[1]==c) {
					isInArray = true;
				}
			}
			assertTrue(isInArray);
			//testing if any token between 0 and 9 is in answer[2]
			assertTrue(isInArray);
			isInArray = false;
			for (int i=0;i<10;i++) {
				char c = Character.forDigit(i, 10);
				if (tokens[2]==c) {
					isInArray = true;
				}
			}
			assertTrue(isInArray);
		}
	}
	
	@Test
	public void testInstanciateSolver() throws AlphabetSizeException, RepeatedTokenException {
		MasterMind game = new MasterMind("abcde", 3, "RANDOM");
		assertTrue(game.getSolver() instanceof RandomSolver);
	}

	@Test
	public void testSolve() throws AlphabetSizeException, MasterMindException {
		int puzzleSize = 4;
		MasterMind game = new MasterMind("1234567890", puzzleSize, "RANDOM");
		game.raffleAnswer();
		int i = game.solve();
		assertTrue(i < Math.pow(10f, (float) puzzleSize));
	}
}
