<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />

<link href="../resources/css/activities.css" rel="stylesheet">
<script src="../resources/js/activities.js"></script>

<style>
#activities {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

#activities:before {
	content: "";
	background-image: url("../resources/img/activityimg.jpg");
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

<div id="SiteContainer" class="container">
	<div id="activities">
		<div class="content">
			<h4>내 기록</h4>
			<p>지금까지 내가 등록한 기록들을 확인해 보세요!</p>
		</div>
	</div>

	<div class="table center">
		<div class="tableRow">
			<span class="tableCell th1">No.</span> <span class="tableCell th3">기간</span>
			<span class="tableCell th3">달성률</span> <span class="tableCell th2">상태</span>
		</div>

		<c:forEach items="${goalList}" var="goals" varStatus="status">
			<div class="tableRow" id="goals${goals.id}"
				onclick="printAllContent('#goals${goals.id}', ${goals.id})">
				<div class="readGoal" hidden>${goals.goal}</div>
				<div class="readGoalNum" hidden>${goals.goalNum}개</div>
				<span class="tableCell td1">${status.count}</span> 
				<span class="tableCell td3 readTitle"> 
					<fmt:formatDate pattern="yyyy-MM-dd" value="${goals.startDate}" /> 
					~ <fmt:formatDate pattern="yyyy-MM-dd" value="${goals.endDate}" />
				</span>
				<span class="tableCell td3">
					<div class="prog">
						<div class="progs" style="width: ${goals.rate}%;">
							<span class="rate readRate"> ${goals.rate}%</span>	
						</div>
					</div>
				</span>
				<fmt:formatDate value="${goals.endDate}" pattern="yyyy-MM-dd"
					var="endDate" />
				<c:choose>
					<c:when test="${endDate > nowDate}">
						<span class="tableCell td2 readStatus" style="color: #e69138ff;">진행중</span>
					</c:when>
					<c:otherwise>
						<span class="tableCell td2 readStatus">종료</span>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
	</div>
	

	<div id="readGoalList" hidden>
		<%@ include file="../ajaxContent/activitiesContent.jsp"%>
	</div>
</div>
<%@ include file="../inc/pagination.jsp"%>


<%@ include file="../inc/footer.jsp"%>

