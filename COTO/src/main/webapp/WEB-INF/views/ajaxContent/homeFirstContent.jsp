<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- container -->
<div id="container">
	<!-- slide_wrap -->
	<div class="slide_wrap">
		<!-- slide_box -->
		<div class="slide_box">
        	<div class="slide_list clearfix">
         		<div class="slide_content slide01">
					<div class="col s12 m4 block" style = "display: inline-block; float: left;">
						<div class="icon-block center">
							<span class="icon icon-award"></span>
							<h5 class="small-title">오늘의 랭킹</h5>
							<ul class="fs-18 textList">
							<c:forEach items="${ranksToday}" var="rankToday" varStatus="status">
							<li class="ranking f_ranking" onclick="moveUserPage('${rankToday.nickName}')">
								<span class="bold">${status.count}.</span> ${rankToday.nickName} [${rankToday.cnt}문제]
							</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					<div class="col s12 m4 block" style = "display: inline-block; float: left;">
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
					<div class="col s12 m4 block" style = "display: inline-block; float: left;">
						<div class="icon-block center">
							<span class="icon icon-problem"></span>
							<h5 class="position-r small-title">
								새로운 문제<a href="./problemList" class="more" style = "right: -13px;">더보기 ></a>
							</h5>
							<ul class="fs-18 textList">
							<c:forEach items="${recentProblems}" var="recentProblem" varStatus="status">
							<li class="ranking" style = "font-size: 14px;">
							<span class="bold" style = "font-size: 14px;">${status.count}.</span>${recentProblem.name}<br>[${recentProblem.siteName}]</li>
							</c:forEach>
							</ul>
						</div>
					</div>
          		</div>
				<div class="slide_content slide02">
					<div class="col s12 m4 block" style = "display: inline-block; float: left;">
						<div class="icon-block center">
							<span class="icon icon-award"></span>
							<h5 class="small-title">누적 랭킹</h5>
							<ul class="fs-18 textList">
							<c:forEach items="${ranks}" var="rank" varStatus="status">
							<li class="ranking f_ranking" onclick="moveUserPage('${rank.nickName}')">
								<span class="bold">${status.count}.</span> ${rank.nickName} [${rank.cnt}문제]
							</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					<div class="col s12 m4 block" style = "display: inline-block; float: left;">
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
					<div class="col s12 m4 block" style = "display: inline-block; float: left;">
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
			</div>
		</div>
		<!-- //slide_box -->
	
		<div class="slide_btn_box">
			<button type="button" class="slide_btn_prev"><</button>
			<button type="button" class="slide_btn_next">></button>
		</div>
		
	    <ul class="slide_pagination"></ul>
	    
    </div>
	<!-- //slide_wrap -->
</div>
<!-- //container -->

<style>
.f_ranking:hover { color: orange; font-weight:bold;}
</style>
 <script>

function moveUserPage(nickName){
	location.href='./'+encodeURI(encodeURIComponent(nickName));
};

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