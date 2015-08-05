google.load('visualization', '1', {
	packages : [ 'corechart' ]
});

function drawChart(array) {

	var data = new google.visualization.DataTable();
	data.addColumn('date', 'Day');
	data.addColumn('number', 'percentage');
	var datesPercentages = [];
	for (var i = 0; i < array.length; i++) {
		datesPercentages.push([new Date(array[i][0][0], array[i][0][1], array[i][0][2]), array[i][1]]);
	}
	data.addRows(datesPercentages);

	var options = {
		title : 'battery under 15%',
		width : 1200,
		height : 600,
		hAxis : {
			format : 'd/M',
			gridlines : {
				count : array.length
			},
			title : 'Days'
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
		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		chart.draw(data, options);
	}

	

}
