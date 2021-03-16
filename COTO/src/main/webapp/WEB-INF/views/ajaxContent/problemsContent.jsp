<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="pTable">
<div class="table">
	<div class="tableRow">
		<span class="tableCell th05">No.</span> <span class="tableCell th2">문제
			제목</span> <span class="tableCell th2">사이트</span> <span class="tableCell th2">메모</span>
		<span class="tableCell th1">날짜</span> <span class="tableCell th1">난이도</span>
		<span class="tableCell th1"></span>
	</div>

	<c:forEach items="${problems}" var="problem" varStatus="status">
		<div class="tableRow center">
			<span class="tableCell td05 pIndex">${(page-1)*list+status.count}</span>
			<span class="tableCell td2 pTitle"><a href="${problem.link}"
				target="_blank">${problem.problem}</a></span> <span
				class="tableCell td2 pSite"><a href="${problem.siteUrl}"
				target="_blank">${problem.site}</a></span> <span
				class="tableCell td2 pMemo">${problem.memo}</span> <span
				class="tableCell td2 pRegdate">${problem.regDate}</span> <span
				class="tableCell td1 pDifficulty"><img style="width: 60px;"
				alt="${problem.difficulty}"
				src="../resources/img/difficulty${problem.difficulty}.png"></span> <span
				class="tableCell td">
				<button value="${problem.id}" class="editBtn" type="button">
					<i class="fas fa-pen"></i>
				</button>
				<button value="${problem.id}" class="deleteBtn" type="button">
					<i class="fas fa-times" /></i>
				</button>
			</span>
		</div>
	</c:forEach>
</div>
</div>

<!-- pagination -->
<div class="table" style="text-align: center">
	<ul class="pagination ">
		<c:if test="${ page eq 1 }">
			<li class="disable-button"><span class="arrow-button"><i
					class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:if test="${ page != 1 }">
			<li class="waves-effect"><span class="arrow-button"
				onclick="search(${page-1})"><i class="material-icons">chevron_left</i></span></li>
		</c:if>
		<c:forEach var="p" begin="${s_page}" end="${e_page}">
			<c:if test="${ p eq page }">
				<li id="recentPage" class="active orange" value="${p}"><span
					class="pagination-button">${p}</span></li>
			</c:if>
			<c:if test="${ p != page }">
				<li class="waves-effect"><span class="pagination-button"
					onclick="search(${p})">${p}</span></li>
			</c:if>
		</c:forEach>
		<c:if test="${ page eq e_page }">
			<li class="disable-button"><span class="arrow-button"><i
					class="material-icons">chevron_right</i></span></li>
		</c:if>
		<c:if test="${ page != e_page }">
			<li class="waves-effect"><span class="arrow-button"
				onclick="search(${page+1})"><i class="material-icons">chevron_right</i></span></li>
		</c:if>
	</ul>
</div>

<br>
<div class="table">
	<div class="tableRow">
		<span class="tableCell th3" style="text-align: left !important">전체
			푼 문제</span>
	</div>
	<span class="tableCell td3 probname tc"> <c:forEach
			items="${Allproblems}" var="problem" varStatus="status">
			<nobr>
				<a href="${problem.link}" target="_blank">${problem.problem}, </a>
			</nobr>
		</c:forEach>
	</span>
</div>



<style>
.fa-times, .fa-pen {
	color: orange;
}

button {
	border: 0;
	outling: 0;
	cursor: pointer;
	background-color: white;
}

.th05 {
	font-weight: bold !important;
	background-color: #e69138ff !important;
	color: white;
	text-align: center;
}
</style>
<script>
$(document).ready(function() {

	var posts = new Array();
	<c:forEach items="${problems}" var="u" varStatus="status">
		
		var list = new Object();
		list.index = "${status.count}";
		list.id = "${u.getId()}";
		list.problem = "${u.getProblem()}";
		list.link = "${u.getLink()}";
		list.site = "${u.getSite()}";
		list.siteUrl = "${u.getSiteUrl()}";
		list.memo = "${u.getMemo()}";
		list.regDate = "${u.getRegDate()}";
		list.difficulty = "${u.getDifficulty()}";
		
		posts.push(list);
	</c:forEach>

	$(document).off("click").on("click", ".editBtn", function(){
		var tableRow = $(this).closest('.tableRow');
		var editCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);
		var pageV = $("#recentPage").val();
		
		$(editCell[0]).html('<input id="Uid" type="hidden" name="id" value="'+ posts[index-5].id +'" /> <p>'+ posts[index-5].index+'</p>');
		$(editCell[1]).html(posts[index-5].problem);
		$(editCell[2]).html(posts[index-5].site);
		$(editCell[3]).html('<input id="Umemo" type="text" name="memo" value="'+posts[index-5].memo+'">');
		$(editCell[4]).html(posts[index-5].regDate);
		$(editCell[5]).html('<input id="Udifficulty" type="number" min="0" max="5" name="difficulty" value="'+ posts[index-5].difficulty +'">');
		$(editCell[6]).html('<button id="cancelbtn" type="button"><i class="fas fa-times"/></i></button><button onclick="updateAjax('+pageV+')" type="button"><i class="fas fa-pen"></i></button>');
	});
	
	$(document).on("click", "#cancelbtn", function() {
		var tableRow = $(this).closest('.tableRow');
		var cancelCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);
	
		$(cancelCell[0]).html(posts[index-5].index);
		$(cancelCell[1]).html('<a href="'+posts[index-5].link+'" target="_blank">'+posts[index-5].problem+'</a>');
		$(cancelCell[2]).html('<a href="'+posts[index-5].siteUrl+'" target="_blank">'+posts[index-5].site+'</a>');
		$(cancelCell[3]).html(posts[index-5].memo);
		$(cancelCell[4]).html(posts[index-5].regDate);
		$(cancelCell[5]).html('<img style="width: 60px;" alt="${problem.difficulty}" src="../resources/img/difficulty'+posts[index-5].difficulty+'.png">');
		$(cancelCell[6]).html('<button value="${problem.id}" class="editBtn" type="button"><i class="fas fa-pen"></i></button><button value="${problem.id}" class="deleteBtn" type="button"><i class="fas fa-times"/></i></button>');
	});

	$(document).on("click", ".deleteBtn", function(){
		var pageV = $("#recentPage").val();
		if (confirm("정말로 삭제하겠습니까?")){
			$.ajax({
				url: "./problems/delete",
				type: "POST",
				async: false,
				data: {
					id: $(this).val(),
					page: pageV
				},
				success: function(data){
					$('#problemsContent').html(data);
				}, 
				error:function(request, status, error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        }
			});
		}
	}); 
})
</script>