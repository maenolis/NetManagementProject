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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		/*if dataProcessor is set in session skip dataProcessor creation,
		 * data reading, streams etc*/
		if (session.getAttribute("dataProcessor") == null) {
			/*DataProcessor instance for data manipulation*/
			DataProcessor dp = new DataProcessor();
			session.setAttribute("dataProcessor", dp);
		}
	
		DataProcessor dp = (DataProcessor) session.getAttribute("dataProcessor");
		
		dp.readBaseStations();
		HashMap<String,ArrayList<Object>> bsmap = dp.getBaseStationMap();
		double latitude, longtitude;
		for(String key : bsmap.keySet()){
			ArrayList<Object> list = bsmap.get(key);
			
			for (Object ap : list) {
				latitude = ((BaseStation) ap).getLocation().getLatitude();
				longtitude = ((BaseStation) ap).getLocation().getLongtitude();
				/* if no location skip */
				if (latitude == -1.0f && longtitude == -1.0f) {
					continue;
				}
				else dp.getbsLocations().put(key, ((BaseStation) list.get(0)).getLocation());
			}
		}
		System.out.println(dp.getbsLocations());
		String user="user11";
		//String user = (String)request.getSession().getAttribute("users");
		System.out.println(user);
		System.out.println(dp.getbsLocations().get(user).toString());
		String url = "/Cells.jsp";
		request.getSession().setAttribute("user",user);
		System.out.println("LALALALAL");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);  
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/Cells.jsp";
		System.out.println("LALALALAL2");
		String user = (String)request.getSession().getAttribute("users");
		System.out.println(user);
		request.getSession().setAttribute("user",user);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);  
		rd.forward(request, response);
	}

}
