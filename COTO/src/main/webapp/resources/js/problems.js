$(document).ready(function() {
	$('#searchButton').on('click', function() {
		search();
	});
	if(gN != -1){
		drawChart1();
		drawChart2();
	}
});

function drawChart1() {
	<!-- Bar cahrt -->
	var ctx1 = document.getElementById("myBarChart").getContext("2d");
	
	// stores the number of bars you have at the beginning.
	var length = -1;
	
	// creates a new plugin
	Chart.pluginService.register({
	
	  // before the update ..
	  beforeUpdate: function(chart) {
	    var data = chart.config.data;
	    for (var i = data.labels.length; i < data.maxBarNumber; i++) {
	      length = (length == -1) ? i : length;
	      // populates both arrays with default values, you can put anything though
	      data.labels[i] = i;
	      data.datasets[0].data[i] = 0;
	    }
	  },
	
	  // after the update ..
	  afterUpdate: function(chart) {
	    //console.log(chart);
	    var data = chart.config.data;
	    if (length == -1) return;
	
	    // prevents new charts to be drawn
	    for (var i = length; i < data.maxBarNumber; i++) {
	      data.datasets[0]._meta[0].data[i].draw = function() {
	        return
	      };
	    }
	  }
	});
	
	var data = {
	
	  // change here depending on how many bar charts you can have
	  maxBarNumber: 20,
	  labels: labels,
	  datasets: [{
	    label: "푼 문제수",
	    backgroundColor: 'rgba(255, 201, 14, 0.5)',
	    borderColor: 'rgba(255, 201, 14, 1)',
	    borderWidth: 1,
	    data: dataForBar,
	  }]
	};
	
	var myBarChart = new Chart(ctx1, {
	  type: 'bar',
	  data: data,
	  options: {
	  	responsive: false,
	    scales: {
	      xAxes: [{
	        display: true,
	        
	        afterTickToLabelConversion: function(data){
            	let xLabels = data.ticks;
            	
            	for(let i = 0; i<xLabels.length;i++){
            		if(0<=xLabels[i] && xLabels[i]<20) xLabels[i] = '';
              	}
         	} 
	      }],
	      yAxes: [{
	        stacked: true,
	        ticks: {
	        	suggestedMin: 0,
	            stepSize: 1,
	        }
	      }]
	    }
	  }
	});

	/*var ctx1 = document.getElementById("myBarChart"); 
	var myBarChart = new Chart(ctx1 , {
	    type: 'bar',
	    data: {
	        labels: labels,
	        datasets: [{
	            label: '푼 문제수',
	            data: dataForBar,
	            borderColor: "rgba(255, 201, 14, 1)",
	            backgroundColor: "rgba(255, 201, 14, 0.5)",
	            fill: false,
	        }]
	    },
	    options: {
	        responsive: false,
	        hover: {
	            mode: 'nearest',
	            intersect: true
	        },
	        scales: {
	            xAxes: [{
	                display: true,
	                //barThickness: 20,
	                maxBarThickness: 20,
	                scaleLabel: {
	                    display: true,
	                },
	                ticks: {
	                    //autoSkip: false,
	                    //maxTicksLimit:4
	                }
	            }],
	            yAxes: [{
	                display: true,
	                ticks: {
	                    suggestedMin: 0,
	                    stepSize: 1,
	                },
	                scaleLabel: {
	                    display: true,
	                }
	            }]
	        }
	    }
	});*/
}

function drawChart2() {
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

function callModal() {
	selectHtml = $('#selectHtml').html();
	
	createModel("#createProblem", "푼 문제 등록", addajax, search);
}

function addajax(){
	var siteId = [];
	var problem = [];
	var link = [];
	
	$('.sweet-modal-content .problem').each(function(){
		var s_id = 0;
		var l = "";
		var p;
		
		var valueSplit = $(this).val().split(' (');
		
		if($(this).attr('name') == 0){ // link로 설정하는 경우
			l = valueSplit[0].trim();
			console.log("link: "+l);
			
			var split = l.split('/');
			p = split[split.length-1].trim();
			console.log("problem: "+split[split.length-1].trim());

		} else { // siteId 존재하는 경우
			s_id = $(this).attr('name');
			p = valueSplit[0].trim();
			len = $(this).val().split(' - ');
			if(len.length != 0)
				l = len[len.length-1].trim();
		}
		
		siteId.push(s_id);
		problem.push(p);
		link.push(l);
		
	});
	
   $.ajax({
        url : '../createProblem',
        type: 'POST',
        data: {
        	"siteId":siteId, "problem":problem, "link":link
        },
        success: function(data){
        	resetContent();
        	console.log("success");
        },
        error:function(request,status,error){
        	resetContent();
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
}

function search(){
	$.ajax({
		url: "./problems/search",
		type: "POST",
		async: false,
		data: {
			searchValue:$('#searchValue').val()
		},
		success: function(data){
			$('#problemsContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function deleteThis(id){
	var allid = "#"+id;
	$(allid).remove();
}

var count=0;
function insertProblems(){
	var siteName = $(".sweet-modal-content #siteName option:selected").text();
	var siteId = $('.sweet-modal-content #siteName').val();
	var site = $(".sweet-modal-content #siteName option:selected").val();
	var value = $(".sweet-modal-content #problems").val();
	var valueSplit = value.split(',');
	var data = $('.sweet-modal-content #confirmSite').html();
	
	$(".sweet-modal-content #problems").val("");
	if(siteId == 1){
		$.ajax({
        url : '../crawling/'+siteName,
        type: 'POST',
        data: {
        	"problem": valueSplit,
        	"siteID": siteId,
        	"count": count
        },
        success: function(data){
            console.log(data);
            var data2 = $('.sweet-modal-content #confirmSite').html()+data;
        	$('.sweet-modal-content #confirmSite').html(data2);
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
	}else{
		for(var i in valueSplit){
			data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><i class="small smaller material-icons" style="color:green;">done</i><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/>';
			count++;
		}
		$('.sweet-modal-content #confirmSite').html(data);
		$('#confirmSite').html(data);
	}
};

function resetContent() {
	$('#createProblem #confirmSite').html("");
	$('#selectHtml').html(selectHtml);
	
};

function baekjoon(){
	var siteSelect = document.getElementById("siteName");
	var selectValue = siteSelect.options[siteSelect.selectedIndex].value;
	var inputValue = $(".sweet-modal-content #problems").val();
	var value = parseFloat(inputValue.replace(/,/gi, " "));

	if(selectValue=='1'){
		if(isNaN(value) == true){ 
			alert("백준 문제를 등록할때는 숫자만 입력할 수 있습니다.");
			
		}else{
			insertProblems();
		}
		
	}else{
		insertProblems();
	}
};