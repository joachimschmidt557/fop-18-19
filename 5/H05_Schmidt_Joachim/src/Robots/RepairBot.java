package Robots;

import Main.*;
import Parts.*;
import kareltherobot.*;

public class RepairBot extends Bot implements Directions{
	
	private Part[] spareParts;
	private RepairInstruction currentJob;
	
	public RepairBot(int street, int avenue, Direction dir, int numBeepers) {
		super(street, avenue, dir, numBeepers);
		
		spareParts = new Part[20];
		
		// Batteries
		for (int i = 0; i <= 4; i++) {
			spareParts[i] = new Battery(Part.conditionNew, 100);
		}
		
		// Cameras
		for (int i = 5; i <= 9; i++) {
			spareParts[i] = new Part("Camera", Part.conditionNew);
		}
		
		// Legs
		for (int i = 10; i <= 14; i++) {
			spareParts[i] = new Part("Legs", Part.conditionNew);
		}
		
		// Arms
		for (int i = 15; i <= 19; i++) {
			spareParts[i] = new Part("Arms", Part.conditionNew);
		}
	}
	
	public int sparePartAvailable(String partName) {
		for (int i = 0; i < spareParts.length; i++) {
			// Check if the part name matches
			if (spareParts[i].getName().equals(partName))
				// Check if the condition is not damaged
				if ((spareParts[i].getCondition().equals(Part.conditionNew)) || 
					(spareParts[i].getCondition().equals(Part.conditionUsed)))
					return i;
		}
		return -1;
	}
	
	public void replacePart(Bot r, int sparePartIndex) {
		Part newItem = spareParts[sparePartIndex];
		Part oldItemFromBot = r.getPart(r.getPartIndexByName(newItem.getName()));
		
		r.setPart(r.getPartIndexByName(newItem.getName()), newItem);
		spareParts[sparePartIndex] = oldItemFromBot;
	}
	
	public void getCloserToRobot(Robot r) {
		// Get to street of r
		if (street() != r.street()) {
			// Face the right direction
			if (street() < r.street())
				faceDirection(North);
			else
				faceDirection(South);
			
			// Move one step
			move();
		}
		// Get to avenue of r
		else {
			if (avenue() != r.avenue()) {
				// Face the right direction
				if (avenue() < r.avenue())
					faceDirection(East);
				else
					faceDirection(West);
				
				// Move one step
				move();
			}
		}
	}
	
	public void doMove() {
		// Poll the main controller for new jobs
		if (currentJob == null) {
			RepairInstruction nextJob = MainController.getNextRepairInstruction();
			if (nextJob != null)
				currentJob = nextJob;
			else {
				randomMove();
				return;
			}
		}
		
		// Do we have a current job?
		if (currentJob != null) {
			//System.out.println("I have a job!");
			// Execute the current job
			// Check if I have the correct part
			if (sparePartAvailable(currentJob.getPartName()) != -1) {
				getCloserToRobot(currentJob.getRobot());
				
				//TODO
				
				if (areYouHere(currentJob.getRobot().street(),
						currentJob.getRobot().avenue())) {
					replacePart(currentJob.getRobot(),
							sparePartAvailable(currentJob.getPartName()));
					//System.out.println(currentJob.getPartName());
					currentJob = null;
				}
				
			}
			// Part is not available
			else {
				currentJob = null;
				randomMove();
			}
		}
	}
	
}
