google.load("visualization", "1.1", {packages:["bar"]});

function drawChart(array) {
	
    var companyUsers = [];
	companyUsers.push(["company", "noOfUsers"]);
	for (var i = 0; i < array.length; i++) {
		companyUsers.push([array[i][0], array[i][1]]);
	}
	
	var data = new google.visualization.arrayToDataTable(companyUsers);

	var options = {
		title: 'Users per company',
		width : 1000,
		height : 450,
		legend: { position: 'none' },
		hAxis : {
			gridlines : {
				count : array.length
			},
			title : 'Companies'
		},
		vAxis : {
			gridlines : {
				color : 'none'
			},
			minValue : 0,
			title : 'noOfusers'
		}
	};

	this.paint = function () {
		var chart = new google.charts.Bar(document.getElementById('chart_div'));
		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
	
};