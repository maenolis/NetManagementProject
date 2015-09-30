package gr.di.netmanagement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Forwarder.
 */
@WebServlet("/Forwarder")
public class Forwarder extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new forwarder.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public Forwarder() {

	}

	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

	}

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession();

		session.setAttribute("dateFrom", request.getParameter("dateFrom"));
		session.setAttribute("dateTo", request.getParameter("dateTo"));
		session.setAttribute("tMin", request.getParameter("tMin"));
		session.setAttribute("dMax", request.getParameter("dMax"));
		session.setAttribute("tMax", request.getParameter("tMax"));
		session.setAttribute("tMax", request.getParameter("dMeasure"));
		session.setAttribute("tMax", request.getParameter("minPoints"));
		session.setAttribute("user", request.getParameter("user"));
		session.setAttribute("page", request.getParameter("page"));
		response.sendRedirect(request.getParameter("page"));
	}
}
