<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel="stylesheet" href="./resources/css/home.css?asd" />
<link rel="stylesheet" href="./resources/css/solvedProblem.css?asd" />

<script src="./resources/js/home.js?ver=2"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
 
<div class="section no-pad-bot">
	<div id="myInfo" class="container center">
		<br> <br>
		<h3 class="main-title">혼자서는 힘든 코딩 연습, 친구들과 함께 해보세요!</h3>
		<h5 class="sub-title">매일 푼 문제를 등록하고 설정한 목표를 달성해보세요.</h5>
		<br>
		<c:choose>
			<c:when test = "${!empty userID}">
				<div class="row center">	
					<button id="register-button" class="probBtn" onclick="callModal()">문제 등록하러 가기 ></button>
				</div>				
			</c:when>
			<c:otherwise>
				<div class="row center">	
					<button  class="probBtn" onclick="login()">문제 등록하러 가기 ></button>
				</div>				
			</c:otherwise>
		</c:choose>	
		<br> <br>
	</div>
</div>

<c:forEach items="${ranksToday}" var="rankToday" varStatus="status">
	<c:if test="${status.first}">
		<c:set var="count" value="${rankToday.cnt}"/>
	</c:if>
	<c:if test="${rankToday.id eq userID && rankToday.cnt eq count}">
		<div class="container center celeb">
			<p class="congraturation">CONGRATURATION</p>
			<p class="congraturation2">${rankToday.nickName}님 오늘의 1등입니다!</p>
		</div>
	</c:if>
	
</c:forEach>

<div id = "first"> 
	<%@ include file="./ajaxContent/homeFirstContent.jsp"%> 
</div> 

 <div id = "second" style="display:none"> 
	<%@ include file="./ajaxContent/homeSecondContent.jsp"%> 
</div> 

<div id = "third" style="display:none"> 
	<%@ include file="./ajaxContent/homeThirdContent.jsp"%> 
</div> 

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
					입력할 때 ,로 구분해주세요!!(띄어쓰기X)</span>
			</div>
			<button type="button" id="add" class="modal_button lighten-1"
				onClick="insertProblems()">추가</button>
		</div>
		<div class="input-field col s10 input-field-custom">
			<label for="last_name">입력한 Problems</label><br>
			<label class="helper-text">문제를 누르면 삭제할 수 있습니다.</label><br><br>
			<div id="confirmSite"></div>
		</div>
	</form>
</div>

<%@ include file="./inc/footer.jsp"%>