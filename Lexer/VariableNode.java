package lexer;

public class VariableNode extends Node {
private String variable;

public VariableNode(String variable) {
	this.variable = variable;
}

public String getVariable() {
	return variable;
}


public String toString() {
	return getVariable();
}
}
