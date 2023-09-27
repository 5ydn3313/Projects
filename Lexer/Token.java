package lexer;

public class Token {
	public enum TokenType{
		 PLUS, MINUS, TIMES, DIVIDE, EndofLine, NUMBER,state, EQUALS, 
		 lessTHAN,lessEQUALS, greaterTHAN, greaterEQUALS, NOTEQUALS, LPAREN, RPAREN, STRING, WORD, PRINT,IDENTIFIER,LABEL,COMMA//Arithmetic operator and numbers 
		  }
		  private TokenType token;
		  private String value;
		  
		  public Token(TokenType token,String value) {
			  this.token =token;
			  this.value = value;
		  }
		  
		  public Token(TokenType token) {
			  this.token =token;
			  
		  }
		  public Token(String value)
		  {
			  this.value =value;
		  }
		  
		  
		  public TokenType getToken() {
			 return token;  
			  
		  }
		  
         public String getValue() {
        	 return value;
         }
		  


		/**
		toString method 
		@return A string denoting the string value of the token 
		 */
		  public String toString()
		  {
		    //Create string overload 
		    
		    if(this.value== null) {
		    	return String.format("%s",token);
		    	
		    }
		    else{ 
		    	
		    	return String.format("%s(%s)",token,value);
		    	
		    }

		    
		  }

}
