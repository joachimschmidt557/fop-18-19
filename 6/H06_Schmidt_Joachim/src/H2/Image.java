package H2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	/**
	 * 3d rgb array containing the data of the image
	 */
	private int[][][] data;

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
	 * @return 3d rgb array
	 */
	public int[][][] getData() {
		return data;
	}

	/**
	 * Constructs a new Image object by using an already existing 3d rgb array
	 * @param data
	 */
	public Image(int[][][] data) {
		this.data = data;
	}
	
	/**
	 * Constructs a new Image object by reading in an image file
	 * @param filePath
	 */
	public Image(String filePath) {
		BufferedImage bi = null;
		try {
			bi = javax.imageio.ImageIO.read(new File(filePath));
		} catch (IOException e) {
			System.err.println("Error loading image | " + filePath + " | " + e.toString() + " | " + e.getMessage());
		}
		if (bi == null) {
			System.err.println("Error loading image | " + filePath + " | BufferedImage == null");
			return;
		}
		data = new int[bi.getHeight()][bi.getWidth()][3];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				Color c = new Color(bi.getRGB(j, i));
				data[i][j][0] = c.getRed();
				data[i][j][1] = c.getGreen();
				data[i][j][2] = c.getBlue();
			}
		}
	}
	
	/**
	 * Saves the 3d rgb array as a PNG file
	 * @param filePath
	 */
	public void saveAsPNG(String filePath) {
		BufferedImage bi = new BufferedImage(data[0].length, data.length, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				Color c = new Color(data[i][j][0], data[i][j][1], data[i][j][2]);
				bi.setRGB(j, i, c.getRGB());
			}
		}

		try {
			File oi = new File(filePath);
			ImageIO.write(bi, "png", oi);
		} catch (IOException e) {
			System.err.println("Error saving image | " + filePath + " | " + e.toString() + " | " + e.getMessage());
		}
	}

}
