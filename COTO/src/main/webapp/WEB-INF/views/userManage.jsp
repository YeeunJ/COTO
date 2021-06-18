<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
<link rel="stylesheet" href="./resources/css/solvedProblem.css?a" />
<link rel="stylesheet" href="./resources/css/userManage.css?a" />
<script src="./resources/js/userManage.js"></script>

<div id="SiteContainer" class="container">
	<div id="user">
		<div class="content">
			<h4>사용자 관리</h4>
			<p>사이트 사용자들을 확인하고, 관리자 설정을 해주세요!</p>
		</div>
	</div>

	<div class="top-bar">
		<fieldset class="search">
			<input id="searchValue" class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button id="searchButton" class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset>
		<div class="col order">
			<select id="orderValue">
				<option value="regdate" selected>등록순</option>
				<option value="name">이름순</option>
				<option value="1">관리자만</option>
				<option value="0">사용자만</option>
			</select>
		</div>
	</div>
	
	<div id="pageajaxContent">
		<%@ include file="./ajaxContent/userManageContent.jsp"%>
	</div>

</div>


<%@ include file="./inc/footer.jsp"%>

