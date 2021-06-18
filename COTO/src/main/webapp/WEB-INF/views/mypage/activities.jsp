<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />

<link href="../resources/css/activities.css" rel="stylesheet">
<script src="../resources/js/activities.js"></script>
<script>
$(document).on("click", ".deleteBtn", function(){
	var id = $(this).val();

	if (confirm("정말로 삭제하겠습니까?")){
		location.href = './activities/delete/' + id;
	}
});  
</script>

<div id="SiteContainer" class="container">
	<div id="activities">
		<div class="content">
			<h4>내 기록</h4>
			<p>지금까지 내가 등록한 기록들을 확인해 보세요!</p>
		</div>
	</div>

	<div class="table center">
		<div class="tableRow">
			<span class="tableCell th1">No.</span> 
			<span class="tableCell th3">기간</span>
			<span class="tableCell th3">달성률</span> 
			<span class="tableCell th1">상태</span>
			<span class="tableCell th1">삭제</span>
		</div>

		<c:forEach items="${goalList}" var="goals" varStatus="status">
			<div class="tableRow" id="goals${goals.id}">
				<div class="readGoal" hidden>${goals.goal}</div>
				<div class="readGoalNum" hidden>${goals.goalNum}개</div>
				<span class="tableCell td1">${status.count}</span> 
				<span class="tableCell td3 readTitle" onclick="printAllContent('#goals${goals.id}', ${goals.id})"> 
					<fmt:formatDate pattern="yyyy-MM-dd" value="${goals.startDate}" /> 
					~ <fmt:formatDate pattern="yyyy-MM-dd" value="${goals.endDate}" />
				</span>
				<span class="tableCell td3" onclick="printAllContent('#goals${goals.id}', ${goals.id})">
					<div class="prog">
						<fmt:formatNumber value="${goals.rate}" pattern=".0" var="userRate"/>
						<c:if test="${userRate <= 100.0 }">
						<div class="progs" style="width: ${userRate}%;">
							<span class="rate readRate"> ${userRate}%</span>	
						</div>
						</c:if>
						<c:if test="${userRate > 100.0 }">
						<div class="progs" style="width: 100%;">
							<span class="rate readRate"> ${userRate}%</span>	
						</div>
						</c:if>
						
					</div>
				</span>
				<fmt:formatDate value="${goals.endDate}" pattern="yyyy-MM-dd" var="endDate" />
				<c:choose>
					<c:when test="${endDate > nowDate}">
						<span class="tableCell td2 readStatus" style="color: #e69138ff;" onclick="printAllContent('#goals${goals.id}', ${goals.id})">진행중</span>
					</c:when>
					<c:otherwise>
						<span class="tableCell td1 readStatus" style="color: #7a7a7a;" onclick="printAllContent('#goals${goals.id}', ${goals.id})">종료</span>
					</c:otherwise>
				</c:choose>
				<span class="tableCell td1"><button value="${goals.id}" class="deleteBtn" type="button"><i class="fas fa-times"/></i></button></span>	
			</div>
		</c:forEach>
		
	</div>
	
	<div id="readGoalList" hidden>
		<%@ include file="../ajaxContent/activitiesContent.jsp"%>
	</div>
</div>
<style>
#activities:before {
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
<%@ include file="../inc/pagination.jsp"%>
<%@ include file="../inc/footer.jsp"%>

