<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
<link rel="stylesheet" href="./resources/css/solvedProblem.css?a" />
<link rel="stylesheet" href="./resources/css/userManage.css?a" />
<link href="./resources/css/problemList.css?qwe" rel="stylesheet">
<link href="./resources/css/userManage.css?asd" rel="stylesheet">

<div id="SiteContainer" class="container">
	<div id="problem">
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
				<option value="admin">관리자만</option>
				<option value="user">사용자만</option>
			</select>
		</div>
	</div>
	
	<div class="table center">
		<div class="tableRow">
			<span class="tableCell th1 skip">No.</span>
			<span class="tableCell th2">이름</span>
			<span class="tableCell th3 skip">이메일</span>
			<span class="tableCell th2">닉네임</span>
			<span class="tableCell th1">권한</span>
		</div>
		
		<%@ include file="./ajaxContent/userManageContent.jsp"%>
	</div>
	
	<br><br>
	
	<div class="table" style="text-align: center">
		<ul class="pagination ">
			<li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
			<li class="active orange"><a href="#!">1</a></li>
			<li class="waves-effect"><a href="#!">2</a></li>
			<li class="waves-effect"><a href="#!">3</a></li>
			<li class="waves-effect"><a href="#!">4</a></li>
			<li class="waves-effect"><a href="#!">5</a></li>
			<li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
		</ul>
	</div> 

</div>


<%@ include file="./inc/footer.jsp"%>

