package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.BaseStation;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Cells
 */
@WebServlet("/Cells")
public class Cells extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cells() {

		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession();
		DataProcessor dp = DataProcessor.getInstance(session);

		dp.readBaseStations();
		HashMap<String, ArrayList<Object>> bsmap = dp.getBaseStationMap();
		double latitude, longtitude;
		for (String key : bsmap.keySet()) {
			ArrayList<Object> list = bsmap.get(key);

			for (Object ap : list) {
				latitude = ((BaseStation) ap).getLocation().getLatitude();
				longtitude = ((BaseStation) ap).getLocation().getLongtitude();
				/* if no location skip */
				if (latitude == -1.0f && longtitude == -1.0f) {
					continue;
				} else
					dp.getbsLocations().put(key,
							((BaseStation) list.get(0)).getLocation());
			}
		}
		String user = (String) request.getSession().getAttribute("user");
		System.out.println(user);
		System.out.println(dp.getbsLocations().get(user));
		String url = "/Cells.jsp";
		Double lat = dp.getbsLocations().get(user).getLatitude();
		Double lon = dp.getbsLocations().get(user).getLongtitude();
		request.getSession().setAttribute("latitude", lat);
		request.getSession().setAttribute("longtitude", lon);
		// request.getSession().setAttribute("user",user);
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

		String url = "/Cells.jsp";
		String user = (String) request.getSession().getAttribute("users");
		System.out.println(user);
		request.getSession().setAttribute("user", user);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
