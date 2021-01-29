$(document).ready(function() {
	$('#register-button').on('click', function() {
		 createModel("#registerSolvedProblem", "문제 등록", addAjax);
		 $('select').formSelect();
	});
	
	$('#searchButton').on('click', function() {
		console.log("click");
		$.ajax({
			url: "problems/search",
			type: "POST",
			async: false,
			data: {
				searchValue:$('#searchValue').val()
			},
			success: function(data){
				//console.log(data);
				$('#problemsContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	});
});
function printAllContent(id){
	$('#site').html($(id+' .pSite').html());
	$('#problemName').html($(id+' .pTitle').html());
	$('#memo').html($(id+' .pMemo').html());
	$('#regdate').html($(id+' .pRegdate').html());
	$('#difficulty').html($(id+' .pDifficulty').html());
	
	$('#UuserProblemID').html(id.substring(8, id.length));
	$('#Usite').html($(id+' .pSite').html());
	$('#UproblemName').html($(id+' .pTitle').html());
	$('#Umemo').html($(id+' .pMemo').html());
	$('#Uregdate').html($(id+' .pRegdate').html());
	$('#Udifficulty').attr('value', $(id+' .pDifficulty').attr('alt'));
	console.log($(id+' .pDifficulty').attr('alt'));
	
	rudModel("#readSolvedProblem", "#updateSolvedProblem", $(id+' .pTitle').html(), updateAjax, deleteAjax);
	//$('select').formSelect();
}

function addAjax (){
	console.log("success");
	//ajax 넣는 함수
	//승아가 구현함!!
}

function updateAjax(){
	$.ajax({
		url: "problems/update",
		type: "POST",
		async: false,
		data: {
			id:$('#UuserProblemID').html(),
			difficulty:$('.sweet-modal-content #Udifficulty').val(),
			memo: $('.sweet-modal-content #Umemo').val()
		},
		success: function(data){
			//console.log(data);
			$('#problemsContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function deleteAjax (){
	$.ajax({
		url: "./problems/delete",
		type: "POST",
		async: false,
		data: {
			id:$('#UuserProblemID').html()
		},
		success: function(data){
			//console.log(data);
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
	var array = ['', '백준', 'leetcode', 'SW expert academy', 'oncoder', 'goorm', 'leetcode[database]', 'link'];
	var site = $("#siteName option:selected").val();
	var value = document.getElementById("problems").value;
	var valueSplit = value.split(',');
	var data = $('#confirmSite').html();
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><input disabled value="'+valueSplit[i]+' ('+array[site]+')" id="last_name disabled" type="text" class="validate"/></div>';
		count++;
	}
	$('#confirmSite').html(data);
	document.getElementById("problems").value = "";
	$("#siteName option:selected").val("");
}