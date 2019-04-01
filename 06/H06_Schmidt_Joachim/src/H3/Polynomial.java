package H3;

public class Polynomial extends GeneralPolynomial implements Function {

	/**
	 * Constructs a new instance of this class
	 * @param coef The coefficients in an array of double
	 */
	public Polynomial(double[] coef) {
		super(coef);
		assert getDegree() > 0;
	}

	/**
	 * Get the value of this function for this specific parameter
	 * @param param The parameter
	 * @return The value of this function for this parameter
	 */
	public double getValue(double param) {
		
		double result = 0.0;
		
		for (int i = 0; i <= getDegree(); i++) {
			result += ( getCoefficients()[getDegree() - i] * 
					    Math.pow(param, i) );
		}
		
		return result;
		
	}
	
	/**
	 * Creates a new general polynomial containing the first derivative
	 * @return The first derivative of this general polynomial
	 */
	public Polynomial firstDeriv() {
		
		if (getDegree() < 1)
			return new Polynomial(new double[] {0.0});
		
		int newDegree = getDegree() - 1;
		double[] coefs = new double[newDegree + 1];
		
		for (int i = newDegree; i >= 0; i--) {
			
			coefs[i] = ( (getDegree() - (i)) * 
						 getCoefficients()[i] );
			
		}
		
		return new Polynomial(coefs);
	}
	
	/**
	 * Creates a new general polynomial containing the first antiderivative
	 * @return The antiderivative of this general polynomial
	 */
	public Polynomial antiDeriv() {
		
		int newDegree = getDegree() + 1;
		double[] coefs = new double[newDegree + 1];
		
		coefs[newDegree] = 0.0;
		for (int i = newDegree - 1; i >= 0; i--) {
			
			coefs[i] = ( (1.0 / (getDegree() - (i-1))) * 
						 getCoefficients()[i] );
			
		}
		
		return new Polynomial(coefs);
		
	}

	
	
}
