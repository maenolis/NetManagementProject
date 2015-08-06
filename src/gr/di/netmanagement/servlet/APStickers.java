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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Location> map;
		HashMap<String, ArrayList<Object>> map2;
		HttpSession session = request.getSession();
		DataProcessor dp = DataProcessor.getInstance(session);
		dp.computeAccessPointsLocation();
		map = dp.getWifiLocations();
		map2 = dp.getWifiMap();
		String user = (String)request.getSession().getAttribute("user");
		List<Double> Longtitudes = new ArrayList<Double>();
		List<Double> Latitudes = new ArrayList<Double>();
		for(String key : map.keySet()){
			if(((Wifi) map2.get(key).get(0)).getEmail().equals(user)){
				Latitudes.add(map.get(key).getLatitude());
				Longtitudes.add(map.get(key).getLongtitude());
			}
		}
		String url = "/APStickers.jsp";
		int length = Latitudes.size();
		for(int i=0;i<length;i++){
			//System.out.println(Latitudes.get(i));
		}
		request.getSession().setAttribute("length", length);
		request.getSession().setAttribute("lat",Latitudes);
		request.getSession().setAttribute("lon",Longtitudes);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);  
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/APStickers.jsp";
		String user = (String)request.getSession().getAttribute("users");
		System.out.println(user);
		request.getSession().setAttribute("user",user);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);  
		rd.forward(request, response);
	}

}
