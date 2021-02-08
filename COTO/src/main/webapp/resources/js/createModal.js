var count = 0;
$('.chip').on('click', function() {
							console.log("change");
						});
function createModel(content, titleValue, actionFunction){
		var variant = {
			args: [
				{
					content: $(content).html(),
					title: titleValue,
					showCloseButton: true,
					onOpen: function(){
						$('.sweet-modal-box select').formSelect();
						//$('.sweet-modal-content .chips').chips();
						$('.sweet-modal-content .chips-placeholder').chips({
							placeholder: 'Enter a tag',
							secondaryPlaceholder: '+Tag',
							onChipAdd: function(){
								var text = $('.sweet-modal-content .chips input').val();
								$('.sweet-modal-content .chips .chip:last').remove();
								$('.sweet-modal-content .chips input').before('<div class = "chip" id="tabindex'+count+'">'+text+'<i class = "material-icons close">close</i></div>');
								count++;
							},
							onChipSelect: function(){
							},
							onChipDelete: function(){
								//console.log($(this));
							}
						});
					},
					theme: $.sweetModal.THEME_MIXED,
					buttons: [
						{
							label: '등록',
							classes: 'modal_button',
							action: function() {
								actionFunction();
								return $.sweetModal({
									content: '<p style = "font-weight:800;font-size:15px;padding-top: 15px;text-align: center;">데이터가 등록 되었습니다~:)</p>',
									theme: $.sweetModal.THEME_MIXED,
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


function rudModel(readContent, updateContent, titleValue, titleValue2, updateFunction, deleteFunction, closeFunction){
		var variant = {
			args: [
				{
					content: $(readContent).html(),
					title: titleValue,
					showCloseButton: true,
					onOpen: function(){
					},
					onClose: function(){
						closeFunction();
					},
					theme: $.sweetModal.THEME_MIXED,
					buttons: [
						{
							label: '수정',
							classes: 'modal_button',
							action: function() {
								return $.sweetModal({
									title: titleValue2,
									content: $(updateContent).html(),
									onClose: function(){
										closeFunction();
									},
									onOpen: function(){
										$('.sweet-modal-box select').formSelect();
										$('.sweet-modal-content .chips-placeholder').chips({
											placeholder: 'Enter a tag',
											secondaryPlaceholder: '+Tag',
											onChipAdd: function(){
												var text = $('.sweet-modal-content .chips input').val();
												$('.sweet-modal-content .chips .chip:last').remove();
												$('.sweet-modal-content .chips input').before('<div class = "chip" id="tabindex'+count+'">'+text+'<i class = "material-icons close">close</i></div>');
												count++;
											},
											onChipSelect: function(){
											},
											onChipDelete: function(){
												//console.log($(this));
											}
										});
									},
									theme: $.sweetModal.THEME_MIXED,
									buttons: [
										{
											label: '등록',
											classes: 'modal_button',
											action: function() {
												updateFunction();
												return $.sweetModal({
													theme: $.sweetModal.THEME_MIXED,
													content: '<p style = "font-weight:800;font-size:15px;padding-top: 15px;text-align: center;">데이터가 수정 되었습니다~:)</p>',
													icon: $.sweetModal.ICON_SUCCESS,
													onClose: function(){
														closeFunction();
													}
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
									content: '<p style = "font-weight:800;font-size:15px;padding-top: 15px;text-align: center;">데이터가 삭제 되었습니다~:)</p>',
									theme: $.sweetModal.THEME_MIXED,
									icon: $.sweetModal.ICON_SUCCESS,
									onClose: function(){
										closeFunction();
									},
								});
							}
						}
					]
				}
			]
		};
		
		variant.fn = variant.fn || $.sweetModal;
		variant.fn.apply(this, variant.args);
		//$('select').formSelect();
}

function readModel(readContent, titleValue){
		var variant = {
			args: [
				{
					content: $(readContent).html(),
					title: titleValue,
					theme: $.sweetModal.THEME_MIXED,
					showCloseButton: true
				}
			]
		};
		
		variant.fn = variant.fn || $.sweetModal;
		variant.fn.apply(this, variant.args);
}