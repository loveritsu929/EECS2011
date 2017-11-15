package A1solution;

import A1.TestHelper;

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
	 * The input is an arbitrary integer array ints[ ]. A contiguous subarray of ints is called almost flat if no two
	 * elements of that subarray differ by more than one. The method longestAFS() takes any integer input array
	 * ints[0..n-1] with n elements and returns a description of its longest almost flat subarray in the form of a
	 * two-element array {start, len}, where start is the starting index of the subarray and len is its length. If there
	 * are several such optimum subarrays, all with the same maximum length, this method breaks the tie by selecting the
	 * optimum subarray with minimum starting index.
	 * 
	 * This method does not alter its input array.
	 * 
	 * @return an array int[2] of the form {start, len} representing the longestAFS of ints[] as a contiguous subarray
	 *         of length len starting at index start.
	 * 
	 *         For example, on the input array {7, 7, 2, 8, 7, 7, 8, 8, 7, 1, 2, 1, 7, 8}, it returns {3, 6}, indicating
	 *         the longest AFS of this array is the subarray {8, 7, 7, 8, 8, 7} which starts at index 3 and has length
	 *         6.
	 * 
	 * @param ints
	 *            the input array.
	 */

	public static int[] longestAFS(int[] ints) {

		/*
		 * For full credit, your solution should be in-place and take linear time.
		 * 
		 * DESIGN IDEA: We scan the input array. At the end of iteration i the scanned prefix is ints[0..i]. Within this
		 * prefix we maintain 3 kinds of subarrays:
		 * 
		 * (1) the longest almost flat subarray of the scanned prefix starts at bestStart and has length bestLength.
		 * This subarray is the best solution found so far.
		 * 
		 * (2) the longest almost flat suffix (lafSuffix) of the scanned prefix is the longest almost flat subarray of
		 * ints[] ending at index i. Its starting position is stored in the variable lafSuffixStart. Variable delta,
		 * which is 0, +1 or -1, indicates the variation of element values within this suffix, i.e., all elements of
		 * this suffix are either equal to ints[lafSuffixStart] or ints[lafSuffixStart] + delta.
		 * 
		 * (3) the longest flat suffix (lFlatSuffix) is the longest subarray of ints[] ending at index i with all
		 * elements equal. Its starting position is stored in the variable lFlatSuffixStart.
		 * 
		 * Subarray (1) will become the final outcome at the end of the scanning process. Subarray (2) is needed to
		 * update subarray (1) after each scanning iteration. Subarray (3) is needed to keep track of where the next
		 * potential subarray (2) should start.
		 * 
		 * RUNNING TIME: The for-loop iterates n times and takes constant time per iteration, hence it clearly takes
		 * linear time, i.e., O(n) time.
		 * 
		 * IN-PLACE: the method is clearly in-place, since it uses only a constant number of int local variables.
		 */

		int bestStart = 0, bestLength = 0, lafSuffixStart = 0, delta = 0, lFlatSuffixStart = 0;

		for (int i = 0; i < ints.length; i++) {

			/*
			 * UPDATE lafSuffix.
			 */
			if (ints[i] == ints[lafSuffixStart] || ints[i] == ints[lafSuffixStart] + delta) { // do nothing
			} else if (delta == 0 && Math.abs(ints[i] - ints[lafSuffixStart]) == 1) {
				delta = ints[i] - ints[lafSuffixStart]; // delta changes from 0 to +1 or -1
			} else if (Math.abs(ints[i] - ints[lFlatSuffixStart]) == 1) { // start new lafSuffix at lFlatSuffixStart
				lafSuffixStart = lFlatSuffixStart;
				delta = ints[i] - ints[lFlatSuffixStart];
			} else { // start a new lafSuffix from position i
				lafSuffixStart = i;
				delta = 0;
			}
			
			/* UPDATE lFlatSuffix */
			if (ints[i] != ints[lFlatSuffixStart]) { // start a new lFlatSuffix at i
				lFlatSuffixStart = i;
			}

			/*
			 * UPDATE best solution --- The new best solution is either the old best solution (from the previous
			 * iteration) or the new lafSuffix (from the current iteration), whichever is longer. Update the best
			 * solution if lafSuffix is strictly longer. This also satisfies the stated tie breaking rule.
			 */
			if (bestLength < i - lafSuffixStart + 1) {
				bestStart = lafSuffixStart;
				bestLength = i - lafSuffixStart + 1;
			}
		}
		
		return new int[] { bestStart, bestLength };
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

		// Insert your additional test cases here.
	}
}