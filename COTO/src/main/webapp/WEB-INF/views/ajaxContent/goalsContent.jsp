<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="tableRow">
	<span class="tableCell th1">No.</span>
	<span class="tableCell th4">목표 내용 </span>
	<span class="tableCell th2">목표 시작일</span>
	<span class="tableCell th2">목표 종료일</span>
</div>
									
<c:forEach items="${goals}" var="goal" varStatus="status">
	<div class="tableRow" id="goal${goal.id}" onclick="printAllContent('#goal${goal.id}')">
		<span class ="tableCell td1">${status.count}</span>
		<span class ="tableCell td4 ">${goal.goal}</span>
		<span class ="tableCell td2 ">${goal.startDate}</span>
		<span class ="tableCell td2 ">${goal.endDate}</span>

	</div>
</c:forEach>