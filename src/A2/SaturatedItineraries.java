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
	
	//private static int[] quantities;
	private static int numResults = 0;
	
	public static void reportSI(int[] unitPriceList, int budget){
		int expense = 0;
		int[] quantities = new int[unitPriceList.length];
		
		System.out.println("Unit Price List = " + Arrays.toString(unitPriceList) + ". Budget = " + budget );
		
		
		reportSI(unitPriceList, budget, quantities, expense, unitPriceList.length-1);
		System.out.println("The number of saturated Itineraries = " + numResults);
	}
	
	public static void reportSI(int[] unitPriceList, int budget, int[] quantities, int expense, int index){
		
		int localExp = expense;
		int[] localQuan = Arrays.copyOf(quantities, quantities.length);

		if(index == -1) index = unitPriceList.length - 1;
		
		localQuan[index]++;
		localExp = unitPriceList[index] + expense;
	
	    if(localExp > budget) {
	    	localQuan[index]--;
			localExp -= unitPriceList[index];
			return;
		}
	    else if(localExp <= budget && localExp + unitPriceList[0] > budget){
			numResults++;
			System.out.println("Quantities = " + Arrays.toString(localQuan) + ". Total Price = " + localExp );
		}
		else{
			
			index --;
			for(int i=0; i < unitPriceList.length; i++)
			reportSI(unitPriceList, budget, localQuan, localExp, i);		
		}
		
	}
	
	public static void main(String[] args) {
		int[] pricelist = {3, 5, 7};
		reportSI(pricelist, 88);
	}
}
