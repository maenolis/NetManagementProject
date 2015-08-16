package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.Wifi;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class APStickers
 */
@WebServlet("/APStickers")
public class APStickers extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APStickers() {

		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		String url = "/APStickers.jsp";
		if (request.getSession().getAttribute("length") != null) {
			List<Double> Longtitudes = (List<Double>) request.getSession()
					.getAttribute("lon");
			List<Double> Latitudes = (List<Double>) request.getSession()
					.getAttribute("lat");
			int length = (int) request.getSession().getAttribute("length");
			request.getSession().setAttribute("length", length);
			request.getSession().setAttribute("lat", Latitudes);
			request.getSession().setAttribute("lon", Longtitudes);
		} else {
			HashMap<String, Location> map;
			HashMap<String, ArrayList<Object>> map2;
			HttpSession session = request.getSession();
			DataProcessor dp = DataProcessor.getInstance(session);
			dp.computeAccessPointsLocation();
			map = dp.getWifiLocations();
			map2 = dp.getWifiMap();
			String user = (String) request.getSession().getAttribute("user");
			List<Double> Longtitudes = new ArrayList<Double>();
			List<Double> Latitudes = new ArrayList<Double>();
			for (String key : map.keySet()) {
				if (((Wifi) map2.get(key).get(0)).getUser().equals(user)) {
					Latitudes.add(map.get(key).getLatitude());
					Longtitudes.add(map.get(key).getLongtitude());
				}
			}
			int length = Latitudes.size();
			request.getSession().setAttribute("length", length);
			request.getSession().setAttribute("lat", Latitudes);
			request.getSession().setAttribute("lon", Longtitudes);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		String url = "/APStickers.jsp";
		String user = (String) request.getSession().getAttribute("users");
		System.out.println(user);
		request.getSession().setAttribute("user", user);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
