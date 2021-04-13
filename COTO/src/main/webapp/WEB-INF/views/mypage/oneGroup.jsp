<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel="stylesheet" href="../resources/css/myGroup.css?asd" />
<script src="../resources/js/myGroup.js"></script>
<script src="../resources/js/createModal.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<link href="../resources/css/oneGroup.css" rel="stylesheet">
<!-- <link href="../resources/css/problems.css" rel="stylesheet">
 -->

<div id="SiteContainer" class="container">

 	<c:forEach items="${groupInfo}" var="groupInfo" varStatus="status">
	<div id="groupTitle">
		<div class="content">
			<h4 class="">| ${groupInfo.groupName}</h4>
			<p>${groupInfo.goal}</p>
		</div>
	</div>	 	
	</c:forEach>
	

	
	<div id="groupAjaxContent">
		<%@ include file="../ajaxContent/groupInfoContent.jsp"%>
	</div> 

	<div class="top-bar">
		<c:if test = "${adminID == userID}">
			<button class="input-field custom-button" onclick="problemCreateModal(${userID})">문제 추가</button>
		</c:if>
		
		<button id="dropBtn" class="input-field custom-button" onclick="dropGroup(${userID}, ${groupID})">탈퇴하기</button>
	</div>
		
	<div id="groupAjaxContent">
		<%@ include file="../ajaxContent/groupContent.jsp"%>
	</div>
	
	<!-- 문제 추가 모달  -->
	<div id="createProblem" class="container" style="display:none;">
		<form class="col s12">
		
			<p class="title">문제 리스트</p>
			<span><input class="groupDate" type="date" id="probStartDate" />   ~   <input class="groupDate" type="date" id="probEndDate" /></span>
			
			<div class="row">
				<div id="selectHtml" class="input-field col s4">
					<select id="siteName" required>
						<optgroup label="코딩사이트 선택">
							<c:forEach items="${CodingSite}" var="site">
								<option value="${site.id}">${site.siteName}</option>
							</c:forEach>
						</optgroup>
						<optgroup label="링크로 입력">
							<option value="0">링크로 입력</option>
						</optgroup>
					</select>
					<label>코딩사이트 선택</label> 
					<span class="helper-text">코딩 사이트를 선택해서 입력하거나 링크로 입력할 수 있습니다.</span>
				</div>
				<div class="input-field col s6">
					<input id="problems" type="text" class="validate"> 
					<label for="problems">Problems</label> 
					<span class="helper-text">문제들을 입력할 때 ,로 구분해주세요!!</span>
				</div>
				<button type="button" id="add" class="modal_button lighten-1" onClick="insertProblems()">추가</button>
			</div>
			
			<div class="input-field col s10">
				<label for="last_name">입력한 Problems</label> <br> <br>
				<div class="recom-confirmSite" id="confirmSite"></div>
			</div>
			
		</form>
	</div>
	
	<!-- 문제 추가 모달  -->
	<div id="readGoalProblem" class="container" style="display:none;">
		<div>
			<p class="title desc">문제 리스트</p>
			<div id="readProblems" class="readBox">
				<c:forEach items="${groupProbDetail}" var="gp" varStatus="status">
					<div class="recomProblemID${status.index}" style="display:none;">${gp.id}</div>
					<div class="sitetitle">${gp.problemID}</div>
					<%-- <div id="eachProblemContent${rp.problemID}">
						<c:if test="${!empty rp.name}">
							<c:if test="${!empty rp.siteName}">
								<div class="recomProblemID${status.index}" style="display:none;">${rp.id}</div>
							</c:if>
						</c:if>
					</div> --%>
				</c:forEach>
			</div>
		</div>
	</div>
	
</div>



<script>
	var groupID = ${groupID};
	var chartColors=[
		'rgb(255, 196, 196)',
		'rgb(255, 221, 196)',
		'rgb(255, 237, 196)',
		'rgb(224, 255, 196)',
		'rgb(196, 240, 255)',
		'rgb(196, 219, 255)',
		'rgb(198, 196, 255)',
		'rgb(231, 196, 255)',
		'rgb(255, 196, 255)',
		'rgb(255, 196, 222)',
		'rgb(255, 196, 200)',
	];
	
	var progressByUser = new Array();
	<c:forEach items="${progressByUser}" var="p">
		var list = new Object();
		list.userID = ${p.userID};
		list.count = ${p.count};
		list.name = '${p.name}';
		list.email = '${p.email}';
		list.nickName = '${p.nickName}';
		//list.userName = ${p.userName};
		progressByUser.push(list);
	</c:forEach>
	
	$(document).ready(function(){
		DrawProgressChart();
	});
	
	
	function DrawProgressChart() {
		
		var chartLabels = [];
		var chartDatas = [];
		var newColor=[];
		
		for (var i=0 ; i< progressByUser.length ; i++){
			chartDatas.push(progressByUser[i].count);
			chartLabels.push(progressByUser[i].nickName);
			newColor.push(chartColors[i])
		}
		
		var ctx = document.getElementById('progressChart').getContext('2d'); 
		var chart = new Chart(ctx, { 
			type: 'bar',
			data: { 
				labels: chartLabels, 
				datasets: [{
					backgroundColor: chartColors,
					data: chartDatas,
				}] }, 
				options: {
					legend: {
				         display: false //This will do the task
				    }, 
					scales: {
						xAxes: [{ 
							ticks: { 
								fontSize: '15' } 
						}], 
						yAxes: [{ 
							ticks: { 
								beginAtZero: true, 
								stepSize: 1,
								fontSize: '15' } 
						}] 
					} 
				} 
			});
	}

	
	

</script>