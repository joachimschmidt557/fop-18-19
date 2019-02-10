package H1;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * a class to visualize the created images
 * @author Florian Kadner
 *
 */
@SuppressWarnings("serial")
public class Visualizer extends JFrame {

	private BufferedImage I;

	public Visualizer(Set s, int maxIter, int zoom, int rMax) {

		super(s.name);

		setBounds(100, 100, 800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		I = s.generate(getWidth(), getHeight(), maxIter, zoom, rMax);

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(I, 0, 0, this);
	}

}