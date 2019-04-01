package H2;

public class Main {
	
	public static void main(String[] args) {
		

		BayerPattern bayerData = new BayerPattern("bayerData1.dat");
		int[][][] split = bayerData.splitColorChannels();
		int[][][] debayer =  BayerPattern.interpolateMissingValues(split);
	
		// Your result will be saved to debayer.png
		Image debayerImage = new Image(debayer);
		debayerImage.saveAsPNG("debayer.png");
	}

}
