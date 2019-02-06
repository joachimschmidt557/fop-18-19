package H1;
import java.awt.image.BufferedImage;

/**
 * a class to represent different fractal sets
 * @author Florian Kadner
 *
 */
public abstract class Set {
	
	BufferedImage I;
	ComplexNumber z, c;
	public String name;
	
	/**
	 * generate an image of the fractal set
	 * @param width the width of the image
	 * @param height the height of the image
	 * @param maxIter the number of maximal iterations
	 * @param zoom the zoom factor
	 * @param rMax the maximum radius
	 * @return the generated image
	 */
	abstract BufferedImage generate(int width, int height, int maxIter, int zoom, int rMax);

}
