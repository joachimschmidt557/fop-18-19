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

	// TODO H2.2
	@Override
	public void paint(Graphics g) {

		final int startX = 250;
		final int startY = 375;
		double currentX = startX;
		double currentY = startY;
		double currentAngle = startingAngle;

		for (char instruction : turns.toCharArray()) {

			currentAngle = rotateAngle(currentAngle, instruction);
			
			g.drawLine((int)currentX, (int)currentY, (int)calculateNewX(currentX, currentAngle, len), (int)calculateNewY(currentY, currentAngle, len));

			currentX = calculateNewX(currentX, currentAngle, len);
			currentY = calculateNewY(currentY, currentAngle, len);
			
		}

	}

	private double calculateNewX(double x, double angle, double len) {

		return x + (len * Math.cos(angle));
		
	}

	private double calculateNewY(double x, double angle, double len) {

		return x + (len * Math.sin(angle));
		
	}

	private double rotateAngle(double angle, char instruction) {

		if (instruction == 'L')
			return angle - (Math.PI / 2);
		else if (instruction == 'R')
			return angle + (Math.PI / 2);
		else
			return angle;

	}

}
