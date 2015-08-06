google.load('visualization', '1.1', {packages: ['line']});

function drawChart(array) {

  var data = new google.visualization.DataTable();
  
  data.addColumn('date', 'Time of the day');
  data.addColumn('number', 'Battery Level');
  
  var batteryLevels = [];
  
  for (var i = 0; i < array.length; i++) {
	  batteryLevels.push([new Date(array[i][0], array[i][1], array[i][2], array[i][3], array[i][4], array[i][5]), array[i][6]]);
  }
  
  data.addRows(batteryLevels);

  var options = {
	chart: {
	  title: 'Battery levels through the day for specific user.',
	  subtitle: 'in %'
	},
	width: 900,
	height: 500,
	hAxis: {
		format: 'd/M',
		gridlines: {count: 15}
	  },
	vAxis : {
			minValue : 0,
			title : 'level'
		}
  };
  
  this.paint = function () {
	  var chart = new google.charts.Line(document.getElementById('chart_div'));
	chart.draw(data, options);
  }

  
}