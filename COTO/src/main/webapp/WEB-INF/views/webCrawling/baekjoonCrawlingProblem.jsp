<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:forEach varStatus = "status" items="${problemsInfo}" var="pi">
	<div id = "confirmProblemValue${status.count}" onClick="deleteThis('confirmProblemValue${status.count}')"><input disabled name="${pi.siteID}" value="${pi.name} ('${pi.siteName}') - link: ${pi.link}" id="last_name disabled" type="text" class="problem validate"/></div>
</c:forEach>