package facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import exceptions.AlphabetSizeException;
import exceptions.FileNotExistsException;
import exceptions.RepeatedTokenException;
import game.MasterMind;

public class Facade {
	
	private MasterMind game;

	/**
	 * This method creates a new masterming game according the parameters
	 * @param alfabet: a unique each caracter String that represents the diferent characters that is used in the game
	 * @param puzzleSize: the number of characters that will be used in the puzzle
	 * @throws AlphabetSizeException 
	 * @throws RepeatedTokenException 
	 */
	public void newGame(String alphabet, int puzzleSize) throws AlphabetSizeException, RepeatedTokenException {
		game = new MasterMind(alphabet, puzzleSize, "RANDOM");
	}
	
	/**
	 * This method starts a new puzzle by raffling the set of peaces in the answer
	 */
	public void startPuzzle() {
		game.raffleAnswer();
	}
	
	/**
	 * This method solves the puzzle and returns the number of trials performed until the soluction is reached
	 */
	public int solvePuzzle() {
		return game.solve();
	}
	
	
	/**
	 * This method sets a new game (creates a new game, starts the puzzle, and sets the solver) by using parameters from a file
	 * @param fileName
	 */
	public void instanceGameFromFile(String fileName) throws FileNotExistsException {
	        String alphabet="", solvertype="";
	        int size=0;
			
	        File f = new File(fileName);  
	        if(!f.exists())  
	        {  
	            throw new FileNotExistsException();
	        }  
	        try {  
	            BufferedReader in = new BufferedReader(new FileReader(f));  
	            String line;  
	            while((line = in.readLine())!=null)  
	            {  
	                StringTokenizer st = new StringTokenizer(line, "=");
	                String token1 = st.nextToken();
	                String token2 = st.nextToken();
	                if ("ALPHABET".equals(token1)) {
	                	alphabet = token2;
	                }
	                if ("PUZZLESIZE".equals(token1)) {
	                	size = Integer.parseInt(token2);
	                }	
	                if ("SOLVER_TYPE".equals(token1)) {
	                	solvertype = token2;
	                }	
	            }
	            in.close();
	            game = new MasterMind(alphabet, size, solvertype);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
	
	public static void main(String[] args) throws FileNotExistsException {
		String inputFile = args[0];
		int numberOfExecutions = Integer.parseInt(args[1]);
		Facade facade = new Facade();
		facade.instanceGameFromFile(inputFile);
		facade.startPuzzle();
		for (int i=0;i<numberOfExecutions;i++) {
			System.out.println("Execucao "+(i+1)+" de "+numberOfExecutions);
			System.out.println(facade.solvePuzzle());
		}
	}
}
