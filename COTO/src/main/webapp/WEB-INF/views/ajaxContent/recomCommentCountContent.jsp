<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="details">
				<c:choose>
					<c:when test = "${countInfo.recommendYN ==true}">
						<span class="clicked-icon icon" onclick="deleteRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
					</c:when>
					<c:otherwise>
						<span class="like-icon icon" onclick="addRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
					</c:otherwise>
				</c:choose>
				<span class="comment-icon icon"></span><span id="commentCount" class="bold">${ commentCount }</span><span></span>
			</div>
			<div id="commentDetail">
				<div class="comment-add">
					<textarea id="comment-textarea" placeholder="댓글을 달아주세요."></textarea>
					<button id="addComment" class="modal_button add-btn" onclick="addComment()">등록</button>
				</div>
				<div id="modal-comment" class="wrapper">
					<input type="text" name="recomID" value="${ recomID }" hidden>
					
					<c:forEach items="${recomComment}" var="r" varStatus="status">
						<div class="comment-wrapper">
							<span class="username">${ r.name }</span><span class="commentdate">${ r.regDate }</span>
							<p class="comment">${ r.content }</p>
						</div>
					</c:forEach>
				</div>
		
			</div>