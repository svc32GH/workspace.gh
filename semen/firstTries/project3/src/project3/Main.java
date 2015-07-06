package project3;

public class Main {
	
	public static void main (String args[]){
		Reader r = new Reader();
		int n = r.scan();
		
		int m = calculate(n);
		System.out.println("m = " + m);
	}
	
	public static int calculate (int x){
		return x + 1;
	}
}
