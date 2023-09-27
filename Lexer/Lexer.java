package lexer;
import java.util.List;

import lexer.Token.TokenType;

import java.util.ArrayList;
import java.util.HashMap;


public class Lexer {
	
		public List<Token> lex (String str) throws Exception
	      {
			HashMap<String,TokenType> hashmap= new HashMap<>();
			hashmap.put("print",TokenType.PRINT);
			 List<Token> list = new ArrayList <Token>();
			 char character;
			 boolean firstLetter = true;
			 boolean lastLetter = false;
			 boolean openString = false;
			 char before = 0;
			 char after = 0;
	         String output = ""; //create string value for output
	         for(int i=0; i<str.length(); i++)// Looping through string input 
		         {
	        	 //System.out.print("start");
		         character = str.charAt(i);
		         //System.out.println("ch " + character);
		         //System.out.println("out "+ output);
		         int j = i-1;
		         int k = i+1;
		         //System.out.println("j: " + j+ " " + "k:" + k);
		        if (i != 0 ) { 
		          before = str.charAt(j);
		         firstLetter = false; 
		         }
		        if (k<str.length()) {
		        	after = str.charAt(k);
		        	//System.out.println("after"+ after);
		        }
		        else {
		        	//System.out.println("last");
		          lastLetter= true;
		        }
		        	
	             if (Character.isWhitespace(character)==true)
	            	 
             	 continue; 
	         
	         
	         
	        
	          
	          
        	  switch (character)
	 	         {
        	  
	 	         
	 	          case '+': 
	 	         
	 	        	 
	 	        	  list.add(new Token(TokenType.PLUS));
 	              break; 
	 	          
 	         case '-':
 	        	//System.out.print("here"); 
 	        	
 	        	 if (firstLetter==false && (Character.compare(str.charAt(j),'/')==0) || (Character.compare(str.charAt(j),'*')==0)||
 	        	 (Character.compare(str.charAt(j),'+')==0) || (Character.isDigit(after)==true)) { 

	              output = output.concat(Character.toString(character));
	              System.out.println(output);
 	        	 continue;
	 	        	 
	 	        	 }	 	        	
 	        	 else {
	 	        		 
	 	        	 list.add(new Token(TokenType.MINUS));	
	 	        	 break;
	 	        	 }
	 	            
	 	            
	 	         case '*':
	 	        	 list.add(new Token(TokenType.TIMES));
	 	        	 break;
	 	        		 
	 	        	 
	 	        		 
	 	       case '/':
	 	    	    
	 	    		  
	 	    	    list.add(new Token(TokenType.DIVIDE));
	 	    		break;  
	 	      case '<':
	 	    	 if (firstLetter==false && Character.compare(before, '=')==0)
	    		   {
	    			   list.add(new Token(TokenType.lessEQUALS));
	    			   output = "";
	    			   break;
	    		   }
	 	    	 else if(firstLetter==false && Character.compare(after,'>')==0) {
	 	    		 continue;
	 	    		 
	 	    	 }
	    		   else {
	    			   list.add(new Token(TokenType.lessTHAN));
	    			   output = "";
	    			   break;
	    		   }
	    		   
    	   case '>':
    		   if (firstLetter==false && Character.compare(before, '=')==0)
    		   {
    			   list.add(new Token(TokenType.greaterEQUALS));
    			   output = "";
    			   break;
    		   }
    		   else if (firstLetter==false && Character.compare(before,'<')==0){

    			   list.add(new Token(TokenType.NOTEQUALS));
    			   output = "";
    			   break;
    		   }
    		   else {
    			   list.add(new Token(TokenType.greaterTHAN));
    			   
    			   output = "";
    			   break;
    		   }
    		   
    		  
    			    
   	          case '=':	  
   	        	  
   		       list.add(new Token(TokenType.EQUALS));
	    		  break; 
   	          case '\"':
   	        	  if (openString==true) {
   	        		 list.add(new Token(TokenType.NUMBER,output));
 					 output = "";  
 					 openString= false;
   	        	  }
   	        	  else {
   	        		  openString= true;
   	        		  continue;
   	        	  }
   	          case ':': 
   	 	    	   if (firstLetter==false && Character.isLetter(before)==true) {
   	 	    		  list.add(new Token(TokenType.LABEL,output));
					  output = "";
   	 	    		  continue;
   	 	    		  
   	 	    	   }
   	 	    	   else {
   	 	    		   throw new Exception("Bad character");
   	 	    	   }
   	 	    	   
   	          case '(':
   	        	  
   	        	  list.add(new Token(TokenType.LPAREN));
   	        	  break; 
   	        	  
   	          case ')':
   	        	  
   	        	  list.add(new Token(TokenType.RPAREN));
   	        	  break;
   	       case ',':
	        	  
	        	  list.add(new Token(TokenType.COMMA));
	        	  break;
	        	  
   	        	  
	 	       case '.':
	 	    	   
	 	    	   if (firstLetter==false && Character.isDigit(before)==true) {
	 	    		  output = output.concat(Character.toString(character));
	 	    		  System.out.println(output);
	 	    		  continue;
	 	    	   }
	 	    	   else {
	 	    		   throw new Exception("Bad character");
	 	    	   }
	 	    		
	 	    		default:
	 	    			if (Character.isDigit(character)==true)  {
	 	    				 output = output.concat(Character.toString(character));
	 	    				System.out.println(output);
	 	    				 if (lastLetter==false && Character.isDigit(after)==true)
	 	    				 continue;
	 	    				 
	 	    				 else {
	 	    					 list.add(new Token(TokenType.NUMBER,output));
	 	    					 output = "";
	 	    				 }
	 	    				 list.add(new Token(TokenType.NUMBER,output));
 	    					 output = "";
 	    					 //System.out.println("added number");
	 	    				 
	 	    			}
	 	    			else if (Character.isLetter(character)==true){
	 	    			 output = output.concat(Character.toString(character));
	 	    			System.out.println(output);
	 	    			 if (lastLetter==false && Character.isLetter(after)==true)
	 	    				 continue;
	 	    				 
	 	    			if (hashmap.containsKey(output.toLowerCase())){
	 	    				 list.add(new Token(TokenType.PRINT));
 	    					 output = "";	
 	    				continue;
	 	    			}
	 	    			else {
	 	    				list.add(new Token(TokenType.IDENTIFIER,output));
	    					 output = "";	
	    				continue;
	 	    			}
	 	    			}
	 	    			
	 	    			else {
	 	 	    		   throw new Exception("Bad character");
	 	 	    	   }
	 	    			//System.out.println("end");
	 	    			list.add(new Token(TokenType.EndofLine));	
	 	    			}
	          }
	          
             return list;
             
             
             
	      }  
}



