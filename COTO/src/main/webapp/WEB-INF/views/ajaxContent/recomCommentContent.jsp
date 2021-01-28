<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h1>test test</h1>
<h2>${ test }</h2>
<c:forEach items="${recomComment}" var="r" varStatus="status">
	<div class="comment-wrapper">
		<span class="username">${ r.userName }</span><span class="commentdate">${ r.regDate }</span>
		<p class="comment">${ r.content }</p>
	</div>
</c:forEach>

<!-- 
<div class="comment-wrapper">
	<span class="username">수루수</span><span class="commentdate">2020-01-17 09:03:17</span>
	<p class="comment">문제 풀면서 다양한 해결법을 배웠습니다!</p>
</div> -->