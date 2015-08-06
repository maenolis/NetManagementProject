<!DOCTYPE html>
<html lang = "en">
<head>
    <style>
  
      #map-canvas {
        width: 500px;
        height: 400px;
        
      }
    </style>
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
  <script src="/NetManagementProject/lib/jquery/jquery.min.js"></script>
  <script src="/NetManagementProject/lib/bootstrap/js/bootstrap.min.js"></script>
  <script src = "https://maps.googleapis.com/maps/api/js?sensor=false"></script>
  
    <title>User Route</title>
    <script src = "https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script>
        function initialize(){
        	var lon = "${longtitude}";
        	var lat = "${latitude}";
        	var myLatlng = new google.maps.LatLng(lat,lon);
            var mapOptions = {
                center: new google.maps.LatLng(38, 23),
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                title: 'Hello World!'
            });
        }

        google.maps.event.addDomListener(window, 'load', initialize);
        </script>
</head>

<body>
<form action="${pageContext.request.contextPath}/Cells" method="post">
<div class="container">
<ul class="nav nav-tabs nav-justified">
  <li><a href="/${pageContext.request.contextPath}/APStickers">Access Points Stickers
    </a></li>
    <li><a href="${pageContext.request.contextPath}/GMapRoute">User Route</a></li>
    <li><a href="${pageContext.request.contextPath}/LevelBatteryDiagram">Level/Battery Diagram</a></li>
    <li class ="active"><a href="#">User Connection Cells</a></li>
    </ul>
    <div id = "map-canvas">
    </div>
    </div>
</form>
</body>
</html>