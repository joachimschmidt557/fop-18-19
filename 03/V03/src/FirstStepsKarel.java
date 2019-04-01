import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class FirstStepsKarel implements Directions {

	public static void main(String[] args) {
		World.setDelay(77);
		World.setSize(10,10);
		World.setVisible(true);
		World.placeBeepers(2, 5, 1);
		
		// TODO

	}

}
