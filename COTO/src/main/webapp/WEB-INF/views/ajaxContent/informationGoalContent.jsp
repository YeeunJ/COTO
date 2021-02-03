<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="table">
	<c:forEach items="${goals}" var="goal" varStatus="status">
		<div class="tableRow">
			<span class="tableCell th3">목표 내용</span> <span
				class="tableCell td8">${goal.goal}
			</span>
		</div>
		<div class="tableRow">
			<span class="tableCell th3">목표 기간</span> <span
				class="tableCell td8"><fmt:formatDate
					pattern="yyyy-MM-dd" value="${goal.startDate}" /> ~ <fmt:formatDate
					pattern="yyyy-MM-dd" value="${goal.endDate}" />
			</span>
			<fmt:formatDate pattern="yyyy-MM-dd" value="${goal.startDate}"
				var="sDate" />
			<fmt:formatDate pattern="yyyy-MM-dd" var="eDate"
				value="${goal.endDate}" />
		</div>
		<div class="tableRow">
			<span class="tableCell th3">목표 개수</span> <span
				class="tableCell td8">${goal.goalNum}
			</span>
		</div>
	</c:forEach>
</div>