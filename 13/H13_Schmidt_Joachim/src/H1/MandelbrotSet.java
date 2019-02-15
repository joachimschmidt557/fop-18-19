package H1;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends Set {

	public MandelbrotSet() {
		this.name = "Mandelbrot";
	}

	@Override
	BufferedImage generate(int width, int height, int maxIter, int zoom, int rMax) {

		I = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				I.setRGB(x, y, generatePixel(x, y, maxIter, rMax, height, width, zoom).getRGB());
			}
		}

		return I;

	}

	/**
	 * [Shared between Mandelbrot and Julia]
	 * 
	 * Turns a pixel into a complex number
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param height The height of the image
	 * @param width The width of the image
	 * @param zoomFactor The zoom factor
	 * @return A complex number
	 */
	public static ComplexNumber getComplexNumberForPixel(int x, int y, int height, int width, int zoomFactor) {

		return new ComplexNumber((y - height / 2.0) / zoomFactor, (x - width / 2.0) / zoomFactor);

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
	Color generatePixel(int x, int y, float maxIter, int rMax, int height, int width, int zoomFactor) {

		ComplexNumber z = new ComplexNumber(0, 0);
		ComplexNumber c = getComplexNumberForPixel(x, y, height, width, zoomFactor);

		for (int i = 0; i < maxIter; i++) {

			if (z.abs() >= rMax)
				return colorMap(i, maxIter);

			z = c.add(z.mult(z));

		}

		return colorMap(maxIter, maxIter);

	}

	/**
	 * Maps i and nMax to one color value
	 * @param i The number of iterations
	 * @param nMax The maximum number of iterations
	 * @return The color
	 */
	public Color colorMap(float i, float nMax) {

		//float h = ((float)(i / 100.0)) > 1.0 ? (float)1 : ((float)(i / 100.0));
		float h = ((float)(i / 100.0));
		float s = (float)1;
		float b = (i < nMax) ? (float)1 : (float)0;
		
		return Color.getHSBColor(h, s, b);

	}

}
