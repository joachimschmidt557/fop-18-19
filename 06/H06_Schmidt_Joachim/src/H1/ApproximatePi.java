package H1;

import java.util.function.DoubleUnaryOperator;

public class ApproximatePi {

	/**
	 * Calculate Pi according to the Monte Carlo method.
	 * @param n Number of random points to generate
	 * @return Pi as a double
	 */
	double monteCarloPi(int n) {
		
		assert n > 0;
		
		double numberOfPoints = n;
		double numberOfPointsInCircle = 0;
		
		// Create n random points
		for (int i = 0; i < n; i++) {
			
			double x = Math.random();
			double y = Math.random();
			
			if ( Math.sqrt((x*x) + (y*y)) <= 1.0 ) {
				numberOfPointsInCircle += 1;
			}
			
		}
		
		return (numberOfPointsInCircle / numberOfPoints) * 4;
	}

	/**
	 * Calculates Pi with the Integration method.
	 * @param n The accuracy of the integration. Higher value means more accuracy.
	 * @return Pi as a double
	 */
	double integrationPi(int n) {
		
		assert n > 0;
		
		double b = 1.0; double a = 0.0;
		double currentSum = 0.0;
		double h = ((b - a) / n);
		DoubleUnaryOperator f = (x) -> (4.0/(1.0+(x*x)));
		
		for (int k = 1; k <= n; k++) {
			double xk = a + k*h;
			double xk1 = a + (k-1)*h;
			
			currentSum += (f.applyAsDouble(xk) + 
						   f.applyAsDouble(xk1));
						
		}
		
		return (h/2.0) * currentSum;
	}


}
