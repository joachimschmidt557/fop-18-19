import kareltherobot.Directions;
import kareltherobot.World;

public class IndianaWorld implements Directions {

	private static int exerciseNumber = 1;

	public static void main(String[] args) {
		World.setVisible(true); // Karel J Fenster anzeigen
		World.setDelay(10); // 10ms Pause nach jedem Schritt des Roboters
		World.setTrace(true); // Gibt den Status des Roboters nach jedem Schritt auf der Konsole aus

		if (exerciseNumber == 1) {
			// H1 - Pyramide bauen
			runPyramid();
		}

		if (exerciseNumber == 2) {
			// H2 - Das Labyrinth
			runMaze();
		}

		if (exerciseNumber == 3) {
			// H3 - Alles einsammeln
			runCollectAll();
		}

	}

	// H1
	private static void runPyramid() {
		World.setSize(18, 18);
		WorldGeneration.setFullBorder(); // Wände werden oben und rechts am Rand platziert, sodass der Roboter nicht aus
											// der Welt laufen kann

		IndianaKarel robot = new IndianaKarel(3, 1, East, IndianaKarel.pyramidBeepers);
		robot.buildPyramid();
	}

	// H2
	private static void runMaze() {
		WorldGeneration.setRandomSize(); // Mit diesem Aufruf wird die Welt auf eine zufällige Größe gesetzt
		WorldGeneration.setFullBorder(); // Wände werden oben und rechts am Rand platziert, sodass der Roboter nicht aus
											// der Welt laufen kann
		WorldGeneration.generateRandomMaze(); // Erstellt ein zufälliges Labyrinth

		int randomCorners[] = WorldGeneration.getRandomCorners();

		World.placeBeepers(randomCorners[0], randomCorners[1], 1); // Platziert einen Beeper am Ausgang des Labyrinths

		IndianaKarel robot = new IndianaKarel(randomCorners[2], randomCorners[3], North, 0);
		robot.solveMaze();
	}

	// H3
	private static void runCollectAll() {
		WorldGeneration.setRandomSize(); // Mit diesem Aufruf wird die Welt auf eine zufällige Größe gesetzt
		WorldGeneration.setFullBorder(); // Wände werden oben und rechts am Rand platziert, sodass der Roboter nicht aus
											// der Welt laufen kann
		WorldGeneration.placeRandomBeepers(); // Mit diesem Aufruf werden eine zufällige Anzahl an Beepern in der Welt
												// verteilt

		int robotStartStreet = WorldGeneration.getRandomNumber(1, World.numberOfStreets()); // Zufallszahl zwischen 1
																							// und numberOfStreets
																							// (beide inklusive)
		int robotStartAvenue = WorldGeneration.getRandomNumber(1, World.numberOfAvenues());

		// Roboter wird auf eine zufällig generierte Startposition gesetzt
		IndianaKarel robot = new IndianaKarel(robotStartStreet, robotStartAvenue, WorldGeneration.getRandomDirection(),
				0);
		robot.collectAll();
	}
}
