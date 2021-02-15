<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!-- container_second -->
<div id="container_second">
	<!-- slide_wrap_second -->
	<div class="slide_wrap_second">
		<!-- slide_box_second -->
		<div class="slide_box_second">
        	<div class="slide_list_second clearfix_second">
				<div class="slide_content_second slide01">	
					<div class="col s12 m4" style = "display: inline-block; width: 50%; height: 150px; float: left;">					
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
					<div class="col s12 m4" style = "display: inline-block; width: 50%; height: 150px; float: left;">				
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
				</div>
		        <div class="slide_content_second slide02">
					<div class="col s12 m4" style = "display: inline-block; width: 50%; height: 150px; float: left;">				
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
					<div class="col s12 m4" style = "display: inline-block; width: 50%; height: 150px; float: left;">								
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
         		<div class="slide_content_second slide03">
					<div class="col s12 m4" style = "display: inline-block; width: 50%; height: 150px; float: left;">
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
					<div class="col s12 m4" style = "display: inline-block; width: 50%; height: 150px; float: left;">				
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
		  		</div>
        	</div>
		</div>
		<!-- //slide_box_second -->
	
		<div class="slide_btn_box_second">
			<button type="button" class="slide_btn_prev_second"><</button>
			<button type="button" class="slide_btn_next_second">></button>
		</div>
		
	    <ul class="slide_pagination_second"></ul>
	    
    </div>
	<!-- //slide_wrap_second -->
</div>
<!-- //container_second -->

  <script>
    (function () {
      const slideList_second = document.querySelector('.slide_list_second');  // Slide parent dom
      const slideContents_second = document.querySelectorAll('.slide_content_second');  // each slide dom
      const slideBtnNext_second = document.querySelector('.slide_btn_next_second'); // next button
      const slideBtnPrev_second = document.querySelector('.slide_btn_prev_second'); // prev button
      const pagination_second = document.querySelector('.slide_pagination_second');
      const slideLen_second = slideContents_second.length;  // slide length
      const slideWidth_second = 500; // slide width
      const slideSpeed_second = 300; // slide speed
      const startNum_second = 0; // initial slide index (0 ~ 4)
      
      slideList_second.style.width = slideWidth_second * (slideLen_second + 2) + "px";
      
      // Copy first and last slide
      let firstChild = slideList_second.firstElementChild;
      let lastChild = slideList_second.lastElementChild;
      let clonedFirst = firstChild.cloneNode(true);
      let clonedLast = lastChild.cloneNode(true);

      // Add copied Slides
      slideList_second.appendChild(clonedFirst);
      slideList_second.insertBefore(clonedLast, slideList_second.firstElementChild);

      // Add pagination_second dynamically
      let pageChild = '';
      for (var i = 0; i < slideLen_second; i++) {
        pageChild += '<li class="dot';
        pageChild += (i === startNum_second) ? ' dot_active' : '';
        pageChild += '" data-index="' + i + '"><a href="#"></a></li>';
      }
      pagination_second.innerHTML = pageChild;
      const pageDots = document.querySelectorAll('.dot'); // each dot from pagination_second

      slideList_second.style.transform = "translate3d(-" + (slideWidth_second * (startNum_second + 1)) + "px, 0px, 0px)";

      let curIndex = startNum_second; // current slide index (except copied slide)
      let curSlide = slideContents_second[curIndex]; // current slide dom
      curSlide.classList.add('slide_active');

      /** Next Button Event */
      slideBtnNext_second.addEventListener('click', function() {
        if (curIndex <= slideLen_second - 1) {
          slideList_second.style.transition = slideSpeed_second + "ms";
          slideList_second.style.transform = "translate3d(-" + (slideWidth_second * (curIndex + 2)) + "px, 0px, 0px)";
        }
        if (curIndex === slideLen_second - 1) {
          setTimeout(function() {
            slideList_second.style.transition = "0ms";
            slideList_second.style.transform = "translate3d(-" + slideWidth_second + "px, 0px, 0px)";
          }, slideSpeed_second);
          curIndex = -1;
        }
        curSlide.classList.remove('slide_active');
        pageDots[(curIndex === -1) ? slideLen_second - 1 : curIndex].classList.remove('dot_active');
        curSlide = slideContents_second[++curIndex];
        curSlide.classList.add('slide_active');
        pageDots[curIndex].classList.add('dot_active');
      });

      /** Prev Button Event */
      slideBtnPrev_second.addEventListener('click', function() {
        if (curIndex >= 0) {
          slideList_second.style.transition = slideSpeed_second + "ms";
          slideList_second.style.transform = "translate3d(-" + (slideWidth_second * curIndex) + "px, 0px, 0px)";
        }
        if (curIndex === 0) {
          setTimeout(function() {
            slideList_second.style.transition = "0ms";
            slideList_second.style.transform = "translate3d(-" + (slideWidth_second * slideLen_second) + "px, 0px, 0px)";
          }, slideSpeed_second);
          curIndex = slideLen_second;
        }
        curSlide.classList.remove('slide_active');
        pageDots[(curIndex === slideLen_second) ? 0 : curIndex].classList.remove('dot_active');
        curSlide = slideContents_second[--curIndex];
        curSlide.classList.add('slide_active');
        pageDots[curIndex].classList.add('dot_active');
      });

      /** Pagination_second Button Event */
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
          curSlide = slideContents_second[curIndex];
          curSlide.classList.add('slide_active');
          slideList_second.style.transition = slideSpeed_second + "ms";
          slideList_second.style.transform = "translate3d(-" + (slideWidth_second * (curIndex + 1)) + "px, 0px, 0px)";
        });
      });
    })();
  </script>