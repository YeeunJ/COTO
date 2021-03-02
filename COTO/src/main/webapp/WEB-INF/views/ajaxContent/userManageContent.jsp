<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <c:set var ="index" value = "1"/>
 <c:forEach items="${users}" var="user" varStatus="status">
	<div class="tableRow">
		<span class="tableCell td1 skip">${ index }</span> 
		<span class="userID" style="display:none">${ user.id }</span> 
		<span class="tableCell td2">${ user.name }</span> 
		<span class="tableCell td3 skip">${ user.email }</span>
		<span class="tableCell td2">${ user.nickName }</span>
		<span class="tableCell td1">
			<c:if test="${user.isAdmin+0 == 0}" >
			<select class="adminSelect">
				<option value="0" selected>사용자</option>
				<option value="1">관리자</option>
			</select>
			</c:if>
			<c:if test="${user.isAdmin+0 == 1}" >
			<select class="adminSelect">
				<option value="0">사용자</option>
				<option value="1" selected>관리자</option>
			</select>
			</c:if>	
		</span>
	</div>
	<c:set var ="index" value = "${ index+1 }"/>
</c:forEach>		
<script>
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
</script>