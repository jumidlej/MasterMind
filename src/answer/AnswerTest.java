package answer;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnswerTest {

	@Test
	public void testInsert() {
		Answer answer = new Answer(4);
		answer.insert(0, 'a');
		answer.insert(1, 'b');
		answer.insert(2, 'c');
		answer.insert(3, 'd');
		char[] tokens = answer.getTokensArray();
		assertEquals('a', tokens[0]);
		assertEquals('b', tokens[1]);
		assertEquals('c', tokens[2]);
		assertEquals('d', tokens[3]);
	}
	
	@Test
	public void testTokenIn() {
		Answer answer = new Answer(4);
		answer.insert(0, '2');
		answer.insert(1, '4');
		answer.insert(2, '6');
		answer.insert(3, '8');
		assertEquals("b", answer.tokenIn('2', answer.getTokensArray(), 0));
		assertEquals("w", answer.tokenIn('2', answer.getTokensArray(), 1));
		assertEquals("w", answer.tokenIn('2', answer.getTokensArray(), 2));
		assertEquals("w", answer.tokenIn('2', answer.getTokensArray(), 3));
		
		assertEquals("w", answer.tokenIn('4', answer.getTokensArray(), 0));
		assertEquals("b", answer.tokenIn('4', answer.getTokensArray(), 1));
		assertEquals("w", answer.tokenIn('4', answer.getTokensArray(), 2));
		assertEquals("w", answer.tokenIn('4', answer.getTokensArray(), 3));
		
		assertEquals("w", answer.tokenIn('6', answer.getTokensArray(), 0));
		assertEquals("w", answer.tokenIn('6', answer.getTokensArray(), 1));
		assertEquals("b", answer.tokenIn('6', answer.getTokensArray(), 2));
		assertEquals("w", answer.tokenIn('6', answer.getTokensArray(), 3));
		
		assertEquals("w", answer.tokenIn('8', answer.getTokensArray(), 0));
		assertEquals("w", answer.tokenIn('8', answer.getTokensArray(), 1));
		assertEquals("w", answer.tokenIn('8', answer.getTokensArray(), 2));
		assertEquals("b", answer.tokenIn('8', answer.getTokensArray(), 3));
		
		assertEquals("o", answer.tokenIn('0', answer.getTokensArray(), 0));
		assertEquals("o", answer.tokenIn('0', answer.getTokensArray(), 1));
		assertEquals("o", answer.tokenIn('0', answer.getTokensArray(), 2));
		assertEquals("o", answer.tokenIn('0', answer.getTokensArray(), 3));
		
	}
	
	@Test
	public void testAnalyze() {
		Answer answer = new Answer(3);
		answer.insert(0, '2');
		answer.insert(1, '4');
		answer.insert(2, '6');
		assertEquals("bbb", answer.analyze("246"));
		assertEquals("bww", answer.analyze("264"));
		assertEquals("www", answer.analyze("624"));
		assertEquals("ooo", answer.analyze("789"));
		assertEquals("boo", answer.analyze("016"));
		assertEquals("boo", answer.analyze("041"));
		assertEquals("boo", answer.analyze("201"));
		assertEquals("bwo", answer.analyze("126"));
		assertEquals("bwo", answer.analyze("142"));
	}
	

}
