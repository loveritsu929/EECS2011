package A2;

public class Problem2 {
 
	static int i = 0;
	public static int f(int n){
		
		if(n>1000) return n-4;
		i++;
		return f( f(n + 5));
		
		/* 
		 * f(1)=> f(f(6)) => f(f(f(11))) => ffff(16) ...===> 201*f(1001) => 200f(997) => 201f(1002) => 200f(998) =>
		 * 201f(1003) => 200f(999) => 201f(1004) => 200f(1000) => 201f(1005) => 200f(1001) => 199f(997)
		 * 
		 * 
		 * */
	}
    
	
	
	public static void main(String[] args) {
		//System.out.println(f(1001));
		System.out.println(f(1));
		System.out.println(i);
		//System.out.println(f(0));
	}
}

