<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="../../resources/vendor/jquery/jquery.min.js"></script>


<button id="dropBtn" class="input-field custom-button">탈퇴하기</button>


<script>

	$('#dropBtn').click(function() {
		alert("groupID: "+${ groupID } + "userID" + ${userID});
		
		$.ajax({
			url: "./dropGroup",
			type: "POST",
			async: false,
			data: {
				userID: ${ userID },
				groupID: ${ groupID }
			},
			success: function(data){
				$('#problemsContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	});

</script>