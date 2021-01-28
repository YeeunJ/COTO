<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="./resources/css/home.css?asd" />
<link rel="stylesheet" href="./resources/css/solvedProblem.css?asd" />

<%@ include file="./inc/header.jsp"%>

<script>
//정렬
document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('select');
    //var instances = M.FormSelect.init(elems, options);
});

// Or with jQuery
$(document).ready(function(){
    $('select').formSelect();
});

var instance = M.FormSelect.getInstance(elem);
instance.getSelectedValues();
instance.destroy();




function callModal() {
	createModel("#createProblem", "푼 문제 등록", addajax);
}


function addajax(){
	
	var probs;
	var siteId = [];
	var problem = [];
	var link = [];
	
	$('.problem').each(function(){
		
		var s_id = 0;
		var l = null;
		var p;
		var valueSplit = $(this).val().split(' ');
		
		if($(this).attr('name') == 0){ // link로 설정하는 경우
			l = valueSplit[0];
			console.log("link: "+l);
			
			var split = l.split('/');
			p = split[split.length-1];
			console.log("problem: "+split[split.length-1]);

		} else { // siteId 존재하는 경우
			s_id = $(this).attr('name');
			p = valueSplit[0];
		}
		
		siteId.push(s_id);
		problem.push(p);
		link.push(l);
		
		/* var p = {
			siteID: siteId,
			problem: valueSplit[0],
			link: link,
		}
		probs.push(p); */
		
		console.log($(this).val());
	});
	
	probs = {"siteId":siteId, "problem":problem, "link":link};

	for(var i=0 ; i<siteId.length ; i++) {
		console.log("TEST: "+siteId[i]+"/"+problem[i]+"/"+link[i]);
	}
	

   $.ajax({
        url : './createProblem',
        type: 'POST',
        data: {
        	"siteId":siteId, "problem":problem, "link":link
        },
        success: function(data) {
            alert('리스트에 추가하였습니다.');
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
	var siteId = $('#siteName').val();
	var array = ['', '백준', 'leetcode', 'SW expert academy', 'oncoder', 'goorm', 'leetcode[database]', 'link'];
	var site = $("#siteName option:selected").val();
	var value = document.getElementById("problems").value;
	var valueSplit = value.split(',');
	var data = $('#confirmSite').html();
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><input disabled name="'+siteId+'" value="'+valueSplit[i]+' ('+array[site]+')" id="last_name disabled" type="text" class="problem validate"/></div>';
		count++;
	}
	$('#confirmSite').html(data);
	document.getElementById("problems").value = "";
};

</script>

<!-- first section start- 문제 등록, 내 정보 -->
<div class="section no-pad-bot">
	<div id="myInfo" class="container center">
		<br> <br>
		<h3 class="main-title">혼자서는 힘든 코딩 연습, 친구들과 함께 해보세요!</h3>
		<h5 class="bold">매일 푼 문제를 등록하고 설정한 목표를 달성해보세요.</h5>
		<br>
		<div class="row center">
			<button id="register-button" class="probBtn" onclick="callModal()">문제 등록하러 가기 ></button>
		</div>
		<br> <br>
	</div>
</div>


<!-- second section start- 랭킹, 태그, 순위 -->
<div class="container">
	<div class="section second">
		<div class="row center">
			<div class="col s12 m4">
				<div class="icon-block">
					<span class="icon icon-award"></span>
					<h5 class="small-title">오늘의 랭킹</h5>
					<ul class="fs-18 list">
						<c:forEach items="${ranks}" var="rank" varStatus="status">
							<li class="ranking"><span class="bold">${status.count}.</span> ${rank.nickName}</li>
						<%-- 	<li class="ranking"><span class="bold">2.</span> ${user.name}</li>
						<li class="ranking"><span class="bold">3.</span> ${user.name}</li>
						<li class="ranking"><span class="bold">4.</span> ${user.name}</li> --%>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="col s12 m4">
				<div class="icon-block">
					<span class="icon icon-tag"></span>
					<h5 class="small-title">인기 태그</h5>
					<ul class="fs-18 list">
						<li class="tag"><span class="bold">#</span> DP</li>
						<li class="tag"><span class="bold">#</span> 수학</li>
						<li class="tag"><span class="bold">#</span> 조건문</li>
						<li class="tag"><span class="bold">#</span> 기본 200제</li>
						<li class="tag"><span class="bold">#</span> DP</li>
						<li class="tag"><span class="bold">#</span> 수학</li>
						<li class="tag"><span class="bold">#</span> 조건문</li>
						<li class="tag"><span class="bold">#</span> 기본 200제</li>
					</ul>
				</div>
			</div>

			<div class="col s12 m4">
				<div class="icon-block">
					<span class="icon icon-problem"></span>
					<h5 class="position-r small-title">
						문제 순위<a href="./recommendProblem" class="more">더보기 ></a>
					</h5>
					<ul class="fs-18 list">
						<li class="ranking"><span class="bold">1.</span> 백준 2097번</li>
						<li class="ranking"><span class="bold">2.</span> 백준 10020번</li>
						<li class="ranking"><span class="bold">3.</span> leetcode 27번</li>
						<li class="ranking"><span class="bold">4.</span> 백준 407번</li>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<br>
</div>
<!-- second finish -->
<div id="createProblem" class="container" style="display:none;">
	<form class="col s12">
		<div class="row">
			<div class="input-field col s4">
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
			<label for="last_name">입력한 Problems</label> <br> <br>
			<div id="confirmSite"></div>
		</div>
	</form>
</div>

<br>


<%@ include file="./inc/footer.jsp"%>
