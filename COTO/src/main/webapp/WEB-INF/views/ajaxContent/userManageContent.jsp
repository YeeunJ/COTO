<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <c:set var ="index" value = "1"/>
 <c:forEach items="${users}" var="user" varStatus="status">
	<div class="tableRow">
		<span class="tableCell td1 skip">${ index }</span> 
		<span class="userID" style="display:none">${ user.id }</span> 
		<span class="tableCell td2">${ user.name }</span> 
		<span class="tableCell td3 skip">${ user.email }</span>
		<span class="tableCell td2">${ user.nickName }</span>
		<span class="tableCell td1">
			<c:if test="${user.isAdmin+0 == 0}" >
			<select class="adminSelect">
				<option value="0" selected>사용자</option>
				<option value="1">관리자</option>
			</select>
			</c:if>
			<c:if test="${user.isAdmin+0 == 1}" >
			<select class="adminSelect">
				<option value="0">사용자</option>
				<option value="1" selected>관리자</option>
			</select>
			</c:if>	
		</span>
	</div>
	<c:set var ="index" value = "${ index+1 }"/>
</c:forEach>		
