<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../inc/header2.jsp"%>

<link href="../resources/css/information.css" rel="stylesheet">
<script src="../resources/js/information.js"></script>
<style>
#information {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

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

.content {
	top: 15%;
	left: 50px;
	width: 100%;
	bottom: 100px;
	color: #666666;
	font-weight: 800;
	position: absolute;
}
</style>

<div class="container">
	<div id="information">
		<div class="content">
			<h4>내 정보 및 목표</h4>
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
			<div class="table">
				<c:forEach items="${users}" var="user" varStatus="status">
					<div class="tableRow">
						<span class="tableCell th3">이름</span>
						<span class="tableCell th8">${user.name}</span>
					</div>
					<div class="tableRow">
						<span class="tableCell th3">닉네임</span> 
						<span class="tableCell th8">${user.nickName}</span>
					</div>
					<div class="tableRow">
						<span class="tableCell th3">학번</span> 
						<span class="tableCell th8">${user.userNumber}</span>
					</div>
					<div class="tableRow">
						<span class="tableCell th3">자기소개</span> 
						<span class="tableCell th8">${user.intro}</span>
					</div>
				</c:forEach>
			</div>
		</div>

		<div id=infoEdit style="display: none">
			<form:form id="updateInfo" method="post"
				action="information/updateUser">
				<br><br>
				<div id="headerButton">
					<div class = "left">
						<h5 class="orange-text" style = "margin: 0;">내 정보 수정</h5>
					</div>
					<div class="right">
						<input id="submit" type="submit" value="수정하기 " class="mybtn" /> <input
							id="add" type="button" value="취소하기 "
							onclick="showInfo(); hideInfoEdit()" class="mybtn" />
					</div>
				</div>
				<br><br>
				<div class="table">
					<c:forEach items="${users}" var="user" varStatus="status">
						<input name="id" value="${user.id}" type="hidden" />
						<div class="tableRow">
							<span class="tableCell th3">이름</span> <span class="tableCell th8"><input
								type="text" name="name" value="${user.name}"
								placeholder="${user.name}" /> </span>
						</div>
						<div class="tableRow">
							<span class="tableCell th3">닉네임</span> <span
								class="tableCell th8"><input type="text" name="nickName"
								value="${user.nickName}" placeholder="${user.nickName}" /> </span>
						</div>
						<div class="tableRow">
							<span class="tableCell th3">학번</span> <span class="tableCell th8"><input
								type="text" name="userNumber" value="${user.userNumber}"
								placeholder="${user.userNumber}" /> </span>
						</div>
						<div class="tableRow">
							<span class="tableCell th3">자기소개</span> <span
								class="tableCell th8"><input type="text" name="intro"
								value="${user.intro}" placeholder="${user.intro}" /> </span>
						</div>
					</c:forEach>
				</div>
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
			<div class="table">
				<c:forEach items="${goals}" var="goal" varStatus="status">
					<div class="tableRow">
						<span class="tableCell th3">목표 내용</span> <span
							class="tableCell th8">${goal.goal}
						</span>
					</div>
					<div class="tableRow">
						<span class="tableCell th3">목표 기간</span> <span
							class="tableCell th8"><fmt:formatDate
								pattern="yyyy-MM-dd" value="${goal.startDate}" /> ~ <fmt:formatDate
								pattern="yyyy-MM-dd" value="${goal.endDate}" />
						</span>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${goal.startDate}"
							var="sDate" />
						<fmt:formatDate pattern="yyyy-MM-dd" var="eDate"
							value="${goal.endDate}" />
					</div>
				</c:forEach>
			</div>
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
				<div class="table">
					<c:forEach items="${goals}" var="goal" varStatus="status">
						<input name="id" value="${goal.id}" type="hidden" />
						<div class="tableRow">
							<span class="tableCell th3">목표 내용 </span> <span
								class="tableCell th8"><input type="text" name="goal"
								value="${goal.goal}" placeholder="${goal.goal}" /> </span>
						</div>
						<div class="tableRow">
							<span class="tableCell th3">목표 시작일</span> <span
								class="tableCell th8"><input type="date" name="startDate"
								value="${sDate}" /> </span>
						</div>
						<div class="tableRow">
							<span class="tableCell th3">목표 종료일</span> <span
								class="tableCell th8"><input type="date" name="endDate"
								value="${eDate}" /> </span>
						</div>
					</c:forEach>
				</div>

			</form:form>
		</div>

	</div>
</div>


<%@ include file="../inc/footer.jsp"%>


