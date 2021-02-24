<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<c:forEach varStatus = "status" items="${problemInfo}" var="pi">
	<div id = "confirmProblemValue${status.index+count}" onClick="deleteThis('confirmProblemValue${status.index+count}')"><i class="small smaller material-icons checkIcon" style="color:green;">done</i><input disabled name="${pi.siteID}" value="${pi.name} (${pi.siteName}) - ${pi.link}" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/></div>
</c:forEach>