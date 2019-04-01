package H2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BayerPattern {
	
	/** 
	 * Array containing the bayer data
	 * first index is the height
	 * second index is the width
	 */
	private int[][] data;

	/**
	 * @return the height of the image
	 */
	public int getHeight() {
		return data.length;
	}

	/**
	 * @return the width of the image
	 */
	public int getWidth() {
		return data[0].length;
	}

	/**
	 * @return the bayer data array
	 */
	public int[][] getData() {
		return data;
	}

	/**
	 * Constructs a new BayerPattern object by rading in a file in bayer data format
	 * @param filePath
	 */
	public BayerPattern(String filePath) {
		DataInputStream is;
		try {
			is = new DataInputStream(new FileInputStream(filePath));
			int height = is.readInt();
			int width = is.readInt();
			data = new int[height][width];
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					data[i][j] = (int) is.readShort();
				}
			}
			is.close();
		} catch (IOException e) {
			System.err
					.println("Error loading bayer data | " + filePath + " | " + e.toString() + " | " + e.getMessage());
		}
	}

	/**
	 * Splits the color channels according to the bayer pattern.
	 * @return A three-dimensional integer array depicting the pixels after extraction
	 */
	public int[][][] splitColorChannels() {

		//int width = data[0].length;
		//int height = data.length;
		int numColors = 3;
		
		int[][][] splittedColorChannels = new int[getHeight()][getWidth()][numColors];
		
		for (int i = 0; i < getHeight(); i++) {
			
			for (int j = 0; j < getWidth(); j++) {
				
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						// Color green
						splittedColorChannels[i][j][0] = -1;
						splittedColorChannels[i][j][1] = data[i][j];
						splittedColorChannels[i][j][2] = -1;
					}
					else {
						// Color red
						splittedColorChannels[i][j][0] = data[i][j];
						splittedColorChannels[i][j][1] = -1;
						splittedColorChannels[i][j][2] = -1;
					}
				}
				else {
					if (j % 2 == 0) {
						// Color blue
						splittedColorChannels[i][j][0] = -1;
						splittedColorChannels[i][j][1] = -1;
						splittedColorChannels[i][j][2] = data[i][j];
					}
					else {
						// Color green
						splittedColorChannels[i][j][0] = -1;
						splittedColorChannels[i][j][1] = data[i][j];
						splittedColorChannels[i][j][2] = -1;
					}
				}
				
			}
			
		}

		return splittedColorChannels;

	}

	
	/**
	 * Interpolates missing R,G,B values from the Bayer pattern
	 * @param splittedColorChannels A three-dimensional integer array without interpolation
	 * @return A three-dimensional integer array with interpolation
	 */
	public static int[][][] interpolateMissingValues(int[][][] splittedColorChannels) {
		
		int height = splittedColorChannels.length;
		int width = splittedColorChannels[0].length;
		int numOfColors = 3;
		
		int positionRed = 0;
		int positionGreen = 1;
		int positionBlue = 2;
		
		int[][][] interpolated = new int[height][width][numOfColors];
		
		// Iterate over rows
		for (int i = 0; i < height; i++) {
			// Iterate over columns
			for (int j = 0; j < width; j++) {
				
				for (int currentColor = positionRed; currentColor <= positionBlue; currentColor++) {
					
					int currentColorValue = splittedColorChannels[i][j][currentColor];
					
					if (currentColorValue == -1) {
						
						ArrayList<Integer> neigboringPixels = new ArrayList<Integer>();
						
						// First row
						if (i == 0) {
							neigboringPixels.add(splittedColorChannels[i+1][j][currentColor]);
							// Top left corner
							if (j == 0) {
								neigboringPixels.add(splittedColorChannels[i][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i+1][j+1][currentColor]);
							}
							// Top right corner
							else if (j == width - 1) {
								neigboringPixels.add(splittedColorChannels[i][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i+1][j-1][currentColor]);
							}
							// Somewhere else on the top row
							else {
								neigboringPixels.add(splittedColorChannels[i][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i+1][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i+1][j-1][currentColor]);
							}
						}
						// Last row
						else if (i == height-1) {
							neigboringPixels.add(splittedColorChannels[i-1][j][currentColor]);
							// Bottom left corner
							if (j == 0) {
								neigboringPixels.add(splittedColorChannels[i][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j+1][currentColor]);
							}
							// Bottom right corner
							else if (j == width - 1) {
								neigboringPixels.add(splittedColorChannels[i][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j-1][currentColor]);
							}
							// Somewhere else on the bottom row
							else {
								neigboringPixels.add(splittedColorChannels[i][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j-1][currentColor]);
							}
						}
						// Some middle row
						else {
							neigboringPixels.add(splittedColorChannels[i+1][j][currentColor]);
							neigboringPixels.add(splittedColorChannels[i-1][j][currentColor]);
							// On the left column
							if (j == 0) {
								neigboringPixels.add(splittedColorChannels[i+1][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i][j+1][currentColor]);
							}
							// On the right column
							else if (j == width - 1) {
								neigboringPixels.add(splittedColorChannels[i+1][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i][j-1][currentColor]);
							}
							// Somewhere in the middle
							else {
								neigboringPixels.add(splittedColorChannels[i+1][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i][j+1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i+1][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i-1][j-1][currentColor]);
								neigboringPixels.add(splittedColorChannels[i][j-1][currentColor]);
							}
						}
						
						neigboringPixels.removeIf(x -> x == -1);
						
						int sum = 0;
						for (int pixel : neigboringPixels) sum += pixel;
						
						currentColorValue = sum / neigboringPixels.size();
					}
					interpolated[i][j][currentColor] = currentColorValue;
				}
			}
		}


		return interpolated;
	}

}
