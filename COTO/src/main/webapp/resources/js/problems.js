$(document).ready(function() {
	/*$('#register-button').on('click', function() {
		 createModel("#registerSolvedProblem", "문제 등록", addAjax);
		 $('select').formSelect();
	});*/

	$('#searchButton').on('click', function() {
		search();
	});
});
function search(){
	$.ajax({
		url: "./problems/search",
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
}
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
	
	var d = jQuery($(id+' .readDifficulty').html()).attr("alt");
	$("#ud"+d).attr('checked', 'checked');
	
	rudModel("#readSolvedProblem", "#updateSolvedProblem", "문제 상세보기", "문제 수정하기", updateAjax, deleteAjax, search);
	//$('select').formSelect();
}

function updateAjax(){
	var difficulty_cnt = document.getElementsByName("difficulty").length;
	
	for(var i=0;i<difficulty_cnt;i++) {
		if(document.getElementsByName("difficulty")[i].checked == true)
			var difficulty = document.getElementsByName("difficulty")[i].value;
	}
	
	$.ajax({
		url: "problems/update",
		type: "POST",
		async: false,
		data: {
			id:$('.sweet-modal-content #UuserProblemID').html(),
			difficulty:difficulty,
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

function callModal() {
	selectHtml = $('#selectHtml').html();
	
	createModel("#createProblem", "푼 문제 등록", addajax);
 	//$('select').formSelect();
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
		
	console.log(problem);
	console.log(siteId);
	console.log(link);
	
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
/*
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><div class="preloader-wrapper small active" style="width:20px; height:20px;"><div class="spinner-layer spinner-green-only"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div></div><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/>';
		count++;
	}
	$('.sweet-modal-content #confirmSite').html(data);
	$('#confirmSite').html(data);*/
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
            /*
        	for(var i in valueSplit){
            	count--;
            	id='confirmProblemValue'+count;
            	console.log(id);
            	deleteThis(id);
        	}
        	var data2 = $('.sweet-modal-content #confirmSite').html()+data;
        	$('.sweet-modal-content #confirmSite').html(data2);*/
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
	
}