package H1;

public class Main {

	public static void main(String[] args) {

		Set mb = new MandelbrotSet();
		new Visualizer(mb, 570, 150, 2 << 4).setVisible(true);

		Set j = new JuliaSet();
		new Visualizer(j, 250, 250, 2).setVisible(true);

	}

}
