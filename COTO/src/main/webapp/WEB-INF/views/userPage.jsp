<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<jsp:useBean id="now" class="java.util.Date" />
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script src="https://www.chartjs.org/samples/latest/utils.js"></script>

<link href="./resources/css/userpage.css" rel="stylesheet">
<script src="./resources/js/userpage.js"></script>


<div id="SiteContainer" class="container">
	<div id="otherUser">
		<div class="content">
			<h4 class="">| ${nickName}</h4>
			<p>${intro}</p>
		</div>
	</div>
	
		<!-- Content Row -->
	<div class="card-wrap">
		<div class="card-content1">
			<div class="card shadow card-body">
				<div class="font-color card-title">${nickName}님의 목표</div>
				<div id="table">
					<c:forEach items="${readOtherUserPage}" var="r" varStatus="status">
						<div class="tableRow">
							<span class="tableCell td2">현재 목표</span> <span class="tableCell td4">${r.goal}</span>
						</div>
						<div class="tableRow">
							<span class="tableCell td2">현재 목표 문제수</span> <span
								class="tableCell td4">${goalNum}문제</span>
						</div>
					</c:forEach>
						<div class="tableRow">
							<span class="tableCell td2" style="font-size: 13px;">전체 푼 문제수</span> 
								<span class="tableCell td4">${t_solved}문제</span>
						</div>
				</div>
			</div>
		</div>

		<div class="card-content2">
			<div class="card shadow card-body">
				<div class="font-color card-title">${nickName}님의 진행도</div>
				<canvas id="myDoughnutChart" width="340vw" height="130px">
				</canvas>
			</div>
		</div>
	</div>

	<br><br>
	<div class="table center" id="problemContent">
			<div class="tableRow">
				<span class="tableCell th3" style="text-align:left !important">전체 푼 문제</span>
			</div>

			<span class="tableCell td3 probname tc"> 
				<c:forEach items="${readOtherUserProblemName}" var="problem" varStatus="status">
					<nobr><a href="${problem.link}" target="_blank">${problem.name}, </a></nobr>
				</c:forEach>
			</span>
	</div>
	
</div>
<script>
var dataForDoughnut = new Array();
var gN = ${goalNum};
var uP = ${solved};
var diff = gN-uP;

if(diff >=0) dataForDoughnut.push(diff);
else {
	diff = 0;
	dataForDoughnut.push(diff);
}
dataForDoughnut.push(uP);
</script>
<style>
#otherUser{
    position: relative;
    padding: 80px 0 60px 0;
    border-bottom: 1px solid #DDD ;
    margin-bottom: 2.5%;
}
</style>
<%@ include file="./inc/footer.jsp"%>