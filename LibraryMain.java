import java.util.ArrayList;
import java.io.*;

public class LibraryMain {
	
	private static String fileName = "LibraryList2";

	public static void main(String[] args){
       /* make a new library object and run files 
        * use scanner to read  files 
        */
		
		Library mainLibrary = new Library();
		//do we need the buffered reader since we scan files in the makelist method 
		//library is an arraylist of books 
		try {
			
			mainLibrary = new Library(fileName);//create library object 	
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
	    
		System.out.println(mainLibrary);
		
		//mainLibrary.insSortWCount();
		
		//System.out.println(mainLibrary);
		
		//mainLibrary.bubbleSortPCount();
		
		//mainLibrary.selSortPCount();
		mainLibrary.sortBookNames();
		
		System.out.println(mainLibrary);
	}

}
