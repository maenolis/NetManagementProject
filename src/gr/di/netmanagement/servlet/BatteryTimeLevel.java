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

		HttpSession session = request.getSession();

		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		response.getWriter().println(dataProcessor.getWifiMap());

		response.sendRedirect("BatteryTimeLevel.jsp");
	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}

}
