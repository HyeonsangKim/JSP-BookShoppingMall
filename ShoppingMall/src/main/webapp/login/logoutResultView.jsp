<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value ="Logged out"/>
</jsp:include>
<h1 align="center">로그아웃</h1>
<div class="container" id="main" align="center">
<h2>로그아웃을 완료했습니다.</h2>
<input type = "button" value = "메인으로" onclick ="location.href='/ShoppingMall/index.do'">
</div>

<jsp:include page="/layout/footer.jsp"/>