
// Or with jQuery
$(document).ready(function(){
    $('select').formSelect();
    
    $('#searchButton').on('click', function() {
		console.log("click");
		search();
	});
	$('#orderValue').on('change', function() {
		console.log("change");
		search();
	});
	
	search(1);

});

function search(page){

	$.ajax({
			url: "groupList/search",
			type: "POST",
			async: false,
			data: {
				page: page,
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val()
			},
			success: function(data){
				//console.log(data);
				$('#pageajaxContent').html(data);
				//alert(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	});
}