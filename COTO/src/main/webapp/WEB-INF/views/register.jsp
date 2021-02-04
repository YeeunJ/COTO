<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%
	String fullHeader ="";
	if(((UserDTO)request.getSession().getAttribute("user")) == null){
		fullHeader = "./inc/logoutHeader.jsp";
	}else if(((UserDTO)request.getSession().getAttribute("user")).getIsAdmin() > 0){
		fullHeader = "./inc/adminHeader.jsp";
	}else {
		fullHeader = "./inc/loginHeader.jsp";
	}
%>
<jsp:include page= "<%=fullHeader%>" />

<link rel="stylesheet" href="./resources/css/register.css?aa" />
<script src="./resources/js/register.js"></script>

<div class="container">
	<div class="register" id=userInfo>
		<h5 class="header center gray-text">초기 정보 등록</h5>
		<br>
		<div id="table">
			<div class="row">
				<span class="cell td1">이름</span> <span class="cell td1"><input
					type="text" name="userName" placeholder="이름을 입력하세요. "  value="${user.name}" readonly/></span>
			</div>
			<div class="row">
				<span class="cell td2">이메일</span> <span class="cell td2"><input
					type="email" name="email" placeholder="이메일을 입력하세요. " value="${user.email}" readonly/> </span>
			</div>
			<div class="row nicknameRow">
				<span class="cell td3">닉네임</span><span class = "red-text">*</span> <br>
				<span class="cell td3"><input type="text" name="nickName" placeholder="닉네임을 입력하세요." required style="display:inline-block;"/>
				<button id="dupCheck" class="btn-small waves-effect waves-light">중복확인</button></span>
			</div>
			<div class="row">
				<span class="cell td4">자기소개 </span> <span class="cell td4"><input
					type="text" name="intro" placeholder="자기소개를 입력하세요. " /></span>
			</div>
		</div>
		<br>
		<br>
		<div class="center">
			<button id="userInfoBtn" class="btn-small waves-effect waves-light" style="background-color: #e69138ff">등록</button>
		</div>
	</div>
	
	
	<div class="register" id=userGoal style="display: none">
		<h5 class="header center gray-text">초기 목표 등록</h5>
		<br>
		<div id="table">
			<div class="row">
				<span class="cell td1">일별 계획 </span> <span class="cell td1"><input
					type="text" name="goal" placeholder="계획을 입력하세요. " /></span>
			</div>
			<div class="row">
				<span class="cell td2">시작 일자 </span> <span class="cell td2"><input
					type="date" name="startDate" /></span>
			</div>
			<div class="row">
				<span class="cell td3">종료 일자 </span> <span class="cell td3"><input
					id="date" type="date" name="endDate" /></span>
			</div>
			<div class="row">
				<span class="cell td4">목표 갯수 </span> <span class="cell td4"><input
					type="number" name="goalNum"></span>
			</div>
		</div>
		<br>
		<br>
		<div class="center">
			<button id="userGoalBtn" class="btn-small waves-effect waves-light" style="background-color: #e69138ff">등록</button>
			<button id="skipBtn" class="btn-small waves-effect waves-light" style="background-color: #e69138ff">건너뛰기</button>
		</div>
	</div>
</div>



<script>
var dupCheck=0;

$('input[name="nickName"]').change(function(){
	dupCheck=0;
});

$('#dupCheck').click(function(){
	if($('input[name="nickName"]').val()==""){
		alert("닉네임을 입력해주세요.");
		return;
	}
	$.ajax({
		url: "./register/dupCheck",
		type: "POST",
		async: false,
		data: {
			nickName:$('input[name="nickName"]').val(),
		},
		success: function(data){
			if(data==0){
				dupCheck=1;
				alert("사용 가능한 닉네임입니다.");
			}else {
				alert("중복된 닉네임입니다. 다른 닉네임을 설정해주세요!");
			}
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
});

$('#skipBtn').click(function(){
	if(confirm("초기 목표 등록을 건너뛰시겠습니까?")){
		location.href="./";
	}
});
$("#userInfoBtn").click(function(){
 	if($('input[name="nickName"]').val()=="") {
		alert("닉네임을 입력하세요.");
		return;
	}
 	if(dupCheck==0) {
		alert("닉네임 중복 확인이 필요합니다!");
		return;
	}
	if(confirm("정보를 등록하시겠습니까?")) {
		$.ajax({
			url: "./register/registerUserinfo",
			type: "POST",
			async: false,
			data: {
				name: $('input[name="userName"]').val(),
				email: $('input[name="email"]').val(),
				nickName:$('input[name="nickName"]').val(),
				intro: $('input[name="intro"]').val()
			},
			success: function(data){
				hideUserInfo();
				showUserGoal();
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}
});

$("#userGoalBtn").click(function(){
 	if($('input[name="goal"]').val()=="") {
		alert("일별 계획을 입력하세요.");
		return;
	}
 	if($('input[name="startDate"]').val()=="") {
		alert("시작 일자를 입력하세요.");
		return;
	}
 	if($('input[name="endDate"]').val()=="") {
		alert("종료 일자를 입력하세요.");
		return;
	}
 	if($('input[name="goalNum"]').val()=="") {
		alert("목표 갯수를 입력하세요.");
		return;
	}
 	
	if(confirm("초기 목표를 등록하시겠습니까?")) {
		$.ajax({
			url: "./register/registerUsergoal",
			type: "POST",
			async: false,
			data: {
				goal: $('input[name="goal"]').val(),
				startDate: $('input[name="startDate"]').val(),
				endDate:$('input[name="endDate"]').val(),
				goalNum: $('input[name="goalNum"]').val()
			},
			success: function(data){
				alert("성공적으로 정보가 등록되었습니다.");
				location.href="./";
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}
});
function showUserGoal(){
	if($('#userGoal').css('display') == 'none'){
	$('#userGoal').show();
	}
  }
function hideUserGoal(){
	if($('#userGoal').css('display') != 'none'){
		$('#userGoal').hide();
		}			
}
function hideUserInfo(){
	if($('#userInfo').css('display') != 'none'){
		$('#userInfo').hide();
		}			
}

</script>

<%@ include file="./inc/footer.jsp"%>
