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

	public String state = "";
	public int acertos_state = 0;
	public int acertos_position = 0;
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
		acertos_position = 0;
		boolean primeira_iteracao=false;
		
		// inicializa o state
		for (Integer i=0;i<size;i++) {
			state = state.concat(i.toString());
		}
		
		response = answer.analyze(state);
		for (int i=0;i<size;i++) {
			if (response.charAt(i)=='b' || response.charAt(i)=='w') {
				acertos_state++;
			}
		}

		// vê se acertou de primeira
		while (!correct(response)) {
			// gera uma nova tentativa com base no state trocando
			// o numero de numeros 'erros' aleatoriamente
			if (acertos_state<size) {
				primeira_iteracao=true;
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
				// System.out.println("Analise: " + response);
				// System.out.println("Tentativa: " + shot);
				// System.out.println("Acertos: " + acertos);
				
				if (acertos > acertos_state) {
					state = shot;
					acertos_state = acertos;
					// System.out.println("Melhor estado: " + state);
				}
			}
			else if (acertos_state==size){
				// EU SEMPRE PRECISO INICIALIZAR (vai que é a certa e eu elimino da hash (na vdd n tem como, se for a certa ele n volta mais))
				if (!primeira_iteracao){
					shot = generateRandomShot(charactersSet, size);
				}
				primeira_iteracao=false;
				
				// analisa a tentativa, se for melhor é o novo state
				// se não mantém o state antigo e faz outra tentativa
				response = answer.analyze(shot);
				// conta quantos acertos
				acertos = 0;
				for (int i=0;i<size;i++) {
					if (response.charAt(i)=='b') {
						acertos++;
					}
				}
				// System.out.println("Analise: " + response);
				// System.out.println("Tentativa: " + shot);
				// System.out.println("Acertos: " + acertos);
				
				if (acertos > acertos_position) {
					state = shot;
					acertos_position = acertos;
					// System.out.println("Melhor estado: " + state);
				}
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
		do {
			if (acertos_state<size) {
				s="";
				for (int i=0;i<size;i++) {
					charState.add(state.charAt(i));
				}
				//now charArray has all characters from the set. 
				Collections.shuffle(charState); //shuffles the state
				for (int i=0; i<acertos_state; i++) {
					s = s.concat(String.valueOf(charState.get(i)));
				}
				Collections.shuffle(charArray);
				for (int i=0; s.length()<size;i++) {
					if (s.indexOf(charArray.get(i))==-1) {
						s = s.concat(String.valueOf(charArray.get(i)));
					}
				}
				charState.clear();
			}
			else if (acertos_state==size) {
				Random rand = new Random();
				int vetor[];
				vetor = new int[size];
				boolean repetido=false;
				
				for (int i=0;i<size;i++) {
					vetor[i]=size+1;
				}
				
				s="";
				// manter 'acertos_position' no lugar e trocar o resto
				// fazer um vetor de indices que queremos manter no lugar
				for (int i=0; i<acertos_position;) {
					int aux = rand.nextInt(size);
					repetido=false;
					for (int j=0;j<size;j++) {
						if(aux==vetor[j]) {
							repetido=true;
						}
					}
					if(!repetido) {
						vetor[i]=aux;
						i++;
					}
				}
				// colocar os que sobraram em charState
				for (int i=0;i<size;i++) {
					repetido=false;
					for (int j=0; j<acertos_position;j++) {
						if (i==vetor[j]) {
							repetido=true;
						}
					}
					if(!repetido) {
						charState.add(state.charAt(i));	
					}
				}

				Arrays.sort(vetor);
				Collections.shuffle(charState);
				
				// escrever em ordem
				int j=0;
				for (int i=0; i<size; i++) {
					if (i==vetor[j]) {
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
		return s;
	}

}