
// Or with jQuery
$(document).ready(function(){
    $('select').formSelect();
    
    $('#searchButton').on('click', function() {
		console.log("click");
		search();
	});
	$('#orderValue').on('change', function() {
		console.log("change");
		search();
	});
	$('#siteValue').on('change', function() {
		console.log("change");
		search();
	});
	
	search(1);
	DrawChart1();
	DrawChart2();

});



//코딩 사이트별 차트
function DrawChart1() {
	var newLabels = [];
	var newData = [];
	var newColor=[];
	var chartColors=[
		'rgb(255, 196, 196)',
		'rgb(255, 221, 196)',
		'rgb(255, 237, 196)',
		'rgb(224, 255, 196)',
		'rgb(196, 240, 255)',
		'rgb(196, 219, 255)',
		'rgb(198, 196, 255)',
		'rgb(231, 196, 255)',
		'rgb(255, 196, 255)',
		'rgb(255, 196, 222)',
		'rgb(255, 196, 200)',
	];
	
	//newLabels.push("전체 문제 수: " + totalProbCnt);
	for (var i=0 ; i< ratioBySite.length ; i++){
		newData.push(ratioBySite[i].ratio*100);
		newLabels.push(ratioBySite[i].siteName+" ("+ratioBySite[i].count+")");
		newColor.push(chartColors[i]);
	}
	
	var chartBySite = {
		type: 'doughnut',
		data: {
			datasets: [{
				data: newData,
				backgroundColor: newColor,
				label: 'Dataset 1'
			}],
			labels: newLabels
		},
		layout: {
            padding: {
                left: 0,
                right: 30,
                top: 0,
                bottom: 0
            }
        },
		options: {
			responsive: false,
			legend: {
				display:true,
				position:'right',
				labels: {
	                fontSize: 10,
	                padding:6
	                
	            }
			},
			title: {
				display: true,
				text: '코딩 사이트별 이용률'
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	};
	
	var ctx1 = document.getElementById('chartBySite').getContext('2d');
	window.myDoughnut = new Chart(ctx1, chartBySite);
	
	
	
};

function DrawChart2() {
	// 일간 평균 차트

	var dates = [];
	var averages = [];
	for (var i=0 ; i< averageWeek.length ; i++){
		dates.push(averageWeek[i].date);
		averages.push(averageWeek[i].average);
	}

	var color = Chart.helpers.color;

	var barChartData = {
		labels: dates,
		datasets: [{
			label: '하루 평균 문제수',
			//backgroundColor: newColor[2],
			//borderColor: window.chartColors.red,
			borderWidth: 1,
			data: averages,
		}],
		labels:dates

	};
	
	var ctx2 = document.getElementById('averageForWeek').getContext('2d');
	window.myBar = new Chart(ctx2, {
		type: 'bar',
		data: barChartData,
		options: {
			responsive: false,
			legend: {
				display:false
			},
			title: {
				display: true,
				text: '일주일간 평균 문제수'
			}
		}
	});
}

function search(page){
	console.log("siteValue: "+$('#siteValue option:selected').val());
	$.ajax({
			url: "problemList/search",
			type: "POST",
			async: false,
			data: {
				page: page,
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val(),
				siteValue:$('#siteValue option:selected').val()
			},
			success: function(data){
				//console.log(data);
				$('#pageajaxContent').html(data);
				//alert(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	});
}