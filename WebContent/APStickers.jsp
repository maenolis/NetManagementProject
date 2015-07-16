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
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>Access Points Stickers</title>
    <script src = "https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script>
        function initialize(){
        	var myLatlng = new google.maps.LatLng(38.00450334902246,23.678711790165565);
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
<div class="container">
<ul class="nav nav-tabs nav-justified">
  <li class="active"><a href="#">Access Points Stickers
    
    <li><a href="/NetManagementProject/GMapRoute.jsp">User Route</a></li>
    <li><a href="/NetManagementProject/LevelBatteryDiagram.jsp">Level/Battery Diagram</a></li>
    <li><a href="/NetManagementProject/Cells.jsp">User Connection Cells</a></li>
    </ul>
    
    </a><div id = "map-canvas">
    </div>
</div>
</body>
</html>