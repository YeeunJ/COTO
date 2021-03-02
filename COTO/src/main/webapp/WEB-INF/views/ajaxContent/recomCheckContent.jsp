<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<c:choose>
	<c:when test="${rp.link eq null}"><p style="display: inline-block;">${rp.name}</p></c:when>
	<c:otherwise><p style="display: inline-block;"><a href="${rp.link}" target="_blank">${rp.name}</a></p></c:otherwise>
</c:choose>

<c:choose>
	<c:when test = "${loginID ==-1}">
		<i class="small smaller material-icons" onclick="guestUser()" style="color:lightgray !important; height: 30px; float: right; cursor: pointer;">done</i>
	</c:when>
	<c:when test="${rp.date eq null}">
		<i class="small smaller material-icons" onclick="checkProblem(${rp.problemID})" style="color:lightgray !important; height: 30px; float: right; cursor: pointer;">done</i>
	</c:when> 
	<c:otherwise>
		<i class="small smaller material-icons" onclick = "uncheckProblem(${rp.problemID}, '${rp.name}')" style="color:green; height: 30px; float: right; cursor: pointer;">done</i>
	</c:otherwise>
</c:choose>