package H3;

import java.util.Arrays;

public class Main {

	/**
	 * To test your implementation
	 */
	public static void main(String[] args) {
		
		double [] coeffS = {3 ,0.333 ,0 ,5};
		Polynomial s = new Polynomial ( coeffS ) ;
		Polynomial sD = s.firstDeriv() ;
		Polynomial sAD = s.antiDeriv() ;
		System.out.println ( Arrays.toString ( s.getCoefficients () ) ) ;
		System.out.println ( Arrays.toString ( sD.getCoefficients () ) ) ;
		System.out.println ( Arrays.toString ( sAD.getCoefficients () ) ) ;
		System.out.println ( s.getValue (4) ) ;
		
		double[] coeffs2 = {0, 0, 4, 6.4};
		Polynomial test = new Polynomial(coeffs2);
		System.out.println(Arrays.toString(test.getCoefficients()));

		
	}

}
