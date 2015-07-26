package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;

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

	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession();
		String minuteFrom = (String) session.getAttribute("minuteFrom");
		String hourFrom = (String) session.getAttribute("hourFrom");
		String dayFrom = (String) session.getAttribute("dayFrom");
		String monthFrom = (String) session.getAttribute("monthFrom");
		String yearFrom = (String) session.getAttribute("yearFrom");
		String minuteTo = (String) session.getAttribute("minuteTo");
		String hourTo = (String) session.getAttribute("hourTo");
		String dayTo = (String) session.getAttribute("dayTo");
		String monthTo = (String) session.getAttribute("monthTo");
		String yearTo = (String) session.getAttribute("yearTo");
		String user = (String) session.getAttribute("user");

		DataProcessor dataProcessor = DataProcessor.getInstance(session);
	}

}
