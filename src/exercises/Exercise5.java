package exercises;

import java.math.BigInteger;

public class Exercise5 {


	public static Double ejercicio5Double(Integer n) {
		if(n <= 6) {
			return 1d;
		}
		else {
			return 1 + ejercicio5Double(n - 1) * log2(n - 1);
		}
	}
	
	public static BigInteger ejercicio5BigInteger(Integer n) {
		if(n <= 6) {
			return BigInteger.valueOf(1);
		}
		else {
			return ejercicio5BigInteger(n - 1).multiply(BigInteger.valueOf((long)log2(n - 1)));
		}
	}

	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}