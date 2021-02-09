<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<jsp:include page= "<%=\"./inc/\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel="stylesheet" href="./resources/css/manageCodingsite.css?asd" />
<script src="./resources/js/manageCodingsite.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>

var isAdd = false;

$(document).ready(function() {
	
	var posts = new Array();
	
	<c:forEach items="${CodingSite}" var="u">
		
		var list = new Object();
		list.siteName = "${u.getSiteName()}";
		list.siteUrl = "${u.getSiteUrl()}";
		list.id = ${u.getId()};
				
		posts.push(list);
	
	</c:forEach>

	$('#editbtn').click(function() {
		if ($("span.btns").css("display") == "none") {
			$('span.btns').show();
		} else {
			$('span.btns').hide();
			console.log($('.siteUrl').val());
			
			$(".siteUrl").each(function() {
				var parent = $(this).parent('.tableCell');
				var url = $(this).val();
				var buttons = '<span class="btns"><button type="button" id="change" class="editSite edit whitebtn">수정</button><button class="deleteBtn edit whitebtn" type="button">삭제</button></span>';

				parent.html('<a href="'+url+'">'+url+'</a>'+buttons);
	      	});
			$(".siteName").each(function() {
				console.log($(this).val());
				var parent = $(this).parent('.tableCell');
				var name = $(this).val();
				parent.html(name);
	      	});
		}
		if ($(this).html().substring(0, 4) == '편집완료') {
			$('div#new').attr('style', 'display: none !important');
			$('#siteName').val("");
			$('#siteUrl').val("");
			
			$(this).html('편집');
			
		} else {
			$(this).html('편집완료');
		}
	});

	
	$('#submitbtn').click(function() {
		var form = document.form1;
		if(form.siteName.value == ''){
			alert('사이트 이름을 입력하세요 ');
			form.siteName.focus();
			return false;
		}
		if(form.siteUrl.value == ''){
			alert('사이트 URL을 입력하세요 ');
			form.siteUrl.focus();
			return false;
		}

		form.submit();
	});
  	
	$('#addbtn').click(function() {
		setAddBtnMode(false);
		$(".wide").css("margin-top", "8px");
		$('.addbtn').css("display", "block");
	});
  	$('#cancelAdd').click(function(){
	  	if(!isAdd) setAddBtnMode(true); 
	  	$('div#new').attr('style', 'display: none !important');
	});
	 
	function setAddBtnMode(add){
		isAdd =  add;
		if(isAdd){
			$("div#new").hide();
			$('#addbtn').show();
			$('#editonly').attr("disabled",false); 
			$('#submitbtn').html('수정');
		}
		else{
			$("div#new").show();
			setForm('', '','');
			$("input#siteName").focus();
			$('#addbtn').hide();
			$('#editonly').attr("disabled",true); 
			$('#submitbtn').html('추가');
		} 
	}
	
	function deleteOk(id) {            
		if (confirm("정말로 삭제하겠습니까?")){
			location.href = './manageCodingsite/deleteok/' + id;
		}
	}
	
	$(document).on("click", ".editSite", function(){
		var form = document.form1;
		var tableRow = $(this).closest('.tableRow');
		var editCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);
		form.action="manageCodingsite/editok";
		
		var buttons = '<span class="btns wide"><button id="cancelbtn" class="cancelbtn whitebtn" type="button">취소</button><button id="submitbtn" class="submitbtn whitebtn" type="submit">수정</button></span>';

		$(editCell[0]).html('<input id="editonly" type="hidden" name="id" value="'+ posts[index-1].id +'" /> <input id="siteName"  class="siteName" type="text" name="siteName" value="'+posts[index-1].siteName+'">');
		$(editCell[1]).html('<input id="siteUrl" class="siteUrl" type="text" name="siteUrl" value="'+ posts[index-1].siteUrl +'">');
		$(editCell[1]).append(buttons);
		
		$(".wide").css("margin-top", "8px");
		$("span.btns").css("display", "block");
 	});


	$(document).on("click", "#cancelbtn", function() {
		var tableRow = $(this).closest('.tableRow');
		var cancelCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);
		var buttons = '<span class="btns"><button type="button" id="change" class="editSite edit whitebtn">수정</button><button class="deleteBtn edit whitebtn" type="button">삭제</button></span>';

		$(cancelCell[0]).html(posts[index-1].siteName);
		$(cancelCell[1]).html('<a href="'+posts[index-1].siteUrl+'">'+posts[index-1].siteUrl+'</a>');
		$(cancelCell[1]).append(buttons);

		$("span.btns").css("margin-top", "0px"); 
		$("span.btns").css("display", "block");

	});
	
	$(document).on("click", ".deleteBtn", function(){
		var id = $(this).val();

		if (confirm("정말로 삭제하겠습니까?")){
			location.href = './manageCodingsite/deleteok/' + id;
		}
	}); 

	function setForm(id, sitename, url){
		var form = document.form1;
		form.id.value = id; 
		form.siteName.value = sitename; 
		form.siteUrl.value = url; 
		if(id=='' )
			form.action="manageCodingsite/addok";
		else
			form.action="manageCodingsite/editok";
			
	}

});

</script>
<style>
#manage {
	position: relative;
	padding: 80px 0;
	margin-bottom: 3%;
}

#manage:before {
	content: "";
	background-image: url("./resources/img/codingSiteimg.jpg");
	background-size: cover;
	top: 0;
	left: 0;
	right: 0px;
	bottom: 0px;
	position: absolute;
	opacity: 0.4;
	z-index: -1;
}

.content {
	top: 15%;
	left: 50px;
	width: 100%;
	bottom: 100px;
	color: #666666;
}
@media screen and (max-width: 715px){
	.table{
 		font-size: 12px !important;
 		overflow-x: auto !important;
 		display: block !important;
	}
	.tablehead{
	 	font-size: 12px !important;
	}
	.tableRow{
		overflow-x: auto !important;
	}
	.tableCell{
		overflow-x: auto !important;
	}	
}
</style>

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