package A2;

import java.util.function.BinaryOperator;

public class DecimalToBinary {

	public static void Rec10To2(int n){
		
		if(n/2 != 0) Rec10To2(n/2);
		System.out.print(n%2);
	}
	
	public static void Iter10To2(int n){
		int result = 0;
		int digit = 1;

		for(; n/2 != 0; n/=2){
			result = result  + n%2 * digit;
			digit *= 10;
			if(n/2 == 1)  result = result + 1 * digit;
		}
	
		System.out.print(result);
		
	}
	
	public static void main(String[] args) {
		//System.out.println(Integer.toBinaryString(-25));
		Rec10To2(25); System.out.print("===="); Iter10To2(25);
		System.out.println();
		Rec10To2(3); System.out.print("===="); Iter10To2(3);
		System.out.println();
		Rec10To2(4); System.out.print("===="); Iter10To2(4);
		System.out.println();
		Rec10To2(99); System.out.print("===="); Iter10To2(99);
	}
}
