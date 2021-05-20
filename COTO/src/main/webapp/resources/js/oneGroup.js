$(document).ready(function(){
	DrawProgressChart();
});

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

function DrawProgressChart() {
	
	var chartLabels = [];
	var chartDatas = [];
	var newColor=[];
	
	for (var i=0 ; i< progressByUser.length ; i++){
		chartDatas.push(progressByUser[i].count);
		chartLabels.push(progressByUser[i].nickName);
		newColor.push(chartColors[i])
	}
	
	var ctx = document.getElementById('progressChart').getContext('2d'); 
	var chart = new Chart(ctx, { 
		type: 'bar',
		data: { 
			labels: chartLabels, 
			datasets: [{
				backgroundColor: chartColors,
				data: chartDatas,
			}] }, 
			options: {
				legend: {
			         display: false //This will do the task
			    }, 
				scales: {
					xAxes: [{ 
						ticks: { 
							fontSize: '15' } 
					}], 
					yAxes: [{ 
						ticks: { 
							beginAtZero: true, 
							stepSize: 1,
							fontSize: '15' } 
					}] 
				} 
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
	$(".sweet-modal-content #problems").val("");
	if(siteId == 1){
		$.ajax({
		url: '../crawling/'+siteName,
        type: 'POST',
        data: {
        	"problem": valueSplit,
        	"siteID": siteId,
        	"count": count
        },
        success: function(data){
            console.log(data);
             count += valueSplit.length+1;
            var data2 = $('.sweet-modal-content #confirmSite').html()+data;
        	$('.sweet-modal-content #confirmSite').html(data2);
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
	}else{
		for(var i in valueSplit){
			data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><i class="small smaller material-icons checkIcon" style="color:green;">done</i><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/></div>';
			count++;
		}
		$('.sweet-modal-content #confirmSite').html(data);
	}
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

function problemCreateModal(userID) {
	if(userID > 0) createModel("#createProblem", "새로운 문제 리스트 생성", addProblem, searchF);
	else alert("로그인을 해야 글쓰기가 가능합니다.");
}

function addProblem(){
	
	var probStartDate = $(".sweet-modal-content #probStartDate").val();
	var probEndDate = $(".sweet-modal-content #probEndDate").val();
	var siteId = [];
	var link = [];
	var problem = [];	
	
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
	
	console.log(problem);
	
	$.ajax({
        url : "./groups/createProblem",
        type: 'POST',
        data: {
	        groupID: groupID,
        	siteId : siteId,
        	problem: problem,
        	probStartDate : probStartDate,
        	probEndDate : probEndDate,
        	link : link
        },
        success: function(data) {
        	console.log(data);
        },
        error:function(request,status,error){
        	alert("error");
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });

}

function searchF() {

}


function printGoalProblems(goalID, groupID) {
		var title;
		var userID;
		var groupLeader;
		
		$.ajax({
			url : "./readModalInfo",
			type : "POST",
			async : false,
			data : {
				goalID : goalID,
				groupID : groupID,
			},
			success : function(data) {
				var dataSplit = data.split("\n");
				for(var i=0;i<dataSplit.length;i++) {
					dataSplit[i] = dataSplit[i].trim();
					
					if(dataSplit[i].indexOf("readGoalTitle") != -1) title = $( dataSplit[i] ).text();
					if(dataSplit[i].indexOf("userID") != -1) userID = $( dataSplit[i] ).text();
					if(dataSplit[i].indexOf("groupLeader") != -1) groupLeader = $( dataSplit[i] ).text();
				}
				
				$("#modalContent").html(data);
				//if(userID == groupLeader) rudModel("#readGoalProblem", "#readGoalProblem", title, title, updateAjax, deleteAjax, search, 0);
				//else readModel("#readGoalProblem", title);
				
				readModel("#readGoalProblem", title);
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
			}
		});
	}
	
function checkProblem(id, goalID, groupID){
	$.ajax({
		url : "./addProbCheck",
		type : "POST",
		async : false,
		data : {
			pID : id,
			goalID : goalID,
			groupID : groupID
		},
		success : function(data) {
			idName = ".sweet-modal-content #eachProblemContent"+id;
			console.log(idName);
			$(idName).html(data);
		},
		error : function(request, status, error) {
			alert("허용되지 않은 접근입니다. 새로고침 후 다시 시도해주세요.");
			console.log("code:" + request.status + "\n"
					+ "message:" + request.responseText + "\n"
					+ "error:" + error);
		}
	});
}
function uncheckProblem(id, name, goalID, groupID){
	$.ajax({
		url : "./deleteProbCheck",
		type : "POST",
		async : false,
		data : {
			pID : id,
			problemName: name,
			goalID : goalID,
			groupID : groupID
		},
		success : function(data) {
			idName = ".sweet-modal-content #eachProblemContent"+ id;
			$(idName).html(data);
		},
		error : function(request, status, error) {
			alert("허용되지 않은 접근입니다. 새로고침 후 다시 시도해주세요.");
			console.log("code:" + request.status + "\n"
					+ "message:" + request.responseText + "\n"
					+ "error:" + error);
		}
	});
}




function deleteGroupGoal(goalID) {
	console.log("그룹골 삭제 버튼 클릭!");
	if (confirm("정말로 삭제하겠습니까?")){
	
	$.ajax({
		url: "./groups/deleteGroupGoal",
		type: "POST",
		async: false,
		data: {
			goalID : goalID
		},
		success: function(data){
			console.log("그룹골 삭제 완료!");
			$('#problemsContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
		
	});
	}
}

	
	