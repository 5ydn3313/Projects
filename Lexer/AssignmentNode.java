package lexer;

public class AssignmentNode extends StatementNode{
	private VariableNode variable;
	private Node nodeExpression;
	
	public AssignmentNode(VariableNode variable,Node nodeExpression) {
		this.variable = variable;
		this.nodeExpression = nodeExpression;
	}

	public VariableNode getVariable() {
		return variable;
	}

	public Node getNodeExpression() {
		return nodeExpression;
	}

	@Override
	public String toString() {
		return String.format("%s",variable + "="+ "%s",nodeExpression);
	}

	
}
