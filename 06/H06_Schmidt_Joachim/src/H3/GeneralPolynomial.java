package H3;

public abstract class GeneralPolynomial implements Function {

	private double[] coefficients;
	private int degree;
	
	/**
	 * Removes trailing zero-value coefficients
	 * @param coef The unprocessed coefficients
	 * @return The processed coefficients
	 */
	private double[] removeTrailingZeroes(double[] coef) {
		
		double[] result;
		
		// Find out first non-zero coefficient
		int firstNonZeroCoefficient = coef.length - 1;
		for (int i = 0; i < coef.length; i++) {
			if (coef[i] != 0) {
				firstNonZeroCoefficient = i;
				break;
			}
		}
				
		// Set result to coef starting from the first non-zero coef
		result = new double[coef.length - firstNonZeroCoefficient];
		for (int i = 0; i < result.length; i++) {
			result[i] = coef[i + firstNonZeroCoefficient];
		}
		
		return result;
		
	}
	
	/**
	 * Construct a new general polynomial function
	 * @param coef An array of coefficients
	 */
	public GeneralPolynomial(double[] coef) {
		
		coefficients = removeTrailingZeroes(coef);
		degree = coefficients.length - 1;
		
	}
	
	/**
	 * Get the coefficients of this general polynomial
	 * @return The coefficients in an array of double
	 */
	public double[] getCoefficients() {
		return coefficients;
	}
	
	/**
	 * Get the degree of this general polynomial
	 * @return The degree as an int
	 */
	public int getDegree() {
		return degree;
	}
	
	/**
	 * Sets the coefficients of this general polynomial
	 * @param coef The new coefficients
	 */
	private void setCoefficients(double[] coef) {
		
		coefficients = removeTrailingZeroes(coef);
		setDegree(coefficients.length - 1);
		
	}
	
	/**
	 * Sets the degree of this general polynomial
	 * @param deg The new degree
	 */
	private void setDegree(int deg) {
		
		degree = deg;
		
	}
	
	/**
	 * Creates a new general polynomial containing the first derivative
	 * @return The first derivative of this general polynomial
	 */
	public abstract GeneralPolynomial firstDeriv();
	
	/**
	 * Creates a new general polynomial containing the first antiderivative
	 * @return The antiderivative of this general polynomial
	 */
	public abstract GeneralPolynomial antiDeriv();

}
