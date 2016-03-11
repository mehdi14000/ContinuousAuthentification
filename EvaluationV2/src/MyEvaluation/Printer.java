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
	    BufferedReader br = new BufferedReader(new FileReader(name));
	    String line;
	    XYSeries series = new XYSeries(name);
	    while ((line = br.readLine()) != null) {
	        StringTokenizer st = new StringTokenizer(line, ";;");
	        // The first token is the x value.
	        String yValue = st.nextToken();
	        // The last token is the y value.
	        String xValue = "";
	        while (st.hasMoreTokens()) {
	            xValue = st.nextToken();
	        }
	        System.out.println(xValue);
	        float x = Float.parseFloat(yValue);
	        float y = Float.parseFloat(xValue);
	        series.add(y, x);
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
