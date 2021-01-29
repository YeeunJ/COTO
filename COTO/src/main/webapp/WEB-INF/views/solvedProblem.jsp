<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
							    
<script>
	$(document).ready(function() {
		var variants = {
				'register-button': {
					args: [
						{
							content: '<div class = "container"><form class="col s12">\
						      <div class="row">\
						      	<div class="input-field col s4">\
							      <select id = "siteName" required>\
								      <optgroup label="코딩사이트 선택">\
								      	<option value="" disabled selected>코딩사이트 별 입력</option>\
								        <option value="1">백준</option>\
								        <option value="2">leetcode</option>\
								        <option value="3">SW expert academy</option>\
								        <option value="4">programmers</option>\
								        <option value="5">oncoder</option>\
								        <option value="6">goorm</option>\
								        <option value="7">leetcode(database)</option>\
								      </optgroup>\
								      <optgroup label="링크로 입력">\
								        <option value="8">링크로 입력</option>\
								      </optgroup>\
							      </select>\
							      <label>코딩사이트 선택</label>\
							      <span class="helper-text">코딩 사이트를 선택해서 입력하거나 링크로 입력할 수 있습니다.</span>\
							    </div>\
						        <div class="input-field col s6">\
						          <input id="problems" type="text" class="validate">\
						          <label for="problems">Problems</label>\
						          <span class="helper-text">문제들을 입력할 때 ,로 구분해주세요!!</span>\
						        </div>\
						        <button type="button" id = "add" class="modal_button green lighten-1" onClick = "insertProblems()">추가</button>\
						      </div>\
						      <div class="input-field col s10">\
						      <label for="last_name">입력한 Problems</label>\
						      <br><br>\
						      <div id = "confirmSite">\
					          </div>\
					        </div>\
						    </form></div>',
							title: '푼 문제 등록',
							showCloseButton: true,

							buttons: [
								{
									label: '등록',
									classes: 'green',
									action: function() {
										//여기서 ajax 처리!
										return $.sweetModal({
											content: '<p style = "font-weight:800; font-size:15px; padding-top: 15px;">데이터가 등록 되었습니다~:)</p>',
											icon: $.sweetModal.ICON_SUCCESS
										});
									}
								}
							]
						}
					]
				}
		};
		
		var variant = variants['register-button'];

		$('#register-button').on('click', { variant: variant }, function(e) {
			var variant = e.data.variant;

			variant.fn = variant.fn || $.sweetModal;
			variant.fn.apply(this, variant.args);
			$('select').formSelect();
			
		});
		
	});

	function deleteThis(id){
		var allid = "#"+id;
		$(allid).remove();
	}
	
	var count=0;
	function insertProblems(){
		var siteId = $('#siteName').val();
		var array = ['', '백준', 'leetcode', 'SW expert academy', 'oncoder', 'goorm', 'leetcode[database]', 'link'];
		var site = $("#siteName option:selected").val();
		var value = document.getElementById("problems").value;
		var valueSplit = value.split(',');
		var data = $('#confirmSite').html();
		for(var i in valueSplit){
			data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><input disabled name="'+siteId+'" value="'+valueSplit[i]+' ('+array[site]+')" id="last_name disabled" type="text" class="problem validate"/></div>';
			count++;
		}
		$('#confirmSite').html(data);
		document.getElementById("problems").value = "";
	};


</script>