<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="tableRow">
	<span class="tableCell th1">No.</span> <span class="tableCell th4">제목</span>
	<span class="tableCell th2">등록자</span> <span class="tableCell th1">난이도</span>
	<span class="tableCell th1">추천수</span> <span class="tableCell th1">댓글수</span>
</div>

<c:forEach items="${recoms}" var="recoms" varStatus="status">
	<%-- <c:set var="count" value="0" />
	<c:forEach items="${commentCount}" var="c">
		<c:if test="${ c.recomID eq recoms.id }">
			<c:set var="count" value="${ c.count }" />
		</c:if>
	</c:forEach> --%>
	
	<div class="tableRow" id="recoms${recoms.id}" onclick="printAllContent('#recoms${recoms.id}', ${recoms.id}, ${recoms.recomCommentCount})">
		<span class="tableCell td1 alignCenter">${status.count}</span> 
		<span class="tableCell td4 alignCenter readTitle">${recoms.title}</span> 
		<span class="tableCell td2 alignCenter">${recoms.nickname}</span> 
		<span class="tableCell td1 alignCenter"><img style="width: 60px;" alt="${recoms.difficulty}" src="./resources/img/difficulty${recoms.difficulty}.png"></span> 
		<span class="tableCell td1 alignCenter readRecommend">${recoms.recomCount}</span> 
		<span class="tableCell td1 alignCenter readCommentCount">${recoms.recomCommentCount}</span> 
		
		<span class="readContent" style="display: none;">${recoms.content}</span>
		<span class="readDifficulty" style="display: none;">
			<c:choose>
				<c:when test="${recoms.difficulty eq 1}"><img style="width: 60px;" alt="1" src="./resources/img/difficulty1.png"></c:when>
				<c:when test="${recoms.difficulty eq 2}"><img style="width: 60px;" alt="2" src="./resources/img/difficulty2.png"></c:when>
				<c:when test="${recoms.difficulty eq 3}"><img style="width: 60px;" alt="3" src="./resources/img/difficulty3.png"></c:when>
				<c:when test="${recoms.difficulty eq 4}"><img style="width: 60px;" alt="4" src="./resources/img/difficulty4.png"></c:when>
				<c:when test="${recoms.difficulty eq 5}"><img style="width: 60px;" alt="5" src="./resources/img/difficulty5.png"></c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</span>
		<span class="readTag" style="display: none;">
			<c:forEach items="${recomProblemTag}" var="rpt">
				<c:if test="${rpt.recomID eq recoms.id}">
					<span class="readTagChips">${rpt.tag}</span>
				</c:if>
			</c:forEach>
		</span> 
		<div class="readProblem" style="display: none;">
			<c:forEach items="${recomProblem}" var="rp">
				<c:if test="${rp.recomID eq recoms.id}">
					<div class="sitetitle">${rp.siteName}</div>
					<div><p>${rp.name}</p></div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</c:forEach>
