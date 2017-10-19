/* *********************************************************
 * EECS2011A: Fundamentals of Data Structures,  Fall 2017
 * Assignment 2, Problem 4
 * 
 * Student Full Name:     Chenxing Zheng
 * Student eecs account:  cxing95
 * Student ID number:     214634901 
 **********************************************************/
package A2;

import java.util.Arrays;

public class SaturatedItineraries {
	
	private static int[] quantities;
	private static int numResults = 0;
	
	public static void reportSI(int[] unitPriceList, int budget){
		int expense = 0;
		
		quantities = new int[unitPriceList.length];
		
		System.out.println("Unit Price List = " + Arrays.toString(unitPriceList) + ". Budget = " + budget );
		
		
		reportSI(unitPriceList, budget, expense, 0);
		System.out.println("The number of saturated Itineraries = " + numResults);
	}
	
	public static void reportSI(int[] unitPriceList, int budget, int expense, int index){
		
		int localExp = expense;

		if(index == unitPriceList.length) index = 0;
		quantities[index]++;
		localExp = unitPriceList[index] + expense;
	
	    if(localExp > budget) {
			quantities[index]--;
			localExp -= unitPriceList[index];
			return;
		}
	    else if(localExp <= budget && localExp + unitPriceList[0] > budget){
			numResults++;
			System.out.println("Quantities = " + Arrays.toString(quantities) + ". Total Price = " + localExp );
		}
		else{
			index ++;
			//System.out.println(index);
			reportSI(unitPriceList, budget, localExp, index);		
		}
		
	}
	
	public static void main(String[] args) {
		int[] pricelist = {3, 5, 7};
		reportSI(pricelist, 88);
	}
}
