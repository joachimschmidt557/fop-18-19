package H2;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * a class to visualize the dragon curve
 * 
 * @author Florian Kadner
 *
 */
@SuppressWarnings("serial")
public class Visualizer extends JFrame {

	private String turns;
	private double startingAngle, len;

	public Visualizer(int n) {
		super("Dragon Curve");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		DragonCurve dc = new DragonCurve();
		turns = dc.getSequence(n);
		startingAngle = -n * (Math.PI / 4);
		len = 400 / Math.pow(2, n / 2.);
	}

	/**
	 * Paints the dragon curve
	 * @param The Graphics onto which the dragon curve will be drawn
	 */
	@Override
	public void paint(Graphics g) {

		final int startX = 250;
		final int startY = 375;
		double currentX = startX;
		double currentY = startY;
		double currentAngle = startingAngle;

		g.drawLine((int)Math.round(currentX),
				(int)Math.round(currentY),
				(int)Math.round(calculateNewX(currentX, currentAngle, len)),
				(int)Math.round(calculateNewY(currentY, currentAngle, len)));
		
		currentX = Math.round(calculateNewX(currentX, currentAngle, len));
		currentY = Math.round(calculateNewY(currentY, currentAngle, len));
		
		for (char instruction : turns.toCharArray()) {

			currentAngle = rotateAngle(currentAngle, instruction);
			
			g.drawLine((int)Math.round(currentX),
					(int)Math.round(currentY),
					(int)Math.round(calculateNewX(currentX, currentAngle, len)),
					(int)Math.round(calculateNewY(currentY, currentAngle, len)));

			currentX = Math.round(calculateNewX(currentX, currentAngle, len));
			currentY = Math.round(calculateNewY(currentY, currentAngle, len));
			
		}

	}

	/**
	 * Calculates the new x point
	 * @param x The current x point
	 * @param angle The direction we need to go
	 * @param len How far we are going
	 * @return The new x point
	 */
	private double calculateNewX(double x, double angle, double len) {

		return x + (len * Math.cos(angle));
		
	}

	/**
	 * Calculates the new Y point
	 * @param y The current Y point
	 * @param angle The direction we need to go
	 * @param len How far we are going
	 * @return The new y point
	 */
	private double calculateNewY(double y, double angle, double len) {

		return y + (len * Math.sin(angle));
		
	}

	/**
	 * Rotates this angle
	 * @param angle The current angle
	 * @param instruction Rotate R(ight) or L(eft)?
	 * @return The new angle
	 */
	private double rotateAngle(double angle, char instruction) {

		if (instruction == 'L')
			return angle - (Math.PI / 2);
		else if (instruction == 'R')
			return angle + (Math.PI / 2);
		else
			return angle;

	}

}
