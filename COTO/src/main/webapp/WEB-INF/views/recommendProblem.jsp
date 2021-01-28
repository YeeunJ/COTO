<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./inc/header.jsp"%>

<link rel="stylesheet" href="./resources/css/solvedProblem.css?a" />
<link href="./resources/css/recommendProblem.css?as" rel="stylesheet">
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

.content {
	top: 15%;
	left: 50px;
	width: 100%;
	bottom: 100px;
	color: #666666;
	font-weight: 800;
	position: absolute;
}

fieldset {
	padding: 0 0 0 40px;
	background: #fff;
	border: none;
}

.search {
	display: flex;
	float: right;
	width: 30% !important;
}

.search_bt {
	all: unset;
}

.search-part {
	margin-bottom: 5%;
}

.top-bar {
	display: flex;
}

.custom-button {
	background-color: white;
	margin-top: 10%; ! important;
	border: 1px solid #666666;
	height: 31px;
	border-radius: 20px;
	float: left;
}

.custom-button:hover {
	background-color: #e69138ff;
	color: white;
	border: 1px solid #e69138ff;
}

.order {
	width: 13%;
	float: right;
}

.title {
	border: 0;
	background-color: Transparent;
}

.comment {
	border-bottom: 1px solid #bdbdbd;
}
</style>

