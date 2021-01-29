<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../inc/header2.jsp"%>

<link href="../resources/css/problems.css" rel="stylesheet">
<script src="../resources/js/problems.js"></script>

<script>
window.onload = function() {
<!-- Bar cahrt -->
var ctx = document.getElementById("myBarChart"); 
var myBarChart = new Chart(ctx , {
    type: 'bar',
    data: {
        labels: ['01.14','01.18'],
        datasets: [{
            label: '푼 문제수',
            data: [
                10, 5
            ],
            borderColor: "rgba(255, 201, 14, 1)",
            backgroundColor: "rgba(255, 201, 14, 0.5)",
            fill: false,
        }]
    },
    options: {
        responsive: true,
        tooltips: {
            mode: 'index',
            intersect: false,
            callbacks: {
                title: function(tooltipItems, data) {
                    return data.labels[tooltipItems[0].datasetIndex];
                }
            }
        },
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
	backgroundColor: ['lightblue','yellow'], 
	data: [ ${goalNum}, ${userSolvedP} ] }],
	labels: ['총 문제수','푼 문제수']};
	
var ctx1 = document.getElementById("myDoughnutChart"); 
var myDoughnutChart = new Chart(ctx1, { 
    type: 'doughnut', 
    data: data, 
    options: {
       legend: {
         display: false
       }
    } 
 });

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

.font-color {
	color: #e69138ff;
}

.search {
	border: none;
	padding: 0px !important;
}

.card-wrap {
	display: flex;
}

.card-content1, .card-content2, .card-content3 {
	flex: 1;
}

.card-content1, .card-content2 {
	margin-right: 15px;
}

.card-body {
	border-radius: 10%;
	height: 250px;
	padding: 10px;
}

.card-title {
	font-size: 20px !important;
	padding: 2% 2% 4% 2%;
	
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
								<span class="tableCell td1">목표</span>
								<span class="tableCell td4">${goal.goal}</span>
							</div>
							<div class="tableRow box">
								<span class="tableCell td1">기간</span>
								<span class="tableCell td4">
								<fmt:formatDate pattern="yyyy-MM-dd" value="${goal.startDate}" /> 
								~ <fmt:formatDate pattern="yyyy-MM-dd" value="${goal.endDate}" />
								</span>
							</div>
							<div class="tableRow box">
								<span class="tableCell td2">총 문제수</span>
								<span class="tableCell td4">${goal.goalNum}개</span>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<div class="card-content2">
			<div class="card shadow card-body">
				<div class="font-color card-title">오늘 해야 할 양</div>
				<canvas id="myBarChart" width="200" height="90"></canvas>
			</div>
		</div>

		<div class="card-content3">
			<div class="card shadow card-body">
				<div class="font-color card-title">현재 상황</div>
				<canvas id="myDoughnutChart" width="200" height="90"></canvas>
				<div class="" style="margin-left:25%; margin-top:5%;">
					<span> 
						<i class="fas fa-circle" style="color: lightblue;"></i> 총 문제수
					</span> 
					<span> 
						<i class="fas fa-circle" style="color: yellow;"></i> 푼 문제수
					</span>
				</div>
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
		<button id="register-button" class="mybtn" style="margin-top: 2%; float: left;">문제 등록하기</button>

		<div class="table" id="problemsContent">
			<%@ include file="../ajaxContent/problemsContent.jsp"%>
		</div>
		
		<div id="registerSolvedProblem" hidden>
			<div class="container">
				<form class="col s12">
					<div class="row">
						<div class="input-field col s4">
							<select id="siteName" required>
								<optgroup label="코딩사이트 선택">
									<option value="" disabled selected>코딩사이트 별 입력</option>
									<option value="1">백준</option>
									<option value="2">leetcode</option>
									<option value="3">SW expert academy</option>
									<option value="4">programmers</option>
									<option value="5">oncoder</option>
									<option value="6">goorm</option>
									<option value="7">leetcode(database)</option>
								</optgroup>
								<optgroup label="링크로 입력">
									<option value="8">링크로 입력</option>
								</optgroup>
							</select> <label>코딩사이트 선택</label> <span class="helper-text">코딩 사이트를
								선택해서 입력하거나 링크로 입력할 수 있습니다.</span>
						</div>
						<div class="input-field col s6">
							<input id="problems" type="text" class="validate"> <label
								for="problems">Problems</label> <span class="helper-text">문제들을
								입력할 때 ,로 구분해주세요!!</span>
						</div>
						<button type="button" id="add"
							class="modal_button green lighten-1" onClick="insertProblems()">추가</button>
					</div>
					<div class="input-field col s10">
						<label for="last_name">입력한 Problems</label> <br> <br>
						<div id="confirmSite"></div>
					</div>
				</form>
			</div>
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
			<form >
				<span id="UuserProblemID" style=""></span>
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
					<input id="Udifficulty" type="text" class="validate" placeholder="1~5사이 숫자로 난이도를 표현하세요!!">
				</div>
				<p class="title">메모</p>
				<textarea id="Umemo" type="text" class="validate" placeholder="이 문제에 메모하고 싶은 내용을 적어주세요!!"></textarea>
			</form>
		</div>

	</div>
</div>

<%@ include file="../inc/footer.jsp"%>
