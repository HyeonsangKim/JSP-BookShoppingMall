<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Join"/>
</jsp:include>
<div class="container" id="main" align="center">
<c:choose>
	<c:when test="${ result }">
		<h3>회원가입에 감사드립니다.</h3>
	<input type = "button" value = "로그인" onclick = "location.href='/ShoppingMall/login/LoginPage.do'">
	</c:when>
	<c:otherwise>
		<h3>회원가입에 실패했습니다.</h3>
		<input type = "button" value = "뒤로가기" onclick = "history.back()">
	</c:otherwise>
</c:choose>
</div>
<jsp:include page="/layout/footer.jsp"/>