<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="com.walab.coding.model.UserDTO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<link href="../resources/css/problems.css" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/solvedProblem.css?asd" />
<script src="../resources/js/problems.js"></script>

<div id="SiteContainer" class="container">
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
					<div id="table">
						<c:forEach items="${goal}" var="goal" varStatus="status">
							<div class="tableRow">
								<span class="tableCell td2">목표</span> <span
									class="tableCell td4">${goal.goal}</span>
							</div>
							<div class="tableRow">
								<span class="tableCell td2">기간</span> 
								<span class="tableCell td4" style="font-size: 14px;"> <fmt:formatDate
										pattern="yyyy-MM-dd" value="${goal.startDate}" /> ~ <fmt:formatDate
										pattern="yyyy-MM-dd" value="${goal.endDate}" />
								</span>
							</div>
							<div class="tableRow">
								<span class="tableCell td2">총 문제수</span> <span
									class="tableCell td4">${goal.goalNum}문제</span>
							</div>
							<div class="tableRow">
								<span class="tableCell td2" style="font-size: 13px;">현재 푼 문제수</span> <span
									class="tableCell td4">${userSolvedP}문제</span>
							</div>
						</c:forEach>
					</div>
			</div>
		</div>

		<div class="card-content2">
			<div class="card shadow card-body" >
				<div class="font-color card-title">하루의 기록</div>
				<div class="myChart" style="overflow-x:scroll; width: 310px; height:200;">
					<div class="chartAreaWrapper" style="overflow-x:scroll; width:900px; height:200">
						<canvas id="myBarChart" width="900px" height="200"></canvas>
					</div>
				</div>
			</div>
		</div>

		<div class="card-content3">
			<div class="card shadow card-body">
				<div class="font-color card-title">현재 상황</div>
				<canvas id="myDoughnutChart" width="300vw" height="180px">
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
		

		<!-- 문제등록 모달 -->
		<div id="createProblem" class="container" style="display:none">
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
					<label for="last_name">입력한 Problems</label><br>
					<label class="helper-text">문제를 누르면 삭제할 수 있습니다.</label><br><br>
					<div id="confirmSite"></div>
				</div>
			</form>
		</div>
		
		<%@ include file="../inc/pagination.jsp"%>
		


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
				<span id="UuserProblemID" hidden></span>
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
								<input type="radio" name="difficulty" id="d1" value="1"
								 class="radioMrg" /> <label for="d1" class="diffCont">1</label>
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
						<div class="input-field col s2">
							<p>
								<input type="radio" name="difficulty" id="d0" value="0" checked
									 /> <label for="d0" class="diffCont">설정
									안함</label>
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
	<div>
		<div class="table" id="problemsContent">
			<%@ include file="../ajaxContent/recomCartContent.jsp"%>
		</div>
	
	</div>
</div>

<script>
var labels = new Array();
var dataForBar = new Array();
var dataForDoughnut = new Array();
var gN = ${goalNum};
var uP = ${userSolvedP};

<c:forEach items="${countSolvedProblemEachDay}" var="countList" >
	var json = new Object();
	labels.push("${countList.regDate}");
	dataForBar.push("${countList.countSolvedP}");
</c:forEach>

dataForDoughnut.push(gN);
dataForDoughnut.push(uP);
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

<%@ include file="../inc/footer.jsp"%>
