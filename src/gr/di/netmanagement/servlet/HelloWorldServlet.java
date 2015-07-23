package gr.di.netmanagement.servlet;

import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorldServlet() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		DataProcessor  dataProcessor = new DataProcessor();
		HashSet<String> users = dataProcessor.getUsersSet();
		List<String> list = new ArrayList<String>(users);
		//System.out.println(list);
		request.setAttribute("users",list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Selection.jsp");  
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		String user = request.getParameter("users");
		//System.out.println(user);
		// TODO Auto-generated method stub
		if (request.getParameter("submit") != null)
			performTask(request, response, user);
	}

	private void performTask(final HttpServletRequest request,
			final HttpServletResponse response, String user) throws ServletException,
			IOException {
		String url = "/APStickers.jsp";
	
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
