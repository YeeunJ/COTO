<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel="stylesheet" href="./resources/css/manageCodingsite.css?asd" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/manageCodingsite.js"></script>

<%@ include file="./jspData/codingSite.jsp"%>

<div id="SiteContainer" class="container">
	<div id="manage">
		<div class="content">
			<h4>코딩 사이트 관리</h4>
			<p>코딩 사이트를 관리할 수 있습니다.</p>
		</div>
	</div>
	
	<div class="table">
		<div style="margin-bottom:10px;"class="right">
			<button id="addbtn"
				class=" whitebtn button">추가</button>
			<button id="editbtn"
				class=" whitebtn button">편집</button>
		</div>
	</div>
		
	<form name="form1" action="manageCodingsite/addok" method="post">	
		 
		<div class="tableManage">
			<div class="tableRow orange white-text">
				<span class="tableCell th3 tablehead center">사이트 이름 </span> 
				<span class="tableCell th7 tablehead center">URL</span> 
			</div>
			<c:forEach items="${CodingSite}" var="u">
			<div class="tableRow">
				
		 		<span class="tableCell td3 sub ">${u.getSiteName()}</span> 
				<span class="tableCell td7 sub "><a href="${u.getSiteUrl()}" target="_blank">${u.getSiteUrl()}</a>
				
				<span class='btns'>
                       <button type="button" id="change" class="editSite edit whitebtn">수정</button>
                       <button type="button" class="deleteBtn edit whitebtn" value="${u.getId()}">삭제</button>
                    </span>
                   </span>
			</div>
			</c:forEach>
			<div id="new" class="tableRow" style="display: none !important;">
				<input id="editonly" type="hidden" name="id" />
				<span class="tableCell td3 sub"><input id="siteName" type='text'name='siteName'></span>
				<span class="tableCell td7 sub"><input id="siteUrl" type='text' name='siteUrl' style='width:70%'>
                    <span class='btns wide addbtn'>
                        <button id="cancelAdd" class="cancelbtn whitebtn" type="button">취소</button>
                        <button id="submitbtn" class="submitbtn whitebtn" type="submit">추가</button>
                    </span>  
                </span> 
			</div>
		</div>
	</form>
</div>

<%@ include file="./inc/footer.jsp"%>