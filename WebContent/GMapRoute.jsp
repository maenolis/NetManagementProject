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
		var num_markers = "${length}"
        function initialize(){
			var lats = [];
			<c:forEach var="lat" items="${lat}"> 
            	lats.push("${lat}");
        	</c:forEach>
        	var longs = [];
        	<c:forEach var="lon" items="${lon}"> 
        		longs.push("${lon}");
    		</c:forEach>
    		var polyline = [];
            var mapOptions = {
                center: new google.maps.LatLng(lats[0], longs[0]),
                zoom: 11,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            var i;
            
            for (i = 0; i < num_markers; i++) {
            	var myLatlng = new google.maps.LatLng(lats[i],longs[i]);
            	polyline[i]=myLatlng;
              }   
            var route = new google.maps.Polyline({
                path: polyline,
                geodesic: true,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 2
              });

              route.setMap(map);
        }

        if(num_markers!=0) google.maps.event.addDomListener(window, 'load', initialize);
        else window.alert("No Access Points for this user.");
        
        
        </script>
</head>

<body>

<div class="container">
<ul class="nav nav-tabs nav-justified">
  <li><a href="${pageContext.request.contextPath}/APStickers">Access Points Stickers</a>
    
    <li class="active"><a href="#">User Route</a></li>
    <li><a href="${pageContext.request.contextPath}/LevelBatteryDiagram">Level/Battery Diagram</a></li>
    <li><a href="${pageContext.request.contextPath}/Cells">User Connection Cells</a></li>
    </ul>
    
    <div id = "map-canvas">
    </div>
</div>
</body>
</html>