<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="table center" id="problemContent">
	<div class="tableRow">
		<span class="tableCell th1 mobile">No.</span>
		<span class="tableCell th3">문제 제목</span>
		<span class="tableCell th1">사이트</span>
		<span class="tableCell th1">참여자</span>
		<span class="tableCell th1 mobile">등록일</span>
	</div>
	
	<c:forEach items="${problems}" var="problem" varStatus="status">
	<div class="tableRow" id="problem${problem.id}">
		<span class="tableCell td1 mobile">${status.count}</span> 
		<span class="tableCell td3 probname"> <nobr><a href="${problem.link}">${problem.name}</a></nobr></span> 
		<span class="tableCell td1"> <a href="${problem.siteUrl}">${problem.siteName}</a></span> 
		<span class="tableCell td1">${problem.count}</span> 
		<fmt:formatDate value="${problem.regdate}" var="formattedDate" type="date" pattern="yyyy-MM-dd" />
		<span class="tableCell td1 mobile">${formattedDate}</span> 
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
<!-- //pagination -->


