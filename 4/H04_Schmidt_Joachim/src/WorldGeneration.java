
import java.awt.Point;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import kareltherobot.Directions.Direction;
import kareltherobot.Directions;
import kareltherobot.World;

public class WorldGeneration {

	/**
	 * Generator configuration
	 */

//	private static int minNumberOfStreets = 10;
	private static int maxNumberOfStreets = 20;

	private static int minNumberOfAvenues = 10;
	private static int maxNumberOfAvenues = 20;

	private static int minNumberOfBeeperSpots = 30;
	private static int maxNumberOfBeeperSpots = 50;

	private static int minNumberOfBeepersStacked = 1;
	private static int maxNumberOfBeepersStacked = 3;

	/**
	 * Generator configuration END
	 */

	private static Random rndm = new Random();

	private static int totalBeepersPlaced;

	public static int getRandomNumber(int min, int max) {
		return min + rndm.nextInt(max - min + 1);
	}

	public static void setRandomSize() {
		int avenues = getRandomNumber(minNumberOfAvenues, maxNumberOfAvenues);
		int streets = getRandomNumber(avenues, maxNumberOfStreets);
		World.setSize(streets, avenues);
	}

	public static void placeRandomBeepers() {
		totalBeepersPlaced = 0;
		int numberOfBeeperSpots = getRandomNumber(minNumberOfBeeperSpots, maxNumberOfBeeperSpots);
		for (int i = 0; i < numberOfBeeperSpots; i++) {
			int streetToBePlaced = getRandomNumber(1, World.numberOfStreets());
			int avenueToBePlaced = getRandomNumber(1, World.numberOfAvenues());

			int amountOfBeepers = getRandomNumber(minNumberOfBeepersStacked, maxNumberOfBeepersStacked);
			totalBeepersPlaced += amountOfBeepers;

			World.placeBeepers(streetToBePlaced, avenueToBePlaced, amountOfBeepers);
		}
	}

	public static void generateRandomMaze() {
		// street == height
		// avenue == width
		for (int i = 1; i < World.numberOfStreets(); i++) {
			World.placeEWWall(i, 1, World.numberOfAvenues());
		}

		for (int i = 1; i < World.numberOfAvenues(); i++) {
			World.placeNSWall(1, i, World.numberOfStreets());
		}

		visited = new boolean[World.numberOfStreets() + 1][World.numberOfAvenues() + 1];

		maze(getRandomNumber(1, World.numberOfStreets()), getRandomNumber(1, World.numberOfAvenues()));
	}

	public static int[] getRandomCorners() {
		int[] corners = new int[4];
		int startCorner = -1;
		for (int i = 0; i < 2; i++) {
			int corner = getRandomNumber(0, 3);
			int cornerStreet = 1;
			int cornerAvenue = 1;

			if (startCorner == corner) {
				if (corner == 3) {
					corner = 0;
				} else {
					corner++;
				}
			}

			if (corner == 1) {
				cornerStreet = World.numberOfStreets();
			}

			if (corner == 2) {
				cornerAvenue = World.numberOfAvenues();
			}

			if (corner == 3) {
				cornerStreet = World.numberOfStreets();
				cornerAvenue = World.numberOfAvenues();
			}

			if (i == 0) {
				corners[0] = cornerStreet;
				corners[1] = cornerAvenue;
				startCorner = corner;
			} else {
				corners[2] = cornerStreet;
				corners[3] = cornerAvenue;
			}

		}

		return corners;
	}

	public static Direction getRandomDirection() {
		Direction dir = Directions.North;
		int randomDir = getRandomNumber(0, 3);

		if (randomDir == 1) {
			dir = Directions.East;
		}

		if (randomDir == 2) {
			dir = Directions.South;
		}

		if (randomDir == 3) {
			dir = Directions.West;
		}

		return dir;
	}

	private static boolean visited[][];

	public static void maze(int street, int avenue) {
		visited[street][avenue] = true;
		LinkedList<Point> neighbours = getNeighbours(street, avenue);
		Collections.shuffle(neighbours);
		for (Point p : neighbours) {
			int nStreet = p.x;
			int nAvenue = p.y;

			if (visited[nStreet][nAvenue] == false) {
				// remove wall between this cell and neighbour
				removeWallBetween(street, avenue, nStreet, nAvenue);
				maze(nStreet, nAvenue);
			}

		}

	}

	public static void removeWallBetween(int streetA, int avenueA, int streetB, int avenueB) {
		if (streetA == streetB) {
			// east west neighbours

			if (avenueA - avenueB == -1) {
				// west nb
				World.removeNSWall(streetA, avenueA);
			} else {
				// east nb
				World.removeNSWall(streetA, avenueB);
			}
		} else {
			// north south neighbours
			if (streetA - streetB == -1) {
				// south nb
				World.removeEWWall(streetA, avenueA);
			} else {
				// north nb
				World.removeEWWall(streetB, avenueA);
			}

		}
	}

	public static LinkedList<Point> getNeighbours(int street, int avenue) {
		LinkedList<Point> l = new LinkedList<Point>();

		if (street < World.numberOfStreets()) {
			Point north = new Point(street + 1, avenue);
			l.add(north);
		}

		if (street > 1) {
			Point south = new Point(street - 1, avenue);
			l.add(south);
		}

		if (avenue < World.numberOfAvenues()) {
			Point east = new Point(street, avenue + 1);
			l.add(east);
		}

		if (avenue > 1) {
			Point west = new Point(street, avenue - 1);
			l.add(west);
		}

		return l;
	}

	public static void setFullBorder() {
		World.placeEWWall(World.numberOfStreets(), 1, World.numberOfAvenues());
		World.placeEWWall(0, 1, World.numberOfAvenues());
		World.placeNSWall(1, World.numberOfAvenues(), World.numberOfStreets());
		World.placeNSWall(1, 0, World.numberOfStreets());
	}

	public static int getTotalBeepersPlaced() {
		return totalBeepersPlaced;
	}

}
