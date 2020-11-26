package solvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import answer.Answer;
import game.MasterMind;
import java.util.*;

public class CachorroSolver implements Solver {
        
	HashMap<String, Integer> hash = new HashMap<String, Integer>();

	public String state = "";
	public int acertos_state = 0;
	/**
	 * This method solves a mastermind game
	 */
	@Override
	public int solve(Set<Character> charactersSet, int size, Answer answer){
		hash.clear();
		int acertos = 0;
		String shot = "";
		String response = "";
		state = "";
		acertos_state = 0;
		
		// inicializa o state
		// state = generateRandomShot(charactersSet, size);
		for (Integer i=0;i<size;i++) {
			state = state.concat(i.toString());
		}
		
		response = answer.analyze(state);
		for (int i=0;i<size;i++) {
			if (response.charAt(i)=='b' || response.charAt(i)=='w') {
				acertos_state++;
			}
		}
		System.out.println("Analise: " + response);
		System.out.println("Melhor estado: " + state);
		System.out.println("Acertos: " + acertos_state);
		// vê se acertou de primeira
		while (!correct(response)) {
			// gera uma nova tentativa com base no state trocando
			// o numero de numeros 'erros' aleatoriamente
			shot = generateRandomShot(charactersSet, size);
			
			// analisa a tentativa, se for melhor é o novo state
			// se não mantém o state antigo e faz outra tentativa
			response = answer.analyze(shot);
			// conta quantos acertos
			acertos = 0;
			for (int i=0;i<size;i++) {
				if (response.charAt(i)=='b' || response.charAt(i)=='w') {
					acertos++;
				}
			}
//			System.out.println("Analise: " + response);
//			System.out.println("Tentativa: " + shot);
//			System.out.println("Acertos: " + acertos);
			
			if (acertos > acertos_state) {
				// não sei se pode isso, provavelmente não
				state = shot;
				acertos_state = acertos;
				System.out.println("Analise: " + response);
				System.out.println("Melhor estado: " + state);
				System.out.println("Acertos: " + acertos_state);
			}
			
		}
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
	// gerar a tentativa (lembrar de mudar as posições)
	protected String generateRandomShot(Set<Character> charactersSet, int size) {
		ArrayList<Character> charArray = new ArrayList<Character>();
		ArrayList<Character> charState = new ArrayList<Character>();
		String s = "";
        for (char c:charactersSet) {
			charArray.add(c);
		}
        for (int i=0;i<size;i++) {
			charState.add(state.charAt(i));
		}
		do {
			if (acertos_state<size) {
				s="";
				//now charArray has all characters from the set. 
				Collections.shuffle(charState); //shuffles the state
				for (int i=0; i<acertos_state; i++) { //inserts in s the (size) initial chars from arraylist
					s = s.concat(String.valueOf(charState.get(i)));
				}
				Collections.shuffle(charArray); //shuffles the arraylist
				for (int i=0; s.length()<size;i++) {
					if (s.indexOf(charArray.get(i))==-1) {
						s = s.concat(String.valueOf(charArray.get(i)));
					}
				}
			}
			else if (acertos_state==size) {
				s="";
				//now charArray has all characters from the set. 
				Collections.shuffle(charState); //shuffles the state
				for (int i=0; i<acertos_state; i++) {
					s = s.concat(String.valueOf(charState.get(i)));
				}
			}
		} while(hash.containsKey(s));
		hash.put(s, 1);
		return s;
	}

}