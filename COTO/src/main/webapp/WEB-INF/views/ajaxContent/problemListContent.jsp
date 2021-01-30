<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="tableRow">
	<span class="tableCell th1">No.</span>
	<span class="tableCell th2">문제 제목</span>
	<span class="tableCell th4">사이트</span>
	<span class="tableCell th1">푼사람 수</span>
</div>

<c:forEach items="${problems}" var="problem" varStatus="status">
	<div class="tableRow" id="problem${problem.id}">
		<span class="tableCell td1">${status.count}</span> 
		<span class="tableCell td2">${problem.name}</span> 
		<span class="tableCell td4"> <a href="${problem.link}" target="_blank">${problem.siteID}</a></span> 
		<span class="tableCell td1">${status.count}</span> 
		

	</div>
</c:forEach>
