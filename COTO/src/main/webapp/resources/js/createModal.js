function createModel(content, titleValue, actionFunction){
		var variant = {
			args: [
				{
					content: $(content).html(),
					title: titleValue,
					showCloseButton: true,

					buttons: [
						{
							label: '등록',
							classes: 'modal_button',
							action: function() {
								actionFunction();
								return $.sweetModal({
									content: '<p style = "font-weight:800; font-size:15px; padding-top: 15px;">데이터가 등록 되었습니다~:)</p>',
									icon: $.sweetModal.ICON_SUCCESS
								});
							}
						}
					]
				}
			]
		};
		
		variant.fn = variant.fn || $.sweetModal;
		variant.fn.apply(this, variant.args);
		$('select').formSelect();
}


function deleteThis(id){
	var allid = "#"+id;
	$(allid).remove();
}

var count=0;
function insertProblems(){
	var target = document.getElementById("siteName");
	var siteName = target.options[target.selectedIndex].text;
	
	var siteId = $('#siteName').val();
	var site = $("#siteName option:selected").val();
	var value = document.getElementById("problems").value;
	var valueSplit = value.split(',');
	var data = $('#confirmSite').html();
	for(var i in valueSplit){
		data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><input disabled name="'+siteId+'" value="'+valueSplit[i]+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate"/></div>';
		count++;
	}
	$('#confirmSite').html(data);
	document.getElementById("problems").value = "";
};


function rudModel(readContent, updateContent, titleValue, titleValue2, updateFunction, deleteFunction){
		var variant = {
			args: [
				{
					content: $(readContent).html(),
					title: titleValue,
					showCloseButton: true,

					buttons: [
						{
							label: '수정',
							classes: 'modal_button',
							action: function() {
								return $.sweetModal({
									title: titleValue2,
									content: $(updateContent).html(),
									buttons: [
										{
											label: '등록',
											classes: 'modal_button',
											action: function() {
												updateFunction();
												return $.sweetModal({
													content: '<p style = "font-weight:800; font-size:15px; padding-top: 15px;">데이터가 수정 되었습니다~:)</p>',
													icon: $.sweetModal.ICON_SUCCESS
												});
											}
										}
									]
								});
							}
						},
						{
							label: '삭제',
							classes: 'modal_button',
							action: function() {
								deleteFunction();
								return $.sweetModal({
									content: '<p style = "font-weight:800; font-size:15px; padding-top: 15px;">데이터가 삭제 되었습니다~:)</p>',
									icon: $.sweetModal.ICON_SUCCESS
								});
							}
						}
					]
				}
			]
		};
		
		variant.fn = variant.fn || $.sweetModal;
		variant.fn.apply(this, variant.args);
		$('select').formSelect();
}

function readModel(readContent, titleValue){
		var variant = {
			args: [
				{
					content: $(readContent).html(),
					title: titleValue,
					showCloseButton: true
				}
			]
		};
		
		variant.fn = variant.fn || $.sweetModal;
		variant.fn.apply(this, variant.args);
}