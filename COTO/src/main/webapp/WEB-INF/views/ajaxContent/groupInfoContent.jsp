<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script src="https://www.chartjs.org/samples/latest/utils.js"></script>

<div id="groupListContent">
	
 	<c:forEach items="${groupInfo}" var="grouplist" varStatus="status">


	<!-- Content Row -->
	<div class="card-wrap">
		<div class="card-content1">
			<div class="card shadow card-body">
				<div class="font-color card-title">${grouplist.groupName} 정보</div>
				
<%-- 				<div id="table">
					<c:forEach items="${readOtherUserPage}" var="r" varStatus="status">
						<div class="tableRow">
							<span class="tableCell td2">현재 목표</span> <span class="tableCell td4">${grouplist.goal}</span>
						</div>
						<div class="tableRow">
							<span class="tableCell td2">현재 푼 문제수</span> <span
								class="tableCell td4">문제</span>
						</div>
					</c:forEach>
						<div class="tableRow">
							<span class="tableCell td2" style="font-size: 13px;">전체 푼 문제수</span> 
								<span class="tableCell td4">문제</span>
						</div>
				</div> --%>
			</div>
		</div>

		<div class="card-content2">
			<div class="card shadow card-body">
				<div class="font-color card-title">${grouplist.groupName} 진행도</div>
				<div class="chart-container"> 
					<canvas id="progressChart"></canvas> 
				</div>

			</div>
		</div>
	</div>
	
	</c:forEach>	
</div>




<%-- 
<div class="table" id="recommendContent">
	
	<div class="tableRow">
		<span class="index-col tableCell th1">No.</span> <span class="tableCell th4">제목</span>
		<span class="tableCell th2">등록자</span> <span class="tableCell th1">난이도</span>
		<span class="like-col tableCell th1">추천수</span> <span class="comment-col tableCell th1">댓글수</span>
		<c:if test = "${!empty userID}">
			<span class="like-col tableCell th1">담기</span>
		</c:if>
		
		
	</div>
	
	<c:forEach items="${recoms}" var="recoms" varStatus="status">
	
		<div class="tableRow" id="recoms${recoms.id}">
			<span class="index-col tableCell td1 alignCenter" onclick="printAllContent(${recoms.id})">${status.count}</span> 
			<span class="tableCell td4 alignCenter readTitle" onclick="printAllContent(${recoms.id})">${recoms.title}</span> 
			<span class="tableCell td2 alignCenter" onclick="printAllContent(${recoms.id})">${recoms.nickname}</span> 
			<span class="tableCell td1 alignCenter" onclick="printAllContent(${recoms.id})"><img style="width: 60px;" alt="${recoms.difficulty}" src="./resources/img/difficulty${recoms.difficulty}.png"></span> 
			<span class="like-col tableCell td1 alignCenter readRecommend" onclick="printAllContent(${recoms.id})">${recoms.recomCount}</span> 

			<span class="comment-col tableCell td1 alignCenter readCommentCount" onclick="printAllContent(${recoms.id})">${recoms.recomCommentCount}</span> 
 			<c:if test = "${!empty userID}">
 					<span class="tableCell td1 alignCenter like-col">	
						<c:choose>
							<c:when test = "${recoms.userCart == '1'}">
								<span class="clicked-cart icon" onclick="deleteRecomCart(${recoms.id})"><span class="clicked-tooltip">장바구니에서 지울 수 있어요!</span></span>
							</c:when>
							<c:otherwise>							
								<span class="cart-icon icon" onclick="addRecomCart(${recoms.id})"><span class="cart-tooltip">장바구니에 담아보세요!</span></span>
							</c:otherwise>	
						</c:choose>		
					</span>										
			</c:if>	 				
		</div>
	</c:forEach>
</div>

<br> <br>

<!-- pagination -->
<div class="table" style="text-align: center">
	<ul class="pagination ">
		<c:if test="${ page eq 1 }">
			<li class="disable-button"><span class="arrow-button"><i class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:if test="${ page != 1 }">
			<li class="waves-effect"><span class="arrow-button" onclick="search(${page-1})"><i class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:forEach var="p" begin="${s_page}" end="${e_page}">
			<c:if test="${ p eq page }">
				<li id="recentPage" class="active orange" value="${p}"><span class="pagination-button" >${p}</span></li>
			</c:if>
			<c:if test="${ p != page }">
				<li class="waves-effect"><span class="pagination-button" onclick="search(${p})">${p}</span></li>
			</c:if>
		</c:forEach>
		<c:if test="${ page eq e_page }">
			<li class="disable-button"><span class="arrow-button"><i class="material-icons">chevron_right</i></span></li>
		</c:if>
		<c:if test="${ page != e_page }">
			<li class="waves-effect"><span class="arrow-button" onclick="search(${page+1})"><i class="material-icons">chevron_right</i></span></li>
		</c:if>
	</ul>
</div>  --%>
