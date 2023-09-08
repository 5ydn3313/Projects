import java.io.*;
import java.util.ArrayList;
public class Sorting {
 
	//create sorting algorithm that takes in a list of numbers and sorts them 
	
	
	
	public static ArrayList<Integer> insertionSort(ArrayList<Integer> toSort ) { //taking in an array list of class Integer 
         /*The outer loop steps the index variable through each 
          * subscript in the array, starting at 1. The portion of the 
          * array consisting of element 0 by itself  is already sorted.
		  */
		
		ArrayList<Integer> returnList = toSort; //exact copy of toSort
		
		for(int i =1; i< returnList.size(); i++ ) {
			int unsortedInt= returnList.get(i); //use .get(i) for an arraylist and [i] for array
			int j = i-1;
			
			
			/*
			 * Move the first element in the still unsorted part 
			 * into its proper position within the sorted part. 
			 * 
			 */
			while(j>=0 && returnList.get(j) > unsortedInt) {
				int nextInt=returnList.get(j+1);
				
				  nextInt = returnList.get(j);
				  
				  j= j-1; //why decrement this 
				
			}
			/*
			 * Insert the unsorted value in its proper position
			 * within the sorted part.
			 */
			returnList.add(j+1,unsortedInt);  //place unsortedInt in place of j+1, increases size of list by 1 
			returnList.remove(i+1);
			 
		
		}
		//System.out.println(toSort);
		return returnList;
		
		
	}
	
	
	
//Command + / to comment out section of code 
//	public ArrayList<Integer> bubbleSort(ArrayList<Integer> toSort) {
//		int lastInt; // position of last element to compare
//		int compInt; // Index of comparison integer
//		int swap; //used to swap elements 
//		
//		/*The outer loop positions lastInt at the last element 
//		 * 
//		 */
//		for(lastInt = toSort.size()-1; lastInt>=0; lastInt--) {
//			
//			for(compInt=0; compInt <=lastInt - 1; compInt++) {
//				
//				//Compare an element with its neighbor.
//				
//				if(toSort.get(compInt) > toSort.get(compInt+1))
//				{
//					//Swap the two elements 
//					swap = toSort.get(compInt);
//					swap = toSort.get(compInt+1); //original :array[int]= coded error: toSort.get(compInt
//					swap= toSort.get(compInt+1);
//				}
//			}
//			
//		}
//		
//			
//		return toSort;
//		
//	}
	
	/*
	 * Bubble sorting method using visual website template
	 * repeatedly swapping the adjacent elements if they are in the wrong order
	 */
	public static ArrayList<Integer> bubbleSort(ArrayList<Integer> toSort) {
		ArrayList<Integer> returnList = toSort; 
		//int lastUnsortedInt = returnList.size()-1; //last unsorted int
		
		
		/*
		 * execute code within do while swapped is set to false
		 * 
		 */
		boolean swapped;
		do {
			int lastPos;
			int index;
			int temp;
			swapped = false; 
			for(int i = 0; i< returnList.size()-1; i++) {
			  // for(int j= 0; j<= returnList.size()-1; j++) {
			  //for (lastPos = returnList.size()-1; lastPos >=0; lastPos--) {
				  
			       //for(index =0; index <= lastPos -1; index++) {
					if (returnList.get(i)>returnList.get(i+1)) {
						
						//swap the position at i and i+1 
						
						temp = returnList.get(i);
						returnList.set(i,returnList.get(i+1));
						returnList.set(i+1,temp);
					    //returnList.remove(lastPos+1);
						swapped = true;
						
					}
				}
			//}
			 
		} while (swapped);
			
        
		
		return returnList;
	}
	
	
 public static ArrayList<Integer> selSort(ArrayList<Integer> toSort) {
	 
	ArrayList<Integer> returnList = toSort;  
	
	int minInt = 0; //miminum value in array 
	int j = 0; //holds value of new small int 
	int minIntIndex; // index of smallest integer 
	
	//int i is the starting position and assumed to be the smallest 
	
	for(int i = 0; i< returnList.size(); i++) {
		
		
		minInt= returnList.get(i);//getting the actual value 
		minIntIndex = i;//getting the value's index 
		
		for(j=i+1; j<returnList.size(); j++) {
			
			//if the smallest value in the rest of the list is 
			//less than the current smallest value swap those values 
			if (returnList.get(j)<minInt) {
				minInt= returnList.get(j);
				minIntIndex = j;
				
			}
			
		}
		
		//Swap the smallest element with the first element 
		
		int temp = returnList.get(minIntIndex);
		returnList.set(minIntIndex, returnList.get(i));
		returnList.set(i,temp);
		
		
		
	}
	
	 
	return toSort;
	
	
 }
 
 
 //public static void stringCompare() {
	 
 //}
	
}





 
//currently organized by first name
//  it would nice if we could sort by anything first name or last name or pages 