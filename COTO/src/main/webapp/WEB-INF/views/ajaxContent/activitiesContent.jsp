<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<!-- read modal -->
<div class="table">
	<div class="tableRow">
		<span class="tableCell th2">목표</span><span class="tableCell td5" id="goal"></span>
	</div>
	<div class="tableRow">
		<span class="tableCell th2">기간</span><span class="tableCell td5" id="term"></span>
	</div>
	<div class="tableRow">
		<span class="tableCell th2">총 문제수</span><span class="tableCell td5" id="goalNum"></span>
	</div>
	<div class="tableRow">
		<span class="tableCell th2">달성률</span> <span class="tableCell td5" id="rate"></span>
	</div>
</div>
<hr style="border: 0.1px solid lightgrey">
<div class="tableRow">
	<span class="tableCell th1">No.</span> 
	<span class="tableCell th3">문제 제목</span> 
	<span class="tableCell th4">메모</span> 
	<span class="tableCell th2">날짜</span>
	<span class="tableCell th2">난이도</span>
</div>
<c:forEach items="${problems}" var="problem" varStatus="status">
	<div class="tableRow" id="problem${problem.id}">
		<span class="tableCell td1">${status.count}</span> <span
			class="tableCell td2 pTitle"> <a href="${problem.link}"
			target="_blank">${problem.problem}</a>
		</span> <span class="tableCell td4 pMemo">${problem.memo}</span> <span
			class="tableCell td2 pRegdate">${problem.regDate}</span> <span
			class="tableCell td1 pDifficulty"> <img style="width: 34px;"
			alt="${problem.difficulty}"
			src="../resources/img/difficulty${problem.difficulty}.png">
		</span> <span class="pSite" style="display: none;">${problem.site}</span> <span
			class="pSiteUrl" style="display: none;">${problem.siteUrl}</span>
	</div>
</c:forEach>