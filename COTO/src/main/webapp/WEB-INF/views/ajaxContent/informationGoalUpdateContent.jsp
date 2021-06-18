<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="table">
	<c:choose>
		<c:when test = "${goals.size() == 0}">
			<input name="id" value="-1" type="hidden" />
				<div class="tableRow">
					<span class="tableCell th3">목표 내용 </span> <span
						class="tableCell td8"><input type="text" name="goal" /> </span>
				</div>
				<div class="tableRow">
					<span class="tableCell th3">목표 시작일</span> <span
						class="tableCell td8"><input type="date" name="startDate"/> </span>
				</div>
				<div class="tableRow">
					<span class="tableCell th3">목표 종료일</span> <span
						class="tableCell td8"><input type="date" name="endDate"/> </span>
				</div>
				<div class="tableRow">
					<span class="tableCell th3">목표 개수</span> <span
						class="tableCell td8"><input type="number" name="goalNum"/> </span>				
				</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${goals}" var="goal" varStatus="status">
				<input name="id" value="${goal.id}" type="hidden" />
				<div class="tableRow">
					<span class="tableCell th3">목표 내용 </span> <span
						class="tableCell td8"><input type="text" name="goal"
						value="${goal.goal}" placeholder="${goal.goal}" /> </span>
				</div>
				<div class="tableRow">
					<span class="tableCell th3">목표 시작일</span> <span
						class="tableCell td8"><input type="date" name="startDate"
						value="${sDate}" /> </span>
				</div>
				<div class="tableRow">
					<span class="tableCell th3">목표 종료일</span> <span
						class="tableCell td8"><input type="date" name="endDate"
						value="${eDate}" /> </span>
				</div>
				<div class="tableRow">
					<span class="tableCell th3">목표 개수</span> <span
						class="tableCell td8"><input type="number" name="goalNum" min="0"
						value="${goal.goalNum}"/> </span>				
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>