import kareltherobot.Robot;

public class DirectionTurner extends Robot {

	public DirectionTurner(int arg0, int arg1, Direction arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public void turnNorth() {
		while (!facingNorth()) turnLeft();
	}
	
	public void turnSouth() {
		while (!facingSouth()) turnLeft();
	}
	
	public void turnWest() {
		while (!facingWest()) turnLeft();
	}
	
	public void turnEast() {
		while (!facingEast()) turnLeft();
	}
}
