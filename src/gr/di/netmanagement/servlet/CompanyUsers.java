package gr.di.netmanagement.servlet;

import gr.di.netmanagement.processdata.BaseStationProcessor;
import gr.di.netmanagement.processdata.DataProcessor;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class companyUsers
 */
@WebServlet("/CompanyUsers")
public class CompanyUsers extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyUsers() {

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

		HttpSession session = request.getSession();
		DataProcessor dataProcessor = DataProcessor.getInstance(session);
		TreeMap<String, Integer> cMap = BaseStationProcessor
				.companiesMap(dataProcessor.getBaseStationMap());
		System.out.println(cMap);
		session.setAttribute("numOfUsers",
				BaseStationProcessor.objectArrayToInt(cMap.values().toArray()));
		session.setAttribute("companies", BaseStationProcessor
				.objectArrayToString(cMap.keySet().toArray()));

		response.sendRedirect("CompanyUsers.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		// TODO Auto-generated method stub
	}

}
