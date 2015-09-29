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

@WebServlet("/BatteryTimeLevel")
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
		session.setAttribute("page", "BatteryTimeLevel");
		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date dateFrom, dateTo;
		try {
			dateFrom = sf.parse((String) session.getAttribute("dateFrom"));
			dateTo = sf.parse((String) session.getAttribute("dateTo"));
			ArrayList<Object> list = dataProcessor.getBatteryMap().get(
					session.getAttribute("user"));
			TreeMap<String, Integer> uMap = BatteryDataProcessor
					.getUserLevelsWithinDates(dateFrom, dateTo, list);
			session.setAttribute("batteryTimeLevels",
					JsArgsProcessor.batteryTimeLevelJsArg(uMap));
			response.sendRedirect((String) session.getAttribute("page")
					+ ".jsp");
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}

}
