package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Cells.
 */
@WebServlet("/Cells")
public class Cells extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new cells.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public Cells() {

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
		session.setAttribute("page", "Cells");
		DataProcessor dp = DataProcessor.getInstance(session);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		Date dateFrom = null, dateTo = null;

		try {
			dateFrom = sf.parse((String) session.getAttribute("dateFrom"));
			dateTo = sf.parse((String) session.getAttribute("dateTo"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		HashMap<String, ArrayList<Object>> bsmap = dp.getBaseStationMap();

		String user = (String) request.getSession().getAttribute("user");

		session.setAttribute("cells",
				JsArgsProcessor.cellsJsArg(bsmap.get(user), dateFrom, dateTo));

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
