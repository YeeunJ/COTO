<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<button class="input-field custom-button" onclick="groupCreateModal(${userID})">그룹 만들기</button>
<h5 class="font-color">내가 관리하는 그룹</h5>
			
<div class="table">
	<div class="tableRow">
		<span class="tableCell th1">No.</span> 
		<span class="tableCell th2">그룹명</span>
		<span class="tableCell th1">시작일</span> 
		<span class="tableCell th1">종료일</span>
	    <span class="tableCell th1">출석</span> 
 	</div>
	
 	<c:forEach items="${adminGroups}" var="group" varStatus="status">
 	<fmt:formatDate value="${group.startDate}" var="startDate" pattern="yyyy-MM-dd"/>
 	<fmt:formatDate value="${group.endDate}" var="endDate" pattern="yyyy-MM-dd"/>
 	
 	<div class="tableRow center" id="recoms${group.id}" onclick="location.href='./eachGroup?groupID=${ group.id }';">
 		<span class = "tableCell td1 pIndex">${status.count}</span>
  		<span class = "tableCell td2 pName"><a href="./eachGroup?groupID=${ group.id }">${group.groupName}</a></span>
  		<span class = "tableCell td1">${startDate}</span>
 		<span class = "tableCell td1">${endDate}</span>
  		<span class = "tableCell td1 pToday">${group.attendance}명 / ${group.totalGroupUser}명</span>
 	</div>
	</c:forEach>
</div>