<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<c:choose>
	<c:when test="${gp.userDate eq null}">
		<i class="small smaller material-icons" onclick="checkProblem(${gp.problemID}, '${groupGoalDetail.id}', '${groupGoalDetail.groupID}')" style="color:lightgray !important; height: 30px; cursor: pointer;">done</i>
	</c:when> 
	<c:otherwise>
		<i class="small smaller material-icons" onclick = "uncheckProblem(${gp.problemID}, '${gp.name}', '${groupGoalDetail.id}', '${groupGoalDetail.groupID}')" style="color:green; height: 30px; cursor: pointer;">done</i>
	</c:otherwise>
</c:choose>