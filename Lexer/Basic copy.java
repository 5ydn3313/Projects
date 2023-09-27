package lexer;

		import java.net.URI;
		import java.io.*;
		import java.io.IOException;
		import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

		
		public class Basic {
		public static void main(String[] args) throws Exception{
		if(args.length==0 || args.length >1) {
		 throw new Exception("Too many arguments or no argument");
		
		}
		
		
		//Path filePath = Paths.get("lexerfile.txt");
		Charset charset = StandardCharsets.UTF_8;
		Path filePath = Paths.get(args[0]);
		List<Token> txtTokens = new ArrayList<Token>();
		    List<String> lines = Files.readAllLines(filePath,charset);
		    for(String line:lines)
		    {
		        System.out.println(line);
		        try {
		        Lexer myLexer = new Lexer();
		       for(int i =0; i<line.length(); i++)
		       txtTokens.add(myLexer.lex(line).get(i)) ;
		        }
		        catch (Exception ex){
			        System.out.format("I/0 error : %s%n", ex);
			    }
		    
		    
		}
		    for (int i =0; i<txtTokens.size(); i++) {
		    	System.out.print(txtTokens.get(i));
		    	
		    }
	}
}


