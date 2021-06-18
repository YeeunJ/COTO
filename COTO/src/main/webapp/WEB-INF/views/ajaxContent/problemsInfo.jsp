<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="card-content1">
	<div class="card shadow card-body">
		<div class="font-color card-title">나의 목표</div>
		<div id="table">
			<c:forEach items="${goal}" var="goal" varStatus="status">
				<div class="tableRow" id="goal">
					<span class="tableCell td2">목표</span> <span class="tableCell td4">${goal.goal}</span>
				</div>
				<div class="tableRow" id="goal">
					<span class="tableCell td2">기간</span> <span class="tableCell td4"
						style="font-size: 14px;"> <fmt:formatDate
							pattern="yyyy-MM-dd" value="${goal.startDate}" /> ~ <fmt:formatDate
							pattern="yyyy-MM-dd" value="${goal.endDate}" />
					</span>
				</div>
				<div class="tableRow" id="goal">
					<span class="tableCell td2">목표 문제수</span> <span
						class="tableCell td4">${goal.goalNum}문제</span>
				</div>
				<div class="tableRow pNow" style="visibility: hidden;">
					<span class="tableCell td2" style="font-size: 13px;">현재 푼
						문제수</span> <span class="tableCell td4">${userSolvedP}문제</span>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<div class="card-content2">
	<div class="card shadow card-body">
		<div class="font-color card-title">하루의 기록</div>
		<div class="myChart"
			style="overflow-x: scroll; width: 310px; height: 200;">
			<div class="chartAreaWrapper"
				style="overflow-x: scroll; width: 900px; height: 200">
				<canvas id="myBarChart" width="900px" height="200"></canvas>
			</div>
		</div>
	</div>
</div>

<div class="card-content3">
	<div class="card shadow card-body">
		<div class="font-color card-title">현재 상황</div>
		<canvas id="myDoughnutChart" width="300vw" height="180px"></canvas>
	</div>
</div>