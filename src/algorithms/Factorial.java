package algorithms;


public class Factorial {
	
	public static int recursiveFactorial(int num) {
		if (num < 2) {
			return num;
		} else {
			return num * recursiveFactorial(num-1);
		}
		
	}
	
	public static int iterativeFactorial(int num) {
		int runningTotal = num;
		
		while (num > 1) {
			num--;
			
			runningTotal *= num;
		}
		
		return runningTotal;
	}
	
}
