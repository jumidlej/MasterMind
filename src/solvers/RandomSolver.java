package solvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import answer.Answer;
import game.MasterMind;
import java.util.*;

public class RandomSolver implements Solver {
        
	HashMap<String, Integer> hash = new HashMap<String, Integer>();

	/**
	 * This method solves a mastermind game
	 */
	@Override
	public int solve(Set<Character> charactersSet, int size, Answer answer){
		hash.clear();
		String shot = "";
		String response = "";
		do {
            shot = generateRandomShot(charactersSet, size);
			response = answer.analyze(shot);
			//this dummy method does not use answer.analyze()!!!!
		}while (!correct(response));
		System.out.println("Resposta encontrada! ("+shot+") em "+answer.getNumberOfTries()+" Tentativas!");
		return answer.getNumberOfTries();
	}

	/**
	 * This method tests if a String is all "b" characters (correct anwer)
	 * @param response
	 * @return
	 */
	protected boolean correct(String response) {
		for (int i=0;i<response.length(); i++) {
			if (response.charAt(i)!='b')
				return false;
		}
		return true;
	}

	/**
	 * This method generates a new random shot and inserts it into
	 * the HashMap (used to test if the shot is new)
	 * @param charactersSet
	 * @param size
	 * @param hashMap
	 * @return
	 */
	protected String generateRandomShot(Set<Character> charactersSet, int size) {
		ArrayList<Character> charArray = new ArrayList<Character>();
		String s = "";
        for (char c:charactersSet) {
			charArray.add(c);
		}
		do {
			s="";
			//now charArray has all characters from the set. 
			Collections.shuffle(charArray); //shuffles the arraylist
			for (int i=0; i<size; i++) { //inserts in s the (size) initial chars
										 //from arraylist
				s = s.concat(String.valueOf(charArray.get(i)));
			}
		} while(hash.containsKey(s));
		hash.put(s, 1);
		return s;
	}

}
