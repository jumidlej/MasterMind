package exceptions;

public class MasterMindException extends Exception {
	

	private String msg;  
	public MasterMindException(String msg){  
		super(msg);  
		this.msg = msg;  
	}  
	public String getMessage(){  
		return msg;  
	}  
}  
   

	

