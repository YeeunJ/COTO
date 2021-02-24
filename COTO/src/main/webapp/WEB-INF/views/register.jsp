<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "./inc/logoutHeader.jsp" />

<link rel="stylesheet" href="./resources/css/register.css?aa" />
<script src="./resources/js/register.js?gfet"></script>


<div id="SiteContainer" class="container">
	<div class="register" id=userInfo>
		<h5 class="header center gray-text">초기 정보 등록</h5>
		<br>
		<div id="table">
			<div class="row">
				<span class="cell td1">이름</span> <span class="cell td1"><input
					type="text" name="userName" placeholder="이름을 입력하세요. "  value="${tempUser.name}" readonly/></span>
			</div>
			<div class="row">
				<span class="cell td2">이메일</span> <span class="cell td2"><input
					type="email" name="email" placeholder="이메일을 입력하세요. " value="${tempUser.email}" readonly/> </span>
			</div>
			<div class="row nicknameRow">
				<span class="cell td3">닉네임</span><span class = "red-text">*</span> <br>
				<span class="cell td3"><input type="text" name="nickName" placeholder="닉네임을 입력하세요." required style="display:inline-block;"/>
				<button id="dupCheck" class="btn-small waves-effect waves-light">중복확인</button></span>
			</div>
			<div class="row">
				<span class="cell td4">자기소개 </span> <span class="cell td4"><input
					type="text" name="intro" placeholder="자기소개를 입력하세요. " /></span>
			</div>
		</div>
		<br>
		<br>
		<div class="center">
			<button id="userInfoBtn" class="btn-small waves-effect waves-light" style="background-color: #e69138ff">등록</button>
		</div>
	</div>
	
	
	<div class="register" id=userGoal style="display: none">
		<h5 class="header center gray-text">초기 목표 등록</h5>
		<br>
		<div id="table">
			<div class="row">
				<span class="cell td1">일별 계획 </span> <span class="cell td1"><input
					type="text" name="goal" placeholder="계획을 입력하세요. " /></span>
			</div>
			<div class="row">
				<span class="cell td2">시작 일자 </span> <span class="cell td2"><input
					type="date" name="startDate" /></span>
			</div>
			<div class="row">
				<span class="cell td3">종료 일자 </span> <span class="cell td3"><input
					id="date" type="date" name="endDate" /></span>
			</div>
			<div class="row">
				<span class="cell td4">목표 갯수 </span> <span class="cell td4"><input
					type="number" name="goalNum"></span>
			</div>
		</div>
		<br>
		<br>
		<div class="center">
			<button id="skipBtn" class="btn-small waves-effect waves-light" style="background-color: #e69138ff">건너뛰기</button>
			<button id="userGoalBtn" class="btn-small waves-effect waves-light" style="background-color: #e69138ff">등록</button>
		</div>
	</div>
</div>


<%@ include file="./inc/footer.jsp"%>
