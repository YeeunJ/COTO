<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="table center">
	<div class="tableRow">
		<span class="tableCell th1 skip">No.</span>
		<span class="tableCell th2">이름</span>
		<span class="tableCell th3 skip">이메일</span>
		<span class="tableCell th2">닉네임</span>
		<span class="tableCell th1">권한</span>
	</div>
		
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
</div>	

<br><br>
	
<!-- pagination -->
<div class="table" style="text-align: center">
	<ul class="pagination ">
		<c:if test="${ page eq 1 }">
			<li class="disable-button"><span class="arrow-button"><i class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:if test="${ page != 1 }">
			<li class="waves-effect"><span class="arrow-button" onclick="search(${page-1})"><i class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:forEach var="p" begin="${s_page}" end="${e_page}">
			<c:if test="${ p eq page }">
				<li id="recentPage" class="active orange" value="${p}"><span class="pagination-button" >${p}</span></li>
			</c:if>
			<c:if test="${ p != page }">
				<li class="waves-effect"><span class="pagination-button" onclick="search(${p})">${p}</span></li>
			</c:if>
		</c:forEach>
		<c:if test="${ page eq e_page }">
			<li class="disable-button"><span class="arrow-button"><i class="material-icons">chevron_right</i></span></li>
		</c:if>
		<c:if test="${ page != e_page }">
			<li class="waves-effect"><span class="arrow-button" onclick="search(${page+1})"><i class="material-icons">chevron_right</i></span></li>
		</c:if>
	</ul>
</div>

	
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