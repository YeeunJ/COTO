document.ready(function(){
	
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
					goalNum: $('input[name="goalNum"]').val(),
					email: $('input[name="email"]').val(),
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
	
	
})


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