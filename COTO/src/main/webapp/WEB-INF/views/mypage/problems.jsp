<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String fullHeader ="";
	if(((UserDTO)request.getSession().getAttribute("user")) == null){
		fullHeader = "../inc/mylogoutHeader.jsp";
	}else if(((UserDTO)request.getSession().getAttribute("user")).getIsAdmin() > 0){
		fullHeader = "../inc/myadminHeader.jsp";
	}else {
		fullHeader = "../inc/myloginHeader.jsp";
	}
%>
<jsp:include page= "<%=fullHeader%>" />

<link href="../resources/css/problems.css" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/solvedProblem.css?asd" />
<script src="../resources/js/problems.js"></script>

<script>
window.onload = function() {
<!-- Bar cahrt -->
var ctx1 = document.getElementById("myBarChart"); 
var labels = new Array();
var data = new Array();
<c:forEach items="${countSolvedProblemEachDay}" var="countList" >
	var json = new Object();
	labels.push("${countList.regDate}");
	data.push("${countList.countSolvedP}");
</c:forEach>

var myBarChart = new Chart(ctx1 , {
    type: 'bar',
    data: {
        labels: labels,
        datasets: [{
            label: '푼 문제수',
            data: data,
            borderColor: "rgba(255, 201, 14, 1)",
            backgroundColor: "rgba(255, 201, 14, 0.5)",
            fill: false,
        }]
    },
    options: {
        //responsive: true,
        hover: {
            mode: 'nearest',
            intersect: true
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                },
                ticks: {
                    autoSkip: false
                }
            }],
            yAxes: [{
                display: true,
                ticks: {
                    suggestedMin: 0,
                    stepSize: 1,
                },
                scaleLabel: {
                    display: true,
                }
            }]
        }
    }
});

<!-- Doughnut cahrt -->
data = { datasets: [{
	backgroundColor: ['#e8e8e8','rgba(255, 201, 14, 0.5)'], 
	data: [ ${goalNum}, ${userSolvedP} ] }],
	labels: ['총 문제수','푼 문제수']};
	
var ctx = document.getElementById("myDoughnutChart"); 
var myDoughnutChart = new Chart(ctx, { 
    type: 'doughnut', 
    data: data, 
    
    options: {
       legend: {
         display: true
       },
       cutoutPercentage: 65
    },
 });
 
/* Chart.pluginService.register({
	  beforeDraw: function(chart) {
	    var width = chart.chart.width,
	        height = chart.chart.height,
	        ctx = chart.chart.ctx;

	    ctx.restore();
	    var fontSize = (height / 160).toFixed(2);
	    ctx.font = fontSize + "em sans-serif";
	    ctx.textBaseline = "middle";

	    var text =  ${userSolvedP}/${goalNum}*100+"%" ,
	        textX = Math.round((width - ctx.measureText(text).width) / 2),
	        textY = height / 1.7;

	    ctx.fillText(text, textX, textY);
	    ctx.save();
	  }
	}); */
}

var selectHtml="";

function callModal() {
	selectHtml = $('#selectHtml').html();
	
	createModel("#createProblem", "푼 문제 등록", addajax);
 	$('select').formSelect();
}

