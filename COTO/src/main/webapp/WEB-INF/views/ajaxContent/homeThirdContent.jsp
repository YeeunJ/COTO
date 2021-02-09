<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <div id="container">
  
    <div class="slide_wrap_third">
      <div class="slide_box_third">
        <div class="slide_list_third clearfix_third">
        
          <div class="slide_content_third slide01">
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
          <div class="slide_content_third slide02">
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
          <div class="slide_content_third slide03">			
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
          
          <div class="slide_content_third slide04">
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
          <div class="slide_content_third slide05">
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
          <div class="slide_content_third slide06">
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
        <!-- // .slide_list -->
      </div>
      <!-- // .slide_box -->
      <div class="slide_btn_box_third">
        <button type="button" class="slide_btn_prev_third"><</button>
        <button type="button" class="slide_btn_next_third">></button>
      </div>
      <!-- // .slide_btn_box -->
      <ul class="slide_pagination_third"></ul>
      <!-- // .slide_pagination -->
    </div>
    <!-- // .slide_wrap -->
  </div>
  <!-- // .container -->
<!-- // .container -->  



  <script>
    (function () {
      const slideList_third = document.querySelector('.slide_list_third');  // Slide parent dom
      const slideContents_third = document.querySelectorAll('.slide_content_third');  // each slide dom
      const slideBtnNext_third = document.querySelector('.slide_btn_next_third'); // next button
      const slideBtnPrev_third = document.querySelector('.slide_btn_prev_third'); // prev button
      const pagination_third = document.querySelector('.slide_pagination_third');
      const slideLen_third = slideContents_third.length;  // slide length
      const slideWidth_third = 300; // slide width
      const slideSpeed_third = 300; // slide speed
      const startNum_third = 0; // initial slide index (0 ~ 4)
      
      slideList_third.style.width = slideWidth_third * (slideLen_third + 2) + "px";
      
      // Copy first and last slide
      let firstChild = slideList_third.firstElementChild;
      let lastChild = slideList_third.lastElementChild;
      let clonedFirst = firstChild.cloneNode(true);
      let clonedLast = lastChild.cloneNode(true);

      // Add copied Slides
      slideList_third.appendChild(clonedFirst);
      slideList_third.insertBefore(clonedLast, slideList_third.firstElementChild);

      // Add pagination_third dynamically
      let pageChild = '';
      for (var i = 0; i < slideLen_third; i++) {
        pageChild += '<li class="dot';
        pageChild += (i === startNum_third) ? ' dot_active' : '';
        pageChild += '" data-index="' + i + '"><a href="#"></a></li>';
      }
      pagination_third.innerHTML = pageChild;
      const pageDots = document.querySelectorAll('.dot'); // each dot from pagination_third

      slideList_third.style.transform = "translate3d(-" + (slideWidth_third * (startNum_third + 1)) + "px, 0px, 0px)";

      let curIndex = startNum_third; // current slide index (except copied slide)
      let curSlide = slideContents_third[curIndex]; // current slide dom
      curSlide.classList.add('slide_active');

      /** Next Button Event */
      slideBtnNext_third.addEventListener('click', function() {
        if (curIndex <= slideLen_third - 1) {
          slideList_third.style.transition = slideSpeed_third + "ms";
          slideList_third.style.transform = "translate3d(-" + (slideWidth_third * (curIndex + 2)) + "px, 0px, 0px)";
        }
        if (curIndex === slideLen_third - 1) {
          setTimeout(function() {
            slideList_third.style.transition = "0ms";
            slideList_third.style.transform = "translate3d(-" + slideWidth_third + "px, 0px, 0px)";
          }, slideSpeed_third);
          curIndex = -1;
        }
        curSlide.classList.remove('slide_active');
        pageDots[(curIndex === -1) ? slideLen_third - 1 : curIndex].classList.remove('dot_active');
        curSlide = slideContents_third[++curIndex];
        curSlide.classList.add('slide_active');
        pageDots[curIndex].classList.add('dot_active');
      });

      /** Prev Button Event */
      slideBtnPrev_third.addEventListener('click', function() {
        if (curIndex >= 0) {
          slideList_third.style.transition = slideSpeed_third + "ms";
          slideList_third.style.transform = "translate3d(-" + (slideWidth_third * curIndex) + "px, 0px, 0px)";
        }
        if (curIndex === 0) {
          setTimeout(function() {
            slideList_third.style.transition = "0ms";
            slideList_third.style.transform = "translate3d(-" + (slideWidth_third * slideLen_third) + "px, 0px, 0px)";
          }, slideSpeed_third);
          curIndex = slideLen_third;
        }
        curSlide.classList.remove('slide_active');
        pageDots[(curIndex === slideLen_third) ? 0 : curIndex].classList.remove('dot_active');
        curSlide = slideContents_third[--curIndex];
        curSlide.classList.add('slide_active');
        pageDots[curIndex].classList.add('dot_active');
      });

      /** Pagination_third Button Event */
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
          curSlide = slideContents_third[curIndex];
          curSlide.classList.add('slide_active');
          slideList_third.style.transition = slideSpeed_third + "ms";
          slideList_third.style.transform = "translate3d(-" + (slideWidth_third * (curIndex + 1)) + "px, 0px, 0px)";
        });
      });
    })();
  </script>