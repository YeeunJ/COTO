
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
	
	// 코딩 사이트별 차트
	var newLabels = [];
	var newData = [];
	var newColor=[];
	var chartColors=[window.chartColors.red,
					window.chartColors.orange,
					window.chartColors.yellow,
					window.chartColors.green,
					window.chartColors.blue,
					window.chartColors.purple,
					window.chartColors.grey,
					];

	for (var i=0 ; i< ratioBySite.length ; i++){
		newData.push(ratioBySite[i].ratio);
		newLabels.push(ratioBySite[i].siteName);
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
		options: {
			responsive: false,
			legend: {
				display:true,
				position:'bottom',
				labels: {
	                fontSize: 8,
	                padding:5
	                
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

//	window.onload = function() {
//		var ctx = document.getElementById('chartBySite').getContext('2d');
//		window.myDoughnut = new Chart(ctx, chartBySite);
//	};

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
			backgroundColor: color(window.chartColors.red).alpha(0.5).rgbString(),
			borderColor: window.chartColors.red,
			borderWidth: 1,
			data: averages,
		}],
		labels:dates

	};

	window.onload = function() {
		var ctx1 = document.getElementById('chartBySite').getContext('2d');
		window.myDoughnut = new Chart(ctx1, chartBySite);
		
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

	};
	
});


function search(){
	$.ajax({
			url: "problemList/search",
			type: "POST",
			async: false,
			data: {
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val()
			},
			success: function(data){
				//console.log(data);
				$('#problemContent').html(data);
				//alert(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	});
}
function callModal() {
	createModel("#createProblems", "문제집 등록", addajax);
 	$('select').formSelect();
}

function printAllContent(id, recomId, count){
	//alert(recomId);
	readComment(recomId);
	$('#readProblems').html($(id+' .readProblem').html());
	$('#readTags').html($(id+' .readTag').html());
	$('#readContents').html($(id+' .readContent').html());
	$('#readRecommends').html($(id+' .readRecommend').html());
	$('#readDifficulties').html($(id+' .readDifficulty').html());
	
	$("#commentCount").text(count);
	
	$('#updateRecomID').html(recomId);
	$('#updateContents').html($(id+' .readContent').html());
	$('#updateTags').html($(id+' .readTag').html());
	$('#updateProblems').html($(id+' .readProblem').html());
	
	rudModel("#readRecommendProblem", "#updateRecommendProblem", $(id+' .readTitle').html(), updateAjax, deleteAjax);
	$('select').formSelect();
}


function addComment() {	
	var userID = $("input[name='writer']").val();
	var recomID = $("input[name='recomID']").val();

	if (confirm("댓글을 추가하시겠습니까?")) {
		$.ajax({
			url : "recommendProblem/addComment",
			type : "POST",
			async : false,
			data : {
				//userID : userID,
				//recomID : recomID,
				//content : $('.sweet-modal-content #comment-textarea').val()
			},
			success : function(data) {
				$('.sweet-modal-content #modal-comment').html(data);
				$('.sweet-modal-content #comment-textarea').val("");
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
			}
		});
	}
}

$('.sweet-modal-content .chips').material_chip();
$('.sweet-modal-content .chips-placeholder').material_chip({
    placeholder: '+tag',
    secondaryPlaceholder: '+Tag',
});

function readComment(recomID) {
		$.ajax({
			url : "recommendProblem/readComment",
			type : "POST",
			async : false,
			data : {
				//recomID : recomID,
			},
			success : function(data) {
				$("#modal-comment").html(data);
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
			}
		});
}


function addajax(){
	
	var siteId = [];
	var problem = [];
	var link = [];
	var title = $('.sweet-modal-content #createTitle').val(); //document.getElementById('createTitle').value;
	var difficulty_cnt = document.getElementsByName("difficulty").length;
	var tag = [];
	var content = $('.sweet-modal-content #createContent').val(); //document.getElementById('createContent').value;
	
	console.log(title + " - " + content)
	
	$('.sweet-modal-content .problem').each(function(){
		
		var s_id = 0;
		var l = "";
		var p;
		var valueSplit = $(this).val().split(' ');
		
		if($(this).attr('name') == 0){ // link로 설정하는 경우
			l = valueSplit[0];
			console.log("link: "+l);
			
			var split = l.split('/');
			p = split[split.length-1];
			console.log("problem: "+split[split.length-1]);

		} else { // siteId 존재하는 경우
			s_id = $(this).attr('name');
			p = valueSplit[0];
		}
		
		siteId.push(s_id);
		problem.push(p);
		link.push(l);
		
	});
	
	console.log(problem);
	console.log(siteId);
	console.log(link);
	
	probs = {"siteId":siteId, "problem":problem, "link":link};
	
	var tag_data= $('.sweet-modal-content #problemTag').material_chip('data');
	for(var i=0; i<tag_data.length; i++) {
		tag.push(tag_data[i].tag);
	}
	console.log(tag);
	
	for(var i=0;i<difficulty_cnt;i++) {
		if(document.getElementsByName("difficulty")[i].checked == true)
			var difficulty = document.getElementsByName("difficulty")[i].value;
	}		

	for(var i=0; i<siteId.length; i++) {
		console.log("TEST: "+siteId[i]+"/"+problem[i]+"/"+link[i]);
	}
	
	$.ajax({
        url : './recommendProblem/createRecomProblem',
        type: 'POST',
        data: {
        	"siteId":siteId, "problem":problem, "link":link, "title":title, "difficulty":difficulty, "tag":tag, "content":content
        },
        success: function(data) {
            alert('리스트에 추가하였습니다.');
            $('#recommendContent').html(data);
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });

}

function updateAjax (){
	var difficulty_cnt = document.getElementsByName("updateDifficulty").length;
	
	for(var i=0;i<difficulty_cnt;i++) {
		if(document.getElementsByName("updateDifficulty")[i].checked == true)
			var difficulty = document.getElementsByName("updateDifficulty")[i].value;
	}
	
	$.ajax({
		url: "./recommendProblem/updateRecomProblem",
		type: "POST",
		async: false,
		data: {
			id:$('#updateRecomID').html(),
			content: $('.sweet-modal-content #updateContents').val(),
			/*tag: $('.sweet-modal-content #updateTags').val(),
			problem: $('.sweet-modal-content #updateProblems').val(),*/
			difficulty:difficulty
		},
		success: function(data){
			console.log(data);
			$('#recommendContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function deleteAjax (){
	$.ajax({
		url: "./recommendProblem/deleteRecomProblem",
		type: "POST",
		async: false,
		data: {
			id:$('#updateRecomID').html()
		},
		success: function(data){
			console.log(data);
			$('#recommendContent').html(data);
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
	console.log("siteId: "+siteId);
	var site = $(".sweet-modal-content #siteName option:selected").val();
	var value = $(".sweet-modal-content #problems").val();
	console.log(value);
	var valueSplit = value.split(',');
	var data = $('.sweet-modal-content #confirmSite').html();
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><input disabled name="'+siteId+'" value="'+valueSplit[i]+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate"/></div>';
		count++;
	}
	$('.sweet-modal-content #confirmSite').html(data);
	$('#confirmSite').html(data);
	$(".sweet-modal-content #problems").val("");
};
