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
<script src="/NetManagementProject/lib/js/bars.js"></script>
<title>Low Levels</title>
</head>
<body>

<div class="container">
		<ul class="nav nav-tabs nav-justified">
			<li><a href="/NetManagementProject/APStickers.jsp">Access
					Points Stickers </a></li>
			<li><a href="/NetManagementProject/GMapRoute.jsp">User Route</a></li>
			<li class="active"><a href="#">Level/Battery Diagram</a></li>
			<li><a href="/NetManagementProject/Cells.jsp">User
					Connection Cells</a></li>
		</ul>
	</div>

	
	<div id="chart_div" ></div>
	<script>drawChart();</script>

</body>
</html>