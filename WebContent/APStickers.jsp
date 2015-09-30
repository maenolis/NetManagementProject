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
    
    var array = <%out.print(session.getAttribute("apStickers"));%>;
		
        function initialize(){
			var mapOptions = {
                center: new google.maps.LatLng(array[0].lat, array[0].lon),
                zoom: 11,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            
            for (var i = 0; i < array.length; i++) {
    			var lon = array[i].lon;
    			var lat = array[i].lat;
    			var bssid = array[i].bssid;
    			var midLevel = array[i].midLevel;
    			var ssid = array[i].ssid;
    			var contentString = '<p> bssid :' + bssid + '</p>'
    					+ '<p> ssid :' + ssid + '</p>'
    					+ '<p> midLevel : ' + midLevel + ' </p>';
    			var myLatlng = new google.maps.LatLng(lat, lon);

    			var infowindow = new google.maps.InfoWindow({
    				content : contentString
    			});

    			var marker = new google.maps.Marker({
    				position : myLatlng,
    				map : map,
    				html : contentString
    			});

    			google.maps.event.addListener(marker, 'click', function() {

    				infowindow.setContent(this.html);
    				infowindow.open(map, this);
    			});
    		}   
        }

        if(array.length != 0) google.maps.event.addDomListener(window, 'load', initialize);
        else window.alert("No Access Points for this user.");
        
        
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