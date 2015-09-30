package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.ClusteredPointOfInterest;
import gr.di.netmanagement.processdata.DataAnalysis;
import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

/**
 * Servlet implementation class DbScan.
 */
@WebServlet("/DbScan")
public class DbScan extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new db scan.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public DbScan() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		final HttpSession session = request.getSession();
		session.setAttribute("page", "DbScan");
		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date dateFrom = null, dateTo = null;
		final float tMin = Float.valueOf((String) session.getAttribute("tMin"));
		final float dMax = Float.valueOf((String) session.getAttribute("dMax"));
		final int tMax = Integer.valueOf((String) session.getAttribute("tMax"));
		final int dMeasure = Integer.valueOf((String) session
				.getAttribute("dMeasure"));
		final int minPoints = Integer.valueOf((String) session
				.getAttribute("minPoints"));

		try {
			dateFrom = sf.parse((String) session.getAttribute("dateFrom"));
			dateTo = sf.parse((String) session.getAttribute("dateTo"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<ClusteredPointOfInterest> clusters = DataAnalysis
				.clusteredPointsOfInterest(dateFrom, dateTo, dataProcessor,
						tMin, dMax, tMax, dMeasure, minPoints);

		JSONArray clustersJs = JsArgsProcessor
				.clusteredPointsOfInterest(clusters);
		session.setAttribute("clusters", clustersJs);

		response.sendRedirect((String) session.getAttribute("page") + ".jsp");
	}

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}

}
