/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 1, Problem 2
 * 
 * Student Full Name:     Chenxing Zheng
 * Student eecs account:  cxing95
 * Student ID number:     214634901 
 **********************************************************/

package A1;

/**
 * The purpose of this class is to find the longest almost flat contiguous subarray of an input
 * array of ints. A subarray is almost flat if no two elements of it differ by more than 1.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class LongestAlmostFlatSubarray {

  	/**
	 * longestAFS() returns the longest almost flat subarray of an input array of ints.
	 * 
	 * This method does not alter its input array.
	 * 
	 * @return an array int[2] of the form {start, len} representing the longestAFS of ints[] as a
	 *         contiguous subarray of length len starting at index start.
	 * 
	 *         For example, on the input array {7, 7, 2, 8, 7, 7, 8, 8, 7, 1, 2, 1, 7, 8}, it
	 *         returns {3, 6}, indicating the longest AFS of this array is the subarray {8, 7, 7, 8,
	 *         8, 7} which starts at index 3 and has length 6.
	 * 
	 * @param ints
	 *            the input array.
	 */
	
	
	public static int[] longestAFS(int[] ints) {

		/* For full credit, your solution should be in-place and take linear time. */
		//A subarray is almost flat if no two elements of it differ by more than 1.
		
		int arrlength = ints.length;
		
		// the length of the longest AFS in the final result
		int resultLength = 0;	
		
		// the length of the current AFS
		int tmpLength = 0;	
		
		// index of the start of the current AFS
		int initialIndex = 0;
		
		// index of the start of the longest AFS 
		int resultIndex = 0;
		
		// index of the last minimum in the current AFS
		int minIndex = 0;
		
		// maximum in current AFS
		int maxInt = ints[0];
		
		// maximum in current AFS
	    int	minInt = ints[0];
		
		
		int[] resultArr = new int[2];
		
		for(int i = 0; i< arrlength; i++){
			if(Math.abs(ints[i] - maxInt) <= 1 && Math.abs(ints[i] - minInt) <= 1){
				// satisfied, the AFS grows
				
				tmpLength++;
				
				// find the maximum ( the larger int)
				maxInt = (ints[i] > minInt) ? ints[i] : maxInt;
				
				// increment the index of minimum if we find a new one
				if(ints[i] <= minInt){
					minInt = ints[i];
					minIndex = i;
				}
				
				//update the result
				resultLength = (resultLength > tmpLength) ? resultLength : tmpLength;
				resultIndex = (resultLength > tmpLength) ? resultIndex : initialIndex ;
				
			}
			else if(ints[i] - maxInt == 1 && ints[i-1] == maxInt ){
				// the AFS is broken, but int[i] and the previous maximums can form a new AFS
				// eg: 2, 2, 3, 3, 4
				//     AFS: 2, 2, 3, 3 ==> AFS: 3, 3, 4
				
				// the new AFS start at the leftmost maximum
				initialIndex = minIndex + 1; 
			    
				// update the minimum and maximum
				minInt = maxInt; 
				maxInt = ints[i];
		
				
				tmpLength = i - initialIndex + 1;   // order is very important !!!!!!!!!!!!!!!
				
				// update result
				resultLength = (resultLength > tmpLength) ? resultLength : tmpLength;
				resultIndex = (resultLength > tmpLength) ? resultIndex : initialIndex ;
				
			}
			else{
				// not satisfied, differ more than 1 
				// --> reset
				
				initialIndex =  i;
				
				minInt = ints[i];
				maxInt = ints[i];
				
				resultLength = (resultLength > tmpLength) ? resultLength : tmpLength;
				tmpLength = 1;
			}
			
			//System.out.println("Now we are at int[" + i + "]" + ": " + ints[i] +"-----" + "minInt:" + minInt + " maxInt: " + maxInt + " resultIndex: " + resultIndex
			//		+ " resultlength: " + resultLength + " tmpLength: " + tmpLength);
			
		
			
		}
		
		resultArr[0] = resultIndex;
		resultArr[1] = resultLength;
	
		return resultArr; 
	}


	/**
	 * testDrive tests longestAFS by comparing its returned result with the correct answer.
	 * 
	 * @param testArray
	 *            the test array
	 * @param answer
	 *            the correct answer to the test array
	 * 
	 */
	public static void testDrive(int[] testArray, String answer) {

		System.out.println("Longest almost flat subarray of " + TestHelper.stringInts(testArray));
		String result = TestHelper.stringInts(longestAFS(testArray));
		System.out.println("is the subarray specified as [ start index , length ] = " + result + ". \n");
		TestHelper.verify(result.equals(answer), "Wrong!!!  No brownies!");
	}

  	/**
	 * main() runs test cases on your longestAFS() method. Prints summary information on basic
	 * operations and halts with an error (and a stack trace) if any of the tests fail.
	 */

	public static void main(String[] args) {

		System.out.println("Let's test longestAFS on some arrays: \n");

		// TEST 1:
		testDrive(new int[] { 7, 7, 2, 8, 7, 7, 8, 8, 7, 1, 2, 1, 7, 8 }, "[ 3 , 6 ]");

		// TEST 2:
		testDrive(new int[] { 7, 7, 2, 3, 8, 8, 8, 8, 8, 6, 1, 2, 1, 7, 8 }, "[ 4 , 5 ]");

		// TEST 3:
		testDrive(new int[] { 7, 7, 2, 8, 7, 7, 8, 8, 8, 9, 9, 6, 1, 2, 1, 7, 8 }, "[ 3 , 6 ]");

		// TEST 4:
		testDrive(new int[] { 7, 7, 2, 8, 7, 7, 8, 8, 8, 9, 9, 8, 9, 9, 6, 1, 2, 1, 7, 8 }, "[ 6 , 8 ]");


		System.out.println("\nAdditional tests done by the student or TA:\n");
		testDrive(new int[] { 7, -1, 2, -2, -3, -2, -2, 8, 7, 1, 2, 1, 7, 8 }, "[ 3 , 4 ]");
		testDrive(new int[] { 7, 7, 9, 8, 8, 8, 8, 8, 9, 7, 2, 6, 7, 8 }, "[ 2 , 7 ]");
		testDrive(new int[] { 0, 0, 2, -8, 7, 7, -8, -8, -7, -9, 2, 1, 7, 8 }, "[ 6 , 3 ]");
		testDrive(new int[] { 0 }, "[ 0 , 1 ]");
		testDrive(new int[] { 0, 0, 0, 0 }, "[ 0 , 4 ]");

		// Insert your additional test cases here.
	}
}