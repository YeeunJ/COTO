<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="table">
	<c:forEach items="${users}" var="user" varStatus="status">
		<input name="id" value="${user.id}" type="hidden" />
		<div class="tableRow">
			<span class="tableCell th3">이름</span>
			<span class="tableCell td8">
			<input type="text" name="name" value="${user.name}" disabled />
			</span>
		</div>
		<div class="tableRow">
			<span class="tableCell th3">이메일</span> 
			<span class="tableCell td8">
			<input type="text" name="email" value="${user.email}" disabled />
			</span>
		</div>				
  		<div class="tableRow" style = "position: relative;">
			<span class="tableCell th3" style = "position: relative;">닉네임</span>
			<span class="tableCell td8" style = "position: relative;">
			<input type="text" name="nickName" placeholder="닉네임을 입력하세요." style="display:inline-block; position: relative;"
					value="${user.nickName}" required />
			<button id="dupCheck" type = "button" class="btn-small waves-effect waves-light">중복확인</button>					
			</span>
		</div>	 		
		<div class="tableRow">
			<span class="tableCell th3">자기소개</span> <span
				class="tableCell td8"><input type="text" name="intro"
				value="${user.intro}" placeholder="자기소개를 입력하세요." /> </span>
		</div>
		<input name="name" value="${user.name}" type="hidden"/>
		<input name="email" value="${user.email}" type="hidden"/> 
	</c:forEach>
</div>