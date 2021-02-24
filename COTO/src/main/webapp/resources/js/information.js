function showInfoEdit(){
	if($('#infoEdit').css('display') == 'none'){
	$('#infoEdit').show();
	}
}
function showGoalEdit(){
	if($('#goalEdit').css('display') == 'none'){
	$('#goalEdit').show();
	}
}
function hideInfo(){
	if($('#info').css('display') != 'none'){
		$('#info').hide();
	}			
}
function hideGoal(){
	if($('#goal').css('display') != 'none'){
		$('#goal').hide();
	}			
}
function showInfo(){
	if($('#info').css('display') == 'none'){
		$('#info').show();
	}			
}
function showGoal(){
	if($('#goal').css('display') == 'none'){
		$('#goal').show();
	}			
}		
function hideInfoEdit(){
	if($('#infoEdit').css('display') != 'none'){
	$('#infoEdit').hide();
	}
}
function hideGoalEdit(){
	if($('#goalEdit').css('display') != 'none'){
	$('#goalEdit').hide();
	}
}	
		  
$(document).ready(function(){
	
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
			url: "../mypage/information/dupCheck",
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
	
	$("#userInfoBtn").click(function(){
	 	if($('input[name="nickName"]').val()=="") {
			alert("닉네임을 입력하세요.");
			return;
		}
	 	if(dupCheck==0) {
			alert("닉네임 중복 확인이 필요합니다!");
			return;
		}
	});
})