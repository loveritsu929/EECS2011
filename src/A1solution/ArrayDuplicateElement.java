package A1solution;

import A1.TestHelper;

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
	 * client does not wish their array to be altered by this method, they can pass a clone to keep their original array
	 * intact.
	 * 
	 * 
	 * Example 1: on input {2, 5, 3, 1, 4} it returns -1.
	 * 
	 * Example 2: on input {2, 5, 2, 1, 4} it returns 2.
	 * 
	 * Example 3: on input {2, 5, 2, 1, 5} it may return 2 or 5 (the choice is up to the method).
	 * 
	 * @param ints
	 *            the input array.
	 */

	public static int findDuplicate(int[] ints) {

		/*
		 * For full credit, your solution should be in-place and take linear time.
		 * 
		 * WARNING: No sorting method is known that is both in-place and linear-time. So, anyone who started with
		 * sorting the input array, forfeited the full credit. There are also methods to solve the problem that take
		 * linear-time but are not in-place, e.g., they use an extra array, arrayList, hash table, string, ... as local
		 * variable. Such structures take more than constant space and hence are not in-place. I mentioned this several
		 * times in class and on the course forum. Also, there are methods that are in-place but are not linear-time,
		 * e.g., they exhaustively compare every pair of elements (with a double nested loop) that takes quadratic time.
		 * These solutions do not take full advantage of the given precondition to gain time/space efficiency. So, how
		 * can we use the precondition to achieve such efficiency? There are a number of ways to do it. Below, we will
		 * show one such solution.
		 * 
		 * DESIGN IDEA: We say a position i is "perfect" if ints[i] = i+1. If all n positions were perfect, then there
		 * would be no duplicates, since all n numbers 1..n would appear in increasing order. So, we scan ints[] and
		 * attempt to rearrange the elements in order to make them all perfect, if possible. Suppose we are now scanning
		 * position i and currently ints[i] = j+1. From the pre-condition we know that j must be between 0 and n-1
		 * (which is a legal index value for the input array). If i=j, then position i is perfect; we advance the scan	
		 * to the next position. Otherwise, we continue working on position i and try to swap ints[i] with the element
		 * ints[j] to make position j perfect (note that j is a legal index value). If position j is already perfect,
		 * i.e., ints[j]=j+1, then we have found a duplicate, namely, both positions i and j have element value j+1. If
		 * position j is not yet perfect, we swap the elements at positions i and j. Such a swap does not alter the
		 * final outcome, but makes one more position perfect, namely, position j. Position i is not yet perfect. So, in
		 * the same manner we continue working on ints[i] until either it becomes perfect or we find it is a duplicate.
		 * If the former occurs, we advance the scan to the next position. If the latter occurs, we stop and return that
		 * duplicate value. If the scanning is complete and we haven't returned yet, that indicates that all positions
		 * are now perfect, and hence there are no duplicates. In that case we return -1.
		 * 
		 * RUNNING TIME: At first look it seems we have double nested loops and the running time should be quadratic.
		 * But let's look more closely. Each iteration of the inner while-loop either increases the number of perfect
		 * positions or stops and returns an answer. But the number of perfect positions cannot exceed n. Therefore the
		 * total number of iterations of the inner while-loop, over all iterations of the outer for-loop, cannot exceed
		 * n. Therefore, the running time of this method is linear, i.e., O(n).
		 * 
		 * IN-PLACE: The method uses only the two int local variables i and j, hence it is clearly in-place.
		 * 
		 */

		for (int i = 0; i < ints.length; i++) {
			while (ints[i] != i + 1) { // position i is not yet perfect
				int j = ints[i] - 1; // by the pre-condition j is an integer between 0 and ints.length - 1
				if (ints[j] == j + 1) { // position j is already perfect
					return ints[i]; // ints[i] = ints[j] = j+1 is duplicate
				}
				// Position j is not perfect. Swap the pair of elements at positions i and j.
				// This doesn't destroy or create new duplicates, but increases the number of perfect positions.
				ints[i] = ints[j];
				ints[j] = j + 1; // now position j is perfect
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
		
	}
}