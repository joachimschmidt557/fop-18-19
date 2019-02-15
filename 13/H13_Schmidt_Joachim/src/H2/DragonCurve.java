package H2;

public class DragonCurve {

	/**
	 * Returns the dragon curve sequence for this n
	 * @param n The number of recursions
	 * @return The sequence
	 */
	public String getSequence(int n) {
		
		if (n == 0)
			return "";
		if (n == 1)
			return "R";
		else
			return getSequence(n-1) + "R" +
				replaceWithL(getSequence(n-1));
		
	}
	
	/**
	 * Replaces the middle character with a L
	 * @param str The string
	 * @return The new string
	 */
	private String replaceWithL(String str) {
		
		char[] explodedStr = str.toCharArray();
		explodedStr[(explodedStr.length-1)/2] = 'L';
		return new String(explodedStr);
		
	}

}
