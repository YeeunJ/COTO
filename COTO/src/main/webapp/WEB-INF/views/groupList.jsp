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


<!-- SiteContainer -->
<div id="SiteContainer" class="container">
	<div id="problem">
		<div class="content">
			<h4>그룹 리스트</h4>
			<p>여러 그룹들을 확인하고 함께 코딩에 참여해보세요!</p>
		</div>
	</div>

	<!-- top-bar -->
	<div class="top-bar">
		<fieldset class="search">
			<input id="searchValue" class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button id="searchButton" class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset>
		<div class="col order" style="margin-right: 10px;">
			<select id="siteValue">
				<option value="" selected>사이트 전체</option>
				<c:forEach items="${codingSite}" var="c" varStatus="status">
					<option value="${ c.id }">${ c.siteName }</option>
				</c:forEach>
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
	<!-- //top-bar -->
	
	<div>
		<%@ include file="./ajaxContent/groupListContent.jsp"%>
	</div>
</div>
<!-- //SiteContainer -->

<script>

var ratioBySite = new Array();
var averageWeek = new Array();
var totalProbCnt = ${totalProblemCnt};
<c:forEach items="${ratio}" var="r">
	var list = new Object();
	list.siteName = "${r.siteName}";
	list.siteID = ${r.siteID};
	list.ratio = ${r.ratio};
	list.count = ${r.count};
	
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