function addajax(){
	
	var siteId = [];
	var problem = [];
	var link = [];
	
	$('.sweet-modal-content .problem').each(function(){
		
		var s_id = 0;
		var l = "";
		var p;
		
		var valueSplit = $(this).val().split(' (');
		
		if($(this).attr('name') == 0){ // link로 설정하는 경우
			l = valueSplit[0].trim();
			console.log("link: "+l);
			
			var split = l.split('/');
			p = split[split.length-1].trim();
			console.log("problem: "+split[split.length-1].trim());

		} else { // siteId 존재하는 경우
			s_id = $(this).attr('name');
			p = valueSplit[0].trim();
		}
		
		siteId.push(s_id);
		problem.push(p);
		link.push(l);
		
	});
		
	console.log(problem);
	console.log(siteId);
	console.log(link);
	
   $.ajax({
        url : 'problems/createProblem',
        type: 'POST',
        data: {
        	"siteId":siteId, "problem":problem, "link":link
        },
        success: function(data){
        	resetContent();
        	console.log("success");
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });

}

function deleteThis(id){
	var allid = "#"+id;
	$(allid).remove();
}

var count=0;
function insertProblems(){
	
	var siteName = $(".sweet-modal-content #siteName option:selected").text();
	var siteId = $('.sweet-modal-content #siteName').val();
	console.log("siteId: "+siteId);
	var site = $(".sweet-modal-content #siteName option:selected").val();
	var value = $(".sweet-modal-content #problems").val();
	console.log(value);
	var valueSplit = value.split(',');
	var data = $('.sweet-modal-content #confirmSite').html();
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate"/></div>';
		count++;
	}
	$('.sweet-modal-content #confirmSite').html(data);
	$('#confirmSite').html(data);
	$(".sweet-modal-content #problems").val("");
};

function resetContent() {
	
	$('#createProblem #confirmSite').html("");
	$('#selectHtml').html(selectHtml);
	
}
</script>
<style>
#problem {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

#problem:before {
	content: "";
	background-image: url("../resources/img/problem.png");
	background-size: cover;
	top: 0;
	left: 0;
	right: 0px;
	bottom: 0px;
	position: absolute;
	opacity: 0.4;
	z-index: -1;
}
</style>

