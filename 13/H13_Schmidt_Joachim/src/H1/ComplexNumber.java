package H1;

public class ComplexNumber {

	private double imaginary;
	private double real;

	/**
	 * Constructs a new complex number
	 * @param im The imaginary part
	 * @param re The real part
	 */
	public ComplexNumber(double im, double re) {
		this.imaginary = im;
		this.real = re;
	}

	/**
	 * Gets the imaginary part
	 * @return The imaginary part
	 */
	public double getIm() {
		return this.imaginary;
	}

	/**
	 * Modifies the imaginary part
	 * @param im The new imaginary part
	 */
	public void setIm(double im) {
		this.imaginary = im;
	}
	
	/**
	 * Gets the real part
	 * @return The real part
	 */
	public double getRe() {
		return this.real;
	}
	
	/**
	 * Modifies the real part
	 * @param re The new real part
	 */
	public void setRe(double re) {
		this.real = re;
	}


	/**
	 * Adds this complex number to another one, returning the sum
	 * @param cn The other complex number
	 * @return The sum of both complex numbers
	 */
	public ComplexNumber add(ComplexNumber cn) {
	
		return new ComplexNumber(this.getIm() + cn.getIm(),
				this.getRe() + cn.getRe());		
	}

	/**
	 * Multiplies this complex number with another one, returning the product
	 * @param cn The other complex number
	 * @return The product of both complex numbers
	 */
	public ComplexNumber mult(ComplexNumber cn) {
				
		return new ComplexNumber(
				this.real * cn.getIm() + this.imaginary * cn.getRe(),
				this.real * cn.getRe() - this.imaginary * cn.getIm());
		
	}

	/**
	 * The absolute value of this complex number
	 * @return The absolute value
	 */
	public double abs() {
		
		return Math.sqrt(this.imaginary * this.imaginary + this.real * this.real);
		
	}

	/**
	 * Crafts a string representation of this complex number
	 * @return The string representation
	 */
	public String toString() {
		return this.imaginary + "i+" + this.real;
	}

}
