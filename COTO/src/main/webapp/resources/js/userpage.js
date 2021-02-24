$(document).ready(function() {
	drawChart1();
});

function drawChart1() {
	<!-- Doughnut cahrt -->		
	var ctx = document.getElementById("myDoughnutChart"); 
	var myDoughnutChart = new Chart(ctx, { 
	    type: 'doughnut', 
	    data: {
	    	labels: ['목표 문제수','푼 문제수'],
	        datasets: [{
	            data: dataForDoughnut,
	            backgroundColor: ['#e8e8e8','rgba(255, 201, 14, 0.5)'],
	        }]
	    },
	    plugins: [{
	    	beforeDraw: function(chart) {
			    var width = chart.chart.width,
			        height = chart.chart.height,
			        ctx = chart.chart.ctx;

			    ctx.restore();
			    var fontSize = (height / 210).toFixed(2);
			    ctx.font = fontSize + "em sans-serif";
			    ctx.textBaseline = "middle";

			    var text =  uP+"문제"+" / "+gN+"문제",
			        textX = Math.round((width - ctx.measureText(text).width) / 2),
			        textY = height / 1.7;

			    ctx.fillText(text, textX, textY);
			    ctx.save();
			  }
	   }],
	    options: {
	       responsive: false,
	       legend: {
	         display: true
	       },
	       cutoutPercentage: 65
	    },
	 });
}