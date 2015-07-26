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
		<title>Forwarder: Choose page!</title>
		</head>
	<body>
		<div class="container">
			<ul class="nav nav-tabs nav-justified">
				<li><a href="${pageContext.request.contextPath}/#">Access
						Points Stickers </a></li>
				<li><a href="${pageContext.request.contextPath}/#">User Route</a></li>
				<li><a href="${pageContext.request.contextPath}/LowBatteryLevels">Level/Battery Diagram</a></li>
				<li><a href="${pageContext.request.contextPath}/#">User
						Connection Cells</a></li>
			</ul>
		</div>
		<h3>Please choose from the list. Forwarder will redirect you!</h3>
	</body>
</html>