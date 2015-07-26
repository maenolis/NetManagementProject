package gr.di.netmanagement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Forwarder
 */
@WebServlet("/Forwarder")
public class Forwarder extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forwarder() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession();
		session.setAttribute("minuteFrom", request.getParameter("minuteFrom"));
		session.setAttribute("hourFrom", request.getParameter("hourFrom"));
		session.setAttribute("dayFrom", request.getParameter("dayFrom"));
		session.setAttribute("monthFrom", request.getParameter("monthFrom"));
		session.setAttribute("yearFrom", request.getParameter("yearFrom"));
		session.setAttribute("minuteTo", request.getParameter("minuteTo"));
		session.setAttribute("hourTo", request.getParameter("hourTo"));
		session.setAttribute("dayTo", request.getParameter("dayTo"));
		session.setAttribute("monthTo", request.getParameter("monthTo"));
		session.setAttribute("yearTo", request.getParameter("yearTo"));
		session.setAttribute("user", request.getParameter("user"));
		response.sendRedirect("Forwarder.jsp");
	}

}
