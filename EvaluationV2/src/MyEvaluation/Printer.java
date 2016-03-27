package MyEvaluation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

@SuppressWarnings("serial")
public final class Printer extends ApplicationFrame{

	public XYSeries readSeries(String name) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(name));
		String line;
		XYSeries series = new XYSeries(name);
		while ((line = reader.readLine()) != null){
			
			StringTokenizer string_token = new StringTokenizer(line, ";;,");
			String y_string = string_token.nextToken();
			String x_string = "";
			
			while (string_token.hasMoreTokens()) {
				x_string = string_token.nextToken();
			} 
			float xValue = Float.parseFloat(y_string);
			float yValue = Float.parseFloat(x_string);
			series.add(yValue,xValue);
		}
		return series;
	}


	public Printer(final String title) {

		super(title);
		final XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		try {
			xyseriescollection.addSeries(readSeries("./out/vecteur/score.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final JFreeChart chart = ChartFactory.createXYLineChart("Vecteurs de score","Score","TimeStamp",xyseriescollection,PlotOrientation.VERTICAL,
				true,true,false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

}
