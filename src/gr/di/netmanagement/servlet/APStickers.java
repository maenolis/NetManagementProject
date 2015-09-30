package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.Wifi;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		session.setAttribute("page", "APStickers");

		try {

			dateFrom = sf.parse((String) session.getAttribute("dateFrom"));

			dateTo = sf.parse((String) session.getAttribute("dateTo"));

			HashMap<String, Location> wifiLocationsMap;
			HashMap<String, ArrayList<Object>> wifiMap;
			DataProcessor dp = DataProcessor.getInstance(request.getSession());
			dp.computeAccessPointsLocation();
			wifiLocationsMap = dp.getWifiLocations();
			wifiMap = dp.getWifiMap();
			String user = (String) request.getSession().getAttribute("user");
			List<Double> Longtitudes = new ArrayList<Double>();
			List<Double> Latitudes = new ArrayList<Double>();

			// TODO: Implement in dataProcessor.
			for (String key : wifiLocationsMap.keySet()) {
				Date ts = ((Wifi) wifiMap.get(key).get(0)).getTimestamp();
				String dsUser = ((Wifi) wifiMap.get(key).get(0)).getUser();
				if (dsUser.equals(user)
						&& ((ts.after(dateFrom) && ts.before(dateTo))
								|| ts.equals(dateFrom) || ts.equals(dateTo))) {

					Latitudes.add(wifiLocationsMap.get(key).getLatitude());
					Longtitudes.add(wifiLocationsMap.get(key).getLongtitude());
				}
			}
			int length = Latitudes.size();
			request.getSession().setAttribute("length", length);

			request.getSession().setAttribute("lat", Latitudes);
			request.getSession().setAttribute("lon", Longtitudes);
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
