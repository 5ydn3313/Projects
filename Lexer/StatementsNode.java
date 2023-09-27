package lexer;

import java.util.ArrayList;
import java.util.List;

public class StatementsNode extends StatementNode{
	private List<Node> statementNode = new ArrayList <Node>();
	
	public StatementsNode(ArrayList<Node>statementNode) {
		this.statementNode = statementNode;
	}
	

	public List<Node> getStatementNode() {
		return statementNode;
	}

	
	public String toString() {
		return String.format("Node list:" + "%s",statementNode);//format string to print Node list 
	}
}
