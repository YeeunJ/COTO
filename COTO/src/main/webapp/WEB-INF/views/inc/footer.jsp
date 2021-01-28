<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><link href="./resources/css/style.css?as" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="./resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />


<footer class="page-footer footergray">
	<div class="container">
		<div class="row center gray-text">
			<!-- <h2 class="bold fs-25 coding-title">코딩 사이트 바로가기</h2> -->
			<!-- <div class="row block-wrapper fs-16">
				<a href="https://www.acmicpc.net/" class="site-block">백준</a> <a
					href="https://leetcode.com/problemset/all/" class="site-block">leetcode</a>
				<a
					href="https://swexpertacademy.com/main/code/problem/problemList.do"
					class="site-block">swexpertacademy</a> <a
					href="https://programmers.co.kr/learn/challenges"
					class="site-block">programmers</a> <a
					href="https://www.oncoder.com/" class="site-block">oncoder</a> <a
					href="https://level.goorm.io/" class="site-block">groom</a>
			</div> -->
			<ul id="codingSite" class="codingSites">
				
			</ul>
			<h6 style="font-size:15px; padding-bottom : 20px; font-weight: normal;"class="gray-text">©️Copyright 김빛나리 김수현 정예은 최승아 양지후 김예진</h6> 
		
		</div>
	</div>


</footer>
</body>
</html>
<script>
	$.ajax({  
	    type: 'get',  
	    url: "${pageContext.request.contextPath}/manageCodingsite/sitelist",  
	    
	    success: function (result) {  
	        $("#codingSite").html(result);
	    }  
	});  
 </script>