package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.BatteryDataProcessor;
import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

/**
 * Servlet implementation class BarDiagram.
 */
@WebServlet("/LowLevels")
public class LowLevels extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new low battery levels.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LowLevels() {

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

		HttpSession session = request.getSession();
		session.setAttribute("page", "LowLevels");
		DataProcessor dataProcessor = DataProcessor.getInstance(session);

		/* Get percentages map. */
		TreeMap<String, Float> percentagesMap = BatteryDataProcessor
				.getLowLevels(dataProcessor.getBatteryMap(), dataProcessor
						.getUsersSet().size());

		/* Adding transformed percentages map(JSONArray) in session. */
		JSONArray jsArray = JsArgsProcessor.lowLevelsJsArg(percentagesMap);
		session.setAttribute("lowLevelsPercentages", jsArray);

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
