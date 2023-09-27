package lexer;

public class FloatNode extends Node{ 
private float floatNumber;
	
	public FloatNode (float floatNumber){
		this.floatNumber = floatNumber; 
	
	}
 
	public float getfloatNumber() {	//read only accessor
	return floatNumber; 
	}
	
	@Override
    public String toString() { 
		return null;
	}
}



