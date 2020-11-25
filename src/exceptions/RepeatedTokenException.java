package exceptions;

public class RepeatedTokenException extends MasterMindException{
	public RepeatedTokenException() {
		super("There are repeated tokens in the alphabet"); 
	}
}