<div class="container">
	<div id="recommend">
		<div class="content">
			<h4>문제 추천</h4>
			<p>자신의 문제집을 등록하고, 사용자들과 공유해 보세요!</p>
		</div>
	</div>

	<div class="search-part">
		<fieldset class="search">
			<input class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset>
	</div>

	<div class="top-bar">
		<button class="input-field custom-button" onclick="createProblems()">문제
			추천집 만들기</button>
		<div class="input-field col s12 order">
			<select>
				<option value="" disabled selected>정렬</option>
				<option value="1">난이도순</option>
				<option value="2">제목순</option>
				<option value="3">추천순</option>
				<option value="4">최신순</option>
			</select>
		</div>
	</div>


	<div class="table">
		<div class="tableRow">
			<span class="tableCell th1">No.</span> <span class="tableCell th4">제목</span>
			<span class="tableCell th2">등록자</span> <span class="tableCell th1">난이도</span>
			<span class="tableCell th1">추천수</span> <span class="tableCell th1">댓글수</span>
		</div>
		<c:forEach items="${recoms}" var="recoms" varStatus="status">
			<c:set var="count" value="0" />
			<c:forEach items="${commentCount}" var="c">
				<c:if test="${ c.recomID eq recoms.id }">
					<c:set var="count" value="${ c.count }" />
				</c:if>
			</c:forEach>
			<div class="tableRow" id="recoms${recoms.id}"
				onclick="printAllContent('#recoms${recoms.id}', ${recoms.id})">
				<span class="tableCell td1">${status.count}</span> <span
					class="tableCell td4 readTitle">${recoms.title}</span> <span
					class="tableCell td2">${recoms.nickname}</span> <span
					class="tableCell td1"></span> <span class="tableCell td1"></span> <span
					class="tableCell td1">${ count }</span> <span
					class="tableCell td15"></span> <span
					class="tableCell td15 readRecommend">${recoms.recomCount}</span> <span
					class="readProblem" style="display: none;">10문제</span> <span
					class="readTag" style="display: none;">정렬</span> <span
					class="readContent" style="display: none;">${recoms.content}</span>
			</div>
		</c:forEach>

	</div>
	<br> <br>
	
	<!-- insert -->
	<%-- <div class="container">
		<form class="col s12">
			<div class="input-field col s10">
				<input id="title" type="text" class="validate"> <label
					for="title">제목</label> <span class="helper-text">문제집의 제목을
					입력해주세요.</span>
			</div>

			<div class="row">
				<div class="input-field col s4">
					<select id="siteName" required>
						<optgroup label="코딩사이트 선택">
							<c:forEach items="${CodingSite}" var="site">
								<option value="${site.id}">${site.siteName}</option>
							</c:forEach>
						</optgroup>
						<optgroup label="링크로 입력">
							<option value="0">링크로 입력</option>
						</optgroup>
					</select> <label>코딩사이트 선택</label> <span class="helper-text">코딩 사이트를
						선택해서 입력하거나 링크로 입력할 수 있습니다.</span>
				</div>
				<div class="input-field col s6">
					<input id="problems" type="text" class="validate"> <label
						for="problems">Problems</label> <span class="helper-text">문제들을
						입력할 때 ,로 구분해주세요!!</span>
				</div>
				<button type="button" id="add"
					class="modal_button waves-effect waves-light btn green"
					onClick="insertProblems()">추가</button>
			</div>
			<div class="input-field col s10">
				<label for="last_name">입력한 Problems</label> <br> <br>
				<div id="confirmSite"></div>
			</div>

			<div class="input-field col s10">
				<!-- <span>난이도: </span>
							<input type="radio" name="difficulty" value="1" checked/>1
							<input type="radio" name="difficulty" value="2" />2
							<input type="radio" name="difficulty" value="3" />3
							<input type="radio" name="difficulty" value="4" />4
							<input type="radio" name="difficulty" value="5" />5 -->
				<input id="difficulty" type="text" class="validate"> <label
					for="difficulty">난이도</label><span class="helper-text">1~5까지만
					입력가능합니다.</span>
			</div>

			<div class="row">
				<div class="input-field col s10">
					<input id="tags" type="text" class="validate"> <label
						for="tags">태그</label> <span class="helper-text">태그들을 입력할 때
						,로 구분해주세요!!</span>
				</div>
				<button type="button" id="addTag"
					class="modal_button waves-effect waves-light btn green"
					onClick="insertTags()">추가</button>
			</div>
			<div class="input-field col s10">
				<label for="last_tag">입력한 Tags</label> <br> <br>
				<div id="confirmTag"></div>
			</div>

			<div class="input-field col s10">
				<textarea id="content" name="content" rows="5"></textarea>
				<span class="helper-text">추천 문제집에 대한 내용을 입력해주세요.</span>
			</div>
		</form>
		<button id="createRecomProblem"
			class="waves-effect waves-light btn green">만들기</button>
	</div> --%>

	<!-- read -->
	<div id="readRecommendProblem" hidden>
		<div class="container">
			<div class="col s12">
				<span>문제 : </span><span id="problems"></span> <span>태그 : </span><span
					id="tags"></span> <span>내용 : </span><span id="contents"></span> <span>추천
					: </span><span id="recommends"></span><span>&nbsp;&nbsp;&nbsp;</span>
				<button class="waves-effect waves-light btn green">추천</button>

				<div>
					<b>댓글</b>
					<p class="comment">좋은 문제 감사합니다~~</p>
				</div>

			</div>
		</div>
	</div>
	<br> <br>

	<!-- update -->
	<!-- <div id="updateRecommendProblem">
					<div class = "container">
					</div>
				</div>
				<br><br> -->
</div>


