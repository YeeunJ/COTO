<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<span class="<c:choose><c:when test = "${countInfo.recommendYN ==true}">clicked-icon</c:when><c:when test = "${countInfo.recommendYN ==false}">like-icon</c:when></c:choose> icon" onclick="addRecomCount()"></span><span id="readRecommends" class="bold">${countInfo.recommendCount}</span><span></span>
