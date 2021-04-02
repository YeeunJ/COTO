<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="table center" id="groupListContent">
	<div class="tableRow">
		<span class="tableCell th1 mobile">No.</span>
		<span class="tableCell th1">그룹명</span>
		<span class="tableCell th3">목표</span>
		<span class="tableCell th1 mobile">그룹장</span>		
		<span class="tableCell th1">인원</span>	
		<span class="tableCell th1">출석률</span>	
	</div>
	
 	<c:forEach items="${groups}" var="groups" varStatus="status">
 	<div class="tableRow center" id="recoms${groups.id}">
 		<span class = "tableCell td1 pIndex mobile">${status.count}</span>
  		<span class = "tableCell td1 pName">${groups.groupName}</span>
  		<span class = "tableCell td3 pNumber">${groups.goal}</span>
  		<span class = "tableCell td1 pAdmin mobile">${groups.nickName}</span> 		
   		<span class = "tableCell td1 ">${groups.userCnt}</span>
   		<span class = "tableCell td1 ">(출석률)</span>  		
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


