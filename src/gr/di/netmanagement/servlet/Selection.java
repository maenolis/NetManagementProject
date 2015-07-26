package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.DatesProvider;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Selection
 */
@WebServlet("/Selection")
public class Selection extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Selection() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("days") == null) {
			session.setAttribute("days", DatesProvider.getDays());
		}
		if (session.getAttribute("months") == null) {
			session.setAttribute("months", DatesProvider.getMonths());
		}
		if (session.getAttribute("hours") == null) {
			session.setAttribute("hours", DatesProvider.getHours());
		}
		if (session.getAttribute("minutes") == null) {
			session.setAttribute("minutes", DatesProvider.getMinutes());
		}
		if (session.getAttribute("users") == null) {
			session.setAttribute("users", DataProcessor.getInstance(session)
					.getUsersSet());
		}
		

		response.sendRedirect("Selection.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}

}
