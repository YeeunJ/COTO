<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />

<!-- <link href="../resources/css/activities.css" rel="stylesheet"> -->
<script src="../resources/js/activities.js"></script>


<div id="SiteContainer" class="container">
	<div id="otherUser">
		<div class="content">
			<h4>| ${nickName}</h4>
			<p>${intro}</p>
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
						<fmt:formatNumber value="${goals.rate}" pattern=".0" var="userRate"/>
						<div class="progs" style="width: ${userRate}%;">
							<span class="rate readRate"> ${userRate}%</span>	
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
	</div> 1
	

</div>
<style>
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
#otherUser{
    position: relative;
    padding: 80px 0;
    margin-bottom: 3%;
}
</style>
<%@ include file="./inc/footer.jsp"%>

