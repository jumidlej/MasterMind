package solvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import java.util.Arrays;

import answer.Answer;
import java.util.*;

public class CachorroSolver implements Solver {
        
	HashMap<String, Integer> hash = new HashMap<String, Integer>();

	public String state; // melhor estado
	public String radical_elements; // redical dos elementos
	public int[] radical_position; // radical das posições
	public int acertos_elements; // número de acertos do melhor estado
	public int acertos_position; // número de acertos do melhor estado

	/**
	 * This method initializes the state with the first elements
	 * of the chosen alphabet
	 */	
	protected void initializeState(Set<Character> charactersSet, int size){
		ArrayList<Character> charArray = new ArrayList<Character>();
		state = "";
		
		for (char c:charactersSet) {
			charArray.add(c);
		}
  
		for (Integer i=0;i<size;i++) {
			state = state.concat(String.valueOf(charArray.get(i)));
		}
		charArray.clear();
	}

	/**
	 * This method analyses the shot and updates the state and the radical
	 * if it is necessary
	 */	
	protected void updateState(String shot, int size, int acertos_shot, int acertos_state, boolean elements){
		if (acertos_shot > acertos_state) {
			state = shot;
			if(elements){
				acertos_elements = acertos_shot;
				generateElementsRadical(size);
			}
			else{
				acertos_position = acertos_shot;
				generatePositionRadical(size);
			}
		}
		else if (acertos_shot < acertos_state) {
			if(elements){
				generateElementsRadical(size);
			}
			else{
				generatePositionRadical(size);
			}
		}
	}
	
	/**
	 * This method solves a mastermind game
	 */
	@Override
	public int solve(Set<Character> charactersSet, int size, Answer answer){
		int acertos_e = 0; // número de acertos de elementos da tentativa
		int acertos_p = 0; // número de acertos de posições da tentativa
		String shot = ""; // tentativa
		String response = ""; // análise da tentativa
		radical_position = new int[size];

		initializeState(charactersSet, size);

		response = answer.analyze(state);
		acertos_elements = answer.getRightElements();
		acertos_position = answer.getRightPositions();

		generateElementsRadical(size);
		generatePositionRadical(size);

		while (!correct(response)) {
			shot = generateShot(charactersSet, size);
			
			response = answer.analyze(shot);
			acertos_e = answer.getRightElements();
			acertos_p = answer.getRightPositions();

			if (acertos_elements<size) {
				updateState(shot, size, acertos_e, acertos_elements, true);
				if(acertos_elements==size){
					acertos_position=acertos_p;
					generatePositionRadical(size);
				}
			}
			else {
				updateState(shot, size, acertos_p, acertos_position, false);
			}
		}
		hash.clear();
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
	 * This method generates a string of elements to be used in new shots
	 * @param size
	 * @return
	 */
	protected void generateElementsRadical(int size) {
		radical_elements = "";

		ArrayList<Character> charState = new ArrayList<Character>();
		for (int i=0;i<size;i++) {
			charState.add(state.charAt(i));
		}

		Collections.shuffle(charState);

		for (int i=0; i<acertos_elements; i++) {
			radical_elements = radical_elements.concat(String.valueOf(charState.get(i)));
		}

		charState.clear();
	}

	/**
	 * This method generates an array that stores the index of the elements
	 * to be maintened in the same position in new shots
	 * @param size
	 * @return
	 */
	protected void generatePositionRadical(int size) {
		Random rand = new Random();
		boolean repetido=false;

		for (int i=0;i<size;i++) {
			radical_position[i]=size+1;
		}
		
		for (int i=0; i<acertos_position;) {
			int aux = rand.nextInt(size);
			repetido=false;
			for (int j=0;j<size;j++) {
				if(aux==radical_position[j]) {
					repetido=true;
				}
			}
			if(!repetido) {
				radical_position[i]=aux;
				i++;
			}
		}

		Arrays.sort(radical_position);
	}

	/**
	 * This method generates a new random shot and inserts it into
	 * the HashMap (used to test if the shot is new)
	 * @param charactersSet
	 * @param size
	 * @param hashMap
	 * @return
	 */
	protected String generateShot(Set<Character> charactersSet, int size) {
		ArrayList<Character> charArray = new ArrayList<Character>();
		ArrayList<Character> charState = new ArrayList<Character>();
		String s = "";
		int loop=0;

        for (char c:charactersSet) {
			charArray.add(c);
		}

		do {
			loop++;
			if (acertos_elements<size) {
				if (loop>size*2){
					generateElementsRadical(size);
				}
				s=radical_elements;
				Collections.shuffle(charArray);
				for (int i=0; s.length()<size;i++) {
					if (s.indexOf(charArray.get(i))==-1) {
						s = s.concat(String.valueOf(charArray.get(i)));
					}
				}
			}
			else {
				if (loop>size*2){
					generatePositionRadical(size);
				}
				boolean repetido=false;
				s="";

				for (int i=0;i<size;i++) {
					repetido=false;
					for (int j=0; j<acertos_position;j++) {
						if (i==radical_position[j]) {
							repetido=true;
						}
					}
					if(!repetido) {
						charState.add(state.charAt(i));	
					}
				}

				Collections.shuffle(charState);
				
				int j=0;
				for (int i=0; i<size; i++) {
					if (i==radical_position[j]) {
						s = s.concat(String.valueOf(state.charAt(i)));
						j++;
					}
					else {
						s = s.concat(String.valueOf(charState.get(0)));
						charState.remove(0);
					}
				}
				charState.clear();
			}
		} while(hash.containsKey(s));
		hash.put(s, 1);
		charArray.clear();
		return s;
	}
}