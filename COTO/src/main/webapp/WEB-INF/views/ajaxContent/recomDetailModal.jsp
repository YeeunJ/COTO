<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<span id="readRecomID" style="display:none;">${ recomID }</span>

<div id="detailRecom">
	<div>
		<div>
			<p class="title">추천 문제 설명</p>
			<div class="readBox">
				<span id="readContents" >${recom.content}</span>
			</div>
			<br><br>
		</div>
		
		<div>
			<p class="title">추천 문제 난이도</p>
			<div class="readBox">
				<span id="readDifficulties">
				<c:choose>
					<c:when test="${recom.difficulty eq 0}"><img style="width: 60px;" alt="0" src="./resources/img/difficulty0.png"></c:when>
					<c:when test="${recom.difficulty eq 1}"><img style="width: 60px;" alt="1" src="./resources/img/difficulty1.png"></c:when>
					<c:when test="${recom.difficulty eq 2}"><img style="width: 60px;" alt="2" src="./resources/img/difficulty2.png"></c:when>
					<c:when test="${recom.difficulty eq 3}"><img style="width: 60px;" alt="3" src="./resources/img/difficulty3.png"></c:when>
					<c:when test="${recom.difficulty eq 4}"><img style="width: 60px;" alt="4" src="./resources/img/difficulty4.png"></c:when>
					<c:when test="${recom.difficulty eq 5}"><img style="width: 60px;" alt="5" src="./resources/img/difficulty5.png"></c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				</span>
			</div>
			<br><br>
		</div>
		
		<div>
			<p class="title">추천 문제 태그</p>
			<div id="readTags">
			<c:forEach items="${recomProblemTag}" var="rpt">
				<c:if test="${rpt.recomID eq recoms.id}">
					<span class="readTagChips">${rpt.tag}</span>
				</c:if>
			</c:forEach>
			</div>
			<br><br>
		</div>
	
		<div>
			<p class="title desc">추천 문제</p>
			<div id="readProblems" class="readBox"></div>
			<c:forEach items="${recomProblem}" var="rp">
				<div class="sitetitle">${rp.siteName}</div>
				<div><p>${rp.name}</p></div>
			</c:forEach>
		</div>
	</div>
	
	<div>
	 <div class="details">
			<span class="<c:choose><c:when test = "${countInfo.recommendYN ==true}">clicked-icon</c:when><c:otherwise>like-icon</c:otherwise></c:choose> icon" onclick="addRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
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
	
	</div>
</div>