package lexer;


public class MathOpNode extends Node {
    
 public enum MathOp{ 
		ADD, SUBTRACT, DIVIDE, MULTIPLY,
	
}
 Node left; 
 Node right;
 MathOp operator;
  

public MathOpNode(Node right, Node left, MathOp operator) {
	this.right = right; 
	this.left = left; 
	this.operator= operator;
	
}
public Node getRight(Node right) {
	return right;
}

public Node getLeft(Node left) {
	return left;
	
}
public MathOp getOperator(MathOp operator) {
	return operator;
}
	
	public String toString() {
		return String.format("%s,%s,%s", operator,left,right);
		
	}
}

