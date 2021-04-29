<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<span class = "readGoalTitle" style="display:none;">${groupGoalDetail.startDate} ~ ${groupGoalDetail.endDate}</span>
<span class = "userID" style="display:none;">${userID}</span>
<span class = "groupLeader" style="display:none;">${groupLeader}</span>

<!-- 그룹 디테일 모달  -->
	<div id="readGoalProblem" class="container" style="display:none;">
		<p class="title desc">문제 리스트</p>
		<div id="readProblems" class="table center">
			<div class="tableRow" style="cursor: default">
				<span class="tableCell th1">No.</span> 
				<span class="tableCell th3">사이트</span> 
				<span class="tableCell th4">문제 제목</span> 
				<span class="tableCell th2">완료</span>
			</div>
			<c:forEach items="${groupProbDetail}" var="gp" varStatus="status">
				<div class="tableRow" style="cursor: default">
					<span class="tableCell td1">${status.count}</span> 
					<span class="tableCell td3">${gp.siteName}</span> 
					<span class="tableCell td4">
						<c:choose>
							<c:when test="${gp.link eq null}"><p style="display: inline-block;">${gp.name}</p></c:when>
							<c:otherwise><p style="display: inline-block;" style="cursor: pointer"><a href="${gp.link}" target="_blank">${gp.name}</a></p></c:otherwise>
						</c:choose>
					</span>
					<span class="tableCell td2" id="eachProblemContent${gp.problemID}">
						<%@ include file="./groupCheckContent.jsp"%>
					</span> 
				</div>
			</c:forEach>
		</div>
	</div>

	