<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
<link rel="stylesheet" href="./resources/css/solvedProblem.css?asd" />
<link href="./resources/css/problemList.css?qwe" rel="stylesheet">
<script src="./resources/js/problemList.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script src="https://www.chartjs.org/samples/latest/utils.js"></script>

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
			<select id="orderValue">
				<option value="problem.regdate" disabled selected>정렬</option>
				<option value="problem.name">제목순</option>
				<option value="problem.siteID">사이트순</option>
				<option value="problem.regdate desc">최신순</option>
				<option value="COUNT(*)">많이 풀어본 문제순</option>
			</select>
		</div>
	</div>
	
	
	<div class="table center" id="problemContent">
		<%@ include file="./ajaxContent/problemListContent.jsp"%>
		<%-- <%@ include file="./ajaxContent/problemListByPageContent.jsp"%> --%>
	</div>
	<br> <br>
	
	<!-- pagination{s} -->
	
	<%-- <ul class="pagination">
		<c:if test="${pagination.prev}">
			<li class="waves-effect"><a href="#!" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')"><i class="material-icons">chevron_left</i></a></li>
		</c:if>

		<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
			<li class="<c:out value="${pagination.page == idx ? 'active' : 'waves-effect'}"/> "><a href="#!" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a></li>
		</c:forEach>

		<c:if test="${pagination.next}">
			<li class="waves-effect"><a href="#" onClick="fn_next('${pagination.range}', '${pagination.range}', '${pagination.rangeSize}')" ><i class="material-icons">chevron_right</i></a></li>
		</c:if>
	</ul> --%>
	
	<div class="table" style="text-align: center">
		<ul class="pagination ">
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
			<li class="active orange"><a href="#!">1</a></li>
			<li class="waves-effect"><a href="#!">2</a></li>
			<li class="waves-effect"><a href="#!">3</a></li>
			<li class="waves-effect"><a href="#!">4</a></li>
			<li class="waves-effect"><a href="#!">5</a></li>
			<li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
		</ul>
	</div> 
	

	<!-- pagination{e} -->

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

