package H1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	static double errorPi(double approxPi) {
		return Math.abs(Math.PI - approxPi);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) {
		ApproximatePi aPI = new ApproximatePi();
		
		stage.setTitle("Approximation of Pi");
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Samples");
		yAxis.setLabel("Difference");
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setTitle("Approximation of Pi");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Monte Carlo");

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Integration");

		for (int i = 1; i < 1000; i = i + 100) {
			series1.getData().add(new XYChart.Data(i, errorPi(aPI.monteCarloPi(i))));
			series2.getData().add(new XYChart.Data(i, errorPi(aPI.integrationPi(i))));
		}

		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().addAll(series1, series2);

		stage.setScene(scene);
		stage.show();

	}

}
