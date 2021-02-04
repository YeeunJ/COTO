<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <c:forEach items="${users}" var="user" varStatus="status">
	<div class="tableRow">
	  <span class="tableCell th1">이름 </span>
	  <span class="tableCell th1">${user.name} </span>
	</div>
	<div class="tableRow">
	  <span class="tableCell th2">이메일</span>
	  <span class="tableCell th2">${user.email}</span>
	</div>
	<div class="tableRow">
	  <span class="tableCell th3">닉네임 </span>
	  <span class="tableCell th3">${user.nickName}</span>
	</div>
	<div class="tableRow">
	  <span class="tableCell th4">자기소개 </span>
	  <span class="tableCell th4">${user.intro} </span>
 	</div>
</c:forEach>		