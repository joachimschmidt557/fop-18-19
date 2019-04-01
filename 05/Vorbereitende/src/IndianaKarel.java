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
	public static int pyramidBeepers = 13*1 + 11*2 + 9*3 + 7*4 + 5*5 + 3*6 + 1*7;

	public void buildPyramid() {

		// Go to start position
		turnLeft(); move(); turnRight();
		move(); move();
		
		// Loop through the rows of the pyramid
		for (int i = 1; i <= 7; i++) {
			// Move through row
			int lengthOfRow = 13 - (2*(i-1));
			for (int k = 0; k < lengthOfRow; k++) {
				// Put beeper(s)
				for (int j = 0; j < i; j++)
					putBeeper();
				move();
			}
			// Go to start of new row
			// Check in which direction we are looking
			if (direction() == East) {
				turnLeft(); move(); turnLeft(); move(); move();
			} else if (direction() == West) {
				turnRight(); move(); turnRight(); move(); move();
			}
			
		}
		
		// Go to end position
		turnLeft(); move(); turnRight();
		
		turnOff();
	}

	
	// H2
	public void solveMaze() {
		// Strategy: always turn right when possible,
		// otherwise move to front, when that isn't possible,
		// go left
		
		while (!nextToABeeper()) {
			// Check if we can go right
			turnRight();
			if (frontIsClear())
				move();
			else {
				// Go back to original direction
				turnLeft();
				// Check if front is clear
				if (frontIsClear())
					move();
				else {
					// Check if left is clear
					turnLeft();
					if (frontIsClear())
						move();
					else {
						// Check if back is clear
						turnLeft();
						if (frontIsClear())
							move();
						else
							System.out.println("It's a trap!");
					}
				}
			}
		}
		if (nextToABeeper())
			pickBeeper();
		
		turnOff();
	}
	

	// H3
	public void collectAll() {
		// Go to (1,1)
		// First look south
		while (direction() != South)
			turnLeft();
		// Go to street 1
		while (frontIsClear())
			move();
		// Then look west
		while (direction() != West)
			turnLeft();
		// Go to avenue 1
		while (frontIsClear())
			move();
		
		// Scan everything
		// If street is odd, move upwards
		// If street is even, move downwards
		boolean finished = false;
		while (!finished) {
			if (avenue() % 2 == 1) {
				while (nextToABeeper())
					pickBeeper();
				while (direction() != North)
					turnLeft();
				if (frontIsClear())
					move();
				else {
					// Turn to next avenue
					turnRight();
					if (frontIsClear()) {
						move(); turnRight();
					}
					else finished = true;
				}
				
			} else {
				while (nextToABeeper())
					pickBeeper();
				while (direction() != South)
					turnLeft();
				if (frontIsClear())
					move();
				else {
					// Turn to next avenue
					turnLeft();
					if (frontIsClear()) {
						move(); turnLeft();
					}
					else finished = true;
				}
			}
		}
		
		/*
		// Get number of streets
		// Look north
		while (direction() != North)
			turnLeft();
		int numberOfStreets = 0;
		while (frontIsClear()) {
			move();
			numberOfStreets++;
		}
		// Get number of avenues
		while (direction() != East)
			turnLeft();
		int numberOfAvenues = 0;
		while (frontIsClear()) {
			move();
			numberOfAvenues++;
		}
		// Now scan everything
		// Go through every street
		for(int i = 0; i < numberOfStreets; i++) {
			// Go through each avenue of each street
			for (int j = 0; j < numberOfAvenues; j++) {
				while (direction() != South)
					turnLeft();
				while(nextToABeeper())
					pickBeeper();
				if (frontIsClear())
					move();
				while(nextToABeeper())
					pickBeeper();
			}
			// Move back to top and turn
			turnRight(); move(); turnRight();
			for(int j = 0; j < numberOfAvenues; j++)
				move();
		}
		// Go through each avenue of the last street
		for (int j = 0; j < numberOfAvenues; j++) {
			while (direction() != South)
				turnLeft();
			while(nextToABeeper())
				pickBeeper();
			if (frontIsClear())
				move();
			while(nextToABeeper())
				pickBeeper();
		}
		// Move back to top and turn
		turnRight(); move(); turnRight();
		for(int j = 0; j < numberOfAvenues; j++)
			move();
		*/
		
		turnOff();
	}

}
