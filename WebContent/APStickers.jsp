<!DOCTYPE html>
<html lang = "en">
<head>
    <style type="text/css">
        html{height: 100%}
        body{height: 100%; margin: 0; padding: 0}
        #map-canvas{height: 100%}
    </style>
    <title>GMaps Demo</title>
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
    <div id = "map-canvas">
    </div>
</body>
</html>