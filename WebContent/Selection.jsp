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
<title>Data and process Selection</title>
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
				<li><a href="${pageContext.request.contextPath}/LowLevels">Cluster1</a></li>
				<li><a href="${pageContext.request.contextPath}/LowLevels">Cluster2</a></li>
			</ul>
</div>

<br/>
<br/>


<form role="form" class="col-xs-4 col-xs-offset-1" action="${pageContext.request.contextPath}/Forwarder" method="post">
<div class="page-header">
            <h3>Rest functions</h3>
        </div>
	
	<!-- <h4>Select start minute:</h4>
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
	</select> -->
	
	<div class="form-group">
	<label class="control-label">Start Date</label>
    
          <input type="text" class="form-control" name="dateFrom" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
   	
   	<div class="form-group">
   	<label class="control-label">End Date</label>
          <input type="text" class="form-control" name="dateTo" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
   	
   	<div class="checkbox">
  		<label><input name="allDates" type="checkbox" value="">Get all the duration.(ignores above values)</label>
	</div>
	
	<!-- <h4>Select end minute:</h4>
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
	</select> -->
	
	<h4>User:</h4>
	
	<select class="form-control" name="user" id="user" onchange="" size="1">
		<c:forEach items="${users}" var="userIterator"> <option value="${userIterator}">${userIterator}</option> </c:forEach>
	</select>
	
	<h4>Function:</h4>
	<select class="form-control" name="page" id="page" onchange="" size="1">
		<option value="APStickers">APStickers</option>
		<option value="BatteryTimeLevel">BatteryTimeLevel</option>
		<option value="Cells">Cells</option>
		<option value="CompanyUsers">Company Users</option>
		<option value="GMapRoute">GMapRoute</option>
		<option value="LowLevels">LowLevels</option>
		<option value="StayPoints">StayPoints</option>
	</select>
	
		<input type="submit" name="submit" value="Submit" />
		
	</form>
	
	<!-- remove -->
	
	<form role="form" class="col-xs-4 col-xs-offset-1" action="${pageContext.request.contextPath}/Forwarder" method="post">
	<div class="page-header">
            <h3>Clustering functions</h3>
        </div>

	<!-- <h4>Select start minute:</h4>
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
	</select> -->
	
	<div class="form-group">
	<label class="control-label">Start Date</label>
    
          <input type="text" class="form-control" name="yearFrom" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
   	
   	<div class="form-group">
   	<label class="control-label">End Date</label>
          <input type="text" class="form-control" name="yearTo" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
	
	<div class="checkbox">
  		<label><input name="allDates" type="checkbox" value="">Get all the duration.(ignores above values)</label>
	</div>
	
	<!-- <h4>Select end minute:</h4>
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
	</select> -->
	
	<h4>User:</h4>
	
	<select class="form-control" name="user" id="user" onchange="" size="1">
		<c:forEach items="${users}" var="userIterator"> <option value="${userIterator}">${userIterator}</option> </c:forEach>
	</select>
	
	<h4>Clustering level:</h4>
	
	<select class="form-control" name="page" id="page" onchange="" size="1">
		<option value="APStickers">APStickers</option>
		<option value="BatteryTimeLevel">BatteryTimeLevel</option>
		<option value="Cells">Cells</option>
		<option value="CompanyUsers">Company Users</option>
		<option value="GMapRoute">GMapRoute</option>
		<option value="LowLevels">LowLevels</option>
		<option value="StayPoints">StayPoints</option>
	</select>
	
		<input type="submit" name="submit" value="Submit" />
		
	</form>

</body>
</html>