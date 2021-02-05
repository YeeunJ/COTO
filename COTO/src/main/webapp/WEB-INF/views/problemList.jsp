<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%
	String fullHeader ="";
	if(((UserDTO)request.getSession().getAttribute("user")) == null){
		fullHeader = "./inc/logoutHeader.jsp";
	}else if(((UserDTO)request.getSession().getAttribute("user")).getIsAdmin() > 0){
		fullHeader = "./inc/adminHeader.jsp";
	}else {
		fullHeader = "./inc/loginHeader.jsp";
	}
%>
<jsp:include page= "<%=fullHeader%>" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
<link rel="stylesheet" href="./resources/css/solvedProblem.css?a" />
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
</style>

<div id="SiteContainer" class="container">
	<div id="problem">
		<div class="content">
			<h4>문제 리스트</h4>
			<p>다른 사람들이 많이 푼 문제를 확인하고 풀어보세요!</p>
		</div>
	</div>
	
	<div id="canvas-holder" style="width:40%">
		<canvas id="chart-area"></canvas>
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
				<option value="regdate" disabled selected>정렬</option>
				<option value="title">제목순</option>
				<option value="site">사이트순</option>
				<option value="regdate">최신순</option>
				<option value="solve">많이 풀어본 문제순</option>
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

<c:forEach items="${ratio}" var="r">
	var list = new Object();
	list.siteName = "${r.siteName}";
	list.siteID = ${r.siteID};
	list.ratio = ${r.ratio};
	
	ratioBySite.push(list);
</c:forEach>

var randomScalingFactor = function() {
	return Math.round(Math.random() * 100);
};

var newLabels = [];
var newData = [];
var newColor=[];
var chartColors=[window.chartColors.red,
				window.chartColors.orange,
				window.chartColors.yellow,
				window.chartColors.green,
				window.chartColors.blue,
				window.chartColors.purple,
				window.chartColors.grey,
				];
// newDataset에 데이터 삽입
for (var i=0 ; i< ratioBySite.length ; i++){
	newData.push(ratioBySite[i].ratio);
	newLabels.push(ratioBySite[i].siteName);
	newColor.push(chartColors[i]);
}

function randomColor() {
    var r = Math.floor(Math.random() * 255);
    var g = Math.floor(Math.random() * 255);
    var b = Math.floor(Math.random() * 255);
    return "rgba(" + r + "," + g + "," + b + ",0.8)";
}


var config = {
	type: 'doughnut',
	data: {
		datasets: [{
			data: newData,
			backgroundColor: newColor,
			label: 'Dataset 1'
		}],
		labels: newLabels
	},
	options: {
		responsive: true,
		legend: {
			display:false
		},
		title: {
			display: true,
			text: '코딩 사이트별 이용률'
		},
		animation: {
			animateScale: true,
			animateRotate: true
		}
	}
};

window.onload = function() {
	var ctx = document.getElementById('chart-area').getContext('2d');
	window.myDoughnut = new Chart(ctx, config);
};


/* var colorNames = Object.keys(window.chartColors);
document.getElementById('addDataset').addEventListener('click', function() {
	var newDataset = {
		backgroundColor: [],
		data: [],
		label: 'New dataset ' + config.data.datasets.length,
	};

	for (var index = 0; index < config.data.labels.length; ++index) {
		newDataset.data.push(randomScalingFactor());

		var colorName = colorNames[index % colorNames.length];
		var newColor = window.chartColors[colorName];
		newDataset.backgroundColor.push(newColor);
	}

	config.data.datasets.push(newDataset);
	window.myDoughnut.update();
});
 */



console.log(${pagination.range});
console.log(${pagination.page});
console.log(${pagination.rangeSize});
console.log(${pagination.startPage});
console.log(${pagination.endPage});
function readProblemByPage(){
	$.ajax({
        url : './recommendProblem/addTag',
        type: 'POST',
        data: {
        	"tag":tag
        },
        success: function(data) {
            alert('리스트에 추가하였습니다.');
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
}

//이전 버튼 이벤트
function fn_prev(page, range, rangeSize) {

		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		var url = "${pageContext.request.contextPath}/board/getBoardList";

		url = url + "?page=" + page;
		url = url + "&range=" + range;

		location.href = url;
}



//페이지 번호 클릭
function fn_pagination(page, range, rangeSize, searchType, keyword) {
	var url = "${pageContext.request.contextPath}/board/getBoardList";

	url = url + "?page=" + page;
	url = url + "&range=" + range;
	location.href = url;	

}

//다음 버튼 이벤트
function fn_next(page, range, rangeSize) {

	var page = parseInt((range * rangeSize)) + 1;
	var range = parseInt(range) + 1;
	var url = "${pageContext.request.contextPath}/board/getBoardList";

	url = url + "?page=" + page;
	url = url + "&range=" + range;

	location.href = url;

}

</script>

<%@ include file="./inc/footer.jsp"%>

