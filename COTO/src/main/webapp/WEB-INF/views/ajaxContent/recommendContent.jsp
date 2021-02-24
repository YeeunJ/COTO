<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="table" id="recommendContent">
	
	<div class="tableRow">
		<span class="index-col tableCell th1">No.</span> <span class="tableCell th4">제목</span>
		<span class="tableCell th2">등록자</span> <span class="tableCell th1">난이도</span>
		<span class="like-col tableCell th1">추천수</span> <span class="comment-col tableCell th1">댓글수</span>
<!-- 		<span class="like-col tableCell th1">담기</span>  -->
		
	</div>
	
	<c:forEach items="${recoms}" var="recoms" varStatus="status">
		<div class="tableRow" id="recoms${recoms.id}" onclick="printAllContent(${recoms.id})">
			<span class="index-col tableCell td1 alignCenter">${status.count}</span> 
			<span class="tableCell td4 alignCenter readTitle">${recoms.title}</span> 
			<span class="tableCell td2 alignCenter">${recoms.nickname}</span> 
			<span class="tableCell td1 alignCenter"><img style="width: 60px;" alt="${recoms.difficulty}" src="./resources/img/difficulty${recoms.difficulty}.png"></span> 
			<span class="like-col tableCell td1 alignCenter readRecommend">${recoms.recomCount}</span> 
			<span class="comment-col tableCell td1 alignCenter readCommentCount">${recoms.recomCommentCount}</span> 
			<c:choose>
				<c:when test = "${recoms.id} == ${recomCarts.recomID}">
								</c:when>
			</c:choose>			

<%-- 			<span class="tableCell dt1 alignCenter">				
			<c:choose>
					<c:when test = "${cartYN > 0}">
						<%! int cart = 0;%>
						<%= cart %>						
						<span class="clicked-cart icon" onclick="deleteRecomCart()"><span class="clicked-tooltip">장바구니에서 지울 수 있어요!</span></span>		
					</c:when>
					<c:otherwise>
						<span class="cart-icon icon" onclick="addRecomCart()"><span class="cart-tooltip">장바구니에 담아보세요!</span></span>
					</c:otherwise>
			</c:choose></span> --%>
>>>>>>> branch 'recomCart' of https://github.com/YeeunJ/COTO.git
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
				<li id="recentPage" class="active orange"><span class="pagination-button" >${p}</span></li>
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
</div> 
