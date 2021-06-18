<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import = "com.walab.coding.model.UserDTO" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page= "<%=\"../inc/my\".concat(((String)request.getAttribute(\"header\")))%>" />

<link rel="stylesheet" href="../resources/css/myGroup.css?v1" />
 <script src="../resources/js/createModal.js?v1"></script>
<script src="../resources/js/oneGroup.js?v1"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<link href="../resources/css/oneGroup.css" rel="stylesheet">

<div id="SiteContainer" class="container">
<div id="groupTitle">
		<div class="content">
			<h4 class="">| 해당 그룹이 없습니다!!</h4>
			<p onClick="location.href='./groups';" style="cursor:pointer;"> 내 그룹 페이지로 돌아가기</p>
		</div>
	</div>	
</div>