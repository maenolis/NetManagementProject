package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.Wifi;
import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;
import gr.di.netmanagement.processdata.WifiDataProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

/**
 * Servlet implementation class APStickers.
 */
@WebServlet("/APStickers")
public class APStickers extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
	 * @see HttpServlet#HttpServlet()
	 */

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		Date dateFrom, dateTo;
		HttpSession session = request.getSession();
		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		String user = (String) session.getAttribute("user");
		session.setAttribute("page", "APStickers");

		try {

			dateFrom = sf.parse((String) session.getAttribute("dateFrom"));

			dateTo = sf.parse((String) session.getAttribute("dateTo"));

			List<Wifi> apStickers = WifiDataProcessor.getUserApStickers(
					dataProcessor, user, dateFrom, dateTo);

			JSONArray apStickersJs = JsArgsProcessor.apStickersJs(apStickers);
			session.setAttribute("apStickers", apStickersJs);

			response.sendRedirect((String) session.getAttribute("page")
					+ ".jsp");
		} catch (ParseException e) {

			e.printStackTrace();
		}
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
