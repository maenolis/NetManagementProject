	google.load('visualization', '1', {
	packages : [ 'corechart' ]
});
//google.setOnLoadCallback(drawChart);

function drawChart(array) {

	var data = new google.visualization.DataTable();
	data.addColumn('date', 'Day');
	data.addColumn('number', 'percentage');
	var datesPercentages = [];
	for (var i = 0; i < array.length; i++) {
		datesPercentages.push([new Date(array[i][0][0], array[i][0][1], array[i][0][2]), array[i][1]]);
	}
	data.addRows(datesPercentages);

	/*data.addRows([ [ new Date(2015, 0, 1), 12 ], [ new Date(2015, 0, 2), 12.5 ],
			[ new Date(2015, 0, 3), 3 ], [ new Date(2015, 0, 4), 1 ],
			[ new Date(2015, 0, 5), 3 ], [ new Date(2015, 0, 6), 4 ],
			[ new Date(2015, 0, 7), 3 ], [ new Date(2015, 0, 8), 4 ],
			[ new Date(2015, 0, 9), 2 ], [ new Date(2015, 0, 10), 5 ],
			[ new Date(2015, 0, 11), 8 ], [ new Date(2015, 0, 12), 6 ],
			[ new Date(2015, 0, 13), 3 ], [ new Date(2015, 0, 14), 3 ],
			[ new Date(2015, 0, 15), 5 ], [ new Date(2015, 0, 16), 7 ],
			[ new Date(2015, 0, 17), 6 ], [ new Date(2015, 0, 18), 6 ],
			[ new Date(2015, 0, 19), 3 ], [ new Date(2015, 0, 20), 1 ],
			[ new Date(2015, 0, 21), 2 ], [ new Date(2015, 0, 22), 4 ],
			[ new Date(2015, 0, 23), 6 ], [ new Date(2015, 0, 24), 5 ],
			[ new Date(2015, 0, 25), 9 ], [ new Date(2015, 0, 26), 4 ],
			[ new Date(2015, 0, 27), 9 ], [ new Date(2015, 0, 28), 8 ],
			[ new Date(2015, 0, 29), 6 ], [ new Date(2015, 0, 30), 4 ],
			[ new Date(2015, 0, 31), 6 ], [ new Date(2015, 1, 1), 7 ],
			[ new Date(2015, 1, 2), 9 ], [ new Date(2015, 1, 3), 6 ],
			[ new Date(2015, 1, 4), 7 ], [ new Date(2015, 1, 5), 9 ],
			[ new Date(2015, 1, 6), 6 ], [ new Date(2015, 1, 7), 7 ],
			[ new Date(2015, 1, 8), 9 ], [ new Date(2015, 1, 9), 6 ],
			[ new Date(2015, 1, 15), 7 ], [ new Date(2015, 1, 16), 9 ] ]);*/

	var options = {
		title : 'battery under 15%',
		width : 1200,
		height : 600,
		hAxis : {
			format : 'd/M',
			gridlines : {
				count : 32
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
		var chart = new google.visualization.ColumnChart(document
			.getElementById('chart_div'));

		chart.draw(data, options);
	}

	

}
