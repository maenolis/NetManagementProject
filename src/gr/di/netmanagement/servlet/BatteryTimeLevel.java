package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.BatteryDataProcessor;
import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BatteryTimeLevels")
public class BatteryTimeLevel extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BatteryTimeLevel() {

	}

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession();

		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		
		//TODO: remove, for dev purposes only!
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date from = null;
		Date to = null;
		try {
			from = sf.parse("2015-04-01 00:00:00");
			to = sf.parse("2015-04-20 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String user = "user31";
		ArrayList<Object> list = dataProcessor.getBatteryMap().get(user);
		TreeMap<String, Integer> uMap = BatteryDataProcessor.getUserLevelsWithinDates(from, to, list);
		session.setAttribute("batteryTimeLevels", JsArgsProcessor.batteryTimeLevelJsArg(uMap));
		response.sendRedirect("BatteryTimeLevels.jsp");
	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		
	}

}
