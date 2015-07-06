package project3;

import java.util.Scanner;

public class Reader {
	
	private int f;

	public int scan() {
		System.out.println("Enter number");
		Scanner scn = new Scanner(System.in);
		f = scn.nextInt();
		return f;
	}
	
}