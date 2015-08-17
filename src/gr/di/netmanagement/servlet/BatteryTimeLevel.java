package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.BatteryDataProcessor;
import gr.di.netmanagement.processdata.DataProcessor;
import gr.di.netmanagement.processdata.JsArgsProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

		DataProcessor dataProcessor = DataProcessor.getInstance(session);

		SimpleDateFormat sf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Calendar calFrom = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		String dateF, dateT;
		Date dateFrom, dateTo;
		calFrom.set(
				Integer.valueOf((String) request.getSession().getAttribute(
						"yearFrom")),
				Integer.valueOf((String) request.getSession().getAttribute(
						"monthFrom")) - 1,
				Integer.valueOf((String) request.getSession().getAttribute(
						"dayFrom")),
				Integer.valueOf((String) request.getSession().getAttribute(
						"hourFrom")),
				Integer.valueOf((String) request.getSession().getAttribute(
						"minuteFrom")));
		dateF = sf.format(calFrom.getTime()); // string format of user selection
												// "from"
		calTo.set(
				Integer.valueOf((String) request.getSession().getAttribute(
						"yearTo")),
				Integer.valueOf((String) request.getSession().getAttribute(
						"monthTo")) - 1,
				Integer.valueOf((String) request.getSession().getAttribute(
						"dayTo")),
				Integer.valueOf((String) request.getSession().getAttribute(
						"hourTo")),
				Integer.valueOf((String) request.getSession().getAttribute(
						"minuteTo")));
		dateT = sf.format(calTo.getTime()); // string format of user selection
											// "to"

		try {
			dateFrom = sf.parse(dateF);
			dateTo = sf.parse(dateT);

			ArrayList<Object> list = dataProcessor.getBatteryMap().get(
					session.getAttribute("user"));
			TreeMap<String, Integer> uMap = BatteryDataProcessor
					.getUserLevelsWithinDates(dateFrom, dateTo, list);
			session.setAttribute("batteryTimeLevels",
					JsArgsProcessor.batteryTimeLevelJsArg(uMap));
			response.sendRedirect("BatteryTimeLevel.jsp");
		} catch (ParseException e) {

			e.printStackTrace();
		} // User-selected "From Date"

	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}

}
