/**
*@author: Ishola Babatunde
*checks if x is a power of y e.g 9 is a power of 3 
*usage: PowerTest 9 3
*/
public class PowerTest{
	//takes command line argument
	public static void main(String[] args){
		if(args.length < 2){
			System.out.println("Use appropriate command e.g. to determine of 9 is a power of 3 use 'java PowerTest 9 3'");
			System.exit(0);
		}
		PowerTest powerTest = new PowerTest();
		System.out.println(""+powerTest.isPowerOfIntX(Integer.parseInt(args[0]), Integer.parseInt(args[1]) ));
	}
	
	public boolean isPowerOfIntX(int value, int x){
		int max_int = Integer.MAX_VALUE;
		int max_power_x = (int)Math.pow(x, Math.floor(Math.log(max_int)/Math.log(x)));
		return (value > 0) && (max_power_x % value == 0);	
	}
}
