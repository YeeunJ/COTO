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
				<li id = "list"><span id = "listspan" onclick="location.href='../groupList'">그룹 리스트</span></li>				
				<li class = "right"><span class = "center" style = "display: inline-block;"><a href=."./login" class="registerA"><button class = "mybtn">로그인</button></a></span></li>
		  	</ul>
			<ul id="nav-mobile" class="sidenav">
				<li><a href="../login">로그인</a></li>
				<li><a href="../recommendProblem">문제 추천</a></li>
				<li><a href="../problemList">문제 리스트</a></li>
				<li><a href="../groupList">그룹 리스트</a></li>
			</ul>
	    </div>
	</div>
</nav>
