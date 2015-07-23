<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Low Battery Levels</title>
<script language="javascript" type="text/javascript" src="/NetManagementProject/lib/js/canvas.js"></script>
<script>
		//Create instance of class and pass the arrays
		var canvasCreator = new painter([" 22/5", " 23/5", " 24/5", " 25/5", " 26/5", " 27/5", " 28/5", " 22/5", " 23/5", " 24/5", " 25/5", " 26/5", " 27/5", " 28/5"],
			[89, 66, 44, 77, 59, 77, 25, 40, 95, 55, 25, 10, 80, 33]);
		//Change onload function to get the job done
		window.onload = canvasCreator.paintCanvas;
	</script>
</head>
<body>
<h3 align="center">Bar Diagram Levels</h3>
	<canvas id="myCanvas" width="1200" height="600" align="center" style="border: 1px solid #000000;" >

</body>
</html>