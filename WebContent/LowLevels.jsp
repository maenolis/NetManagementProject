<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1.1','packages':['corechart', 'timeline']}]}"></script>
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<script src="/NetManagementProject/lib/jquery/jquery.min.js"></script>
<script src="/NetManagementProject/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script src="/NetManagementProject/lib/js/lowLevels.js"></script>
<title>Low Levels</title>
</head>
<body>

	<div class="container">
			<ul class="nav nav-tabs nav-justified">
			<li><a href="${pageContext.request.contextPath}/Selection">Start Page </a></li>
				<li><a href="${pageContext.request.contextPath}/APStickers">Access Points Stickers </a></li>
				<li><a href="${pageContext.request.contextPath}/GMapRoute">User Route</a></li>
				<li><a href="${pageContext.request.contextPath}/BatteryTimeLevel">Level/Battery Diagram</a></li>
				<li><a href="${pageContext.request.contextPath}/Cells">User Connection Cells</a></li>
			</ul>
</div>

	
	<div id="chart_div" style="margin-left:50px;margin-top:50px;"></div>
	<script>
		var chart = new drawChart(<%out.print(session.getAttribute("lowLevelsPercentages"));%>);
		window.onload = chart.paint;
	
	</script>

</body>
</html>