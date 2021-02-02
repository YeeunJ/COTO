// Or with jQuery
$(document).ready(function(){
    //$('select').formSelect();
    $('#orderValue').formSelect();
    $('#searchButton').on('click', function() {
		console.log("click");
		search();
	});
	$('#orderValue').on('change', function() {
		console.log("change");
		search();
	});
});

var selectHtml="";

function search(){
	$.ajax({
			url: "recommendProblem/search",
			type: "POST",
			async: false,
			data: {
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val()
			},
			success: function(data){
				console.log(data);
				$('#problemsContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	});
}
function callModal() {
	selectHtml = $('#selectHtml').html();
	createModel("#createProblems", "문제집 등록", addajax);
 	$('select').formSelect();
}

function printAllContent(id, recomId, count){
	selectHtml = $('#selectHtml').html();
	readComment(recomId);
	
	$('#readRecomID').html(recomId);
	$('#readProblems').html($(id+' .readProblem').html());
	$('#readTags').html($(id+' .readTag').html());
	$('#readContents').html($(id+' .readContent').html());
	$('#readRecommends').html($(id+' .readRecommend').html());
	$('#readDifficulties').html($(id+' .readDifficulty').html());
	//$('#commentCount').html($(id+' .readCommentCount').html());
	$("#commentCount").text(count);
	
	$('#updateRecomID').html(recomId);
	$('input[name=updateTitle]').attr('value',$(id+' .readTitle').text());
	//$('.sweet-modal-content #updateTitle').val($(id+' .readTitle').text());
	$('#updateContents').html($(id+' .readContent').html());
	$('#updateTags').text($(id+' .readTag').text());
	$('#updateProblems').html($(id+' .readProblem').html());
	
	var d = jQuery($(id+' .readDifficulty').html()).attr("alt");
	if(d != 0) {
		//jQuery버전 1.6 이하 일때 아래코드로, 아니라면 $("#ud"+d).prop("checked", true);
		$("#ud"+d).attr('checked', 'checked');
		//$("input:radio[name='updateDifficulty']:radio[value=\'" + d + "\']").prop('checked', true); 
		//document.getElementsByName("updateDifficulty")[d-1].checked;
	}
	
	//updateConfirmSite
	updateInsertProblems($(id+' .readProblem').text());
	
	rudModel("#readRecommendProblem", "#updateRecommendProblem", $(id+' .readTitle').html(), $(id+' .readTitle').html(), updateAjax, deleteAjax);
	$('select').formSelect();
}

function updateInsertProblems(data){
	
	var dataSplit = data.split('\n');
	var cnt=0;
	var result = "";
	var data2 = [];
	var pName = [];
	var sName = [];
	
	for(var i in dataSplit){
		dataSplit[i] = dataSplit[i].trim();
		dataSplit[i] = dataSplit[i].replaceAll(' ', ''); 
		
		if(dataSplit[i] === '') continue;
		else data2.push(dataSplit[i]);
	}
	
	for(var i in data2){
		if(i%2==0) sName.push(data2[i]);
		else pName.push(data2[i]);
	}
	
	for(var i in pName){
		//input name에 site id필요!!!
		result += '<div id = "updateConfirmProblemValue'+count+'" onClick="deleteThis(\'updateConfirmProblemValue'+count+'\')"><input disabled name="1" value="'+pName[i]+' ('+sName[i]+')" id="updateLast_name disabled" type="text" class="updateConfirmProblem validate"/></div>';
		count++;
	}
	
	$('.sweet-modal-content #updateConfirmSite').html(result);
	$('#updateConfirmSite').html(result);
};


function addComment() {	
	var userID = $("input[name='writer']").val();
	var recomID = $("input[name='recomID']").val();

	if (confirm("댓글을 추가하시겠습니까?")) {
		$.ajax({
			url : "recommendProblem/addComment",
			type : "POST",
			async : false,
			data : {
				userID : userID,
				recomID : recomID,
				content : $('.sweet-modal-content #comment-textarea').val()
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
			recomID : recomID,
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

function resetContent() {
	$('#createProblems #confirmSite').html("");
	$('#selectHtml').html(selectHtml);
	$('.sweet-modal-content .chip').remove();
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
	var recomID = $('.sweet-modal-content #updateRecomID').val(); 
	var title = $('.sweet-modal-content #updateTitle').val(); 
	var content = $('.sweet-modal-content #updateContents').val();
	var tag = [];
	var difficulty_cnt = document.getElementsByName("updateDifficulty").length;
	var siteId = [];
	var problem = [];
	var link = [];
	
	console.log(recomID);
	
	$('.sweet-modal-content .updateConfirmProblem').each(function(){
		
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
	
	var tag_data= $('.sweet-modal-content #updateProblemTag').material_chip('data');
	for(var i=0; i<tag_data.length; i++) {
		tag.push(tag_data[i].tag);
	}
	
	for(var i=0;i<difficulty_cnt;i++) {
		if(document.getElementsByName("updateDifficulty")[i].checked == true)
			var difficulty = document.getElementsByName("updateDifficulty")[i].value;
	}
	
	$.ajax({
		url: "./recommendProblem/updateRecomProblem",
		type: "POST",
		async: false,
		data: {
			"recomID": recomID, "title":title, "difficulty":difficulty, "tag":tag, "content":content
			/*"siteId":siteId, "problem":problem, "link":link, */
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

count=0;
function updateProblems(){
	
	var siteName = $(".sweet-modal-content #updateSiteName option:selected").text();
	var siteId = $('.sweet-modal-content #updateSiteName').val();
	console.log("siteId: "+siteId);
	var site = $(".sweet-modal-content #updateSiteName option:selected").val();
	var value = $(".sweet-modal-content #updateConfirmProblems").val();
	console.log(value);
	var valueSplit = value.split(',');
	var data = $('.sweet-modal-content #updateConfirmSite').html();
	for(var i in valueSplit){
		data += '<div id = "updateConfirmProblemValue'+count+'" onClick="deleteThis(\'updateConfirmProblemValue'+count+'\')"><input disabled name="'+siteId+'" value="'+valueSplit[i]+' ('+siteName+')" id="updateLast_name disabled" type="text" class="updateConfirmProblem validate"/></div>';
		count++;
	}
	$('.sweet-modal-content #updateConfirmSite').html(data);
	$('#updateConfirmSite').html(data);
	$(".sweet-modal-content #updateConfirmProblems").val("");
};

function addRecomCount(){
	var recomID = $('#readRecomID').html();
	
	console.log(recomID);

}
