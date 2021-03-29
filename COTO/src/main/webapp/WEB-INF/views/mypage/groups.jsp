<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="com.walab.coding.model.UserDTO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<link href="../resources/css/problems.css" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/myGroup.css?asd" />

<script src="../resources/js/myGroup.js"></script>

<div id="SiteContainer" class="container">
	
	<div id="group">
		<div class="content">
			<h4>내 그룹</h4>
			<p>나의 그룹을 확인해 보세요!</p>
		</div>
	</div>
	<!-- Content Row -->
	<div>
		<br>
		<button class="input-field custom-button" onclick="">그룹 관리하기</button>
		<h5 class="font-color">내가 속한 그룹</h5>

<!-- 		<fieldset class="search" style="float: right;">
			<input id="searchValue" class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button id="searchButton" class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset> -->
		
		<div class="table" id="problemsContent">
			<%@ include file="../ajaxContent/myGroupContent.jsp"%>
		</div>
						
		<div id="adminGroupContent">
			<%@ include file="../ajaxContent/adminGroupContent.jsp"%>
		</div>
		
		
		
		
		
	</div>
</div>

<div id="createGroup" class="container" style="display:none;">
	<form class="col s12">
		<p class="title" style="display:inline-block">그룹 이름</p><p class = "red-text" style="display:inline-block; padding-bottom: 10px;">&nbsp;*</p>
		<input id="groupTitle" type="text" placeholder="그룹명을 입력해주세요." required></input>

		<p class="title">사용자 등록</p>
		<div id="groupUsers" class="chips chips-placeholder input-field">
		</div>
				
		<p class="title">그룹 목표</p>
		<input id="groupGoal" type="text" placeholder="그룹 목표를 입력해주세요." required></input>
		
		<p class="title">그룹 유지 기간</p>
		<span><input class="groupDate" type="date" id="startDate" />   ~   <input class="groupDate" type="date" id="endDate" /></span>
		
		<p class="title">그룹 추가 설명</p>
		<textarea id="groupDesc" rows="5"></textarea>
		
		<p class="title">문제 리스트</p>
		<span><input class="groupDate" type="date" id="probStartDate" />   ~   <input class="groupDate" type="date" id="probEndDate" /></span>
		
		<div class="row">
			<div id="selectHtml" class="input-field col s4">
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
			<div class="recom-confirmSite" id="confirmSite"></div>
		</div>
		
	</form>
</div>



<style>
#group {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

#group:before {
	content: "";
	background-image: url("../resources/img/problem.png");
	background-size: cover;
	top: 0;
	left: 0;
	right: 0px;
	bottom: 0px;
	position: absolute;
	opacity: 0.4;
	z-index: -1;
}

.tc {
	word-break: break-all;
	text-align: left !important;
	border-left: 1px solid #DDD !important;
	border-right: 1px solid #DDD !important;
}

.probname {
	font-size: 0.8rem;
}
</style>

<%@ include file="../inc/footer.jsp"%>
