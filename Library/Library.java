import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Library {
    private String fileName; 
    
    private ArrayList<Book> inventory;
    
    //default constructor
    public Library() {
    	this.fileName="";
    	this.inventory = new ArrayList<Book>(); //assigns inventory to empty inventory list
    }
    //this constructor builds inventory off of passed in file 
    public Library(String fileName) throws FileNotFoundException {
    	this.fileName=fileName;
    	this.inventory= makeList(fileName);
    	
    }
    
    public Library(String fileName, ArrayList<Book> inventory) {
    	this.fileName =fileName;
    	this.inventory= inventory;
    }
    
    
    public String getFileName() {
    	return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName=fileName;
    }
    
    public ArrayList<Book> getInventory() {
        return inventory;  
    }
    
    public void setInventory(ArrayList<Book> inventory) {
        this.inventory=inventory;
    }
    
  //take in string filename and actually export and properly construct an ArrayList
    public static ArrayList<Book> makeList(String fileName) throws FileNotFoundException {
    	String name;
    	String genre; 
    	String authorName;
    	int wCount;
    	int pCount;
    	ArrayList<Book> bookList = new ArrayList<Book>();
    	Scanner sc = new Scanner(new File(fileName));
    	sc.useDelimiter(",|\n");//comma or newline dictation
    	while(sc.hasNext()) {
    		name = sc.next();
    		genre = sc.next();
    		authorName = sc.next();
    		wCount = sc.nextInt();
    		pCount= sc.nextInt();
    		Book newBook = new Book (name,genre,authorName, wCount,pCount);//creating a new book object 
    		bookList.add(newBook);    		
    	}
    	return bookList;
    }
   
    public String toString() {
    	String retString ="";
    	for(int i=0; i<inventory.size(); i++) {
    		retString+= inventory.get(i).toString() + "\n"; //for every book add on string rep to our return string 	
    	}
    	return retString;
    }
    
    //sorts books by word count 
    public void insSortWCount (){
    	
    	for(int i =1; i< inventory.size(); i++ ) {
			int unsortedInt= inventory.get(i).getwCount(); //use .get(i) for an arraylist and [i] for array
			int j = i-1;
			
			
			while(j>=0 && inventory.get(j).getwCount() > unsortedInt) {
				int nextInt= inventory.get(j+1).getwCount();
				
				  nextInt = inventory.get(j).getwCount();
				  
				  j= j-1; //shifts order to look at the next value to sort 
				  	  
				 
			}	
			//at this point your putting the actual book in spot j+1
			inventory.add(j+1,inventory.get(i));  //place unsortedInt in place of j+1, increases size of list by 1 
			inventory.remove(i+1);
    	} 
    	
    }
    
    //sorts books by page count 
    public void insSortPCount (){
		for(int i=1; i<inventory.size(); i++) {
			int unsortedInt = inventory.get(i).getpCount(); 
			int j = i-1;
			
			while(j>=0 && inventory.get(j).getpCount()> unsortedInt) {
				int nextInt = inventory.get(j).getpCount();
				
				nextInt = inventory.get(j).getpCount();
				
				j= j-1;
		     }
			
			inventory.add(j+1,inventory.get(i));
			inventory.remove(i+1);
		}
	}
    
    public void bubbleSortPCount() {
    	Book temp; //you're swapping the actual books in the list not just numbers now 
    	boolean swapped; 
    	do {
    		swapped = false; 
    		for(int i = 0; i< inventory.size()-1; i++) 
    		{
    	       
    			
    		  if (inventory.get(i).getpCount()>inventory.get(i+1).getpCount())
    		  
    		  {
    				 temp = inventory.get(i);
    				 inventory.set(i,inventory.get(i+1));
    				 inventory.set(i+1,temp);
    				 swapped = true; 
    				 
    				 
    		   }
    			
    	
    	}
    	
    		
    } 
    	while (swapped);
    	
    } 	
    
    
    
    public void selSortPCount() {

    	int minPageInt; //miminum value in array 
    	int j; //holds value of new small int 
    	int minIntIndex; // index of smallest integer 
    	
    	
    	for(int i = 0; i< inventory.size(); i++) {
    		minPageInt = inventory.get(i).getpCount();//gets actual page count value 
    		minIntIndex = i;
    		
    		for (j = i+1; j<inventory.size(); j++) {
    			
    			//if a smaller value is found in the rest of the list 
    			//than the current value swap them
    	
    			if (inventory.get(j).getpCount()<minPageInt) {
    				
    				minPageInt = inventory.get(j).getpCount();
    				minIntIndex=j;
    			}
    			
    			
    			
    		}
    		
    		//Swap the book with smallest page count in its new position 
    		
    		
    	    Book temp = inventory.get(minIntIndex);
    	    inventory.set(minIntIndex, inventory.get(i));
    	    inventory.set(i,temp);
    	  
    	}
    		
      
    }
  
    public void sortBookNames() {
    	
    	for(int i = 0; i < inventory.size(); i++) {
    		for(int j = i+1; j < inventory.size(); j++) {
    			
    			//compare one titles with the others 
    			
    			if(inventory.get(i).getName().compareTo(inventory.get(j).getName())>0) {
    				
    				Book temp = inventory.get(i);
    				inventory.set(i, inventory.get(j));
    				inventory.set(j, temp);
    			}
    			
    		}
    	}
    }
}

