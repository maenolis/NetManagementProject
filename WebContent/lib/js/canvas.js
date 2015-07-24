var painter = function (xAxis, percentages) {
	
	var xAxisLabelArr = xAxis;
	var yAxisSize = percentages;
	
	
	
	this.paintCanvas = function () {
		console.log("start");
		console.log(xAxisLabelArr);
		console.log("end");
		var ctx = document.getElementById("myCanvas").getContext("2d");
		var height = document.getElementById("myCanvas").height - 50;
		var width = document.getElementById("myCanvas").width;
		
		//For each bar
		for (var i = 0; i < xAxisLabelArr.length; i += 1) {
			ctx.fillStyle='#0000ff';
			ctx.fillRect(5 + i * width / xAxisLabelArr.length, 30 + (height - yAxisSize[i] * height / 100), width / xAxisLabelArr.length - 20, yAxisSize[i] * height / 100);
			ctx.fillStyle='#000000';
			ctx.font = "10px Arial";
			ctx.fillText(xAxisLabelArr[i], 10 + i * width / xAxisLabelArr.length, height + 45);
			ctx.fillText(yAxisSize[i], 10 + i * width / xAxisLabelArr.length, 25 + (height - yAxisSize[i] * height / 100));
			ctx.fillStyle='#ff00ff';
		}
		
		/*ctx.fillRect(60, 80, 30, height - 40 - 80);
		ctx.fillStyle='#000000';
		ctx.font = "10px Arial";
		ctx.fillText(xAxisLabelArr[1], 60, height - 20);
		ctx.fillStyle='#ff00ff';
		ctx.fillRect(100, 200, 30, height - 40 - 200);
		ctx.fillStyle='#000000';
		ctx.font = "10px Arial";
		ctx.fillText(xAxisLabelArr[2], 100, height - 20);*/
	}
	
	
}