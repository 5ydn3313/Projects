package lexer;

public class StringNode extends Node {
	
	private String conString; 
	
	public String getString() { //read only accessor 	
		return conString; 
		}
		
	
	public StringNode (String conString){
		this.conString= conString; 
	
	}

}
