

function printAllContent(id, goalID){

	readProblemActivities(goalID);
	
	$('#goal').html($(id+' .readGoal').html());
	$('#term').html($(id+' .readTitle').html());
	$('#goalNum').html($(id+' .readGoalNum').html());
	$('#rate').html($(id+' .readRate').html());
	
	readModel("#readGoalList", "기록 상세보기");
	$('select').formSelect();
} 

function readProblemActivities(goalID){
	$.ajax({
		url: "activities/readProblemActivities",
		type: "POST",
		async: false,
		data: {
			id:goalID
		},
		success: function(data){
			console.log(data);
			$('#readGoalList').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}