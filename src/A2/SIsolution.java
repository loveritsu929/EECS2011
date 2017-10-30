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
	
	/**
	 * <pre>
	 * Overloaded: this is the purely recursive routine
	 *
	 * PreCondition:
	 *   (1) Total Price = budget - rem = SUM_i(qList[i]*uPL[i]) + more*uPL[maxIndex]
	 *   (2) qList[i] >= 0  (if i >= maxIndex); 
	 *                 = 0  (if i <  maxIndex)
	 *   (3) more >= 0,  maxIndex >= -1
	 *   (4) uPL[-1] = 0 (fictitiously used only in (1) if maxIndex = -1)
	 * 
	 * PostCondition:
	 *   prints each saturated itinerary (with its total price) 
	 *   whose quantity array, denoted Q[], satisfies:
	 *   Q[i] >= 0               (if i < maxIndex); 
	 *        >= qList[i] + more (if i = maxIndex); 
	 *         = qList[i]        (if i > maxIndex)
	 * </pre>
	 * 
	 * @param maxIndex
	 *            item quantities up to this index are tentative; the rest are fixed
	 * 
	 * @param rem
	 *            the remaining amount of budget to be spent on tentative items.
	 * 
	 * @param more
	 *            this many more should be added to qList[maxIndex]
	 */
	private static void reportSI(int rem, int maxIndex, int more){
		
		if(rem< 0 || (maxIndex < 0 && rem >= uPL[0])){
			//over budget
			return;
			
		}
		qList[maxIndex] += more;
		
		if(rem < uPL[0]) outputSI(budget - rem); // find & report
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
