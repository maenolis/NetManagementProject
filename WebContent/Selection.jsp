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
  <script src = "https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<title>Date Selection</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/HelloWorldServlet" method="post">
	
	<h4>Select start minute:</h4>
	<select name="minuteFrom" id="minuteFrom" onchange="" size="1">
	
    	 <c:forEach items="${minutes}" var="minuteIterator">
    	 	<option value="${minuteIterator}">${minuteIterator}</option>
    	 </c:forEach>
	</select>
	
	<h4>Select start hour:</h4>
	<select name="hourFrom" id="hourFrom" onchange="" size="1">
	
    	 <c:forEach items="${hours}" var="hourIterator">
    	 	<option value="${hourIterator}">${hourIterator}</option>
    	 </c:forEach>
	</select>
	
	<h4>Select start day:</h4>
	<select name="dayFrom" id="dayFrom" onchange="" size="1">
	
    	 <c:forEach items="${days}" var="dayIterator">
    	 	<option value="${dayIterator}">${dayIterator}</option>
    	 </c:forEach>
	</select>
	
	<h4>Select start month:</h4>
	<select name="monthFrom" id="monthFrom" onchange="" size="1">
    	
    	<c:forEach items="${months}" var="monthIterator">
    	 	<option value="${monthIterator}">${monthIterator}</option>
    	 </c:forEach>
	</select>
	<h4>Select start year:</h4>
	<select name="yearFrom" id="yearFrom" onchange="" size="1">
		<option value="2015">2015</option>
	</select>
	
	<h4>Select end minute:</h4>
	<select name="minuteTo" id="minuteTo" onchange="" size="1">
	
    	 <c:forEach items="${minutes}" var="minuteIterator">
    	 	<option value="${minuteIterator}">${minuteIterator}</option>
    	 </c:forEach>
	</select>
	
	<h4>Select end hour:</h4>
	<select name="hourTo" id="hourTo" onchange="" size="1">
	
    	 <c:forEach items="${hours}" var="hourIterator">
    	 	<option value="${hourIterator}">${hourIterator}</option>
    	 </c:forEach>
	</select>
	
	<h4>Select end day:</h4>
	<select name="dayTo" id="dayTo" onchange="" size="1">
	
    	 <c:forEach items="${days}" var="dayIterator">
    	 	<option value="${dayIterator}">${dayIterator}</option>
    	 </c:forEach>
	</select>
	
	<h4>Select end month:</h4>
	<select name="monthTo" id="monthTo" onchange="" size="1">
    	
    	<c:forEach items="${months}" var="monthIterator">
    	 	<option value="${monthIterator}">${monthIterator}</option>
    	 </c:forEach>
	</select>
	<h4>Select end year:</h4>
	<select name="yearTo" id="yearTo" onchange="" size="1">
		<option value="2015">2015</option>
	</select>
	
	<h4>Select user:</h4>
	
	<select name="user" id="user" onchange="" size="1">
		<c:forEach items="${users}" var="userIterator"> <option value="${userIterator}">${userIterator}</option> </c:forEach>
	</select>
	<p>
	<p>
		<input type="submit" name="submit" value="Submit" />
		
	</form>

</body>
</html>