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
		// TODO!!!
		// session.setAttribute("dateFrom", request.getParameter("dateFrom"));
		// session.setAttribute("dateTo", request.getAttribute("dateTo"));
		session.setAttribute("dateFrom", "2015/01/01 00:00:00");
		session.setAttribute("dateTo", "2015/08/01 00:00:00");
		session.setAttribute("user", "user31");
		session.setAttribute("page", request.getParameter("page"));
		response.sendRedirect(request.getParameter("page"));
	}
}
