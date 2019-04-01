import kareltherobot.Robot;
import kareltherobot.World;

public class IndianaKarel extends Robot {

	public IndianaKarel(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public void turnRight() {
		for (int i = 0; i < 3; i++)
			turnLeft();
	}

	
	// H1
	public static int pyramidBeepers = 0;

	public void buildPyramid() {
		// TODO
		turnOff();
	}

	
	// H2
	public void solveMaze() {
		// TODO
		turnOff();
	}
	

	// H3
	public void collectAll() {
		// TODO
		turnOff();
	}

}
