package exceptions;

public class AlphabetSizeException extends MasterMindException{
	public AlphabetSizeException() {
		super("Alphabet size is too small"); 
	}
}
