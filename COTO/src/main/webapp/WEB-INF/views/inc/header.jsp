<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>codingTogether</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="./resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link rel="stylesheet" href="./resources/css/jquery.sweet-modal.min.css" />
<link href="./resources/css/style.css?as" type="text/css" rel="stylesheet" media="screen,projection" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link href="./resources/css/header.css" type="text/css" rel="stylesheet" media="screen,projection" />

<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css' rel='stylesheet'/>

<script src="./resources/vendor/jquery/jquery.min.js"></script>
<script src="./resources/js/materialize.js"></script>
<script src="./resources/js/init.js"></script>
<script src="./resources/js/jquery.sweet-modal.min.js"></script>
<script src="./resources/js/createModal.js"></script>
<script>
$(document).ready(function){
	$(".dropdown-trigger").dropdown();
}
</script>
</head>
<body>
<!--   <nav class="white" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="../home" class="brand-logo"></a><img src="https://i.ibb.co/pXsymdb/2021-01-24-10-09-26.png" alt="2021-01-24-10-09-26" border="0" style="width: 80px; margin-top 5px;">
      <ul class="center hide-on-med-and-down">
        <li><a href="./recommendProblem">문제 추천</a></li>
        <li><a href="./manageCodingsite">코딩 사이트 관리</a></li>
        <li><a href="./mypage/problems">마이 페이지</a></li>
        <li><a href="./register">로그인</a></li>
      </ul>

      <ul id="nav-mobile" class="sidenav">
        <li><a href="./register">로그인</a></li>
        <li><a href="./recommendProblem">문제 추천</a></li>
        <li><a href="./manageCodingsite">코딩 사이트 관리</a></li>
        <li><a href="./mypage/problems">mypage - my problems</a></li>
        <li><a href="./mypage/information">myPage - 내 정보 수정</a></li>
        <li><a href="./mypage/activities">myPage - my activities</a></li>
      </ul>
      <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
  </nav>
  -->
<ul id="dropdown1" class="dropdown-content">
	  <li><a href="#!">one</a></li>
	  <li><a href="#!">two</a></li>
	  <li><a href="#!">three</a></li>
</ul>
<nav role="navigation">
	<div class = "container" >
		<div class = "nav-wrapper">
			<a href="./"><img class = "left" src="https://i.ibb.co/pXsymdb/2021-01-24-10-09-26.png" alt="2021-01-24-10-09-26" border="0" style="width: 90px; margin-top: 5px;"></a>		  
			<a href="#" data-target="nav-mobile" class="sidenav-trigger right"><i class="material-icons orange-text">menu</i></a>
			<ul class = "hide-on-med-and-down mymenu">
				<li id = "list"><span id = "listspan"><a href="./recommendProblem">문제 추천</a></span></li>
				<li id = "list"><span id = "listspan"><a href="./manageCodingsite">코딩 사이트 관리</a></span></li>
<!-- 				<li id = "list"><span id = "listspan"><a href="./mypage/problems">마이 페이지</a></span></li> -->
				<li id = "list" ><span id = "listspan"><a href="./mypage/problems" >마이 페이지<i class="material-icons right">arrow_drop_down</i></a></span>
					<ul class="submenu">
						<li><a href="./mypage/problems"> my problems</a></li>
						<li><a href="./mypage/information">내 정보 수정</a></li>
						<li><a href="./mypage/activities">my activities</a></li>
					</ul>
				</li>
				<li class = "right"><span class = "center" style = "display: inline-block;"><a href="./register"><button class = "mybtn">로그인</button></a></span></li>
		  	</ul>

			<ul id="nav-mobile" class="sidenav">
				<li><a href="./register">로그인</a></li>
				<li><a href="./recommendProblem">문제 추천</a></li>
				<li><a href="./manageCodingsite">코딩 사이트 관리</a></li>
				<li><a href="./mypage/problems">마이 페이지 - my problems</a></li>
				<li><a href="./mypage/information">마이 페이지 - 내 정보 수정</a></li>
				<li><a href="./mypage/activities">마이 페이지 - my activities</a></li>
			</ul>
	    </div>
	</div>
</nav>
</body>
</html>
