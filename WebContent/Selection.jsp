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
			</ul>
</div>

<br/>


<form role="form" class="col-xs-4 col-xs-offset-1" action="${pageContext.request.contextPath}/Forwarder" method="post">
<div class="page-header">
            <h3>Rest functions</h3>
    </div>
	
	<div class="form-group">
	<label class="control-label">Start Date</label>
          <input type="text" class="form-control" name="dateFrom" id="dateFrom" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
   	
   	<div class="form-group">
   	<label class="control-label">End Date</label>
          <input type="text" class="form-control" name="dateTo" id="dateTo" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
	
	<label class="control-label">User</label>
	<select class="form-control" name="user" id="user" onchange="" size="1">
		<c:forEach items="${users}" var="userIterator"> <option value="${userIterator}">${userIterator}</option> </c:forEach>
	</select>
	
	<label class="control-label">Function</label>
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
	
	
	
	<form role="form" class="col-xs-4 col-xs-offset-1" action="${pageContext.request.contextPath}/Forwarder" method="post">
	<div class="page-header">
            <h3>Clustering functions</h3>
    </div>

	
	<div class="form-group">
	<label class="control-label">Start Date</label>
    
          <input type="text" class="form-control" name="dateFrom" id="dateFrom" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
   	
   	<div class="form-group">
   	<label class="control-label">End Date</label>
          <input type="text" class="form-control" name="dateTo" id="dateTo" placeholder="YYYY/MM/DD hh:mm:ss" />
   	</div>
	
	<label class="control-label">User</label>
	<select class="form-control" name="user" id="user" onchange="" size="1">
		<c:forEach items="${users}" var="userIterator"> <option value="${userIterator}">${userIterator}</option> </c:forEach>
	</select>
	
	<label class="control-label">Time minimum</label>
	<input type="text" class="form-control" name="tMin" id="tMin" placeholder="Time minimum" />
	
	<label class="control-label">Time maximum</label>
	<input type="text" class="form-control" name="tMax" id="tMax" placeholder="Time maximum" />
	
	<label class="control-label">Distance maximum</label>
	<input type="text" class="form-control" name="dMax" id="dMax" placeholder="Distance maximum" />
	
	<label class="control-label">Minimum points (DbScan)</label>
	<input type="text" class="form-control" name="minPoints" id="minPoints" placeholder="Minimum points" />
	
	<label class="control-label">Distance measure (DbScan)</label>
	<input type="text" class="form-control" name="dMeasure" id="dMeasure" placeholder="Distance measure" />
	
	<label class="control-label">Function</label>
	<select class="form-control" name="page" id="page" onchange="" size="1">
		<option value="StayPoints">StayPoints</option>
		<option value="DbScan">DbScan</option>
	</select>
	
		<input type="submit" name="submit" value="Submit" />
		
	</form>

</body>
</html>