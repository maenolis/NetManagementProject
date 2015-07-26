<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<script src="/NetManagementProject/lib/jquery/jquery.min.js"></script>
<script src="/NetManagementProject/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<title>Low Battery Levels</title>
<script language="javascript" type="text/javascript"
	src="/NetManagementProject/lib/js/canvas.js"></script>

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
	<h3 align="center">Bar Diagram Levels</h3>
	<canvas id="myCanvas" width="1200" height="500" align="center"
		style="border: 1px solid #000000;margin-left: 60px;"> </canvas>
		<script>
			var array = [];
		</script>
		<c:forEach items="${sessionScope.percentages}" var="percentage">
	        <script>
	       		 array.push("${percentage}");
	   		</script>
    	</c:forEach>
    	<script>
	    	//Create instance of class and pass the arrays
			var canvasCreator = new painter(<%out.print(session.getAttribute("dates"));%>, array);
			//Change onload function to get the job done
			window.onload = canvasCreator.paintCanvas;
    	</script>
    	
		
		
</body>
</html>