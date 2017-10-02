/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 1, Problem 1
 * 
 * Student Full Name:     Chenxing Zheng
 * Student eecs account:  cxing95
 * Student ID number:     214634901 
 **********************************************************/

package A1;

/**
 * The purpose of this class is to find if an integer input array contains at least one duplicate element.
 * 
 * PRE-CONDITION: each element in the input array is an integer between 1 and n, where n is the length of the array.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArrayDuplicateElement {

	/**
	 * The findDuplicate() method takes an array of ints with the above stated pre-condition. It returns the special
	 * value -1 if the input array has no duplicates (i.e., all its elements are distinct). Otherwise, it arbitrarily
	 * selects one of the duplicate element values and returns it.
	 * 
	 * This method may alter its input array, but its output is with respect to the originally given input array. If the
	 * client does not wish their array to be destroyed by this method, they can pass a copy of their array to keep
	 * their original array intact.
	 * 
	 * 
	 * Example 1: on input {2, 5, 2, 1, 4} it returns 2.
	 * 
	 * Example 2: on input {2, 5, 3, 1, 4} it returns -1.
	 * 
	 * Example 3: on input {2, 5, 2, 1, 5} it may return 2 or 5 (the choice is up to the method).
	 * 
	 * @param ints
	 *            the input array.
	 */

	public static int findDuplicate(int[] ints) {

		/* For full credit, your solution should be in-place and take linear time. */

		// Each element in the input array is an integer between 1 and n (positive!), where n is the length of the array.
		
		// If there are duplicates and we use them as index of the array, they all point to the same int.
	    // All the ints are positive, we can turn ints[int[i] - 1] to -ints[int[i] - 1] to indicate we have read int[i].
		
		int length = ints.length;
		
		for(int i = 0; i < length; i++){
			
			if( ints[i] > 0){
				if( ints[ints[i] - 1] < 0){
					// negative == have read this int before
					return ints[i];
				}
				
				else{
					// Negate the int with index==ints[i]-1, as a mark.
					ints[ints[i] - 1] = -ints[ints[i] - 1];
				}
			}
			else{
				//ints[i] < 0
				
				if( ints[-ints[i] - 1] < 0){
					// negative == have read this int before
					return -ints[i];
				}
				else{
					// Negate the int with index==-ints[i]-1, as a mark.
					ints[-ints[i] - 1] = -ints[-ints[i] - 1];
				}
				
			}
		}
		
		return -1;
	}

	/**
	 * testDrive tests findDuplicate by comparing its returned result with the correct answer.
	 * 
	 * @param testArray
	 *            the test array
	 * @param answer
	 *            contains blank & comma separated duplicate values in the test array, e.g., " 28 , 85 , 2854 ".
	 * 
	 */
	public static void testDrive(int[] testArray, String answer) {

		System.out.println("Test Array " + TestHelper.stringInts(testArray) + ":");
		String result = " " + findDuplicate(testArray) + " ";
		System.out.println("Output:" + ((result.equals(" -1 ")) ? " No duplicates found." : result + "is a duplicate.") + "\n");
		TestHelper.verify(answer.contains(result), "Wrong!!!  No chocolate ice cream!");
	}


	/**
	 * main() runs test cases on your findDuplicate() method.
	 */

	public static void main(String[] args) {

		System.out.println("Let's test findDuplicate on some arrays: \n ");

		// TEST 1:
		testDrive(new int[] { 5, 2, 10, 7, 4, 9, 3, 6, 1, 8 }, " -1 ");


		// TEST 2:
		testDrive(new int[] { 10, 8, 5, 2, 6, 4, 9, 2, 7, 1 }, " 2 ");

		// TEST 3:
		testDrive(new int[] { 8, 4, 9, 5, 2, 4, 10, 6, 2, 1 }, " 4 , 2 ");


		System.out.println("\nAdditional tests done by the student or TA:\n");

		// Insert your additional test cases here.
		testDrive(new int[] { 3, 1, 5, 4, 7, 8, 2, 6 }, " -1 ");
		
		testDrive(new int[] { 3, 1, 5, 4, 6, 6, 7, 8 }, " 6 ");
		
		testDrive(new int[] { 5, 2, 10, 7, 4, 9, 3, 6, 10, 8 }, " 10 ");
	}
}