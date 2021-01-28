<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="./resources/css/manageCodingsite.css" />
<script src="./resources/js/manageCodingsite.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ include file="./inc/header.jsp"%>
<script>

var isAdd = true;

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
		if ($(".edit").css("display") == "none") {
			$('.edit').show();
		} else {
			$('.edit').hide();
		}
		if ($(this).html().substring(0, 4) == '편집완료') {
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
		setAddBtnMode(false)
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
		if (confirm("정말로 삭제하겠습니까?"))
			location.href = './manageCodingsite/deleteok/' + id;
	}
	
	$(document).on("click", ".editSite", function(){
		var form = document.form1;
		var tableRow = $(this).closest('.tableRow');
		var editCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);
		form.action="manageCodingsite/editok";
		
		$(editCell[0]).html('<input id="editonly" type="hidden" name="id" value="'+ posts[index-1].id +'" /> <input id="siteName" type="text" name="siteName" value="'+posts[index-1].siteName+'">');
		$(editCell[1]).html('<input id="siteUrl" type="text" name="siteUrl" value="'+ posts[index-1].siteUrl +'">');
		$(editCell[2]).html('<button id="cancelbtn" class="cancelbtn whitebtn" type="button">취소</button>');
		$(editCell[3]).html('<button id="submitbtn" class="submitbtn whitebtn" type="submit">수정</button>');
	});


	$(document).on("click", "#cancelbtn", function() {
		var tableRow = $(this).closest('.tableRow');
		var cancelCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);

		$(cancelCell[0]).html(posts[index-1].siteName);
		$(cancelCell[1]).html('<a href="'+posts[index-1].siteUrl+'">'+posts[index-1].siteUrl+'</a>');
		$(cancelCell[2]).html('<button type="button" id="change" class="editSite edit whitebtn">수정</button>');
		$(cancelCell[3]).html('<button value="'+posts[index-1].id+'" class="deleteBtn edit whitebtn" type="button">삭제</button>');


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
	font-weight: 800;
	position: absolute;
}
</style>

<div id="codingSiteContainer" class="container">
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
		 
		<div id="table">
			<div class="tableRow orange white-text">
				<span class="tableCell th3 tablehead center">사이트 이름 </span> 
				<span class="tableCell th5 tablehead center">URL</span> 
				<span class="tableCell th1"></span> 
				<span class="tableCell th1"></span>

			</div>
			<c:forEach items="${CodingSite}" var="u">
				<div class="tableRow">
			 		<span class="tableCell td3 sub ">${u.getSiteName()}</span> 
					<span class="tableCell td5 sub "><a href="${u.getSiteUrl()}">${u.getSiteUrl()}</a></span> 
					<span class="tableCell td1 sub "><button type="button" id="change" style="display:none;" class="editSite edit whitebtn">수정</button></span> 
					<span class="tableCell td1 sub "><button style="display:none;" value="${u.getId()}" class="deleteBtn edit whitebtn" type="button">삭제</button></span>
				</div>
			</c:forEach>
			
			<div id="new" style="display: none !important;" class="tableRow content">
				<input id="editonly" type="hidden" name="id" />
				<span class="tableCell td3 sub"><input  id="siteName" type='text'name='siteName'></span>
				<span class="tableCell td5 sub"><input  id="siteUrl" type='text' name='siteUrl'></span> 
				<span class="tableCell td1 sub"><button id="cancelAdd"class=" whitebtn" type="button">취소</button></span>
				<span class="tableCell td1 sub"><button id="submitbtn"class=" whitebtn" type="submit">추가</button></span>
			</div>
		</div>
		</form>
	</div><br><br><br>




<%@ include file="./inc/footer.jsp"%>