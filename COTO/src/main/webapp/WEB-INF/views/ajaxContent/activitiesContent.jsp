<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- read modal -->
<div>
	<div class="table">
		<div class="tableRow">
			<span class="tableCell th2_">목표</span>
			<span class="tableCell td5"	id="goal"></span>
		</div>
		<div class="tableRow">
			<span class="tableCell th2_">기간</span>
			<span class="tableCell td5" id="term"></span>
		</div>
		<div class="tableRow">
			<span class="tableCell th2_">목표 문제수</span>
			<span class="tableCell td5" id="goalNum"></span>
		</div>
		<div class="tableRow">
			<span class="tableCell th2_">달성률</span>
			<span class="tableCell td5" id="rate"></span>
		</div>
	</div>

	<br>
	<br>

	<div class="table">
		<div class="tableRow">
			<span class="tableCell th1">No.</span> <span class="tableCell th2">문제
				제목</span> <span class="tableCell th4">메모</span> <span class="tableCell th2">날짜</span>
			<span class="tableCell th2">난이도</span>
		</div>
		<c:forEach items="${readProblemActivities}" var="readP"
			varStatus="status">
			<div class="tableRow">
				<span class="tableCell td1">${status.count}</span> <span
					class="tableCell td2"><a href="${readP.link}"
					target="_blank">${readP.problem}</a></span> <span class="tableCell td4">${readP.memo}</span>
				<span class="tableCell td2">${readP.regDate}</span> <span
					class="tableCell td2"> <img style="width: 60px;"
					alt="${readP.difficulty}"
					src="../resources/img/difficulty${readP.difficulty}.png">
				</span>
			</div>
		</c:forEach>
	</div>
</div>

<style>
.tableCell {text-align: center;}
</style>
