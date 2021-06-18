<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script src="https://www.chartjs.org/samples/latest/utils.js"></script>

<div id="groupListContent">
	
 	<c:forEach items="${groupInfo}" var="grouplist" varStatus="status">
	<!-- Content Row -->
	<div class="card-wrap">
		<div class="card-content1">
			<div class="card shadow card-body">
				<div class="font-color card-title">${grouplist.groupName} 정보</div>
				<c:if test = "${adminID == userID}">
					<div id="editBtn" onclick="editInfo()">수정</div>
				</c:if>
				<div id="editBtn" class="complete" onclick="complete(${groupID})" style="display: none;">완료</div>
				<div id="editBtn" class="cancel" onclick="cancel()" style="display: none;">취소</div>
 				<fmt:formatDate value="${grouplist.endDate}" pattern="yyyy-MM-dd" var="endDate"></fmt:formatDate>	  
 				<fmt:formatDate value="${grouplist.startDate}" pattern="yyyy-MM-dd" var="startDate"></fmt:formatDate>				
				<div id="groupInfo">
				<div id="table">
						<div class="tableRow goal">
							<span class="tableCell td2">소개</span> 
							<span class="tableCell td4">${grouplist.groupDesc}</span>
						</div>
						<div class="tableRow goal">
							<span class="tableCell td2">유지기간</span> 
							<span class="tableCell td4">
								${startDate} ~ ${endDate}
							</span>
						</div>
						<div class="tableRow goal">
							<span class="tableCell td2">인원</span> 
								<span class="tableCell td4">${countGroupUser}명</span>
						</div>
				</div>
				</div>
	  
				
				<div id="editInfo" style="display: none;">
				<div id="table">
						<div class="tableRow goal">
							<span class="tableCell td2">그룹 소개</span> 
							<span class="tableCell td4">
								<input type="text" id="desc" name="desc" value="${grouplist.groupDesc}">
							</span>
						</div>
						<div class="tableRow goal">
							<span class="tableCell td2">그룹 시작일</span> 
							<span class="tableCell td4">
								<input type="date" id="startDate" name="startDate" value="${startDate}">
							</span>
						</div>
						<div class="tableRow goal">
							<span class="tableCell td2">그룹 종료일</span> 
							<span class="tableCell td4">
								<input type="date" id="endDate" name="endDate" value="${endDate}">
							</span>
						</div>
				</div>
				</div>
			</div>
		</div>
 		<c:set var="now" value="<%=new java.util.Date()%>"/>
 		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate"></fmt:formatDate>	 
 		<c:choose>
			<c:when test="${endDate < nowDate}">	
				<div class="card-content2">
					<div class="card shadow card-body">
						<div class="font-color card-title">유지기간이 만료되어 종료된 그룹입니다.</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>  
				<div class="card-content2">
					<div class="card shadow card-body">
						<div class="font-color card-title">${grouplist.groupName} 진행도</div>
						<div class="chart-container"> 
							<canvas id="progressChart"></canvas> 
						</div>
		
					</div>
				</div>
  			</c:otherwise>
		</c:choose> 
 

	</div>
	
	</c:forEach>	
</div>

<script>
function editInfo(){
	$('#editBtn').hide();
	$('.complete').show();
	$('.cancel').show();
	
	$('#groupInfo').hide();
	$('#editInfo').show();
}

function complete(groupID){
	$.ajax({
		url: "./update",
		type: "POST",
		async: false,
		data: {
			id: groupID,
			desc: $('#desc').val(),
			startDate: $('#startDate').val(),
			endDate: $('#endDate').val()
		},
		success: function(data){
			$('#groupListContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
	
	$('#editBtn').show();
	$('.complete').hide();
	$('.cancel').hide();
	
	$('#groupInfo').show();
	$('#editInfo').hide();
}

function cancel(){
	$('#editBtn').show();
	$('.complete').hide();
	$('.cancel').hide();
	
	$('#groupInfo').show();
	$('#editInfo').hide();
}

	//차트 
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




<%-- 
<div class="table" id="recommendContent">
	
	<div class="tableRow">
		<span class="index-col tableCell th1">No.</span> <span class="tableCell th4">제목</span>
		<span class="tableCell th2">등록자</span> <span class="tableCell th1">난이도</span>
		<span class="like-col tableCell th1">추천수</span> <span class="comment-col tableCell th1">댓글수</span>
		<c:if test = "${!empty userID}">
			<span class="like-col tableCell th1">담기</span>
		</c:if>
		
		
	</div>
	
	<c:forEach items="${recoms}" var="recoms" varStatus="status">
	
		<div class="tableRow" id="recoms${recoms.id}">
			<span class="index-col tableCell td1 alignCenter" onclick="printAllContent(${recoms.id})">${status.count}</span> 
			<span class="tableCell td4 alignCenter readTitle" onclick="printAllContent(${recoms.id})">${recoms.title}</span> 
			<span class="tableCell td2 alignCenter" onclick="printAllContent(${recoms.id})">${recoms.nickname}</span> 
			<span class="tableCell td1 alignCenter" onclick="printAllContent(${recoms.id})"><img style="width: 60px;" alt="${recoms.difficulty}" src="./resources/img/difficulty${recoms.difficulty}.png"></span> 
			<span class="like-col tableCell td1 alignCenter readRecommend" onclick="printAllContent(${recoms.id})">${recoms.recomCount}</span> 

			<span class="comment-col tableCell td1 alignCenter readCommentCount" onclick="printAllContent(${recoms.id})">${recoms.recomCommentCount}</span> 
 			<c:if test = "${!empty userID}">
 					<span class="tableCell td1 alignCenter like-col">	
						<c:choose>
							<c:when test = "${recoms.userCart == '1'}">
								<span class="clicked-cart icon" onclick="deleteRecomCart(${recoms.id})"><span class="clicked-tooltip">장바구니에서 지울 수 있어요!</span></span>
							</c:when>
							<c:otherwise>							
								<span class="cart-icon icon" onclick="addRecomCart(${recoms.id})"><span class="cart-tooltip">장바구니에 담아보세요!</span></span>
							</c:otherwise>	
						</c:choose>		
					</span>										
			</c:if>	 				
		</div>
	</c:forEach>
</div>

<br> <br>

<!-- pagination -->
<div class="table" style="text-align: center">
	<ul class="pagination ">
		<c:if test="${ page eq 1 }">
			<li class="disable-button"><span class="arrow-button"><i class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:if test="${ page != 1 }">
			<li class="waves-effect"><span class="arrow-button" onclick="search(${page-1})"><i class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:forEach var="p" begin="${s_page}" end="${e_page}">
			<c:if test="${ p eq page }">
				<li id="recentPage" class="active orange" value="${p}"><span class="pagination-button" >${p}</span></li>
			</c:if>
			<c:if test="${ p != page }">
				<li class="waves-effect"><span class="pagination-button" onclick="search(${p})">${p}</span></li>
			</c:if>
		</c:forEach>
		<c:if test="${ page eq e_page }">
			<li class="disable-button"><span class="arrow-button"><i class="material-icons">chevron_right</i></span></li>
		</c:if>
		<c:if test="${ page != e_page }">
			<li class="waves-effect"><span class="arrow-button" onclick="search(${page+1})"><i class="material-icons">chevron_right</i></span></li>
		</c:if>
	</ul>
</div>  --%>
