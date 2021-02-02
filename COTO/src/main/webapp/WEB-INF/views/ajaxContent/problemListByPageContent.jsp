<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:forEach items="${problems}" var="problem" varStatus="status">
	<div class="tableRow" id="problem${problem.id}">
		<span class="tableCell td1">${status.count}</span> 
		<span class="tableCell td3"> <a href="${problem.link}">${problem.name}</a></span> 
		<span class="tableCell td2"> <a href="${problem.siteUrl}">${problem.siteName}</a></span> 
		<span class="tableCell td1">${problem.count}</span> 
	</div>
</c:forEach>