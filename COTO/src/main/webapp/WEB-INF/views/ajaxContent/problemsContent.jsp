<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="tableRow">
	<span class="tableCell th05">No.</span>
	<span class="tableCell th2">문제 제목</span>
	<span class="tableCell th2">사이트</span>
	<span class="tableCell th2 pMemo">메모</span>
	<span class="tableCell th1 pRegdate">날짜</span>
	<span class="tableCell th1 pDifficulty">난이도</span>
	<span class="tableCell th1"></span>
</div>
									
<c:forEach items="${problems}" var="problem" varStatus="status">
	<div class="tableRow center" id="problem${problem.id}" onclick="printAllContent('#problem${problem.id}')">
		<span class ="tableCell td05">${status.count}</span>
		<span class ="tableCell td2 pTitle"><a href="${problem.link}" target="_blank">${problem.problem}</a></span>
		<span class ="tableCell td2 pSite">${problem.site}</a></span>
		<span class ="tableCell td2 pMemo">${problem.memo}</span>
		<span class ="tableCell td2 pRegdate">${problem.regDate}</span>
		<span class ="tableCell td1 pDifficulty"><img style="width: 60px;" alt="${problem.difficulty}" src="../resources/img/difficulty${problem.difficulty}.png"></span>
		<span class="tableCell td">
			<button value="${problem.id}" class="editBtn" type="button"><i class="fas fa-pen"></i></button>
			<button value="${problem.id}" class="deleteBtn" type="button"><i class="fas fa-times"/></i></button>
		</span>
		<span class="pSiteUrl" style="display:none;">${problem.siteUrl}</span>
	</div>
</c:forEach>

<script>
$(document).ready(function() {
	$(document).on("click", ".editBtn", function(){
		var form = document.form1;
		var tableRow = $(this).closest('.tableRow');
		var editCell = tableRow.find('.tableCell');
		var index = $('.tableRow').index(tableRow);
		form.action="manageCodingsite/editok";
		console.log(posts[index-1]);
		
		$(editCell[0]).html('<input id="editonly" type="hidden" name="id" value="'+ posts[index-1].id +'" /> <input id="siteName" type="text" name="siteName" value="'+posts[index-1].siteName+'">');
		$(editCell[1]).html('<input id="siteUrl" type="text" name="siteUrl" value="'+ posts[index-1].siteUrl +'">');
		$(editCell[2]).html('<button id="cancelbtn" class="cancelbtn waves-effect waves-light btn-small green" type="button">취소</button>');
		$(editCell[3]).html('<button id="submitbtn" class="submitbtn waves-effect waves-light btn-small green" type="submit">수정</button>');
	});
	
	$(document).on("click", ".deleteBtn", function(){
		var id = $(this).val();

		if (confirm("정말로 삭제하겠습니까?")){
			location.href = './problems/delete/' + id;
		}
	}); 
})
</script>
<style>
.fa-times, .fa-pen {color: orange;}
button{border:0; outling:0; cursor:pointer; background-color: white;}
.th05 {
	font-weight: bold !important;
	background-color: #e69138ff !important;
	color: white;
	text-align: center;
}
</style>