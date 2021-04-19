<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>

var ratioBySite = new Array();
var averageWeek = new Array();
var totalProbCnt = ${totalProblemCnt};
<c:forEach items="${ratio}" var="r">
	var list = new Object();
	list.siteName = "${r.siteName}";
	list.siteID = ${r.siteID};
	list.ratio = ${r.ratio};
	list.count = ${r.count};
	
	ratioBySite.push(list);
</c:forEach>

<c:forEach items="${averageForWeek}" var="avg">
	var list = new Object();
	
	list.date = "${avg.date}";
	list.average = ${avg.average};
	
	averageWeek.push(list);
</c:forEach>


</script>