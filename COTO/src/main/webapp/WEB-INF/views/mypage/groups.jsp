<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="com.walab.coding.model.UserDTO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<link href="../resources/css/problems.css" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/myGroup.css?asd" />

<script src="../resources/js/myGroup.js"></script>


<div id="SiteContainer" class="container">
	<div id="group">
		<div class="content">
			<h4>내 그룹</h4>
			<p>나의 그룹을 확인해 보세요!</p>
		</div>
	</div>
	<!-- Content Row -->
	<div>
		<br>
		<h5 class="font-color">내가 속한 그룹</h5>

		<fieldset class="search" style="float: right;">
			<input id="searchValue" class="search_problem" type="search"
				placeholder="검색어를 입력해주세요." />
			<button id="searchButton" class="search_bt" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</fieldset>
		
		<div class="table" id="problemsContent">
			<%@ include file="../ajaxContent/myGroupContent.jsp"%>
		</div>
	</div>

</div>

<style>
#group {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

#group:before {
	content: "";
	background-image: url("../resources/img/problem.png");
	background-size: cover;
	top: 0;
	left: 0;
	right: 0px;
	bottom: 0px;
	position: absolute;
	opacity: 0.4;
	z-index: -1;
}

.tc {
	word-break: break-all;
	text-align: left !important;
	border-left: 1px solid #DDD !important;
	border-right: 1px solid #DDD !important;
}

.probname {
	font-size: 0.8rem;
}
</style>

<%@ include file="../inc/footer.jsp"%>
