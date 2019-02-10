package H2;

public class DragonCurve {

	// TODO H2.1
	public String getSequence(int n) {
		
		if (n == 0)
			return "";
		if (n == 1)
			return "R";
		else
			return getSequence(n-1) + "R" +
				replaceWithL(getSequence(n-1));
		
	}
	
	private String replaceWithL(String str) {
		
		char[] explodedStr = str.toCharArray();
		explodedStr[(explodedStr.length-1)/2] = 'L';
		return new String(explodedStr);
		
	}

}
