$('select').formSelect();
$('.adminSelect').change(function(){
	var tableRow = $(this).closest('.tableRow');
	var userID = $(tableRow).find(".userID").text();
	var updateAdmin = $(this).val();
	if(confirm("해당 사용자의 권한을 변경하시겠습니까?")){
		$.ajax({
			url: "./usermanage/updateAdmin",
			type: "POST",
			async: false,
			data: {
				userID: userID,
				isAdmin: updateAdmin
			},
			success: function(data){
				alert("권한이 변경되었습니다.");
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}
	/* $.ajax({
		url: "./usermanage/updateAdmin",
		type: "POST",
		async: false,
		data: {
			userID: userID,
			isAdmin: updateAdmin
		}
		success: function(data){
			console.log("success");
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	}); */
})
function readUserlist(){
	console.log("hello");
	$.ajax({
		url: "./usermanage/readUsers",
		type: "POST",
		async: false,
		success: function(data){
			console.log("success");
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}