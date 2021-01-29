// 정렬
//document.addEventListener('DOMContentLoaded', function() {
//	var elems = document.querySelectorAll('select');
//    //var instances = M.FormSelect.init(elems, options);
//});

// Or with jQuery
$(document).ready(function(){
    $('select').formSelect();
});


/*$(document).ready(function() {
	$('#register-button').on('click', function() {
		 createModel("#registerRecommendProblem", "문제집 등록", addAjax);
		 $('select').formSelect();
	});
});*/

function createProblems() {
	createModel("#createProblems", "문제집 등록", addAjax);
}

function printAllContent(id, recomId, count){
	//alert(recomId);
	readComment(recomId);
	$('#problems').html($(id+' .readProblem').html());
	$('#tags').html($(id+' .readTag').html());
	$('#contents').html($(id+' .readContent').html());
	$('#recommends').html($(id+' .readRecommend').html());
	
	$("#commentCount").text(count);
	
	rudModel("#recomDetailModal", "#updateRecommendProblem", $(id+' .readTitle').html(), updateAjax, deleteAjax);
//	rudModel("#readRecommendProblem", "#updateRecommendProblem", $(id+' .readTitle').html(), updateAjax, deleteAjax);
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


function addAjax (){
	console.log("success");
	//ajax 넣는 함수
}

function updateAjax (){
	console.log("update!!");
	//ajax 넣는 함수
}

function deleteAjax (){
	console.log("update!!");
	//ajax 넣는 함수
}

function deleteThis(id){
	var allid = "#"+id;
	$(allid).remove();
}

$('#createProblem').click(function() {
	var probs;
	var siteId = [];
	var problem = [];
	var link = [];
	var title = document.getElementById("title").value;
	var difficulty = document.getElementById("difficulty").value;
	var tag = [];
	var content = document.getElementById("content").value;
	
	$('.problem').each(function(){
		
		var s_id = 0;
		var l = null;
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
		
		/* var p = {
			siteID: siteId,
			problem: valueSplit[0],
			link: link,
		}
		probs.push(p); */
		
		console.log($(this).val());
	});
	
	probs = {"siteId":siteId, "problem":problem, "link":link};
	
	var data= $('#problemTag').material_chip('data');
	for(var i=0 ; i<data.length ; i++) {
		tag.push(data[i].tag);
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
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
});

var count=0;
function insertProblems(){
	var siteId = $('#siteName').val();
	var array = ['', '백준', 'leetcode', 'SW expert academy', 'oncoder', 'goorm', 'leetcode[database]', 'link'];
	var site = $("#siteName option:selected").val();
	var value = document.getElementById("problems").value;
	var valueSplit = value.split(',');
	var data = $('#confirmSite').html();
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><input disabled name="'+siteId+'" value="'+valueSplit[i]+' ('+array[site]+')" id="last_name disabled" type="text" class="problem validate"/></div>';
		count++;
	}
	$('#confirmSite').html(data);
	document.getElementById("problems").value = "";
};
	
count=0;
function insertTags(){
	var value = document.getElementById("tags").value;
	var valueSplit = value.split(',');
	var data = $('#confirmTag').html();
	for(var i in valueSplit){
		valueSplit[i] = valueSplit[i].trim();
		data += '<div id = "confirmTagValue'+count+'" onClick="deleteThis(\'confirmTagValue'+count+'\')"><input disabled name="'+valueSplit[i]+'" value="'+valueSplit[i]+'" id="last_tag disabled" type="text" class="tag validate"/></div>';
		count++;
	}
	$('#confirmTag').html(data);
	document.getElementById("tags").value = "";
};