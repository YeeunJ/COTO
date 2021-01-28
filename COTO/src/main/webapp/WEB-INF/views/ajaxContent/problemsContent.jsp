<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="tableRow">
	<span class="tableCell th1">No.</span>
	<span class="tableCell th2">문제 제목</span>
	<span class="tableCell th4">메모</span>
	<span class="tableCell th2">날짜</span>
	<span class="tableCell th1">난이도</span>
</div>
									
<c:forEach items="${problems}" var="problem" varStatus="status">
	<div class="tableRow" id="problem${problem.id}" onclick="printAllContent('#problem${problem.id}')">
		<span class ="tableCell td1">${status.count}</span>
		<span class ="tableCell td2 pTitle"><a href="${problem.link}" target="_blank">${problem.problem}</a></span>
		<span class ="tableCell td4 pMemo">${problem.memo}</span>
		<span class ="tableCell td2 pRegdate">${problem.regDate}</span>
		<span class ="tableCell td1 pDifficulty"><img style="width: 34px;" alt="${problem.difficulty}" src="../resources/img/difficulty${problem.difficulty}.png"></span>
		<span class="pSite" style="display:none;">${problem.site}</span>
		<span class="pSiteUrl" style="display:none;">${problem.siteUrl}</span>
	</div>
</c:forEach>