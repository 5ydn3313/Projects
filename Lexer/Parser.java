package lexer;

import java.util.ArrayList;
import java.util.List;

import lexer.MathOpNode.MathOp;


public class Parser {
	
   
	private List<Token> list;



	public Parser(List<Token> list) {
	this.list=list;
}
	public Node parse() throws Exception {
		return Statements();
	}
	
	public Node Expression() throws Exception{
		MathOp operator;
		Node right;
		Node left = Term();
     if (left == null) {
    	 return null;	
	}
     if (matchAndRemove(Token.TokenType.PLUS)!=null) {
    	 operator = MathOpNode.MathOp.ADD;
     }
     else if(matchAndRemove(Token.TokenType.MINUS)!=null){
    	 operator = MathOpNode.MathOp.SUBTRACT;
     }
     else {
    	 return left;
     }
     right = Term();
     
     
     if(operator == null) {
    	 return null; 
     }
     if (right== null) {
    	 throw new Exception("Error");
     }
	return left;
 
	}
		
     
	public Node Term() throws Exception{
			MathOp operator;
			Node right;
			Node left = Factor();
	     if (left == null) {
	    	 return null;	
		}
	     if (matchAndRemove(Token.TokenType.TIMES)!=null) {
	    	 operator = MathOpNode.MathOp.MULTIPLY;
	     }
	     else if(matchAndRemove(Token.TokenType.DIVIDE)!=null){
	    	 operator = MathOpNode.MathOp.DIVIDE;
	     }
	     else {
	    	 return left;
	     }
	     right = Term();
	     
	     
	     if(operator == null) {
	    	 return null; 
	     }
	     if (right== null) {
	    	 throw new Exception("Error");
	     }
	     
		
		return null;
	}
	
	public Token matchAndRemove(Token.TokenType token) {
		if(list.get(0).getToken() == token) {
			return list.remove(0);
		}
		
		return null;
	}
	
	public Node Factor() throws Exception {
		Node temp;
		Token index = matchAndRemove(Token.TokenType.NUMBER);
		if (index!= null) {
		if (index.getValue().contains(".")){
			return new FloatNode(Float.parseFloat(index.toString())); 
		}	
			else {
				return new IntegerNode(Integer.parseInt(index.toString())); 	
		
			}
		}
		    index=matchAndRemove(Token.TokenType.LPAREN);
			if (index!=null) {
				temp = Expression();
			if (matchAndRemove(Token.TokenType.RPAREN)==null) {
				throw new Exception("Error");
			}
			return temp; 
			} 
			index = matchAndRemove(Token.TokenType.IDENTIFIER);
			if (index!= null) {
				return new VariableNode((index.toString()));
			}
			
			return null;
			
				
			}
	
	public Node Statements() throws Exception{
		Node temp;
		ArrayList<Node> statementList = new ArrayList <Node>();
		temp=Statement();
		while(temp!=null) {
			statementList.add(temp);
			temp=Statement();
		}
		return new StatementsNode(statementList);
		
	}
	public Node Statement() throws Exception{
		Node temp; 
		temp=PrintStatement();
		if(temp!=null) {
			return temp; 
		}
		temp=Assignment();
		if(temp!=null){
			return temp;
		}
		temp = Expression();
		if(temp!=null) {
			return temp;
			
			//Add new method for constant string 
		}
		return null;
	}
	
	public Node PrintStatement() throws Exception {
		List<Node> printList = PrintList();
		return new PrintNode(printList);
		
	}
	/*
	 * Looks through Arraylist of print statements 
	 * uses temp variable to keep track of tokens being found and removed 
	 */
	public List<Node> PrintList() throws Exception{
		Node temp;
		ArrayList<Node> printList = new ArrayList<Node>();
		Token index = matchAndRemove(Token.TokenType.PRINT);
		if (index!=null) {
			temp = Expression();
		if (temp !=null) {
			printList.add(temp);
		}
		
        while(matchAndRemove(Token.TokenType.COMMA)!=null) {
        	if (index!=null) 
    			temp = Expression();
    		if (temp !=null) 
    			printList.add(temp);	
        }
        return printList; 		
		}
		
		return null;
	}
	
	public StatementNode Assignment() throws Exception {
		//match and remove identifier
		//make it a variable node 
		//match and remove equals
		//store return value 
		//then return assignment node 
		Node temp;//used when looking through statement 
		VariableNode newVariable =null;
		Token index = matchAndRemove(Token.TokenType.IDENTIFIER);
		if(index!=null) {
			newVariable = new VariableNode(index.toString());
		}
		 index = matchAndRemove(Token.TokenType.EQUALS);
		 if(index!=null) {
		 temp = Expression();
		 if(temp!= null) {
			 return new AssignmentNode(newVariable,temp);
		 }
		 
		 }
		 return null;
	}
	}
	