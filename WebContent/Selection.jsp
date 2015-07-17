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
	<h4>Select a day:</h4>
	<select name="day" id="day" onchange="" size="1">
    	<option value="01">01</option>
    	<option value="02">02</option>
    	<option value="03">03</option>
    	<option value="04">04</option>
    	<option value="05">05</option>
    	<option value="06">06</option>
    	<option value="07">07</option>
    	<option value="08">08</option>
    	<option value="09">09</option>
    	<option value="10">10</option>
    	<option value="11">11</option>
    	<option value="12">12</option>
    	<option value="13">13</option>
    	<option value="14">14</option>
    	<option value="15">15</option>
    	<option value="16">16</option>
    	<option value="17">17</option>
    	<option value="18">18</option>
    	<option value="19">19</option>
    	<option value="20">20</option>
    	<option value="21">21</option>
    	<option value="22">22</option>
    	<option value="23">23</option>
    	<option value="24">24</option>
    	<option value="25">25</option>
    	<option value="26">26</option>
    	<option value="27">27</option>
    	<option value="28">28</option>
    	<option value="29">29</option>
    	<option value="30">30</option>
    	<option value="31">31</option>
	</select>
	<h4>Select a month:</h4>
	<select name="month" id="month" onchange="" size="1">
    	<option value="01">January</option>
    	<option value="02">February</option>
    	<option value="03">March</option>
    	<option value="04">April</option>
    	<option value="05">May</option>
    	<option value="06">June</option>
    	<option value="07">July</option>
    	<option value="08">August</option>
    	<option value="09">September</option>
    	<option value="10">October</option>
    	<option value="11">November</option>
    	<option value="12">December</option>
	</select>
	<h4>Select a year:</h4>
	<select name="year" id="year" onchange="" size="1">
		<option value="2015">2015</option>
	</select>
	<h4>Select a user:</h4>
	<select name="user" id="user" onchange="" size="1">
	</select>
	<p>
	<form action="${pageContext.request.contextPath}/HelloWorldServlet" method="post">
		<input type="submit" name="submit" value="Submit" />
	</form>
	

</body>
</html>