package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.Battery;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class BarDiagram.
 */
@WebServlet("/LowBatteryLevels")
public class LowBatteryLevels extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new low battery levels.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LowBatteryLevels() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		DataProcessor dataProcessor = new DataProcessor();
		HashMap<String, Float> lowLevels = getLowLevels(dataProcessor.getBatteryMap());
		String str = getArrayString(lowLevels.keySet().toArray());
		System.out.println("values " + lowLevels.values());
		System.out.println("keys " + lowLevels.keySet());
		System.out.println(lowLevels);
		System.out.println(convertToPercentages(lowLevels, dataProcessor.getUsersSet().size()));
		//response.getWriter().println(lowLevels.keySet());
		//response.getWriter().println("<br />");
		//response.getWriter().println("<br />");
		//response.getWriter().println(lowLevels);
		request.getSession().setAttribute("lowLevels", str);
		request.getSession().setAttribute("percentages", convertToPercentages(lowLevels, dataProcessor.getUsersSet().size()));
		response.sendRedirect("LevelBatteryDiagram.jsp");
	}
	
	/**
	 * Gets the low levels.
	 *
	 * @param batteryMap the battery map
	 * @return the low levels
	 */
	private HashMap<String, Float> getLowLevels(HashMap<String, ArrayList<Object>> batteryMap) {
		HashMap<String, Float> dateMap = new HashMap<String, Float>();
		HashMap<String, HashSet<String>> dateUserMap = new HashMap<String, HashSet<String>>();
		
		for (ArrayList<Object> batteries : batteryMap.values()) {
			for (Object battery : batteries) {
				if (!dateMap.containsKey((((Battery)battery).toShortString()))) {
					dateMap.put((((Battery)battery).toShortString()), 0.0f);
					dateUserMap.put((((Battery)battery).toShortString()), new HashSet<String>());
				}
				if (((Battery)battery).getLevel() <= 15) {
					if (!dateUserMap.get((((Battery)battery).toShortString())).contains(((Battery)battery).getUser())) {
						dateUserMap.get((((Battery)battery).toShortString())).add(((Battery)battery).getUser());
						dateMap.put((((Battery)battery).toShortString()), dateMap.get((((Battery)battery).toShortString())) + 1.0f);
					}
				}
			}
			
		}
		System.out.println("dateMap before " + dateMap);
		return dateMap;
	}
	
	private Float[] convertToPercentages(HashMap<String, Float> dateMap, int size) {
		Set<String> set = dateMap.keySet();
		for (String key : set) {
			dateMap.put(key, dateMap.get(key) / size * 100.0f);
		}
		System.out.println("dateMap after " + dateMap);
		return (Float[]) dateMap.values().toArray();
	}
	
	
	public String getArrayString(Object[] items){
	    String result = "[";
	    for(int i = 0; i < items.length; i++) {
	    	String itemI = (String) items[i];
	        result += "\"" + itemI + "\"";
	        if(i < items.length - 1) {
	            result += ", ";
	        }
	    }
	    result += "]";

	    return result;
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		
	}

}