<!-- 문제집 등록 모달 -->
<div id="createProblems" class="container" style="display: none;">
	<form class="col s12">
		<p class="title">추천 문제집 제목</p>
		<input id="title" type="text" placeholder="제목을 입력해주세요."></input>

		<p class="title">추천 문제 등록</p>
		<div class="row">
			<div class="input-field col s4">
				<select id="siteName" required>
					<optgroup label="코딩사이트 선택">
						<c:forEach items="${CodingSite}" var="site">
							<option value="${site.id}">${site.siteName}</option>
						</c:forEach>
					</optgroup>
					<optgroup label="링크로 입력">
						<option value="0">링크로 입력</option>
					</optgroup>
				</select>
				<label>코딩사이트 선택</label> 
				<span class="helper-text">코딩 사이트를 선택해서 입력하거나 링크로 입력할 수 있습니다.</span>
			</div>
			<div class="input-field col s6">
				<input id="problems" type="text" class="validate"> 
				<label for="problems">Problems</label> 
				<span class="helper-text">문제들을 입력할 때 ,로 구분해주세요!!</span>
			</div>
			<button type="button" id="add" class="modal_button lighten-1" onClick="insertProblems()">추가</button>
		</div>
		<div class="input-field col s10">
			<label for="last_name">입력한 Problems</label> <br> <br>
			<div id="confirmSite"></div>
		</div>
		
		<p class="title">추천 문제집 난이도</p>
		<div class="input-field col s10">
			<!-- <input type="radio" name="difficulty" value="1" checked/>1
			<input type="radio" name="difficulty" value="2" />2
			<input type="radio" name="difficulty" value="3" />3
			<input type="radio" name="difficulty" value="4" />4
			<input type="radio" name="difficulty" value="5" />5 -->
			<input id="difficulty" type="text" class="validate"> 
			<span class="helper-text">1~5까지만 입력가능합니다.</span>
		</div>
		
		<p class="title">추천 문제집 태그</p>
		<div class="row">
			<div class="input-field col s10">
				<input id="tags" type="text" class="validate"> 
				<span class="helper-text">태그들을 입력할 때 ,로 구분해주세요!!</span>
			</div>
			<button type="button" id="addTag" class="modal_button lighten-1" onClick="insertTags()">추가</button>
		</div>
		<div class="input-field col s10">
			<label for="last_tag">입력한 Tags</label> <br> <br>
			<div id="confirmTag"></div>
		</div>
		
		<p class="title">추천 문제집 설명</p>
		<textarea id="content" name="content" rows="5"></textarea>

	</form>
</div>


<!-- 세부 정보 모달 -->
<div id="recomDetailModal" style="display: none;">

	<div id="detailRecom">
		<div>
			<div>
				<p class="title">추천 문제 설명</p>
				<p>배열에 대해 익히고, 재귀 함수를 익힐 수 있는 문제집입니다. 한번씩 풀어보세요~~</p>
			</div>

			<div>
				<p class="title desc">추천 문제</p>
				<div id="readProblems">
					<div class="sitetitle">백준</div>
					<div>
						<p>122번</p>
						<p>1256번</p>
						<p>129번</p>
						<p>3번</p>
						<p>2980번</p>
					</div>
					<div class="sitetitle">leetcode</div>
					<div>
						<p>별짓기</p>
						<p>하노이탑</p>
						<p>9002번</p>
					</div>
				</div>
			</div>
		</div>
		<div>
			<div class="details">
				<span class="like-icon icon"></span><span class="bold">34</span><span></span>
				<span class="comment-icon icon"></span><span class="bold">18</span><span></span>
				<span class="diff-icon icon">3</span>
			</div>
			<div id="commentDetail">
				<div class="comment-add">
					<textarea id="comment-textarea" placeholder="댓글을 달아주세요."></textarea>
					<button id="addComment" class="modal_button add-btn">등록</button>
				</div>
				<div id="modal-comment" class="wrapper">
					<%-- 					<%@ include file="./ajaxContent/recomCommentContent.jsp"%>
 --%>
				</div>

			</div>

		</div>
	</div>

</div>

<%@ include file="./inc/footer.jsp"%>

<script>
$('#createRecomProblem').click(function() {
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
	
	$('.tag').each(function(){
		var tagVal = $(this).val();
		console.log(tagVal);
		
		tag.push(tagVal);
	});

	for(var i=0 ; i<siteId.length ; i++) {
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
					$('#modal-comment').append(data);
				},
				error : function(request, status, error) {
					console.log("code:" + request.status + "\n"
							+ "message:" + request.responseText + "\n"
							+ "error:" + error);

	
	$('#addComment').click(
			function() {
				if (confirm("댓글을 추가하시겠습니까?")) {
					$.ajax({
						url : "recommendProblem/addComment",
						type : "POST",
						async : false,
						data : {
							recomID : 2,
							content : $('#content').val()
						},
						success : function(data) {
							$('#comment').append(data);
						},
						error : function(request, status, error) {
							console.log("code:" + request.status + "\n"
									+ "message:" + request.responseText + "\n"
									+ "error:" + error);
						}
					});
				}
			});
		}
	});
	
</script>
