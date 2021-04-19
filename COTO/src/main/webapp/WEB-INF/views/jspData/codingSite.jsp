<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>

var posts = new Array();
	<c:forEach items="${CodingSite}" var="u">
	
	var list = new Object();
	list.siteName = "${u.getSiteName()}";
	list.siteUrl = "${u.getSiteUrl()}";
	list.id = ${u.getId()};
			
	posts.push(list);

</c:forEach>

</script>