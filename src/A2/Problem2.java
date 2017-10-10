package A2;

public class Problem2 {
 
	static int i = 0;
	public static int f(int n){
		
		if(n>1000) return n-4;
		i++;
		return f( f(n + 5));
	}
    
	
	
	public static void main(String[] args) {
		//System.out.println(f(1001));
		System.out.println(f(-1000));
		System.out.println(i);
		//System.out.println(f(0));
	}
}

