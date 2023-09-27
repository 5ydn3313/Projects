package lexer;

public class IntegerNode extends Node{
	
	private int number;
	
	public IntegerNode (int number){
		this.number = number; 
	
	}
 
	public int getNumber() { //read only accessor 	
	return number; 
	}
	
	@Override
    public String toString() {
		return null;
	}
}
