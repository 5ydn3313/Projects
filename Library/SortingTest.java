import java.util.ArrayList;
import java.util.*;

public class SortingTest {
	
	public static void main(String args[]) {
		
//		ArrayList<Integer> testList = new ArrayList<Integer>();
//		testList.add(4);
//		testList.add(3);
//		testList.add(76);
//		testList.add(35);
//		testList.add(4);
//		testList.add(44);
//		testList.add(5);
//		testList.add(10);
		
		
		//create a list of Integer type 
		//and intialize it using List.of()
		
		ArrayList<Integer> testList = new ArrayList<>(List.of(4,3,76,35,4,44,5,10));
		
		
		//ArrayList<Integer> sortedList = Sorting.insertionSort(testList);
		//ArrayList<Integer> sortedList = Sorting.bubbleSort(testList);
		ArrayList<Integer> sortedList = Sorting.selSort(testList);
		
		System.out.println(sortedList);
	}

}
