var tagChecking;
var tagName;

// Or with jQuery
$(document).ready(function(){
	tagChecking = 0;

    $('#orderValue').formSelect();
    $('#searchButton').on('click', function() {
		if($('#searchValue').val() == "") tagChecking = 0;

    	if(tagChecking == 0) search();
    	else if(tagChecking == 1) searchTag();
	});
	$('#orderValue').on('change', function() {
		if(tagChecking == 0) search();
    	else if(tagChecking == 1) searchTag();
	});
	search();
	tagMore(0, 5);
	
	if(!(getParameterByName('tagName') == "")) parameterTag(getParameterByName('tagName'));
});

function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

function tagMore(first, length) {
	for(var i=0;i<length;i++) {
		$('#check'+i+'Button').show();
	}

	if(first == 1) {
		$('#tag_show').hide();
		$('#tag_hide').show();
	}
}

function tagMoreDone(length) {
	for(var i=5;i<length;i++) {
		$('#check'+i+'Button').hide();
	}

	$('#tag_hide').hide();
	$('#tag_show').show();
}

var selectHtml="";

function search(page){
	var tagArray = [];
	$('.tagCheck:checked').each(function(){
		tagArray.push(this.value);
		console.log(this.value);
	});

	if($('#recentPage').val() == null) {
		page=1;
	}	
	
	$.ajax({
			url: "recommendProblem/search",
			type: "POST",
			async: false,
			data: {
				page: page,
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val(),
				tagValue: JSON.stringify(tagArray)
			},
			success: function(data){
				$('#pageajaxContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	});
}

function moveUserPage(nickName){
	location.href='./'+encodeURI(encodeURIComponent(nickName));
};

function parameterTag(name) {
	tagChecking = 1;
	tagName = name;
	console.log(name);
	
	searchTag();
}

function clickTag(id) {
	tagChecking = 1;
	tagName = $('#'+id).val();
	searchTag();

	/*console.log(id);
	console.log($('#'+id).val());
	$.ajax({
			url: "recommendProblem/tagSearch",
			type: "POST",
			async: false,
			data: {
				tagValue: $('#'+id).val()
			},
			success: function(data){
				$('#pageajaxContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	});*/
}

function searchTag(page){
	//console.log(tagChecking);
	//console.log(tagName);

	var tagArray = [];
	$('.tagCheck:checked').each(function(){
		tagArray.push(this.value);
		console.log(this.value);
	});

	if($('#recentPage').val() == null) {
		page=1;
	}	

	$.ajax({
			url: "recommendProblem/tagSearch",
			type: "POST",
			async: false,
			data: {
				page: page,
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val(),
				tagValue: JSON.stringify(tagArray),
				tagName: tagName
			},
			success: function(data){
				$('#pageajaxContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	});
}

function searchF(){
	search(1);
}

/* create */
//추천집 create modal 부르는것
function callModal(userID) {
	selectHtml = $('#selectHtml').html();
	
	if(userID > 0) createModel("#createProblems", "문제집 등록", addajax, searchF);
	else alert("로그인을 해야 글쓰기가 가능합니다.");
}

//comment create
function addComment() {	
	var recomID = $('#readRecomID').html();

	if (confirm("댓글을 추가하시겠습니까?")) {
		$.ajax({
			url : "recommendProblem/addComment",
			type : "POST",
			async : false,
			data : {
				recomID : recomID,
				content : $('.sweet-modal-content #comment-textarea').val()
			},
			success : function(data) {
//				count += valueSplit.length+1;
				$('.sweet-modal-content #recomCountCommentContent').html(data);
				$('.sweet-modal-content #comment-textarea').val("");
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
			}
		});
	}
}

// delete comment
function deleteComment (commentID) {
	var recomID = $('#readRecomID').html();
	if(confirm("댓글을 삭제하시겠습니까?")) {
		$.ajax({
	        url : './recommendProblem/deleteComment',
	        type: 'POST',
	        data: {
				recomID: recomID,
				commentID : commentID
	        },
	        success: function(data) {
				$('.sweet-modal-content #recomCountCommentContent').html(data);
	        },
	        error:function(request,status,error){
	            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        },
	    });
	}
	
}



//create modal db에
function addajax(){
	var siteId = [];
	var problem = [];
	var link = [];
	var title = $('.sweet-modal-content #createTitle').val();
	var difficulty_cnt = document.getElementsByName("difficulty").length;
	var tag = [];
	var content = $('.sweet-modal-content #createContent').val();
	
	$('.sweet-modal-content .problem').each(function(){
		
		var s_id = 0;
		var l = "";
		var p;
		
		var valueSplit = $(this).val().split(' (');
		
		if($(this).attr('name') == 0){ // link로 설정하는 경우
			l = valueSplit[0].trim();
			console.log("link: "+l);
			
			var split = l.split('/');
			p = split[split.length-1].trim();
			console.log("problem: "+split[split.length-1].trim());

		} else { // siteId 존재하는 경우
			s_id = $(this).attr('name');
			p = valueSplit[0].trim();
			len = $(this).val().split(' - ');
			if(len.length != 0)
				l = len[len.length-1].trim();
		}
		
		siteId.push(s_id);
		problem.push(p);
		link.push(l);
		
	});
	
	probs = {"siteId":siteId, "problem":problem, "link":link};
	
	var tag_data= $('.sweet-modal-content #problemTag').text(); //$('.sweet-modal-content #problemTag').material_chip('data');
	var tagSplit = tag_data.split("close");
	for(var i in tagSplit) {
		tagSplit[i] = tagSplit[i].trim();
		
		if(tagSplit[i] === '') continue;
		else tag.push(tagSplit[i]);
	}
	
	for(var i=0;i<difficulty_cnt;i++) {
		if(document.getElementsByName("difficulty")[i].checked == true)
			var difficulty = document.getElementsByName("difficulty")[i].value;
	}		
	
	$.ajax({
        url : './recommendProblem/createRecomProblem',
        type: 'POST',
        data: {
        	"siteId":siteId, "problem":problem, "link":link, "title":title, "difficulty":difficulty, "tag":tag, "content":content
        },
        success: function(data) {
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });

}

var count=0;
var index;

//create modal에서 problem입력할 때
function insertProblems(){
	
	var siteName = $(".sweet-modal-content #siteName option:selected").text();
	var siteId = $('.sweet-modal-content #siteName').val();
	var site = $(".sweet-modal-content #siteName option:selected").val();
	var value = $(".sweet-modal-content #problems").val();
	var valueSplit = value.split(',');
	var data = $('.sweet-modal-content #confirmSite').html();
	$(".sweet-modal-content #problems").val("");
	if(siteId == 1){
		$.ajax({
        url : './crawling/'+siteName,
        type: 'POST',
        data: {
        	"problem": valueSplit,
        	"siteID": siteId,
        	"count": count
        },
        success: function(data){
            count += valueSplit.length+1;
            var data2 = $('.sweet-modal-content #confirmSite').html()+data;
        	$('.sweet-modal-content #confirmSite').html(data2);
        	index="success";
        	checkInsert(index);
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
	}else{
		for(var i in valueSplit){
			data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><i class="small smaller material-icons checkIcon" style="color:green;">done</i><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/></div>';
			count++;
		}
		$('.sweet-modal-content #confirmSite').html(data);
//		$('#confirmSite').html(data);
	}
};

function baekjoon(){
	var siteSelect = document.getElementById("siteName");
	var selectValue = siteSelect.options[siteSelect.selectedIndex].value;
	var inputValue = $(".sweet-modal-content #problems").val();
	var value = parseFloat(inputValue.replace(/,/gi, " "));

	if(selectValue=='1'){
		if(isNaN(value) == true){ 
			alert("백준 문제를 등록할때는 숫자만 입력할 수 있습니다.");
			
		}else{
			insertProblems();
		}
		
	}else{
		insertProblems();
	}
}

//read modal에서 recom count
function addRecomCount(){
	$.ajax({
		url: "./recommendProblem/addRecomCount",
		type: "POST",
		async: false,
		data: {
			recomID:$('#readRecomID').html()
		},
		success: function(data){
			$('.sweet-modal-content #recomCountCommentContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}


/* read */
//문제 list에서 클릭 시, detail modal 불러오기
function printAllContent(recomId){
	selectHtml = $('#selectHtml').html();
	
	readDetailModalContent(recomId);
}

//detail modal에 들어갈 내용 read
function readDetailModalContent(recomID, count) {
	var title;
	var logID;
	var uID;
	var adminID;
	var tCnt;
	
	$.ajax({
		url : "recommendProblem/readModalInfo",
		type : "POST",
		async : false,
		data : {
			recomID : recomID,
		},
		success : function(data) {
			var dataSplit = data.split("\n");
			for(var i=0;i<dataSplit.length;i++) {
				dataSplit[i] = dataSplit[i].trim();

				if(dataSplit[i].indexOf("readTitle") != -1) title = $( dataSplit[i] ).text(); //console.log(dataSplit[i]);
				else if(dataSplit[i].indexOf("readLoginID") != -1) logID = $( dataSplit[i] ).text();
				else if(dataSplit[i].indexOf("readUserID") != -1) uID = $( dataSplit[i] ).text();
				else if(dataSplit[i].indexOf("readAdminID") != -1) adminID = $( dataSplit[i] ).text();
				else if(dataSplit[i].indexOf("updateTagCount") != -1) tCnt = $( dataSplit[i] ).text();
				
			}
			
			$("#modalContent").html(data);
			if(logID == uID || adminID > 0) rudModel("#readRecommendProblem", "#updateRecommendProblem", title, title, updateAjax, deleteAjax, search, tCnt);
			else readCartModel("#readRecommendProblem", title, search);
		},
		error : function(request, status, error) {
			console.log("code:" + request.status + "\n"
					+ "message:" + request.responseText + "\n"
					+ "error:" + error);
		}
	});
}


/* update */
//read modal를 update
function updateAjax (){
	var recomID = $('.sweet-modal-content #updateRecomID').val(); 
	var title = $('.sweet-modal-content #updateTitle').val(); 
	var content = $('.sweet-modal-content #updateContents').val();
	var tag = [];
	var difficulty_cnt = document.getElementsByName("updateDifficulty").length;
	var siteId = [];
	var problem = [];
	var link = [];
	
	$('.sweet-modal-content .problem').each(function(){
		
		var s_id = 0;
		var l = "";
		var p;
		
		var valueSplit = $(this).val().split(' (');
		
		if($(this).attr('name') == 0){ // link로 설정하는 경우
			l = valueSplit[0].trim();
			console.log("link: "+l);
			
			var split = l.split('/');
			p = split[split.length-1].trim();
			console.log("problem: "+split[split.length-1].trim());

		} else { // siteId 존재하는 경우
			s_id = $(this).attr('name');
			p = valueSplit[0].trim();
			len = $(this).val().split(' - ');
			if(len.length != 0)
				l = len[len.length-1].trim();
		}
		
		siteId.push(s_id);
		problem.push(p);
		link.push(l);
		
	});
	
	var tag_data= $('.sweet-modal-content #updateProblemTag').text(); //$('.sweet-modal-content #problemTag').material_chip('data');
	var tagSplit = tag_data.split("close");
	for(var i in tagSplit) {
		tagSplit[i] = tagSplit[i].trim();
		
		if(tagSplit[i] === '') continue;
		else tag.push(tagSplit[i]);
	}
	
	for(var i=0;i<difficulty_cnt;i++) {
		if(document.getElementsByName("updateDifficulty")[i].checked == true)
			var difficulty = document.getElementsByName("updateDifficulty")[i].value;
	}
	
	$.ajax({
		url: "./recommendProblem/updateRecomProblem",
		type: "POST",
		async: false,
		data: {
			"recomID": recomID, "siteId":siteId, "problem":problem, "link":link, "title":title, "difficulty":difficulty, "tag":tag, "content":content
		},
		success: function(data){
			$('#recommendContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}
var count = 0;
//update modal에서 problem입력할 때
function updateProblems(){
	var siteName = $(".sweet-modal-content #siteName option:selected").text();
	var siteId = $('.sweet-modal-content #siteName').val();
	console.log("siteId: "+siteId);
	var site = $(".sweet-modal-content #siteName option:selected").val();
	var value = $(".sweet-modal-content #updateConfirmProblems").val();
	
	var valueSplit = value.split(',');
	var data = $('.sweet-modal-content #updateConfirmSite').html();
	$(".sweet-modal-content #problems").val("");
	console.log(count);
	if(siteId == 1){
		$.ajax({
        url : './crawling/'+siteName,
        type: 'POST',
        data: {
        	"problem": valueSplit,
        	"siteID": siteId,
        	"count": count
        },
        success: function(data){
            console.log(count);
            count += valueSplit.length+1;
            console.log(count);
            var data2 = $('.sweet-modal-content #updateConfirmSite').html()+data;
        	$('.sweet-modal-content #updateConfirmSite').html(data2);
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
    });
	}else{
		for(var i in valueSplit){
			data += '<div id = "confirmProblemValue'+count+'" onClick="deleteThis(\'confirmProblemValue'+count+'\')"><i class="small smaller material-icons" style="color:green;">done</i><input disabled name="'+siteId+'" value="'+valueSplit[i].trim()+' ('+siteName+')" id="last_name disabled" type="text" class="problem validate" style="width:90%;padding-left: 10px;"/></div>';
			count++;
		}
		$('.sweet-modal-content #updateConfirmSite').html(data);
		$('#updateConfirmSite').html(data);
	}
	$(".sweet-modal-content #updateConfirmProblems").val("");
};


/* delete */
//read modal을 delete
function deleteAjax (){
	$.ajax({
		url: "./recommendProblem/deleteRecomProblem",
		type: "POST",
		async: false,
		data: {
			id:$('#updateRecomID').html()
		},
		success: function(data){
			$('#recommendContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

//(create나 update에서)problem을 클릭하면, 해당 problem는 delete
function deleteThis(id){
	var allid = "#"+id;
	$(allid).remove();
	checkDelete(id);
}

//read modal에서 recom count
function deleteRecomCount(){
	$.ajax({
		url: "./recommendProblem/deleteRecomCount",
		type: "POST",
		async: false,
		data: {
			recomID:$('#readRecomID').html()
		},
		success: function(data){
			console.log(data);
			$('.sweet-modal-content #recomCountCommentContent').html(data);
		}, 
		error:function(request, status, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function addRecomCart(recom){
	var page;
	if(confirm("문제집을 장바구니에 담으시겠습니까?")) {
		var tagArray = [];
		$('.tagCheck:checked').each(function(){
			tagArray.push(this.value);
			console.log(this.value);
		});
	
		if($('#recentPage').val() == null) {
			page=1;
		}	

		$.ajax({
			url: "./recommendProblem/addRecomCart",
			type: "POST",
			async: false,
			data: {
				recomID:recom,
				page: page,
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val(),
				tagValue: JSON.stringify(tagArray)
			},
			success: function(data){
				$('#pageajaxContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}

}

function addRecomCartinModal(recom){
	if(confirm("문제집을 장바구니에 담으시겠습니까?")) {
		$.ajax({
			url: "./recommendProblem/addRecomCartinModal",
			type: "POST",
			async: false,
			data: {
				recomID:recom
			},
			success: function(data){
				$('.sweet-modal-content #recomCountCommentContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}

}

function deleteRecomCart(recom){
	var page;
	if(confirm("문제집을 장바구니에서 지우시겠습니까?")) {
		var tagArray = [];
		$('.tagCheck:checked').each(function(){
			tagArray.push(this.value);
			console.log(this.value);
		});
	
		if($('#recentPage').val() == null) {
			page=1;
		}	
				
		$.ajax({
			url: "./recommendProblem/deleteRecomCart",
			type: "POST",
			async: false,
			data: {
				recomID:recom,
				page: page,
				searchValue:$('#searchValue').val(),
				orderValue:$('#orderValue option:selected').val(),
				tagValue: JSON.stringify(tagArray)
			},
			success: function(data){
				$('#pageajaxContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}

}

function deleteRecomCartinModal(){
	if(confirm("문제집을 장바구니에서 지우시겠습니까?")) {
		$.ajax({
			url: "./recommendProblem/deleteRecomCartinModal",
			type: "POST",
			async: false,
			data: {
				recomID:$('#readRecomID').html()
			},
			success: function(data){
				$('.sweet-modal-content #recomCountCommentContent').html(data);
			}, 
			error:function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}

}




function checkProblem(id, name, link){
	$.ajax({
		url : "recommendProblem/addRecomCheck",
		type : "POST",
		async : false,
		data : {
			rpID : id,
			problemName: name,
			link: link
		},
		success : function(data) {
			console.log(data);
			idName = ".sweet-modal-content #eachProblemContent"+id;
			console.log(idName);
			$(idName).html(data);
		},
		error : function(request, status, error) {
			alert("허용되지 않은 접근입니다. 새로고침 후 다시 시도해주세요.");
			console.log("code:" + request.status + "\n"
					+ "message:" + request.responseText + "\n"
					+ "error:" + error);
		}
	});
}
function uncheckProblem(id, name, link){
	$.ajax({
		url : "recommendProblem/deleteRecomCheck",
		type : "POST",
		async : false,
		data : {
			rpID : id,
			problemName: name,
			link: link
		},
		success : function(data) {
			console.log(data);
			idName = ".sweet-modal-content #eachProblemContent"+ id;
			$(idName).html(data);
		},
		error : function(request, status, error) {
			alert("허용되지 않은 접근입니다. 새로고침 후 다시 시도해주세요.");
			console.log("code:" + request.status + "\n"
					+ "message:" + request.responseText + "\n"
					+ "error:" + error);
		}
	});
}

function guestUser() {
	alert("로그인 후 이용할 수 있는 기능입니다.");
	if(confirm("로그인 창으로 이동하시겠습니까?")) location.href="./login";
}

function resetContent() {
	$('#createProblems #confirmSite').html("");
	$('#selectHtml').html(selectHtml);
	$('.sweet-modal-content .chip').remove();
}



// 수정 필요 ! -> update 창이 뜨면 보이도록
/*function updateChipTag(data) {
	//var tagData = data;
	var tdSplit = data.split('\n');
	var cnt=0;
	var td = "";
	
	for(var i in tdSplit){
		tdSplit[i] = tdSplit[i].trim();
		tdSplit[i] = tdSplit[i].replaceAll(' ', ''); 
		if(tdSplit[i] === '') continue;
		else {
			console.log(tdSplit[i]);
			if(cnt == 0) td += "[{\ntag: \'"+tdSplit[i]+"\',\n}"
			else td += ", {\ntag: \'"+tdSplit[i]+"\',\n}"

			//console.log(td);
			//tag[cnt].push()
			
			
			$('#updateProblemTag').html('<div class="chip" id="tabindex"'+cnt+'>'+tdSplit[i]+'<i class="material-icons close">close</i></div>');
			cnt++;
		}

		//td = "";
	}
	
	$('#updateProblemTag').html('<input class="input" id="8b9cc387-e450-ef54-bd7b-64f87cf19ba0" placeholder="+Tag">');

	td += "]";

}

function updateInsertProblems(data){
	
	var dataSplit = data.split('\n');
	var cnt=0;
	var result = "";
	var data2 = [];
	var pName = [];
	var sName = [];
	var siteData = $('#siteName').text();
	var siteSplit = siteData.split('\n');
	var site = [];
	
	for(var i in dataSplit){
		dataSplit[i] = dataSplit[i].trim();
		dataSplit[i] = dataSplit[i].replaceAll(' ', ''); 
		
		if(dataSplit[i] === '') continue;
		else data2.push(dataSplit[i]);
	}
	
	for(var i in siteSplit){
		siteSplit[i] = siteSplit[i].trim();
		siteSplit[i] = siteSplit[i].replaceAll(' ', ''); 
		
		if(siteSplit[i] === '') continue;
		else site.push(siteSplit[i]);
	}
	
	for(var i in data2){
		if(i%2==0) sName.push(data2[i]);
		else pName.push(data2[i]);
	}
	
	for(var i in pName){
		//input name에 site id필요!!!
		
		if(sName[i] == site[site.length-1]) result += '<div id = "updateConfirmProblemValue'+count+'" onClick="deleteThis(\'updateConfirmProblemValue'+count+'\')"><input disabled name="0" value="'+pName[i]+' ('+sName[i]+')" id="updateLast_name disabled" type="text" class="updateConfirmProblem validate"/></div>';
		else {
			for(var j=0;j<site.length-1;j++) {
				if(sName[i] == site[j]) {
					result += '<div id = "updateConfirmProblemValue'+count+'" onClick="deleteThis(\'updateConfirmProblemValue'+count+'\')"><input disabled name="'+j+'" value="'+pName[i]+' ('+sName[i]+')" id="updateLast_name disabled" type="text" class="updateConfirmProblem validate"/></div>';
					break;
				}
			}
		}
		
		count++;
	}
	
	$('.sweet-modal-content #updateConfirmSite').html(result);
	$('#updateConfirmSite').html(result);
};*/
