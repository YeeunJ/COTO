<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<h5 class="font-color">내가 찜한 문제들</h5>
<br>	

<div class="table center" id="recomCartContent">
	
	<div class="tableRow">
		<span class="index-col tableCell th1">No.</span> <span class="tableCell th3">제목</span>
		<span class="tableCell th2">등록자</span> <span class="tableCell th1">난이도</span>
		<span class="like-col tableCell th1">전체 문제 수</span> <span class="like-col tableCell th1">푼 문제 수</span>
 		<span class="like-col tableCell th1">추천수</span> <span class="comment-col tableCell th1">댓글수</span>
	</div>
	
	
	<c:forEach items="${recomCarts}" var="recomCart" varStatus="status">
		<c:choose>
			<c:when test = "${recomCart.totalProbCnt == recomCart.userProbCnt && recomCart.totalProbCnt != 0}">
				<div class="tableRow" style="background:#e592391a;" id="recoms${recomCart.id}" onclick="printCartAllContent(${recomCart.id})">			
			</c:when>
			<c:otherwise>
				<div class="tableRow" id="recoms${recomCart.id}" onclick="printCartAllContent(${recomCart.id})">			
			</c:otherwise>
		</c:choose>
			<span class="index-col tableCell td1 alignCenter">${status.count}</span> 
			<span class="tableCell td3 alignCenter readTitle">${recomCart.title}</span> 
			<span class="tableCell td2 alignCenter">${recomCart.nickname}</span> 
			<span class="tableCell td1 alignCenter"><img style="width: 60px;" alt="${recomCart.difficulty}" src="../resources/img/difficulty${recomCart.difficulty}.png"></span> 
 			<span class="tableCell td1 alignCenter">${recomCart.totalProbCnt}</span> 
 			<span class="tableCell td1 alignCenter">${recomCart.userProbCnt}</span> 
 			<span class="like-col tableCell td1 alignCenter readRecommend">${recomCart.recomCount}</span> 
			<span class="comment-col tableCell td1 alignCenter readCommentCount">${recomCart.recomCommentCount}</span>
		</div>
	</c:forEach>
</div>

<br> <br>