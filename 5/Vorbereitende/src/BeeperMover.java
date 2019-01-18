import kareltherobot.Robot;

public class BeeperMover extends Robot {

	public BeeperMover(int arg0, int arg1, Direction arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	public void beeperMove(int numberOfSteps) {
		if (numberOfSteps > beepers()) turnOff();
		for (int i = 0; i < numberOfSteps; i++) {
			putBeeper(); move();
		}
		while(anyBeepersInBeeperBag()) putBeeper();
	}
	
	/*public void putAllBeepers() {
		while (anyBeepersInBeeperBag()) putBeeper();
	}*/
	
	public void putAllBeepers() {
		while (anyBeepersInBeeperBag()) putBeeper();
	}

}
