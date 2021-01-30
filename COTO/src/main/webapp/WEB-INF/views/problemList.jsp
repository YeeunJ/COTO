<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./inc/header.jsp"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
<link rel="stylesheet" href="./resources/css/solvedProblem.css?a" />
<link href="./resources/css/recommendProblem.css?qwe" rel="stylesheet">
<script src="./resources/js/recommendProblem.js"></script>

<style>
#recommend {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

#recommend:before {
	content: "";
	background-image: url("./resources/img/recommendimg.jpg");
	background-size: cover;
	top: 0;
	left: 0;
	right: 0px;
	bottom: 0px;
	position: absolute;
	opacity: 0.4;
	z-index: -1;
}

.readTagChips {
	height: 32px;
	font-size: 15px;
	font-weight: 500;
    color: rgba(0, 0, 0, 0.6);
    line-height: 32px;
	padding: 8px 12px;
    border-radius: 16px;
    background-color: #e4e4e4;
    margin-right: 2%;
}
</style>

<div class="container">
	<div id="recommend">
		<div class="content">
			<h4>문제 리스트</h4>
			<p>다른 사람들이 많이 푼 문제를 확인하고 풀어보세요!</p>
		</div>
	</div>


	<div class="table center" id="problemContent">
		<%@ include file="./ajaxContent/problemListContent.jsp"%>
	</div>
	<br> <br>
</div>

<!-- 세부 정보 모달 -->
<div id="readRecommendProblem" style="display:none;">

	<div id="detailRecom">
		<div>
			<div>
				<p class="title">추천 문제 설명</p>
				<div class="readBox">
					<span id="readContents" ></span>
				</div>
				<br><br>
			</div>
			
			<div>
				<p class="title">추천 문제 난이도</p>
				<%-- <img style="width: 50px;" alt="" src="./resources/img/difficulty${}.png"> --%>
				<div class="readBox">
					<span id="readDifficulties"></span>
				</div>
				<br><br>
			</div>
			
			<div>
				<p class="title">추천 문제 태그</p>
				<div id="readTags"></div>
				<br><br>
			</div>

			<div>
				<p class="title desc">추천 문제</p>
				<div id="readProblems" class="readBox"></div>
			</div>
		</div>
		<div>
			<div class="details">
				<span class="like-icon icon"></span><span id="readRecommends" class="bold"></span><span></span>
				<span class="comment-icon icon"></span><span id="commentCount" class="bold"></span><span></span>
				<!-- <span class="diff-icon icon">3</span> -->
			</div>
			<div id="commentDetail">
				<div class="comment-add">
					<textarea id="comment-textarea" placeholder="댓글을 달아주세요."></textarea>
					<button id="addComment" class="modal_button add-btn" onclick="addComment()">등록</button>
				</div>
				<div id="modal-comment" class="wrapper">
					<%-- <%@ include file="./ajaxContent/recomCommentContent.jsp"%> --%>
				</div>

			</div>

		</div>
	</div>
</div>


<%@ include file="./inc/footer.jsp"%>

<script>
$("#problemTag").click(function(){
	var v = document.getElementsByClassName("chips");
	alert(v.length);
	
	var att = document.createAttribute("onclick");
	att.value="btnClick()";
	v.setAttributeNode(att);
});

function btnClick(){ alert("Click!"); }

function chipTag(){
	$('.sweet-modal-content .chips').material_chip();
	$('.sweet-modal-content .chips-placeholder').material_chip({
	    placeholder: '+tag',
	    secondaryPlaceholder: '+Tag',
	});
}

function closeTag(){
	alert("hello");
};

$(document).on('click', '.sweet-modal-content .close', function(){
	alert("1");
});

$('.chips').material_chip();
$('.chips-placeholder').material_chip({
    placeholder: '+tag',
    secondaryPlaceholder: '+Tag',
});

$('#tagAdd').click(function(){
	var data= $('#problemTag').material_chip('data');
	var tag = new Array();
	for(var i=0 ; i<data.length ; i++) {
		tag.push(data[i].tag);
	}
	$.ajax({
        url : './recommendProblem/addTag',
        type: 'POST',
        data: {
        	"tag":tag
        },
        success: function(data) {
            alert('리스트에 추가하였습니다.');
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
	
});
	

	$("#addComment").click(function() {
		var userID = $("input[name='writer']").val();
		var recomID = $("input[name='recomID']").val();
		alert(userID + "/" + recomID);
		
		if (confirm("댓글을 추가하시겠습니까?")) {
			$.ajax({
				url : "recommendProblem/addComment",
				type : "POST",
				async : false,
				data : {
					userID : userID,
					recomID : recomID,
					content : $('#comment-textarea').val()
				},
				success : function(data) {
					$('#modal-comment').html(data);
				},
				error : function(request, status, error) {
					console.log("code:" + request.status + "\n"
							+ "message:" + request.responseText + "\n"
							+ "error:" + error);
				}
			});
		}
	});
	
	$(".sweet-modal-content #addComment").click(function() {
		var userID = $("input[name='writer']").val();
		var recomID = $("input[name='recomID']").val();
		alert(userID + "/" + recomID);
		
		if (confirm("댓글을 추가하시겠습니까?")) {
			$.ajax({
				url : "recommendProblem/addComment",
				type : "POST",
				async : false,
				data : {
					userID : userID,
					recomID : recomID,
					content : $('#comment-textarea').val()
				},
				success : function(data) {
					$('#modal-comment').html(data);
				},
				error : function(request, status, error) {
					console.log("code:" + request.status + "\n"
							+ "message:" + request.responseText + "\n"
							+ "error:" + error);
				}
			});
		}
	});

</script>
