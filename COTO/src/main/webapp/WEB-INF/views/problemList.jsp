<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
<link rel="stylesheet" href="./resources/css/solvedProblem.css?asd" />
<link href="./resources/css/problemList.css?qwe" rel="stylesheet">
<script src="./resources/js/problemList.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script src="https://www.chartjs.org/samples/latest/utils.js"></script>
<script>
$.ajax({  
    type: 'get',  
    url: "${pageContext.request.contextPath}/manageCodingsite/problem",  
    
    success: function (result) {  
        $("#siteValue").html(result);
    }  
}); 
</script>
<style>
canvas {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
}		
		

#problem {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

#problem:before {
	content: "";
	background-image: url("./resources/img/problemList.jpg");
	background-size: cover;
	top: 0;
	left: 0;
	right: 0px;
	bottom: 0px;
	position: absolute;
	opacity: 0.4;
	z-index: -1;
}
.chart-div {
	display:inline-block;
}
</style>

<div id="SiteContainer" class="container">
	<div id="problem">
		<div class="content">
			<h4>문제 리스트</h4>
			<p>다른 사람들이 많이 푼 문제를 확인하고 풀어보세요!</p>
		</div>
	</div>
	
	<div id="chart-wrapper">
		<div id="canvas-holder" class="card shadow card-body chart-div">
			<canvas id="chartBySite" class="chart-canvas" height="185px"></canvas>
		</div>
		
		<div id="container" class="card shadow card-body chart-div right">
			<canvas id="averageForWeek" class="chart-canvas" style="margin-top:23px"></canvas>
		</div>
	</div>


	<div class="top-bar">
		<fieldset class="search">
			<input id="searchValue" class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button id="searchButton" class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset>
		<div class="col order">
			<select id="siteValue">
			
			</select>
		</div>
		<div class="col order" style="margin-right: 10px;">
			<select id="orderValue">
				<option value="problem.regdate" disabled selected>정렬</option>
				<option value="problem.name">제목순</option>
				<option value="problem.siteID">사이트순</option>
				<option value="problem.regdate desc">최신순</option>
				<option value="COUNT(*) desc">많이 풀어본 문제순</option>
			</select>
		</div>
	</div>
	
	
	<div class="table center" id="problemContent">
		<div class="tableRow">
			<span class="tableCell th1">No.</span>
			<span class="tableCell th3">문제 제목</span>
			<span class="tableCell th1">사이트</span>
			<span class="tableCell th1">참여자</span>
			<span class="tableCell th1">등록일</span>
		</div>
		
		<c:forEach items="${problems}" var="problem" varStatus="status">
		<fmt:formatDate value="${problem.regdate}" var="formattedDate" type="date" pattern="yyyy-MM-dd" />
		<div class="tableRow" id="problem${problem.id}">
			<span class="tableCell td1">${status.count}</span> 
			<span class="tableCell td3"> <a href="${problem.link}">${problem.name}</a></span> 
			<span class="tableCell td1"> <a href="${problem.siteUrl}">${problem.siteName}</a></span> 
			<span class="tableCell td1">${problem.count}</span> 
			<span class="tableCell td1">${formattedDate}</span> 
		</div>
		</c:forEach>	
		<%-- <%@ include file="./ajaxContent/problemListContent.jsp"%> --%>
	</div>
	<br> <br>
	
	<%@ include file="./inc/pagination.jsp"%>
	

</div>

<script>

var ratioBySite = new Array();
var averageWeek = new Array();

<c:forEach items="${ratio}" var="r">
	var list = new Object();
	list.siteName = "${r.siteName}";
	list.siteID = ${r.siteID};
	list.ratio = ${r.ratio};
	
	ratioBySite.push(list);
</c:forEach>

<c:forEach items="${averageForWeek}" var="avg">
	var list = new Object();
	
	list.date = "${avg.date}";
	list.average = ${avg.average};
	
	averageWeek.push(list);
</c:forEach>


</script>

<%@ include file="./inc/footer.jsp"%>

