
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
//$('#userInfoBtn').click(function(){
//	alert("test");
//	
//	if($('input[name="nickName"]').val()=="") {
//		alert("닉네임을 입력하세요.");
//		return;
//	}
//	if(confirm("정보를 등록하시겠습니까?")) {
//		$.ajax({
//			url: "./registerUserinfo",
//			type: "POST",
//			async: false,
//			data: {
//				name: $('input[name="name]').val(),
//				email: $('input[name="email"]').val(),
//				nickName:$('input[name="nickName"]').val(),
//				intro: $('input[name="intro"]').val()
//			},
//			success: function(data){
//				console.log(data);
//				hideUserInfo();
//				showUserGoal();
//			}, 
//			error:function(request, status, error){
//				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
//	        }
//		});
//	}
	
// });


		