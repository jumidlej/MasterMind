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
	public String radical_elements = "";
	public int acertos_elements = 0;
	public int acertos_position = 0;
	/**
	 * This method solves a mastermind game
	 */
	@Override
	public int solve(Set<Character> charactersSet, int size, Answer answer){
		ArrayList<Character> charArray = new ArrayList<Character>();
		int acertos_e = 0;
		int acertos_p = 0;
		acertos_elements = 0;
		acertos_position = 0;
		radical_elements = "";
		String shot = "";
		String response = "";
		state = "";
		
		for (char c:charactersSet) {
			charArray.add(c);
		}
		// inicializa o state com os primeiros elementos do set
		for (Integer i=0;i<size;i++) {
			state = state.concat(String.valueOf(charArray.get(i)));
		}
		charArray.clear();
		
		// analisa o primeiro state
		response = answer.analyze(state);
		for (int i=0;i<size;i++) {
			if (response.charAt(i)=='b' || response.charAt(i)=='w') {
				acertos_elements++;
			}
			if (response.charAt(i)=='b') {
				acertos_position++;
			}
		}

		// gerar o primeiro radical
		generateElementsRadical(size);

		// System.out.println("Primeiro estado: " + state);
		// System.out.println("Acertos (elements): " + acertos_elements);

		// faz novas tentativas até acertar
		while (!correct(response)) {
			// gera uma nova tentativa
			shot = generateRandomShot(charactersSet, size);
			
			// analisa a tentativa, se for melhor é o novo state
			// se não mantém o state antigo e faz outra tentativa
			response = answer.analyze(shot);

			acertos_e = 0;
			acertos_p = 0;
			for (int i=0;i<size;i++) {
				if (response.charAt(i)=='b' || response.charAt(i)=='w') {
					acertos_e++;
				}
				if (response.charAt(i)=='b') {
					acertos_p++;
				}
			}

			// System.out.println("Tentativa: " + shot);
			// System.out.println("Acertos (elements): " + acertos_e);
			// System.out.println("Acertos (position): " + acertos_p);
			
			// ainda não sabemos todos os elementos
			if (acertos_elements<size) {
				// atualiza o state e o radical
				if (acertos_e > acertos_elements) {
					state = shot;
					acertos_elements = acertos_e;
					generateElementsRadical(size);
					// System.out.println("Melhor estado (elements): " + state);
				}
				else if (acertos_e < acertos_elements) {
					generateElementsRadical(size);
				}
				// inicializa para mudar apenas as posições
				if(acertos_elements==size){
					acertos_position=acertos_p;
				}
			}
			// não sabemos a posição dos elementos
			else {
				if (acertos_p > acertos_position) {
					state = shot;
					acertos_position = acertos_p;
				}
			}
		}
		hash.clear();
		charArray.clear();
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
	 * @return
	 */
	protected void generateElementsRadical(int size) {
		radical_elements = "";

		ArrayList<Character> charState = new ArrayList<Character>();
		for (int i=0;i<size;i++) {
			charState.add(state.charAt(i));
		}

		Collections.shuffle(charState); //shuffles the state

		// coloca aleatoriamente 'acertos_elements' caracteres
		// no inicio de 'radical' pegos do 'melhor state' atual
		for (int i=0; i<acertos_elements; i++) {
			radical_elements = radical_elements.concat(String.valueOf(charState.get(i)));
		}

		charState.clear();
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
		ArrayList<Character> charState = new ArrayList<Character>();
		String s = "";
		int loop=0;
		//now charArray has all characters from the set.
        for (char c:charactersSet) {
			charArray.add(c);
		}
		do {
			loop++;
			if (loop>size*2){
				generateElementsRadical(size);
			}
			if (acertos_elements<size) {
				s=radical_elements;
				Collections.shuffle(charArray); //shuffles the array
				// coloca aleatoriamente caracteres que faltam em 's'
				// do set de caracteres
				for (int i=0; s.length()<size;i++) {
					if (s.indexOf(charArray.get(i))==-1) {
						s = s.concat(String.valueOf(charArray.get(i)));
					}
				}
			}
			else {
				Random rand = new Random();
				int[] radical_position = new int[size];
				boolean repetido=false;
				s="";

				// inicializa o radical
				for (int i=0;i<size;i++) {
					radical_position[i]=size+1;
				}
				
				// faz um vetor de indices de caracteres do 'state'
				// que queremos manter na mesma posição
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

				// coloca os caracteres que sobraram em charState
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

				// mistura o charState
				Collections.shuffle(charState);
				
				// escreve a nova tentativa mantendo 'acertos_position'
				// na mesma posição e trocando os outros
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