$(document).ready(function(){
    $('#orderValue').formSelect();
    $('#searchButton').on('click', function() {
		search();
	});
	$('#orderValue').on('change', function() {
		search();
	});
	search(1);
});

var selectHtml="";

//comment create
function addComment() {	
	var recomID = $('#readRecomID').html();

	if (confirm("댓글을 추가하시겠습니까?")) {
		$.ajax({
			url : "../mypage/problems/addComment",
			type : "POST",
			async : false,
			data : {
				recomID : recomID,
				content : $('.sweet-modal-content #comment-textarea').val()
			},
			success : function(data) {
				$('.sweet-modal-content #recomCountCommentContent').html(data);
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

//read modal에서 recom count
function addRecomCount(){
	$.ajax({
		url: "../mypage/problems/addRecomCount",
		type: "POST",
		async: false,
		data: {
			recomID:$('#readRecomID').html()
		},
		success: function(data){
			$('.sweet-modal-content #recomCountCommentContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

//문제 list에서 클릭 시, detail modal 불러오기
function printCartAllContent(recomId){
	selectHtml = $('#selectHtml').html();
	
	readDetailModalContent(recomId);
}

//detail modal에 들어갈 내용 read
function readDetailModalContent(recomID, count) {
	var title;
	var logID;
	var uID;
	var adminID;
	
	$.ajax({
		url : "../mypage/problems/readModalInfo",
		type : "POST",
		async : false,
		data : {
			recomID : recomID,
		},
		success : function(data) {
			var dataSplit = data.split("\n");
			for(var i=0;i<dataSplit.length;i++) {
				dataSplit[i] = dataSplit[i].trim();

				if(dataSplit[i].indexOf("readTitle") != -1) title = $( dataSplit[i] ).text(); //console.log(dataSplit[i]);
				else if(dataSplit[i].indexOf("readLoginID") != -1) logID = $( dataSplit[i] ).text();
				else if(dataSplit[i].indexOf("readUserID") != -1) uID = $( dataSplit[i] ).text();
				else if(dataSplit[i].indexOf("readAdminID") != -1) adminID = $( dataSplit[i] ).text();
			}			
			
			$("#modalContent").html(data);
			if(logID == uID || adminID > 0) rudModel("#readRecommendProblem", "#updateRecommendProblem", title, title, updateAjax, deleteAjax, readRecomCartContent);
			else readCartModel("#readRecommendProblem", title, readRecomCartContent);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n"
					+ "message:" + request.responseText + "\n"
					+ "error:" + error);
		}
	});
}

function deleteAjax (){
	$.ajax({
		url: "../mypage/problems/deleteRecomProblem",
		type: "POST",
		async: false,
		data: {
			id:$('#updateRecomID').html()
		},
		success: function(data){
			$('#recomCartContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function readRecomCartContent(){
	$.ajax({
		url: "../mypage/problems/readRecomCartContent",
		type: "POST",
		async: false,
		success: function(data){
			$('#recomCartContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

//read modal에서 recom count
function deleteRecomCount(){
	$.ajax({
		url: "../mypage/problems/deleteRecomCount",
		type: "POST",
		async: false,
		data: {
			recomID:$('#readRecomID').html()
		},
		success: function(data){
			console.log(data);
			$('.sweet-modal-content #recomCountCommentContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}


function addRecomCart(){
	if(confirm("문제집을 장바구니에 담으시겠습니까?")) {
		$.ajax({
			url: "../mypage/problems/addRecomCart",
			type: "POST",
			async: false,
			data: {
				recomID:$('#readRecomID').html()
			},
			success: function(data){
				$('.sweet-modal-content #recomCountCommentContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}

}

function deleteRecomCartinModal(){
	if(confirm("문제집을 장바구니에서 지우시겠습니까?")) {
		$.ajax({
			url: "../mypage/problems/deleteRecomCart",
			type: "POST",
			async: false,
			data: {
				recomID:$('#readRecomID').html()
			},
			success: function(data){
				$('.sweet-modal-content #recomCountCommentContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}

}

function checkProblem(id, name, link){
	$.ajax({
		url : "../recommendProblem/addRecomCheck",
		type : "POST",
		async : false,
		data : {
			rpID : id,
			problemName: name,
			link: link
		},
		success : function(data) {
			console.log(data);
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

function uncheckProblem(id, name, link){
	$.ajax({
		url : "../recommendProblem/deleteRecomCheck",
		type : "POST",
		async : false,
		data : {
			rpID : id,
			problemName: name,
			link: link
		},
		success : function(data) {
			console.log(data);
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
