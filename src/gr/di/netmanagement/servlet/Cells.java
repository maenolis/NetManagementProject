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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HashMap<String, ArrayList<Object>> bsmap = dp.getBaseStationMap();
		// double latitude, longtitude;
		String user = (String) request.getSession().getAttribute("user");
		// Object k = null;
		// for (String key : bsmap.keySet()) {
		// ArrayList<Object> list = bsmap.get(key);
		//
		// for (Object ap : list) {
		//
		// latitude = ((BaseStation) ap).getLocation().getLatitude();
		// longtitude = ((BaseStation) ap).getLocation().getLongtitude();
		// /* if no location skip */
		// if (latitude == -1.0f && longtitude == -1.0f) {
		// continue;
		// } else {
		// dp.getBaseStationLocations().put(key,
		// ((BaseStation) list.get(0)).getLocation());
		// k = ap;
		// }
		// }
		// }
		//
		// String operator = ((BaseStation) k).getOperator();
		// String cid = ((BaseStation) k).getCid();
		// String mcc = ((BaseStation) k).getMcc();
		// String mnc = ((BaseStation) k).getMnc();
		// String lac = ((BaseStation) k).getLac();
		// Double lat = dp.getBaseStationLocations().get(user).getLatitude();
		// Double lon = dp.getBaseStationLocations().get(user).getLongtitude();
		// request.getSession().setAttribute("operator", operator);
		// request.getSession().setAttribute("cid", cid);
		// request.getSession().setAttribute("mcc", mcc);
		// request.getSession().setAttribute("mnc", mnc);
		// request.getSession().setAttribute("lac", lac);
		// request.getSession().setAttribute("latitude", lat);
		// request.getSession().setAttribute("longtitude", lon);
		session.setAttribute("baseStations",
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
