package Main;

import java.util.Random;

import Robots.Bot;
import Robots.RepairBot;
import kareltherobot.Directions;
import kareltherobot.World;

public class MainController implements Directions {

	private static RepairInstruction[] repairInstructions = new RepairInstruction[5];

	private static int amountOfRobots = 1;
	private static Bot robotsInWorld[];

	public static void main(String[] args) {
		World.setVisible(true); // Karel J Fenster anzeigen
		World.setDelay(10); // 10ms Pause nach jedem Schritt des Roboters
		World.setTrace(false); // Gibt den Status des Roboters nach jedem Schritt auf der Konsole aus
		World.setSize(18, 18);
		setFullBorder(); // Wände werden oben und rechts am Rand platziert, sodass der Roboter nicht aus
							// der Welt laufen kann
		robotsInWorld = new Bot[amountOfRobots + 1];

		for (int i = 0; i < amountOfRobots; i++) {
			robotsInWorld[i] = new Bot(9, 9, North, 0);
		}
		robotsInWorld[amountOfRobots] = new RepairBot(9, 9, North, 0);

		while (true) {
			for (Bot r : robotsInWorld) {
				r.doMove();
			}
		}

	}

	public static RepairInstruction getNextRepairInstruction() {
		RepairInstruction next = repairInstructions[0];
		if (next == null) {
			return null;
		} else {
			for (int i = 0; i < repairInstructions.length - 1; i++) {
				repairInstructions[i] = repairInstructions[i + 1];
			}
			repairInstructions[repairInstructions.length - 1] = null;

			return next;

		}
	}

	public static void orderRepairInstruction(RepairInstruction rep) {
		boolean inserted = false;
		for (int i = 0; i < repairInstructions.length; i++) {
			RepairInstruction current = repairInstructions[i];
			if (current == null) {
				repairInstructions[i] = rep;
				inserted = true;
				return;
			}
		}

		if (inserted == false) {
			// extend array
			RepairInstruction extendedInstructions[] = new RepairInstruction[repairInstructions.length + 5];
			for (int i = 0; i < repairInstructions.length; i++) {
				extendedInstructions[i] = repairInstructions[i];
			}
			extendedInstructions[repairInstructions.length] = rep;
			repairInstructions = extendedInstructions;

		}
	}

	public static void printRepairInstructions() {
		System.out.println("Size: " + repairInstructions.length);
		for (int i = 0; i < repairInstructions.length; i++) {
			RepairInstruction current = repairInstructions[i];
			if (current == null) {
				System.out.print("null" + " ; ");
			} else {
				System.out.print(current.getPartName() + " ; ");
			}

		}
		System.out.println();
	}

	public static RepairInstruction[] repairInstructionArray() {
		return repairInstructions;
	}

	public static void setFullBorder() {
		World.placeEWWall(World.numberOfStreets(), 1, World.numberOfAvenues());
		World.placeEWWall(0, 1, World.numberOfAvenues());
		World.placeNSWall(1, World.numberOfAvenues(), World.numberOfStreets());
		World.placeNSWall(1, 0, World.numberOfStreets());
	}

	private static Random rndm = new Random();

	public static int getRandomNumber(int min, int max) {
		return min + rndm.nextInt(max - min + 1);
	}

}
