package gr.di.netmanagement.servlet;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 * Servlet implementation class BarDiagram
 */
@WebServlet("/BarDiagram")
public class BarDiagram extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BarDiagram() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		final double[][] data = new double[][] { { 210, 300, 320, 265, 299 },
				{ 200, 304, 201, 201, 340 } };

		final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				"Team ", "", data);

		JFreeChart chart = null;
		BarRenderer renderer3D = null;
		CategoryPlot plot = null;

		final CategoryAxis3D categoryAxis = new CategoryAxis3D("Match");
		final ValueAxis valueAxis = new NumberAxis3D("Run");
		renderer3D = new BarRenderer3D();

		plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer3D);
		plot.setOrientation(PlotOrientation.VERTICAL);
		chart = new JFreeChart("Srore Bord", JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);

		chart.setBackgroundPaint(new Color(249, 231, 236));
		try {

			final ChartRenderingInfo info = new ChartRenderingInfo(
					new StandardEntityCollection());
			OutputStream outputStream = new FileOutputStream("pic.png");
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(byteArrayOutputStream, chart, 600,
					400, info);
			// byteArrayOutputStream.writeTo(outputStream);
			response.getOutputStream().write(
					byteArrayOutputStream.toByteArray());
			outputStream.write(byteArrayOutputStream.toByteArray());
			outputStream.close();

		} catch (Exception e) {
			System.out.println(e);
		} finally {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		// TODO Auto-generated method stub
	}

}
