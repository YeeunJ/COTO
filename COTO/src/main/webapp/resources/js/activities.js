

function printAllContent(id, goalID){
	$('#readGoalList #goal').html($(id+' .readGoal').html());
	$('#readGoalList #term').html($(id+' .readTitle').html());
	$('#readGoalList #goalNum').html($(id+' .readGoalNum').html());
	$('#readGoalList #rate').html($(id+' .readRate').html());
	
	readProblemActivities(goalID);
	
	readModel("#readGoalList", $(id+' .readTitle').html());
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
			//console.log(data);
			$('#activitiesContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}