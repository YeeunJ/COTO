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
}

function rudModel(readContent, updateContent, titleValue, updateFunction, deleteFunction){
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