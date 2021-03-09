<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="table">
	<div class="tableRow">
		<span class="tableCell th1">No.</span> <span class="tableCell th4">그룹명</span>
		<span class="tableCell th1">인원</span> <span class="tableCell th1">그룹장</span>
		<span class="tableCell th1">출석</span> <span class="tableCell th1">진행률</span>
	</div>
	
 	<c:forEach items="${groups}" var="group" varStatus="status">
 	<div class="tableRow center" id="recoms${group.id}">
 		<span class = "tableCell th1 pIndex">${status.count}</span>
  		<span class = "tableCell th4 pName">${group.groupName}</span>
  		<span class = "tableCell th1 pNumber"></span>
 		<span class = "tableCell th1 pAdmin"></span>
 		<span class = "tableCell th1 pToday"></span>
  		<span class = "tableCell th1 pNow"></span>
 	</div>
	</c:forEach>
</div>

<br>

<style>
button {
	border: 0;
	outling: 0;
	cursor: pointer;
	background-color: white;
}
</style>