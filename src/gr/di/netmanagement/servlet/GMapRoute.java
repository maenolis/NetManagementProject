package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.Wifi;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * Servlet implementation class GMapRoute
 */
@WebServlet("/GMapRoute")
public class GMapRoute extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/GMapRoute.jsp";
		SimpleDateFormat sf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Calendar calFrom = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		String dateF, dateT;
		Date dateFrom, dateTo;
		calFrom.set(Integer.valueOf((String)request.getSession().getAttribute("yearFrom")), 
					Integer.valueOf((String)request.getSession().getAttribute("monthFrom"))-1, 
					Integer.valueOf((String)request.getSession().getAttribute("dayFrom")), 
					Integer.valueOf((String)request.getSession().getAttribute("hourFrom")), 
					Integer.valueOf((String)request.getSession().getAttribute("minuteFrom")));
		dateF = sf.format(calFrom.getTime()); // string format of user selection "from"
		calTo.set(Integer.valueOf((String)request.getSession().getAttribute("yearTo")), 
				Integer.valueOf((String)request.getSession().getAttribute("monthTo"))-1, 
				Integer.valueOf((String)request.getSession().getAttribute("dayTo")), 
				Integer.valueOf((String)request.getSession().getAttribute("hourTo")), 
				Integer.valueOf((String)request.getSession().getAttribute("minuteTo")));
		dateT = sf.format(calTo.getTime()); // string format of user selection "to"
		try {
			dateFrom = sf.parse(dateF); // User-selected "From Date" 
			dateTo = sf.parse(dateT); // User-selected "To Date" 

			HashMap<String, Location> map;
			HashMap<String, ArrayList<Object>> map2;
			DataProcessor dp = DataProcessor.getInstance(request.getSession());
			dp.computeAccessPointsLocation();
			map = dp.getWifiLocations();
			map2 = dp.getWifiMap();
			String user = (String) request.getSession().getAttribute("user");
			List<Double> Longtitudes = new ArrayList<Double>();
			List<Double> Latitudes = new ArrayList<Double>();

			for(String key : map.keySet()){
				Date ts=(Date) ((Wifi) map2.get(key).get(0)).getTimestamp();
				String dsUser=(String) ((Wifi) map2.get(key).get(0)).getUser();
				if(dsUser.equals(user) && ((ts.after(dateFrom) && ts.before(dateTo)) || ts.equals(dateFrom) || ts.equals(dateTo))){

					Latitudes.add(map.get(key).getLatitude());
					Longtitudes.add(map.get(key).getLongtitude());
				}
			}
			int length = Latitudes.size();
			request.getSession().setAttribute("length", length);

			request.getSession().setAttribute("lat",Latitudes);
			request.getSession().setAttribute("lon",Longtitudes);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);  
			rd.forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		String url = "/GMapRoute.jsp";
		String user = (String) request.getSession().getAttribute("users");
		System.out.println(user);
		request.getSession().setAttribute("user", user);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
