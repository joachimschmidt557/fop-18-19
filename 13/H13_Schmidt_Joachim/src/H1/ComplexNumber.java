package H1;

public class ComplexNumber {

	private double imaginary;
	private double real;

	public ComplexNumber(double im, double re) {
		this.imaginary = im;
		this.real = re;
	}


	public double getIm() {
		return this.imaginary;
	}

	public void setIm(double im) {
		this.imaginary = im;
	}
	
	public double getRe() {
		return this.real;
	}
	
	public void setRe(double re) {
		this.real = re;
	}


	/**
	 * 
	 * @param cn
	 * @return
	 */
	public ComplexNumber add(ComplexNumber cn) {
	
		return new ComplexNumber(this.getIm() + cn.getIm(),
				this.getRe() + cn.getRe());		
	}

	/**
	 * 
	 * @param cn
	 * @return
	 */
	public ComplexNumber mult(ComplexNumber cn) {
		
		return new ComplexNumber(
				this.real * cn.getIm() + this.imaginary * cn.getRe(),
				this.real * cn.getRe() - this.imaginary * cn.getIm());
		
	}

	/**
	 * 
	 * @return
	 */
	public double abs() {
		
		return Math.sqrt(this.imaginary * this.imaginary + this.real * this.real);
		
	}

	/**
	 * 
	 */
	public String toString() {
		return this.imaginary + "i+" + this.real;
	}

}
