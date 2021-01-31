

/*$(document).ready(function() {
	$('#register-button').on('click', function() {
		 createModel("#registerRecommendProblem", "문제집 등록", addAjax);
		 $('select').formSelect();
	});
});*/

function printAllContent(id){
	$('#readGoalList #goal').html($(id+' .readGoal').html());
	$('#readGoalList #term').html($(id+' .readTitle').html());
	$('#readGoalList #goalNum').html($(id+' .readGoalNum').html());
	$('#readGoalList #rate').html($(id+' .readRate').html());
	
	/*$('#UuserProblemID').html(id.substring(8, id.length));
	$('#Usite').html($(id+' .pSite').html());
	$('#UproblemName').html($(id+' .pTitle').html());
	$('#Umemo').html($(id+' .pMemo').html());
	$('#Uregdate').html($(id+' .pRegdate').html());
	$('#Udifficulty').attr('value', $(id+' .pDifficulty').attr('alt'));
	console.log($(id+' .pDifficulty').attr('alt'));*/
	
	readModel("#readGoalList", $(id+' .readTitle').html());
	$('select').formSelect();
}