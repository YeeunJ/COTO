<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="table" style="text-align: center">
	<ul class="pagination ">
		<c:if test="${ page eq 1 }">
			<li class="disabled"><a href='javascript:void(0);'><i class="material-icons">chevron_left</i></a></li>
		</c:if>
		<c:if test="${ page != 1 }">
			<li class="waves-effect"><a href="./${ pagename }?page=${page-1}"><i class="material-icons">chevron_left</i></a></li>
		</c:if>
		<c:forEach var="p" begin="${s_page}" end="${e_page}">
			<c:if test="${ p eq page }">
				<li class="active orange"><a href="./${ pagename }?page=${p}">${p}</a></li>
			</c:if>
			<c:if test="${ p != page }">
				<li class="waves-effect"><a href="./${ pagename }?page=${p}">${p}</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${ page eq e_page }">
			<li class="disabled"><a href='javascript:void(0);'><i class="material-icons">chevron_right</i></a></li>
		</c:if>
		<c:if test="${ page != e_page }">
			<li class="waves-effect"><a href="./${ pagename }?page=${page+1}"><i class="material-icons">chevron_right</i></a></li>
		</c:if>
	</ul>
</div> 