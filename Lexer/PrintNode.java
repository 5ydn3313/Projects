package lexer;

import java.util.ArrayList;
import java.util.List;

public class PrintNode extends Node{
	

	private List<Node> nodeList = new ArrayList <Node>();
	public PrintNode(List<Node>nodeList) {
		this.nodeList = nodeList;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	@Override
	public String toString() {
	return String.format("Print Node:" + "%s",nodeList);
	} ;
	}

