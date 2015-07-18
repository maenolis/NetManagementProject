<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.awt.*"%>
<%@ page import="java.io.*"%>
<%@ page import="org.jfree.chart.*"%>
<%@ page import="org.jfree.chart.axis.*"%>
<%@ page import="org.jfree.chart.entity.*"%>
<%@ page import="org.jfree.chart.labels.*"%>
<%@ page import="org.jfree.chart.plot.*"%>
<%@ page import="org.jfree.chart.renderer.category.*"%>
<%@ page import="org.jfree.chart.urls.*"%>
<%@ page import="org.jfree.data.category.*"%>
<%@ page import="org.jfree.data.general.*"%>

<%
	final double[][] data = new double[][] {
			{ 210, 300, 320, 265, 299 }, { 200, 304, 201, 201, 340 } };

	final CategoryDataset dataset = DatasetUtilities
			.createCategoryDataset("Team ", "", data);

	JFreeChart chart = null;
	BarRenderer renderer3D = null;
	CategoryPlot plot = null;

	final CategoryAxis3D categoryAxis = new CategoryAxis3D("Match");
	final ValueAxis valueAxis = new NumberAxis3D("Run");
	renderer3D = new BarRenderer3D();

	plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
			renderer3D);
	plot.setOrientation(PlotOrientation.VERTICAL);
	chart = new JFreeChart("Srore Bord", JFreeChart.DEFAULT_TITLE_FONT,
			plot, true);

	chart.setBackgroundPaint(new Color(249, 231, 236));

	try {
		final ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());
		final File picFile = new File("pic.png");
		ChartUtilities.saveChartAsPNG(picFile, chart, 600, 400, info);
	} catch (Exception e) {
		out.println(e);
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<script src="/NetManagementProject/lib/jquery/jquery.min.js"></script>
<script src="/NetManagementProject/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<title>Level/Battery Diagram</title>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs nav-justified">
			<li><a href="/NetManagementProject/APStickers.jsp">Access
					Points Stickers </a></li>
			<li><a href="/NetManagementProject/GMapRoute.jsp">User Route</a>
			<div id="map-canvas"></div></li>
			<li class="active"><a href="#">Level/Battery Diagram</a></li>
			<li><a href="/NetManagementProject/Cells.jsp">User
					Connection Cells</a></li>
		</ul>
	</div>
	<br />
	<br />
	<IMG SRC="pic.png" WIDTH="600" HEIGHT="400" BORDER="0" USEMAP="#chart">
</body>
</html>