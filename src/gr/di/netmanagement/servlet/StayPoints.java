//package gr.di.netmanagement.servlet;
//
//import gr.di.netmanagement.processdata.DataAnalysis;
//import gr.di.netmanagement.processdata.DataProcessor;
//
//import java.io.IOException;
//import java.util.Date;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Servlet implementation class StayPoints
// */
//@WebServlet("/StayPoints")
//public class StayPoints extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public StayPoints() {
//		super();
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	@Override
//	protected void doGet(final HttpServletRequest request,
//			final HttpServletResponse response) throws ServletException,
//			IOException {
//
//		final HttpSession session = request.getSession();
//		System.out.println(DataAnalysis.analizeLocations(new Date(""),
//				new Date(""), "user31", DataProcessor.getInstance(session),
//				dMin, dMax, tMax, maxMeters));
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	@Override
//	protected void doPost(final HttpServletRequest request,
//			final HttpServletResponse response) throws ServletException,
//			IOException {
//
//	}
//
// }
