<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<link href="../resources/css/information.css" rel="stylesheet">
<script src="../resources/js/information.js"></script>

<div id="SiteContainer" class="container">
	<div id="information">
		<div class="content">
			<h4>내 정보</h4>
			<p>내 정보와 목표를 확인하고 수정할 수 있습니다.</p>
		</div>
	</div>
	
	<div class="area">
		<div id="info">
			<br><br>
			<div id="headerButton">
				<div class = "left">
					<h5 class="orange-text" style = "margin: 0;">내 정보</h5>
				</div>
				<div class="right">
					<input type="button" value="수정하기 " onclick="showInfoEdit();hideInfo();" class="mybtn right" />						
				</div>
			</div>
			<br><br>
			<%@ include file="../ajaxContent/informationUserContent.jsp"%>
		</div>

		<div id=infoEdit style="display: none">
			<form:form id="updateInfo" method="post"
				action="information/updateUser">
				<br><br>
				<div id="headerButton">
					<div class = "left">
						<h5 class="orange-text" style = "margin: 0;">내 정보 수정</h5>
					</div>
					<div class="right" id="userInfoBtn">
						<input id="submit" type="submit" value="수정하기 " class="mybtn" /> <input
							id="add" type="button" value="취소하기 "
							onclick="showInfo(); hideInfoEdit()" class="mybtn" />
					</div>
				</div>
				<br><br>
				<%@ include file="../ajaxContent/informationUserUpdateContent.jsp"%>
			</form:form>
		</div>

		<div id="goal">
			<br><br>
			<div id="headerButton">
				<div class="left">
					<h5 class="orange-text" style = "margin: 0;">내 목표</h5>
				</div>
				<div class="right">
					<input type="button" value="수정하기 " onclick="showGoalEdit(); hideGoal();" class="mybtn right" />						
				</div>
			</div>
			<br><br>
			<%@ include file="../ajaxContent/informationGoalContent.jsp"%>
		</div>


		<div id=goalEdit style="display: none">
			<form:form id="updateGoal" method="post"
				action="information/updateGoal">
				<br><br>
				<div id="headerButton">
					<div class="left">
						<h5 class="orange-text" style = "margin: 0;">내 목표 수정</h5>
					</div>
					<div class="right">
						<input id="submit" type="submit" value="수정하기 " class="mybtn" /> 
						<input id="add" type="button" value="취소하기 "
							onclick="showGoal(); hideGoalEdit()" class="mybtn" />
					</div>
				</div>
				<br><br>
				<%@ include file="../ajaxContent/informationGoalUpdateContent.jsp"%>
			</form:form>
		</div>

	</div>
</div>

<style>
#information:before {
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
</style>

<%@ include file="../inc/footer.jsp"%>


