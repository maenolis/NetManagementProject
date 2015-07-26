package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String dayFrom = request.getParameter("dayFrom");
		String monthFrom = request.getParameter("monthFrom");
		String yearFrom = request.getParameter("yearFrom");
		String dayTo = request.getParameter("dayTo");
		String monthTo = request.getParameter("monthTo");
		String yearTo = request.getParameter("yearTo");
		String user = request.getParameter("user");

		DataProcessor dataProcessor = DataProcessor.getInstance(request
				.getSession());
	}

}
