package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.DataAnalysis;
import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

/**
 * Servlet implementation class StayPoints.
 */
@WebServlet("/StayPoints")
public class StayPoints extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new stay points.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public StayPoints() {
		super();
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
		session.setAttribute("page", "StayPoints");
		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date dateFrom = null, dateTo = null;
		final float tMin = Float.valueOf((String) session.getAttribute("tMin"));
		final float dMax = Float.valueOf((String) session.getAttribute("dMax"));
		final int tMax = Integer.valueOf((String) session.getAttribute("tMax"));
		String user = (String) session.getAttribute("user");
		try {
			dateFrom = sf.parse((String) session.getAttribute("dateFrom"));
			dateTo = sf.parse((String) session.getAttribute("dateTo"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONArray stayPoints = JsArgsProcessor
				.pointsOfInterestJsArg(DataAnalysis.analyzeLocations(dateFrom,
						dateTo, user, dataProcessor, tMin, dMax, tMax));
		session.setAttribute("stayPoints", stayPoints);

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
