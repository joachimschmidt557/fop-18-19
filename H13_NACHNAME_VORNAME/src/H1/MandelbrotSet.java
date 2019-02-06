package H1;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends Set {

	public MandelbrotSet() {
		this.name = "Mandelbrot";
	}

	// TODO H1.2
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

	ComplexNumber getComplexNumberForPixel(double x, double y, double height, double width, double zoomFactor) {

		return new ComplexNumber((y - height / 2) / zoomFactor, (x - width / 2) / zoomFactor);

	}

	Color generatePixel(int x, int y, int maxIter, int rMax, int height, int width, int zoomFactor) {

		ComplexNumber z = new ComplexNumber(0, 0);
		ComplexNumber c = getComplexNumberForPixel(x, y, height, width, zoomFactor);

		for (int i = 0; i < maxIter; i++) {

			if (z.abs() >= rMax)
				return colorMap(i, maxIter);

			z = c.add(z.mult(z));

		}

		return colorMap(maxIter, maxIter);

	}

	Color colorMap(float i, float nMax) {

		float h = i / (float)100;
		float s = (float)1;
		float b = (i < nMax) ? (float)1 : (float)0;
		
		return Color.getHSBColor(h, s, b);

	}

}
