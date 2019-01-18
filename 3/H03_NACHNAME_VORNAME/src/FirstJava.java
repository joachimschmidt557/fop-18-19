import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class FirstJava implements Directions {

	public static void main(String[] args) {
		World.setDelay(5);
		World.setSize(7, 5);
		World.setVisible(true);

		// Create 5 robots
		int numOfRobots = 5;
		Robot [] theRobots = new Robot [numOfRobots];
		for (int i = 1; i <= numOfRobots; i++) {
			theRobots[i-1] = new Robot(1, i, North, i*i+i);
		}
		
		// Control each robot
		for (int i = 1; i <= numOfRobots; i++) {
			// The steps
			for (int j = 0; j <= i; j++) {
				// Robot 1 takes 2 steps
				// Put beepers
				for (int k = 0; k < i; k++) {
					// Put i beepers on each field
					theRobots[i-1].putBeeper();
				}
				// Move
				theRobots[i-1].move();
			}
		}

	}

}
