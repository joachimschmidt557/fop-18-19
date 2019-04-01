package Robots;

import kareltherobot.*;
import Main.MainController;
import Main.RepairInstruction;
import Parts.*;

public class Bot extends Robot implements Directions {
	
	private Part[] parts;
	private boolean waitingForRepair;

	public Bot(int arg0, int arg1, Direction arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
		
		waitingForRepair = false;
		
		parts = new Part[4];
		parts[0] = new Battery(Part.conditionNew, 100);
		parts[1] = new Part("Camera", Part.conditionNew);
		parts[2] = new Part("Legs", Part.conditionNew);
		parts[3] = new Part("Arms", Part.conditionNew);
		
	}
	
	public Part getPart(int index) {
		return parts[index];
	}
	
	public void setPart(int index, Part part) {
		parts[index] = part;
	}
	
	public int getPartIndexByName(String name) {
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].getName().equals(name))
				return i;
		}
		return -1;
	}
	
	public void faceDirection(Direction dir) {
		while (direction() != dir)
			turnLeft();
	}
	
	public void randomMove() {
		// Get a random number
		int randomNumber = MainController.getRandomNumber(0, 3);
		
		// Switch on the random number
		if (randomNumber == 0)
			faceDirection(North);
		else if (randomNumber == 1)
			faceDirection(South);
		else if (randomNumber == 2)
			faceDirection(West);
		else
			faceDirection(East);
		
		// Make sure the front is clear
		while (!frontIsClear())
			turnLeft();
		
		// Move
		move();
	}
	
	public Part checkForDamagedParts() {
		for (Part aPart : parts) {
			if (aPart.getCondition().equals(Part.conditionDamaged))
				return aPart;
		}
		return null;
	}
	
	public void wearOutParts() {
		boolean iJustDamagedTheBattery = false;
		
		// Wear out battery
		Battery myBattery = (Battery) getPart(getPartIndexByName("Battery"));
		if (!myBattery.getCondition().equals(Part.conditionDamaged)) {
			myBattery.setLevel((myBattery.getLevel() - 1));
			//System.out.println(myBattery.getLevel());
			if (myBattery.getCondition().equals(Part.conditionDamaged))
				iJustDamagedTheBattery = true;
		}

		if (!iJustDamagedTheBattery) {
			// Get random number
			//System.out.println("asdf");
			int randNum = MainController.getRandomNumber(1, 200);
			//System.out.println(randNum);
			
			// What part do we want to damage today?
			if (randNum <= 20) {
				getPart(getPartIndexByName("Camera")).setCondition(Part.conditionDamaged);
				getPart(getPartIndexByName("Legs")).setCondition(Part.conditionUsed);
				getPart(getPartIndexByName("Arms")).setCondition(Part.conditionUsed);
			}
			else if (randNum <= 65) {
				getPart(getPartIndexByName("Legs")).setCondition(Part.conditionDamaged);
				getPart(getPartIndexByName("Camera")).setCondition(Part.conditionUsed);
				getPart(getPartIndexByName("Arms")).setCondition(Part.conditionUsed);

			}
			else if (randNum <= 91) {
				getPart(getPartIndexByName("Arms")).setCondition(Part.conditionDamaged);
				getPart(getPartIndexByName("Camera")).setCondition(Part.conditionUsed);
				getPart(getPartIndexByName("Legs")).setCondition(Part.conditionUsed);

			}
			else {
				getPart(getPartIndexByName("Camera")).setCondition(Part.conditionUsed);
				getPart(getPartIndexByName("Arms")).setCondition(Part.conditionUsed);
				getPart(getPartIndexByName("Legs")).setCondition(Part.conditionUsed);
			}
		}
	}
	
	public void doMove() {
		// No part is damaged
		if (checkForDamagedParts() == null) {
			// If we are waiting, set waiting to off
			waitingForRepair = false;
			
			// Move
			randomMove();
			wearOutParts();
		} else {
			// Are we waiting for repair?
			if (!waitingForRepair) {
				waitingForRepair = true;
				MainController.orderRepairInstruction(
						new RepairInstruction(this, 
								checkForDamagedParts().getName())
				);
			}
		}
	}

}
