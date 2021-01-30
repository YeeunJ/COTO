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
	<c:set var="count" value="0" />
	<c:forEach items="${commentCount}" var="c">
		<c:if test="${ c.recomID eq recoms.id }">
			<c:set var="count" value="${ c.count }" />
		</c:if>
	</c:forEach>
	
	<div class="tableRow" id="recoms${recoms.id}" onclick="printAllContent('#recoms${recoms.id}', ${recoms.id}, ${ count })">
		<span class="tableCell td1">${status.count}</span> 
		<span class="tableCell td4 readTitle">${recoms.title}</span> 
		<span class="tableCell td2">${recoms.nickname}</span> 
		<span class="tableCell td1"><img style="width: 34px;" alt="${recoms.difficulty}" src="./resources/img/difficulty${recoms.difficulty}.png"></span> 
		<span class="tableCell td1 readRecommend">${recoms.recomCount}</span> 
		<span class="tableCell td1">${ count }</span> 
		
		<span class="readContent" style="display: none;">${recoms.content}</span>
		<span class="readDifficulty" style="display: none;">${recoms.difficulty}</span>
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
