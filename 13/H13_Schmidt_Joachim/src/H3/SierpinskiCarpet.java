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
	private int n = 4;

	public SierpinskiCarpet() {
		super("Sierpinski Carpet");
		setBounds(100, 100, 100 + len, 100 + len);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// TODO H3
	@Override
	public void paint(Graphics g) {
		
		recursivePaint(g, n, 50, len+50, 50, len+50);
		
	}
	
	private void recursivePaint(Graphics g, int n, int minX, int maxX, int minY, int maxY) {
		
		int width = maxX - minX; int height = maxY - minY;
		int thirdWidth = width / 3; int thirdHeight = height / 3;
		g.fillRect(minX + thirdWidth, minY + thirdHeight, thirdWidth, thirdHeight);
		if (n > 1) {
			
			recursivePaint(g, n-1, minX, minX + thirdWidth, minY, minY + thirdHeight);
			recursivePaint(g, n-1, minX + thirdWidth, minX + 2*thirdWidth, minY, minY + thirdHeight);
			recursivePaint(g, n-1, minX + 2*thirdWidth, maxX, minY, minY + thirdHeight);
			
			recursivePaint(g, n-1, minX, minX + thirdWidth, minY + thirdHeight, minY + 2*thirdHeight);
			recursivePaint(g, n-1, minX + 2*thirdWidth, maxX, minY + thirdHeight, minY + 2*thirdHeight);
			
			recursivePaint(g, n-1, minX, minX + thirdWidth, minY+2*thirdHeight, maxY);
			recursivePaint(g, n-1, minX + thirdWidth, minX + 2*thirdWidth, minY + 2*thirdHeight, maxY);
			recursivePaint(g, n-1, minX + 2*thirdWidth, maxX, minY + 2*thirdHeight, maxY);
			
		}
		
	}

	/*
	private double sierpinskiArea(int n) {
		
		if (n == 1)
			return 1;
		else
			return sierpinskiArea(n-1) - (Math.pow(8, n-1) / Math.pow(9, n));
		
	}
	*/
	
	public static void main(String[] args) {
		new SierpinskiCarpet().setVisible(true);
	}
}