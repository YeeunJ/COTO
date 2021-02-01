<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<input type="text" name="writer" value="${ writer }" hidden>
<input type="text" name="recomID" value="${ recomID }" hidden>

<c:forEach items="${recomComment}" var="r" varStatus="status">
	<div class="comment-wrapper">
		<span class="username">${ r.name }</span><span class="commentdate">${ r.regDate }</span>
		<p class="comment">${ r.content }</p>
	</div>
</c:forEach>
