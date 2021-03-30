<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<link href="../resources/css/problems.css" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/myGroup.css?asd" />
<script src="../resources/js/myGroup.js"></script>


<c:if test = "${adminID == userID}">
	<button class="input-field custom-button" onclick="problemCreateModal(${userID})">문제 추가</button>
</c:if>


<button id="dropBtn" class="input-field custom-button" onclick="dropGroup(${userID}, ${groupID})">탈퇴하기</button>



<div id="createProblem" class="container" style="display:none;">
	<form class="col s12">
	
		<p class="title">문제 리스트</p>
		<span><input class="groupDate" type="date" id="probStartDate" />   ~   <input class="groupDate" type="date" id="probEndDate" /></span>
		
		<div class="row">
			<div id="selectHtml" class="input-field col s4">
				<select id="siteName" required>
					<optgroup label="코딩사이트 선택">
						<c:forEach items="${CodingSite}" var="site">
							<option value="${site.id}">${site.siteName}</option>
						</c:forEach>
					</optgroup>
					<optgroup label="링크로 입력">
						<option value="0">링크로 입력</option>
					</optgroup>
				</select>
				<label>코딩사이트 선택</label> 
				<span class="helper-text">코딩 사이트를 선택해서 입력하거나 링크로 입력할 수 있습니다.</span>
			</div>
			<div class="input-field col s6">
				<input id="problems" type="text" class="validate"> 
				<label for="problems">Problems</label> 
				<span class="helper-text">문제들을 입력할 때 ,로 구분해주세요!!</span>
			</div>
			<button type="button" id="add" class="modal_button lighten-1" onClick="insertProblems()">추가</button>
		</div>
		
		<div class="input-field col s10">
			<label for="last_name">입력한 Problems</label> <br> <br>
			<div class="recom-confirmSite" id="confirmSite"></div>
		</div>
		
	</form>
</div>




<script>
	var groupID = ${groupID};

	/* $('#dropBtn').click(function() {
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
	}); */

</script>