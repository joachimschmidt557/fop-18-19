package H3;

import java.awt.*;

import javax.swing.JFrame;

/**
 * a class to draw sierpinski carpets
 * @author Florian Kadner
 *
 */
@SuppressWarnings("serial")
public class SierpinskiCarpet extends JFrame {

	private int len = 729;
	private int n = 2;

	public SierpinskiCarpet() {
		super("Sierpinski Carpet");
		setBounds(100, 100, 100 + len, 100 + len);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// TODO H3
	@Override
	public void paint(Graphics g) {
		
	}

	
	public static void main(String[] args) {
		new SierpinskiCarpet().setVisible(true);
	}
}