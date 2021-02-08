<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>codingTogether</title>

<link href="../resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="../resources/css/jquery.sweet-modal.min.css" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css' rel='stylesheet'/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>

<link href="../resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="../resources/css/header.css" type="text/css" rel="stylesheet" media="screen,projection" />

<script src="../resources/vendor/jquery/jquery.min.js"></script>
<script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="../resources/js/materialize.js"></script>
<script src="../resources/js/init.js"></script>
<script src="../resources/js/jquery.sweet-modal.min.js"></script>
<script src="../resources/vendor/chart.js/Chart.min.js"></script>
<script src="../resources/js/createModal.js"></script>

</head>

<body>
<nav role="navigation">
	<div class = "container" >
		<div class = "nav-wrapper">
			<a href="../"><img class = "left" src="https://i.ibb.co/pXsymdb/2021-01-24-10-09-26.png" alt="2021-01-24-10-09-26" border="0" style="width: 90px; margin-top: 5px;"></a>		  
			<a href="#" data-target="nav-mobile" class="sidenav-trigger right"><i class="material-icons orange-text">menu</i></a>
			<ul class = "hide-on-med-and-down mymenu">
				<li id = "list"><span id = "listspan" onclick="location.href='../recommendProblem'">문제 추천</span></li>
				<li id = "list"><span id = "listspan" onclick="location.href='../problemList'">문제 리스트</span></li>
				<li id = "list"><span id = "listspan" onclick="location.href='../manageCodingsite'">코딩 사이트 관리</span></li>
				<li id = "list"><span id = "listspan" onclick="location.href='../usermanage'">사용자 관리</span></li>
				<li id = "list"><span id = "listspan" onclick="location.href='../mypage/problems'">마이 페이지<i class="material-icons right">arrow_drop_down</i></span>
					<ul class = "submenu">
						<li id = "list" onclick="location.href='../mypage/problems'"><span id = "listspan">내가 푼 문제</span></li>
						<li id = "list" onclick="location.href='../mypage/information'"><span id = "listspan">내 정보</span></li>
						<li id = "list" onclick="location.href='../mypage/activities'"><span id = "listspan">내 기록</span></li>
					</ul>
				</li>
				<li class = "right">
					<span class = "center" style = "display: inline-block !important;">
						<span id = "listspan" class="gray-text">안녕하세요, <span class="orange-text">${user.name}</span> 님
						<a href="../login/cancel" class="registerA" style='display:inline-block !important'><button class = "mybtn">로그아웃</button></a></span>
					</span>
				</li>
		  	</ul>
			<ul id="nav-mobile" class="sidenav">
				<li><a href="../login">로그인</a></li>
				<li><a href="../recommendProblem">문제 추천</a></li>
				<li><a href="../problemList">문제 리스트</a></li>
				<li><a href="../manageCodingsite">코딩 사이트 관리</a></li>
				<li><a href="../usermanage">사용자 관리</a></li>
				<li><a href="../mypage/problems">마이 페이지 - 내가 푼 문제</a></li>
				<li><a href="../mypage/information">마이 페이지 - 내 정보</a></li>
				<li><a href="../mypage/activities">마이 페이지 - 내 기록</a></li>
			</ul>
	    </div>
	</div>
</nav>