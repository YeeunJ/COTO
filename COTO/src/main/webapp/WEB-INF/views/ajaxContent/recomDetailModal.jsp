<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<!-- 세부 정보 모달 -->
<div id="readRecommendProblem" style="display:none;">
	<span id="readRecomID" style="display:none;">${ recomID }</span>
	
	<div id="detailRecom">
		<div>
			<div>
				<p class="title">추천 문제 설명</p>
				<div class="readBox">
					<span id="readContents" >${recom.content}</span>
				</div>
				<br><br>
			</div>
			
			<div>
				<p class="title">추천 문제 난이도</p>
				<div class="readBox">
					<span id="readDifficulties">
					<c:choose>
						<c:when test="${recom.difficulty eq 0}"><img style="width: 60px;" alt="0" src="./resources/img/difficulty0.png"></c:when>
						<c:when test="${recom.difficulty eq 1}"><img style="width: 60px;" alt="1" src="./resources/img/difficulty1.png"></c:when>
						<c:when test="${recom.difficulty eq 2}"><img style="width: 60px;" alt="2" src="./resources/img/difficulty2.png"></c:when>
						<c:when test="${recom.difficulty eq 3}"><img style="width: 60px;" alt="3" src="./resources/img/difficulty3.png"></c:when>
						<c:when test="${recom.difficulty eq 4}"><img style="width: 60px;" alt="4" src="./resources/img/difficulty4.png"></c:when>
						<c:when test="${recom.difficulty eq 5}"><img style="width: 60px;" alt="5" src="./resources/img/difficulty5.png"></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					</span>
				</div>
				<br><br>
			</div>
			
			<div>
				<p class="title">추천 문제 태그</p>
				<div id="readTags">
				<c:forEach items="${recomProblemTag}" var="rpt">
					<c:if test="${rpt.recomID eq recoms.id}">
						<span class="readTagChips">${rpt.tag}</span>
					</c:if>
				</c:forEach>
				</div>
				<br><br>
			</div>
		
			<div>
				<p class="title desc">추천 문제</p>
				<div id="readProblems" class="readBox"></div>
				<c:forEach items="${recomProblem}" var="rp">
					<div class="sitetitle">${rp.siteName}</div>
					<div><p>${rp.name}</p></div>
				</c:forEach>
			</div>
		</div>
		
		<div>
		 <div class="details">
				<span class="<c:choose><c:when test = "${countInfo.recommendYN ==true}">clicked-icon</c:when><c:otherwise>like-icon</c:otherwise></c:choose> icon" onclick="addRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>		
				<span class="comment-icon icon"></span><span id="commentCount" class="bold">${ commentCount }</span><span></span>
			</div>
			<div id="commentDetail">
				<div class="comment-add">
					<textarea id="comment-textarea" placeholder="댓글을 달아주세요."></textarea>
					<button id="addComment" class="modal_button add-btn" onclick="addComment()">등록</button>
				</div>
				<div id="modal-comment" class="wrapper">
					<input type="text" name="recomID" value="${ recomID }" hidden>
					
					<c:forEach items="${recomComment}" var="r" varStatus="status">
						<div class="comment-wrapper">
							<span class="username">${ r.name }</span><span class="commentdate">${ r.regDate }</span>
							<p class="comment">${ r.content }</p>
						</div>
					</c:forEach>
				</div>
		
			</div>
		
		</div>
	</div>
</div>

<!-- 세부 정보 모달 update -->
<div id="updateRecommendProblem" style="display:none;">
	<form>
			<textarea id="updateRecomID" class="validate" style="display:none;"></textarea>
			
			<div>
				<p class="title">추천 문제집 제목</p>
				<input id="updateTitle" name="updateTitle" type="text" class="validate" value=""/>
				<br><br>
			</div>
			
			<div>
				<p class="title">추천 문제 설명</p>
				<textarea id="updateContents" class="validate" rows="5"></textarea>
				<br><br>
			</div>
			
			<div>
				<p class="title">추천 문제 난이도</p>
				<div class="row">
					<div class="input-field col s2">
						<p>
							<input type="radio" name="updateDifficulty" id="ud1" value="1"/>
							<label for="ud1" class="diffCont">1</label>
						</p>
					</div>
					<div class="input-field col s2">
						<p>
							<input type="radio" name="updateDifficulty" id="ud2" value="2" class="radioMrg"/>
							<label for="ud2" class="diffCont">2</label>
						</p>
					</div>
					<div class="input-field col s2">
						<p>
							<input type="radio" name="updateDifficulty" id="ud3" value="3" class="radioMrg"/>
							<label for="ud3" class="diffCont">3</label>
						</p>
					</div>
					<div class="input-field col s2">
						<p>
							<input type="radio" name="updateDifficulty" id="ud4" value="4" class="radioMrg"/>
							<label for="ud4" class="diffCont">4</label>
						</p>
					</div>
					<div class="input-field col s2">
						<p>
							<input type="radio" name="updateDifficulty" id="ud5" value="5" class="radioMrg"/>
							<label for="ud5" class="diffCont">5</label>
						</p>
					</div>
					<div class="input-field col s2">
						<p>
							<input type="radio" name="updateDifficulty" id="ud0" value="0" class="radioMrg" /> 
							<label for="ud0" class="diffCont">설정 안함</label>
						</p>
					</div>
				</div>
				<br><br>
			</div>
			
			<div>
				<p class="title">추천 문제 태그</p>
				<textarea id="updateTags" class="validate" style="display:none;"></textarea>
				<div id="updateProblemTag" class="chips chips-placeholder input-field"></div>
				<br><br>
			</div>
	
			<div>
				<p class="title desc">추천 문제</p>
				<div class="row">
					<div id="selectHtml" class="input-field col s4">
						<select id="siteName" required>
							<optgroup label="코딩사이트 선택">
								<c:forEach items="${codingSite}" var="site">
									<option value="${site.id}">${site.siteName}</option>
								</c:forEach>
							</optgroup>
							<optgroup label="링크로 입력">
								<option value="0">링크로 입력</option>
							</optgroup>
						</select>
						<label>코딩사이트 선택</label> 
						<span class="helper-text">코딩 사이트를 선택해서 입력하거나 링크로 입력할 수 있습니다.</span>
					</div>
					<div class="input-field col s6">
						<input id="updateConfirmProblems" type="text" class="validate"> 
						<label for="updateConfirmProblems">Problems</label> 
						<span class="helper-text">문제들을 입력할 때 ,로 구분해주세요!!</span>
					</div>
					<button type="button" id="updateAdd" class="modal_button lighten-1" onClick="updateProblems()">추가</button>
				</div>
				<div class="input-field col s10">
					<label for="updateLast_name">입력한 Problems</label> <br> <br>
					<div class="recom-confirmSite" id="updateConfirmSite"></div>
				</div>
			</div>
	</form>		
</div>