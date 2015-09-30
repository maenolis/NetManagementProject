<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang = "en">

<head>
    <style>
      #map-canvas {
        width: 900px;
        height: 600px;
      }
    </style>
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
  <script src="/NetManagementProject/lib/jquery/jquery.min.js"></script>
  <script src="/NetManagementProject/lib/bootstrap/js/bootstrap.min.js"></script>
  <script src = "https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <title>Access Points Stickers</title>
    
    <script>
    
    	var array =	<%out.print(session.getAttribute("clusters"));%>;
		
        function initialize(){
            var mapOptions = {
                center: new google.maps.LatLng(array[0].midLat, array[0].midLon),
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            var polyline = [];
            for (var i = 0; i < array.length; i++) {
            	polyline = [];
            	var southWest = new google.maps.LatLng(array[i].southWestLat, array[i].southWestLon);
            	polyline[0]=southWest;
            	console.log("southWest: " + southWest);
            	var northWest = new google.maps.LatLng(array[i].northWestLat, array[i].northWestLon);
            	polyline[1]=northWest;
            	console.log("northWest: " + northWest);
            	var northEast = new google.maps.LatLng(array[i].northEastLat, array[i].northEastLon);
            	polyline[2]=northEast;
            	console.log("northEast: " + northEast);
            	var southEast = new google.maps.LatLng(array[i].southEastLat, array[i].southEastLon);
            	polyline[3]=southEast;
            	console.log("southEast: " + southEast);
            	
            	polyline[4]=southWest;
            	
            	var route = new google.maps.Polyline({
                    path: polyline,
                    strokeColor: '#FF0000',
                    strokeOpacity: 1.0,
                    strokeWeight: 2
                });

                route.setMap(map);
            }   
            
        }

        if(array.length != 0) google.maps.event.addDomListener(window, 'load', initialize);
        else window.alert("No clusters.");
        
        
        </script>
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
    
    <div id = "map-canvas" style="margin-left:100px;margin-top:10px;">
    </div>

</body>
</html>