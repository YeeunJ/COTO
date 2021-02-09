<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel="stylesheet" href="./resources/css/home.css?asd" />
<link rel="stylesheet" href="./resources/css/solvedProblem.css?asd" />
<script src="./resources/js/home.js"></script>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <meta http-equiv="X-UA-Compatible" content="ie=edge">
 
 <script>

function reponsive(){
	var windowWidth = $( window ).width();
	if(windowWidth < 770) {
		if($("#second").css("display") == "none"){
		    $("#second").show();
		    $("#first").hide();
		}
	} 
}


/* if ($(window).width() < 770 ) {
	if($("#second").css("display") == "none"){

} */
 </script>

<!-- first section start- 문제 등록, 내 정보 -->
<div class="section no-pad-bot">
	<div id="myInfo" class="container center">
		<br> <br>
		<h3 class="main-title">혼자서는 힘든 코딩 연습, 친구들과 함께 해보세요!</h3>
		<h5 class="bold">매일 푼 문제를 등록하고 설정한 목표를 달성해보세요.</h5>
		<br>
		<div class="row center">
			<button id="register-button" class="probBtn" onclick="callModal()">문제 등록하러 가기 ></button>
			<!-- onclick="callModal()" -->
		</div>
		<br> <br>
	</div>
</div>

<div id = "first" > 
<%@ include file="./ajaxContent/homeFirstContent.jsp"%> 
</div> 

 <div id = "second" style="display:none"> 
<%@ include file="./ajaxContent/homeSecondContent.jsp"%> 
</div> 

<div id = "third" style="display:none"> 
<%@ include file="./ajaxContent/homeThirdContent.jsp"%> 
</div> 






<%-- 
 <!-- second section start- 랭킹, 태그, 순위 -->
<div  class="container">
	<div class="section second">
		<div class="row center">
		  <div class="carousel carousel-slider center">
		  <ul>
<!-- 			 <li><a href="#"><i class="material-icons gray-text left" style="margin-top: 20%;">chevron_left</i></a></li>
 -->			 <li><a href="#"><i class="material-icons gray-text right" style="margin-top: 20%;">chevron_right</i></a></li>
		  </ul>
		    <div class="carousel-item gray-text" href="#one!">
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-award"></span>
						<h5 class="small-title">누적 랭킹</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${ranks}" var="rank" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span> ${rank.nickName} [${rank.cnt}문제]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-tag"></span>
						<h5 class="small-title">인기 태그</h5>
						<ul class="fs-18 list">
							<c:forEach items="${tags}" var="tag">
								<li class="tag"><span class="bold">#</span>${tag.tag}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-problem"></span>
						<h5 class="position-r small-title">
							문제 순위<a href="./recommendProblem" class="more">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${problems}" var="problem" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span>${problem.problem} [${problem.site}]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
		    </div>

		    <div class="carousel-item gray-text" href="#two!">
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-award"></span>
						<h5 class="small-title">오늘의 랭킹</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${ranksToday}" var="rankToday" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span> ${rankToday.nickName} [${rankToday.cnt}문제]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-tag"></span>
						<h5 class="position-r small-title">
							새로운 추천 글<a href="./recommendProblem" class="more" style = "right: -29px;">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${recoms}" var="recom" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span>${recom.title} </li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-problem"></span>
						<h5 class="position-r small-title">
							새로운 문제<a href="./problemList" class="more" style = "right: -13px;">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${recentProblems}" var="recentProblem" varStatus="status">
								<li class="ranking" style = "font-size: 14px;"><span class="bold" style = "font-size: 14px;">${status.count}.</span>${recentProblem.name}<br>[${recentProblem.siteName}]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
		    </div>
		    
<!-- 			<ul class="indicators">
				<li class="indicator-item gray active"><a href="#!"></a></li>
				<li class="indicator-item gray active"><a href="#!"></a></li>
			</ul> -->
		    
		  </div>

		</div>
	</div>
	<br>
</div> 
<!-- second finish --> --%>

<div id="createProblem" class="container" style="display:none">
	<form class="col s12">
		<div class="row">
			<div id="selectHtml" class="input-field col s4">
				<select id="siteName" required>
					<optgroup label="코딩사이트 선택">
						<c:forEach items="${CodingSite}" var="site">
							<option value="${ site.id }">${ site.siteName }</option>
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
			<button type="button" id="add" class="modal_button lighten-1"
				onClick="insertProblems()">추가</button>
		</div>
		<div class="input-field col s10">
			<label for="last_name">입력한 Problems</label><br>
			<label class="helper-text">문제를 누르면 삭제할 수 있습니다.</label><br><br>
			<div id="confirmSite"></div>
		</div>
	</form>
</div>

<br>


<%@ include file="./inc/footer.jsp"%>
<%-- <%@ include file="./solvedProblem.jsp"%>
 --%>
 
