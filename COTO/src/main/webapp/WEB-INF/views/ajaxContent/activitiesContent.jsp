<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<style>
.th2_ {
	text-align: center;
	border-right: solid 1px orange !important;
	border-bottom: none !important;
	font-weight: normal !important;
	background-color: white !important;
	color: #666666 !important;
	width: 20%;
}

.td5 {
	font-weight: normal !important;
	padding-left: 50px !important;
	border-bottom: none !important;
}
</style>
<!-- read modal -->
<div class="table">
	<div class="tableRow">
		<span class="tableCell th2_">목표</span><span class="tableCell td5"
			id="goal"></span>
	</div>
	<div class="tableRow">
		<span class="tableCell th2_">기간</span><span class="tableCell td5"
			id="term"></span>
	</div>
	<div class="tableRow">
		<span class="tableCell th2_">총 문제수</span><span class="tableCell td5"
			id="goalNum"></span>
	</div>
	<div class="tableRow">
		<span class="tableCell th2_">달성률</span> <span class="tableCell td5" id="rate">
<%-- 			<div class="prog">
				<div class="progs" style="width: ${goals.rate}%;">
					<span id="rate" class="rate readRate"> ${goals.rate}% </span>
				</div>
			</div> --%>
		</span>
	</div>
</div>

<br><br>

<div class="table">
<div class="tableRow">
	<span class="tableCell th1">No.</span> 
	<span class="tableCell th2">문제 제목</span> 
	<span class="tableCell th4">메모</span> 
	<span class="tableCell th2">날짜</span>
	<span class="tableCell th2">난이도</span>
</div>
<c:forEach items="${readProblemActivities}" var="readP" varStatus="status">
	<div class="tableRow">
		<span class ="tableCell td1">${status.count}</span>
		<span class ="tableCell td2"><a href="${readP.link}" target="_blank">${readP.problem}</a></span>
		<span class ="tableCell td4">${readP.memo}</span>
		<span class ="tableCell td2">${readP.regDate}</span>
		<span class ="tableCell td2">
			<img style="width: 60px;" alt="${readP.difficulty}" src="../resources/img/difficulty${readP.difficulty}.png">
		</span>
	</div>
</c:forEach>
</div>
