<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel="stylesheet" href="./resources/css/home.css?asd" />
<link rel="stylesheet" href="./resources/css/solvedProblem.css?asd" />

  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
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
/*
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><div class="preloader-wrapper small active" style="width:20px; height:20px;"><div class="spinner-layer spinner-green-only"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div></div><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/>';
		count++;
	}
	$('.sweet-modal-content #confirmSite').html(data);
	$('#confirmSite').html(data);*/
	$(".sweet-modal-content #problems").val("");
	
	$.ajax({
        url : './crawling/'+siteName,
        type: 'POST',
        data: {
        	"problem": valueSplit,
        	"siteID": siteId,
        	"count": count
        },
        success: function(data){
            console.log(data);
            var data2 = $('.sweet-modal-content #confirmSite').html()+data;
        	$('.sweet-modal-content #confirmSite').html(data2);
            /*
        	for(var i in valueSplit){
            	count--;
            	id='confirmProblemValue'+count;
            	console.log(id);
            	deleteThis(id);
        	}
        	var data2 = $('.sweet-modal-content #confirmSite').html()+data;
        	$('.sweet-modal-content #confirmSite').html(data2);*/
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
};

function resetContent() {
	
	$('#createProblem #confirmSite').html("");
	$('#selectHtml').html(selectHtml);
	
}

/* $(document).ready(function(){
    $('.carousel').carousel();
  });
  
$('.carousel.carousel-slider').carousel({
    fullWidth: true,
    indicators: true
  });
  
autoplay();

function autoplay() {
    $('.carousel').carousel('next');
    setTimeout(autoplay, 4000);
} */
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


  
  <div id="container">
  
    <div class="slide_wrap">
      <div class="slide_box">
        <div class="slide_list clearfix">
        
          <div class="slide_content slide01">
				<div class="col s12 m4" style = "display: inline-block; width: 33%; height: 150px; float: left;">
					<div class="icon-block center">
						<span class="icon icon-award"></span>
						<h5 class="small-title">누적 랭킹</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${ranks}" var="rank" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span> ${rank.nickName} [${rank.cnt}문제]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4" style = "display: inline-block; width: 33%; height: 150px; float: left;">
					<div class="icon-block center">
						<span class="icon icon-tag"></span>
						<h5 class="small-title">인기 태그</h5>
						<ul class="fs-18 list">
							<c:forEach items="${tags}" var="tag">
								<li class="tag"><span class="bold">#</span>${tag.tag}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4" style = "display: inline-block; width: 33%; height: 150px; float: left;">
					<div class="icon-block center">
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
          
          <div class="slide_content slide02">
				<div class="col s12 m4" style = "display: inline-block; width: 33%; height: 150px; float: left;">
					<div class="icon-block center">
						<span class="icon icon-award"></span>
						<h5 class="small-title">오늘의 랭킹</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${ranksToday}" var="rankToday" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span> ${rankToday.nickName} [${rankToday.cnt}문제]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4" style = "display: inline-block; width: 33%; height: 150px; float: left;">
					<div class="icon-block center">
						<span class="icon icon-tag"></span>
						<h5 class="position-r small-title">
							새로운 추천 글<a href="./recommendProblem" class="more" style = "right: -29px;">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${recoms}" var="recom" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span>${recom.title} </li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4" style = "display: inline-block; width: 33%; height: 150px; float: left;">
					<div class="icon-block center">
						<span class="icon icon-problem"></span>
						<h5 class="position-r small-title">
							새로운 문제<a href="./problemList" class="more" style = "right: -13px;">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${recentProblems}" var="recentProblem" varStatus="status">
								<li class="ranking" style = "font-size: 14px;"><span class="bold" style = "font-size: 14px;">${status.count}.</span>${recentProblem.name}<br>[${recentProblem.siteName}]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
          </div>
          
        </div>
        <!-- // .slide_list -->
      </div>
      <!-- // .slide_box -->
      <div class="slide_btn_box">
        <button type="button" class="slide_btn_prev"><</button>
        <button type="button" class="slide_btn_next">></button>
      </div>
      <!-- // .slide_btn_box -->
      <ul class="slide_pagination"></ul>
      <!-- // .slide_pagination -->
    </div>
    <!-- // .slide_wrap -->
  </div>
  <!-- // .container -->
<!-- // .container -->



<%-- <!-- second section start- 랭킹, 태그, 순위 -->
<div  class="container">
	<div class="section second">
		<div class="row center">
		  <div class="carousel carousel-slider center">
		  <ul>
<!-- 			 <li><a href="#"><i class="material-icons gray-text left" style="margin-top: 20%;">chevron_left</i></a></li>
 -->			 <li><a href="#"><i class="material-icons gray-text right" style="margin-top: 20%;">chevron_right</i></a></li>
		  </ul>
		    <div class="carousel-item gray-text" href="#one!">
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-award"></span>
						<h5 class="small-title">누적 랭킹</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${ranks}" var="rank" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span> ${rank.nickName} [${rank.cnt}문제]</li>
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
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-award"></span>
						<h5 class="small-title">오늘의 랭킹</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${ranksToday}" var="rankToday" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span> ${rankToday.nickName} [${rankToday.cnt}문제]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-tag"></span>
						<h5 class="position-r small-title">
							새로운 추천 글<a href="./recommendProblem" class="more" style = "right: -29px;">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${recoms}" var="recom" varStatus="status">
								<li class="ranking"><span class="bold">${status.count}.</span>${recom.title} </li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col s12 m4">
					<div class="icon-block">
						<span class="icon icon-problem"></span>
						<h5 class="position-r small-title">
							새로운 문제<a href="./problemList" class="more" style = "right: -13px;">더보기 ></a>
						</h5>
						<ul class="fs-18 textList">
							<c:forEach items="${recentProblems}" var="recentProblem" varStatus="status">
								<li class="ranking" style = "font-size: 14px;"><span class="bold" style = "font-size: 14px;">${status.count}.</span>${recentProblem.name}<br>[${recentProblem.siteName}]</li>
							</c:forEach>
						</ul>
					</div>
				</div>
		    </div>
		    
<!-- 			<ul class="indicators">
				<li class="indicator-item gray active"><a href="#!"></a></li>
				<li class="indicator-item gray active"><a href="#!"></a></li>
			</ul> -->
		    
		  </div>

		</div>
	</div>
	<br>
</div> --%>
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
 
 <script>

 (function () {
     const slideList = document.querySelector('.slide_list');  // Slide parent dom
     const slideContents = document.querySelectorAll('.slide_content');  // each slide dom
     const slideBtnNext = document.querySelector('.slide_btn_next'); // next button
     const slideBtnPrev = document.querySelector('.slide_btn_prev'); // prev button
     const pagination = document.querySelector('.slide_pagination');
     const slideLen = slideContents.length;  // slide length
     const slideWidth = 800; // slide width
     const slideSpeed = 500; // slide speed
     const startNum = 0; // initial slide index (0 ~ 4)
     
     slideList.style.width = slideWidth * (slideLen + 2) + "px";
     
     // Copy first and last slide
     let firstChild = slideList.firstElementChild;
     let lastChild = slideList.lastElementChild;
     let clonedFirst = firstChild.cloneNode(true);
     let clonedLast = lastChild.cloneNode(true);

     // Add copied Slides
     slideList.appendChild(clonedFirst);
     slideList.insertBefore(clonedLast, slideList.firstElementChild);

     // Add pagination dynamically
     let pageChild = '';
     for (var i = 0; i < slideLen; i++) {
       pageChild += '<li class="dot';
       pageChild += (i === startNum) ? ' dot_active' : '';
       pageChild += '" data-index="' + i + '"><a href="#"></a></li>';
     }
     pagination.innerHTML = pageChild;
     const pageDots = document.querySelectorAll('.dot'); // each dot from pagination

     slideList.style.transform = "translate3d(-" + (slideWidth * (startNum + 1)) + "px, 0px, 0px)";

     let curIndex = startNum; // current slide index (except copied slide)
     let curSlide = slideContents[curIndex]; // current slide dom
     curSlide.classList.add('slide_active');

     /** Next Button Event */
     slideBtnNext.addEventListener('click', function() {
       if (curIndex <= slideLen - 1) {
         slideList.style.transition = slideSpeed + "ms";
         slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 2)) + "px, 0px, 0px)";
       }
       if (curIndex === slideLen - 1) {
         setTimeout(function() {
           slideList.style.transition = "0ms";
           slideList.style.transform = "translate3d(-" + slideWidth + "px, 0px, 0px)";
         }, slideSpeed);
         curIndex = -1;
       }
       curSlide.classList.remove('slide_active');
       pageDots[(curIndex === -1) ? slideLen - 1 : curIndex].classList.remove('dot_active');
       curSlide = slideContents[++curIndex];
       curSlide.classList.add('slide_active');
       pageDots[curIndex].classList.add('dot_active');
     });

     /** Prev Button Event */
     slideBtnPrev.addEventListener('click', function() {
       if (curIndex >= 0) {
         slideList.style.transition = slideSpeed + "ms";
         slideList.style.transform = "translate3d(-" + (slideWidth * curIndex) + "px, 0px, 0px)";
       }
       if (curIndex === 0) {
         setTimeout(function() {
           slideList.style.transition = "0ms";
           slideList.style.transform = "translate3d(-" + (slideWidth * slideLen) + "px, 0px, 0px)";
         }, slideSpeed);
         curIndex = slideLen;
       }
       curSlide.classList.remove('slide_active');
       pageDots[(curIndex === slideLen) ? 0 : curIndex].classList.remove('dot_active');
       curSlide = slideContents[--curIndex];
       curSlide.classList.add('slide_active');
       pageDots[curIndex].classList.add('dot_active');
     });

     /** Pagination Button Event */
     let curDot;
     Array.prototype.forEach.call(pageDots, function (dot, i) {
       dot.addEventListener('click', function (e) {
         e.preventDefault();
         curDot = document.querySelector('.dot_active');
         curDot.classList.remove('dot_active');
         
         curDot = this;
         this.classList.add('dot_active');

         curSlide.classList.remove('slide_active');
         curIndex = Number(this.getAttribute('data-index'));
         curSlide = slideContents[curIndex];
         curSlide.classList.add('slide_active');
         slideList.style.transition = slideSpeed + "ms";
         slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 1)) + "px, 0px, 0px)";
       });
     });
   })();
 </script>