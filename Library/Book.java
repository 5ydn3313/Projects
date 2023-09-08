
public class Book {

	private String name;
	private String genre; 
	private String authorName; 
	private int wCount; 
	private int pCount;
	
	//default constructor
	public Book() {
		this.name = "";
		this.genre = "";
		this.authorName = "";
		this.wCount= 0;
		this.pCount= 1;
	}
	
	public Book (String name, String genre, String authorName, int wCount, int pCount) {
		this.name=name;
		this.genre=genre;
		this.authorName= authorName;
		this.wCount= wCount;
		this.pCount =pCount;

	}
	//return whatever state name is in 
	public String getName() { 
		return name;
	}
	//no return, takes something in (has parameters)
	public void setName(String name) {
		this.name= name;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre=genre;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName=authorName;		
	}
	
	public int getwCount() {
		return wCount;
	}
	
	public void setwCount(int wCount) {
		this.wCount=wCount;		
	}
	
	public int getpCount() {
		return pCount;
	}
	
	public void setpCount(int pCount) {
		this.pCount=pCount;
	}
	
	public String toString() {
		String retStr = String.format("\"%s\" - %s - by: %s has %d words in %d pages", this.name, this.genre, this.authorName, this.wCount, this.pCount);
    	return retStr;
	}

}
