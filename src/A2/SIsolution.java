package A2;

import java.util.Arrays;

public class SIsolution {
	private static int[] uPL;
	private static int[] qList;
	private static int budget;
	private static int numR = 0;
	
	private static void reportSI(int[] uPL1, int budget1 ){
		uPL = uPL1;
		budget = budget1;
		qList = new int[uPL1.length];
		
		System.out.println("Unit Price List = " + Arrays.toString(uPL) + ". Budget = " + budget );
		
		
		reportSI(budget, uPL1.length - 1, 0);
		System.out.println("The number of saturated Itineraries = " + numR);
	}
	
	private static void reportSI(int rem, int maxIndex, int more){
		
		if(rem< 0 || (maxIndex < 0 && rem >= uPL[0])){
			//over budget
			return;
			
		}
		qList[maxIndex] += more;
		
		if(rem < uPL[0]) outputSI(budget - rem);
		else if(maxIndex == 0) 
			//
			reportSI(rem % uPL[0], 0, rem / uPL[0]);
		else{
			
			reportSI(rem, maxIndex - 1, 0);
			reportSI(rem - uPL[maxIndex], maxIndex, 1);
		}
		
		qList[maxIndex] -= more; //back-track to pre-call quantity 
		
		
	}
	
    public static void outputSI(int exp){
    	numR++;
		System.out.println("Quantities = " + Arrays.toString(qList) + ". Total Price = " + exp );
    }
	
	public static void main(String[] args) {
		int[] pricelist = {3, 5, 7};
		reportSI(pricelist, 88);
	}

}
