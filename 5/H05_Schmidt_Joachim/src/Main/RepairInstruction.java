package Main;

import Robots.Bot;

public class RepairInstruction {

	private Bot robot;
	private String partName;

	public RepairInstruction(Bot robot, String partName) {
		this.robot = robot;
		this.partName = partName;
	}

	public Bot getRobot() {
		return robot;
	}

	public String getPartName() {
		return partName;
	}

}
