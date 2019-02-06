package H2;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * a class to visualize the dragon curve
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
		
		
	}
	
	
}
