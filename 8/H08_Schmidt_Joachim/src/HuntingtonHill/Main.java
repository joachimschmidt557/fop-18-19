package HuntingtonHill;

public class Main {

	public static void main(String[] args) throws MoreStatesThanSeatsException {
		HuntingtonHill hh = new HuntingtonHill("TestFile3.txt", 6);
		hh.distributeSeats();
	}

}
