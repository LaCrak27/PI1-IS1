package exercises;

import java.math.BigInteger;
import java.math.BigInteger.*;

public class Exercise4 {
	
	public static Double funcRecDouble(Integer a) {
		if(a < 10) {
			return 5d;
		}
		else {
			return Math.sqrt(3*a) * funcRecDouble(a-2);
		}
	}
	
	public static BigInteger funcRecBig(Integer a) {
		if(a < 10) {
			return BigInteger.valueOf(5);
		}
		else {
			return funcRecBig(a-2).multiply(BigInteger.valueOf(3*a).sqrt());
		}
	}
	
	public static Double funcItDouble(Integer a) {
		Double acDouble = 5d;
		while(a >= 10) {
			acDouble *= Math.sqrt(3*a);
			a -= 2;
		}
		return acDouble;
	}
	
	public static BigInteger funcItBig(Integer a) {
		BigInteger ac = BigInteger.valueOf(5);
		while(a >= 10) {
			ac.multiply(BigInteger.valueOf(3*a).sqrt());
			a -= 2;
		}
		return ac;
	}

}
