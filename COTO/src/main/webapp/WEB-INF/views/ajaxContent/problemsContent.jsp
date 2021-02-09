<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="tableRow">
	<span class="index-col tableCell th1">No.</span>
	<span class="tableCell th2">문제 제목</span>
	<span class="tableCell th2">사이트</span>
	<span class="tableCell th3 pMemo">메모</span>
	<span class="tableCell th1 pRegdate">날짜</span>
	<span class="tableCell th1 pDifficulty">난이도</span>
</div>
									
<c:forEach items="${problems}" var="problem" varStatus="status">
	<div class="tableRow center" id="problem${problem.id}" onclick="printAllContent('#problem${problem.id}')">
		<span class ="index-col tableCell td1">${status.count}</span>
		<span class ="tableCell td2 pTitle"><a href="${problem.link}" target="_blank">${problem.problem}</a></span>
		<span class ="tableCell td2 pSite">${problem.site}</a></span>
		<span class ="tableCell td3 pMemo">${problem.memo}</span>
		<span class ="tableCell td2 pRegdate">${problem.regDate}</span>
		<span class ="tableCell td1 pDifficulty"><img style="width: 60px;" alt="${problem.difficulty}" src="../resources/img/difficulty${problem.difficulty}.png"></span>
		<span class="pSiteUrl" style="display:none;">${problem.siteUrl}</span>
	</div>
</c:forEach>