<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>
<link rel="stylesheet" href="./resources/css/home.css?asd" />
<link rel="stylesheet" href="./resources/css/solvedProblem.css?asd" />

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

<script>
//정렬
/* document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('select');
    //var instances = M.FormSelect.init(elems, options);
});
 */
// Or with jQuery

/* var instance = M.FormSelect.getInstance(elem);
instance.getSelectedValues();
instance.destroy(); */



var selectHtml="";

function callModal() {
	selectHtml = $('#selectHtml').html();
	
	createModel("#createProblem", "푼 문제 등록", addajax);
 	//$('select').formSelect();
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
        url : './createProblem',
        type: 'POST',
        data: {
        	"siteId":siteId, "problem":problem, "link":link
        },
        success: function(data){
        	resetContent();
        	console.log("success");
        },
        error:function(request,status,error){
        	resetContent();
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
$(document).ready(function(){
    $('.carousel').carousel();
  });
$('.carousel.carousel-slider').carousel({
    fullWidth: true,
    indicators: true
  });
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
			<!-- onclick="callModal()" -->
		</div>
		<br> <br>
	</div>
</div>


<!-- second section start- 랭킹, 태그, 순위 -->
<div  class="container">
	<div class="section second">
		<div class="row center">
		  
		  <div class="carousel carousel-slider center">
		    <div class="carousel-fixed-item center">
<!-- 		      <a class="btn waves-effect white grey-text darken-text-2">button</a>
 -->		    </div>
		    <div class="carousel-item gray-text" href="#one!">
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-award"></span>
						<h5 class="small-title">오늘의 랭킹</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${ranks}" var="rank" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span> ${rank.nickName} (${rank.cnt}문제)</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-tag"></span>
						<h5 class="small-title">인기 태그</h5>
						<ul class="fs-18 list">
							<c:forEach items="${tags}" var="tag">
								<li class="tag"><span class="bold">#</span>${tag.tag}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-problem"></span>
						<h5 class="position-r small-title">
							문제 순위<a href="./recommendProblem" class="more">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${problems}" var="problem" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span>${problem.problem} [${problem.site}]</li>
							</c:forEach>
						</ul>
					</div>
			</div>

		    </div>

		    <div class="carousel-item gray-text" href="#two!">
		      <h2>Second Panel</h2>
		      <p class="white-text">This is your second panel</p>
		    </div>
		    <ul class="indicators">
		    	<li class="indicator-item gray"></li>
		    	<li class="indicator-item  gray active"></li>
		    </ul>
		  </div>
		  
			<div class="col s12 m4 carousel-item">
				<div class="icon-block">
					<span class="icon icon-award"></span>
					<h5 class="small-title">오늘의 랭킹</h5>
					<ul class="fs-18 textList">
						<c:forEach items="${ranks}" var="rank" varStatus="status">
							<li class="ranking"><span class="bold">${status.count}.</span> ${rank.nickName}</li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="col s12 m4 carousel-item">
				<div class="icon-block">
					<span class="icon icon-tag"></span>
					<h5 class="small-title">인기 태그</h5>
					<ul class="fs-18 list">
						<c:forEach items="${tags}" var="tag">
							<li class="tag"><span class="bold">#</span>${tag.tag}</li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="col s12 m4 carousel-item">
				<div class="icon-block">
					<span class="icon icon-problem"></span>
					<h5 class="position-r small-title">
						문제 순위<a href="./recommendProblem" class="more">더보기 ></a>
					</h5>
					<ul class="fs-18 textList">
						<c:forEach items="${problems}" var="problem" varStatus="status">
							<li class="ranking"><span class="bold">${status.count}.</span>${problem.problem} [${problem.site}]</li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<br>
</div>
<!-- second finish -->

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

<br>


<%@ include file="./inc/footer.jsp"%>
<%-- <%@ include file="./solvedProblem.jsp"%>
 --%>