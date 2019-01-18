import java.util.regex.Pattern;

public class V8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String asdf = new String("asdf");
		String qwer = new String("asdf");
		System.out.println(strEqual(asdf, qwer));
		//System.out.println("asdf".compareTo("asdfasdfasdfasdf4utrhw4uhrt"));
		System.out.println(karelAreYouThere("Karel"));
		System.out.println(karelAreYouThere("K3rel"));
		System.out.println(karelAreYouThere("Ksdfawuerbsdfjrel"));
		System.out.println(karelAreYouThere("asdfKaasdfrelasdf"));

	}
	
	static int strEqual (String a, String b) {
		if (a == b)
			return 2;
			else if (a.equals(b))
				return 1;
				else return 0;
	}
	
	static boolean karelAreYouThere(String input) {
		Pattern p = Pattern.compile(".*K.rel.*");
		return p.matcher(input).matches();
	}

}
