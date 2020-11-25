package exceptions;

public class FileNotExistsException extends MasterMindException{
	public FileNotExistsException() {
		super("File does not exists or could not be opened"); 
	}
}
