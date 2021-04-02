
var isAdd = false;

$(document).ready(function() {

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
	$(document).on("click", ".editSite", function(){
		var form = document.form1;
		var tableRow = $(this).closest('.tableRow');
		var editCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);
		form.action="manageCodingsite/editok";
		
		var buttons = '<span class="btns wide"><button id="cancelbtn" class="cancelbtn whitebtn" type="button">취소</button><button id="submitbtn" class="submitbtn whitebtn" type="submit">수정</button></span>';

		$(editCell[0]).html('<input id="editonly" type="hidden" name="id" value="'+  posts[index-1].id +'" /> <input id="siteName"  class="siteName" type="text" name="siteName" value="'+posts[index-1].siteName+'">');
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