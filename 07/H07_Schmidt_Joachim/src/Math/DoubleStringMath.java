package Math;

import Main.Utils;
import Main.Utils.*;

public class DoubleStringMath implements StringMath {

	/**
	 * Adds the numbers
	 * @param x A number
	 * @param y Another number
	 * @return The sum of x and y
	 */
	@Override
	public String add(String x, String y) {
		
		return Utils.doubleToString(Double.parseDouble(x) + Double.parseDouble(y));
		
	}

	/**
	 * Subtracts the numbers
	 * @param x A number
	 * @param y Another number
	 * @return The difference of x and y
	 */
	@Override
	public String sub(String x, String y) {
		
		return Utils.doubleToString(Double.parseDouble(x) - Double.parseDouble(y));
		
	}

	/**
	 * Multiplies the numbers
	 * @param x A number
	 * @param y Another number
	 * @return The result
	 */
	@Override
	public String mul(String x, String y) {
		
		return Utils.doubleToString(Double.parseDouble(x) * Double.parseDouble(y));
		
	}

	/**
	 * Divides the numbers
	 * Attention: Division by zero is handled without error!
	 * @param x A number
	 * @param y Another number
	 * @return The result
	 */
	@Override
	public String div(String x, String y) {
		
		return Utils.doubleToString(Double.parseDouble(x) / Double.parseDouble(y));

	}

}