<div class="container">
	<div id="problem">
		<div class="content">
			<h4>내가 푼 문제</h4>
			<p>지금까지 자신이 푼 문제와 목표 현황을 확인해 보세요!</p>
		</div>
	</div>

	<!-- Content Row -->
	<div class="card-wrap">
		<div class="card-content1">
			<div class="card shadow card-body">
				<div class="font-color card-title">나의 목표</div>
				<div>
					<div id="table">
						<c:forEach items="${goal}" var="goal" varStatus="status">
							<div class="tableRow box">
								<span class="tableCell td1">목표</span> <span
									class="tableCell td4">${goal.goal}</span>
							</div>
							<div class="tableRow box">
								<span class="tableCell td1">기간</span> <span
									class="tableCell td4"> <fmt:formatDate
										pattern="yyyy-MM-dd" value="${goal.startDate}" /> ~ <fmt:formatDate
										pattern="yyyy-MM-dd" value="${goal.endDate}" />
								</span>
							</div>
							<div class="tableRow box">
								<span class="tableCell td2">총 문제수</span> <span
									class="tableCell td4">${goal.goalNum}개</span>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<div class="card-content2">
			<div class="card shadow card-body">
				<div class="font-color card-title">하루의 기록</div>
				<canvas id="myBarChart" width="200" height="130"></canvas>
			</div>
		</div>

		<div class="card-content3">
			<div class="card shadow card-body">
				<div class="font-color card-title">현재 상황</div>
				<canvas id="myDoughnutChart" width="200" height="110">
				</canvas>
			</div>
		</div>
	</div>

	<!-- Content Row -->
	<div>
		<br>
		<h5 class="font-color">내가 푼 문제들</h5>

		<fieldset class="search" style="float: right;">
			<input id="searchValue" class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button id="searchButton" class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset>
		<button onclick="callModal()" id="register-button" class="mybtn"
			style="margin-top: 2%; float: left;">문제 등록하기</button>

		<div class="table" id="problemsContent">
			<%@ include file="../ajaxContent/problemsContent.jsp"%>
		</div>
		<div class="table" style="text-align: center">
			<ul class="pagination ">
				<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
				<li class="active orange"><a href="#!">1</a></li>
				<li class="waves-effect"><a href="#!">2</a></li>
				<li class="waves-effect"><a href="#!">3</a></li>
				<li class="waves-effect"><a href="#!">4</a></li>
				<li class="waves-effect"><a href="#!">5</a></li>
				<li class="waves-effect"><a href="#!"><i
						class="material-icons">chevron_right</i></a></li>
			</ul>
		</div>
		
		<!-- 문제등록 모달 -->
		<div id="createProblem" class="container" hidden>
			<form class="col s12">
				<div class="row">
					<div id="selectHtml" class="input-field col s4">
						<select id="siteName" required>
							<optgroup label="코딩사이트 선택">
								<c:forEach items="${CodingSite}" var="site">
									<option value="${ site.id }">${ site.siteName }</option>
								</c:forEach>
							</optgroup>
							<optgroup label="링크로 입력">
								<option value="0">링크로 입력</option>
							</optgroup>
						</select> <label>코딩사이트 선택</label> <span class="helper-text">코딩 사이트를
							선택해서 입력하거나 링크로 입력할 수 있습니다.</span>
					</div>
					<div class="input-field col s6">
						<input id="problems" type="text" class="validate"> <label
							for="problems">Problems</label> <span class="helper-text">문제들을
							입력할 때 ,로 구분해주세요!!</span>
					</div>
					<button type="button" id="add" class="modal_button lighten-1"
						onClick="insertProblems()">추가</button>
				</div>
				<div class="input-field col s10">
					<label for="last_name">입력한 Problems</label><br> <label
						class="helper-text">문제를 누르면 삭제할 수 있습니다.</label><br>
					<br>
					<div id="confirmSite"></div>
				</div>
			</form>
		</div>


		<!-- 모달 -->
		<div id="readSolvedProblem" hidden>
			<div class="row mrg">
				<p class="title">문제 제목</p>
				<span id="problemName" class="box"></span>
			</div>
			<div class="row mrg">
				<p class="title">사이트 이름</p>
				<span id="site" class="box"></span>
			</div>
			<div class="row mrg">
				<p class="title">날짜</p>
				<span id="regdate" class="box"></span>
			</div>
			<div class="row mrg">
				<p class="title">난이도</p>
				<span id="difficulty" class="box"></span>
			</div>
			<p class="title">메모</p>
			<span id="memo" class="box"></span>
		</div>
		<div id="updateSolvedProblem" hidden>
			<form>
				<!-- <span id="UuserProblemID" style=""></span> -->
				<div class="row mrg">
					<p class="title">문제 제목</p>
					<span id="UproblemName" class="box"></span>
				</div>
				<div class="row mrg">
					<p class="title">사이트 이름</p>
					<span id="Usite" class="box"></span>
				</div>
				<div class="row mrg">
					<p class="title">날짜</p>
					<span id="Uregdate" class="box"></span>
				</div>
				<div class="row mrg">
					<p class="title">난이도</p>
					<div class="row">
						<div class="input-field col s2">
							<p>
								<input type="radio" name="difficulty" id="d1" value="1" checked />
								<label for="d1" class="diffCont">1</label>
							</p>
						</div>
						<div class="input-field col s2">
							<p>
								<input type="radio" name="difficulty" id="d2" value="2"
									class="radioMrg" /> <label for="d2" class="diffCont">2</label>
							</p>
						</div>
						<div class="input-field col s2">
							<p>
								<input type="radio" name="difficulty" id="d3" value="3"
									class="radioMrg" /> <label for="d3" class="diffCont">3</label>
							</p>
						</div>
						<div class="input-field col s2">
							<p>
								<input type="radio" name="difficulty" id="d4" value="4"
									class="radioMrg" /> <label for="d4" class="diffCont">4</label>
							</p>
						</div>
						<div class="input-field col s2">
							<p>
								<input type="radio" name="difficulty" id="d5" value="5"
									class="radioMrg" /> <label for="d5" class="diffCont">5</label>
							</p>
						</div>

					</div>
				</div>
				<p class="title">메모</p>
				<textarea id="Umemo" type="text" class="validate"
					placeholder="이 문제에 메모하고 싶은 내용을 적어주세요!!"></textarea>
			</form>
		</div>

	</div>
</div>

<%@ include file="../inc/footer.jsp"%>
