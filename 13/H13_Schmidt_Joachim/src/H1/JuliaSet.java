package H1;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class JuliaSet extends Set {

	public JuliaSet() {
		this.name = "Julia";
	}

	@Override
	public BufferedImage generate(int width, int height, int maxIter, int zoom, int rMax) {

		I = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				I.setRGB(x, y, generatePixel(x, y, maxIter, rMax, height, width, zoom).getRGB());
			}
		}

		return I;
	}
	
	/**
	 * Generates the color for one pixel
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param maxIter The maximum number of iterations
	 * @param rMax The maximum radius
	 * @param height The height of the image
	 * @param width The width of the image
	 * @param zoomFactor The zoom factor
	 * @return The color for this pixel
	 */
	Color generatePixel(int x, int y, int maxIter, int rMax, int height, int width, int zoomFactor) {
		
		ComplexNumber z = chooseStartValue();
		ComplexNumber c = MandelbrotSet.getComplexNumberForPixel(x, y, height, width, zoomFactor);
		
		for (int i = 0; i < maxIter; i++) {
			
			if (c.abs() >= rMax)
				return colorMap(i, maxIter);
			
			double newReal = c.getRe() * c.getRe() - c.getIm() * c.getIm();
			c.setIm(2 * c.getRe() * c.getIm());
			c.setRe(newReal);
			
			c = c.add(z);
			
		}
		
		return colorMap(maxIter, maxIter);
		
	}
	
	/**
	 * Selects a start value
	 * @return In this case: -0.8+0.156i
	 */
	ComplexNumber chooseStartValue() {
		
		return new ComplexNumber(0.156, -0.8);
		
	}
	
	/**
	 * Maps i and nMax to one color value
	 * @param i The number of iterations
	 * @param nMax The maximum number of iterations
	 * @return The color
	 */
	public Color colorMap(int i, int nMax) {

		float h = ((float)(i % 256/ 255.0)) > 1.0 ? (float)1 : ((float)(i % 256/ 255.0));
		float s = (float)0.6;
		float b = (i < nMax) ? (float)1 : (float)0;
		
		return Color.getHSBColor(h, s, b);

	}

}
