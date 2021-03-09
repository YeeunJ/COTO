<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="details">
	<c:choose>
		<c:when test = "${loginID ==-1}">
			<span class="like-icon icon" onclick="guestUser()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
		</c:when>
		<c:when test = "${countInfo.recommendYN ==true}">
			<span class="clicked-icon icon" onclick="deleteRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
		</c:when>
		<c:otherwise>
			<span class="like-icon icon" onclick="addRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
		</c:otherwise>
	</c:choose>
	<span class="comment-icon icon"></span><span id="commentCount" class="bold">${ commentCount }</span><span></span>
	<c:choose>
		<c:when test = "${cartYN > 0}">
			<span class="clicked-cart icon" onclick="deleteRecomCart()"><span class="clicked-tooltip">장바구니에서 지울 수 있어요!</span></span>		
		</c:when>
		<c:otherwise>
			<span class="cart-icon icon" onclick="addRecomCart()"><span class="cart-tooltip">장바구니에 담아보세요!</span></span>
		</c:otherwise>
	</c:choose>
	
	
	<%-- <c:choose>
		<c:when test="${countInfo.recommendYN ==true}">
			<span class="clicked-icon icon" onclick="deleteRecomCount()"></span>
			<span id="readRecommends" class="bold">${countInfo.recommendCount}</span>
			<span></span>
		</c:when>
		<c:otherwise>
			<span class="like-icon icon" onclick="addRecomCount()"></span>
			<span id="readRecommends" class="bold">${countInfo.recommendCount}</span>
			<span></span>
		</c:otherwise>
	</c:choose>
	<span class="comment-icon icon"></span><span id="commentCount"
		class="bold">${ commentCount }</span><span></span>
	</div> --%>
</div>
	
		
<div id="commentDetail">
	<div class="comment-add">
		<textarea id="comment-textarea" placeholder="댓글을 달아주세요."></textarea>
		<button id="addComment" class="modal_button add-btn"
			onclick="addComment()">등록</button>
	</div>
	<div id="modal-comment" class="wrapper">
		<input type="text" name="recomID" value="${ recomID }" hidden>

		<c:forEach items="${recomComment}" var="r" varStatus="status">
			<div class="comment-wrapper">
				<span class="username" onclick="moveUserPage('${r.nickName}')">${ r.name }</span>
				<span class="commentdate">${r.regDate}</span>
				<p class="comment">${ r.content }</p>
				<%-- <c:choose>
					<c:when test = "${loginID ==-1}">
						<span class="like-icon icon" onclick="guestUser()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
					</c:when>
					<c:when test = "${countInfo.recommendYN ==true}">
						<span class="clicked-icon icon" onclick="deleteRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
					</c:when>
					<c:otherwise>
						<span class="like-icon icon" onclick="addRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
					</c:otherwise>
				</c:choose>
				<span class="comment-icon icon"></span><span id="commentCount" class="bold">${ commentCount }</span><span></span>
				<c:choose>
					<c:when test = "${cartYN > 0}">
						<span class="clicked-cart icon" onclick="deleteRecomCart()"><span class="clicked-tooltip">장바구니에서 지울 수 있어요!</span></span>		
					</c:when>
					<c:otherwise>
						<span class="cart-icon icon" onclick="addRecomCart()"><span class="cart-tooltip">장바구니에 담아보세요!</span></span>
					</c:otherwise>
				</c:choose> --%>
			</div>
		</c:forEach>
	</div>
</div>
<style>
.username:hover{ color: #e69138ff; font-weight:bold; cursor: pointer;}
</style>
<script>
function moveUserPage(nickName){
	location.href='./'+encodeURI(encodeURIComponent(nickName));
};
</script>