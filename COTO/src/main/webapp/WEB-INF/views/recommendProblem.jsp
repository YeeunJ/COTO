<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- <link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css"> -->
<link rel="stylesheet" href="./resources/css/solvedProblem.css?a" />
<link href="./resources/css/recommendProblem.css?asd" rel="stylesheet">
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
.alignCenter {
	text-align: center;
}
</style>

<div id="SiteContainer" class="container">
	<div id="recommend">
		<div class="content">
			<h4>문제 추천</h4>
			<p>자신의 문제집을 등록하고, 사용자들과 공유해 보세요!</p>
		</div>
	</div>

	<div class="top-bar">
		<fieldset class="search">
			<input id="searchValue" class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button id="searchButton" class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset>
		<button class="input-field custom-button" onclick="callModal()">글쓰기</button>
		<div class="col order">
			<select id="orderValue">
				<option value="recom.regdate desc" disabled selected>정렬</option>
				<option value="recom.difficulty desc">난이도순</option>
				<option value="recom.title">제목순</option>
				<option value="recom.recomCount desc">추천순</option>
				<option value="recom.regdate desc">최신순</option>
			</select>
		</div>
	</div>

	<%-- <div class="table" id="recommendContent">
		<%@ include file="./ajaxContent/recommendContent.jsp"%>
	</div> 
	<br> <br>--%>
	
	<div id="pageajaxContent">
	<%@ include file="./ajaxContent/recommendContent.jsp"%>
	</div>
	
	<%-- <%@ include file="./inc/pagination.jsp"%> --%>
	
</div>

<!-- 문제집 등록 모달 -->
<div id="createProblems" class="container" style="display:none;">
	<form class="col s12">
		<p class="title" style="display:inline-block">추천 문제집 제목</p><p class = "red-text" style="display:inline-block; padding-bottom: 10px;">&nbsp;*</p>
		<input id="createTitle" type="text" placeholder="제목을 입력해주세요." required></input>

		<p class="title">추천 문제 등록</p>
		<div class="row">
			<div id="selectHtml" class="input-field col s4">
				<select id="siteName" required>
					<optgroup label="코딩사이트 선택">
						<c:forEach items="${codingSite}" var="site">
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
			<div class="recom-confirmSite" id="confirmSite"></div>
		</div>
		
		<p class="title">추천 문제집 난이도</p>
		<div class="row">
			<div class="input-field col s2">
				<p>
					<input type="radio" name="difficulty" id="d1" value="1"/>
					<label for="d1" class="diffCont">1</label>
				</p>
			</div>
			<div class="input-field col s2">
				<p>
					<input type="radio" name="difficulty" id="d2" value="2" class="radioMrg"/>
					<label for="d2" class="diffCont">2</label>
				</p>
			</div>
			<div class="input-field col s2">
				<p>
					<input type="radio" name="difficulty" id="d3" value="3" class="radioMrg"/>
					<label for="d3" class="diffCont">3</label>
				</p>
			</div>
			<div class="input-field col s2">
				<p>
					<input type="radio" name="difficulty" id="d4" value="4" class="radioMrg"/>
					<label for="d4" class="diffCont">4</label>
				</p>
			</div>
			<div class="input-field col s2">
				<p>
					<input type="radio" name="difficulty" id="d5" value="5" class="radioMrg"/>
					<label for="d5" class="diffCont">5</label>
				</p>
			</div>
			<div class="input-field col s2">
				<p>
					<input type="radio" name="difficulty" id="d0" value="0" class="radioMrg" /> 
					<label for="d0" class="diffCont">설정 안함</label>
				</p>
			</div>
			
		</div>
		
		<p class="title">추천 문제집 태그</p>
		<div id="problemTag" class="chips chips-placeholder input-field">
		</div>
		
		<p class="title">추천 문제집 설명</p>
		<textarea id="createContent" rows="5"></textarea>

	</form>
</div>

<div id="modalContent">
<%@ include file="./ajaxContent/recomDetailModal.jsp"%>
</div>

<%@ include file="./inc/footer.jsp"%>
