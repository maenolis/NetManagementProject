<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<script src="/NetManagementProject/lib/jquery/jquery.min.js"></script>
<script src="/NetManagementProject/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script src="/NetManagementProject/lib/js/batteryTimeLevels.js"></script>
<title>Battery levels through the time</title>
</head>
<body>

<div class="container">
			<ul class="nav nav-tabs nav-justified">
			<li><a href="${pageContext.request.contextPath}/Selection">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/APStickers">Wifi Points</a></li>
				<li><a href="${pageContext.request.contextPath}/GMapRoute">Wifi Route</a></li>
				<li><a href="${pageContext.request.contextPath}/BatteryTimeLevel">Battery Diagram</a></li>
				<li><a href="${pageContext.request.contextPath}/Cells">Cell Points</a></li>
				<li><a href="${pageContext.request.contextPath}/LowLevels">Low Levels</a></li>
			</ul>
</div>

	
	<div id="chart_div" style="margin-left:100px;margin-top:100px;"></div>
	<script>
		var chart = new drawChart(<%out.print(session.getAttribute("batteryTimeLevels"));%>);
		window.onload = chart.paint;
	
	</script>

</body>
</html>