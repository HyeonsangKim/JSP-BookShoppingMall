<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "mypage"/>
</jsp:include>
<h1 align="center">내 정보 보기</h1>
<div class="container" id="main" align="center">
<form action = "MyPageLogic.do" method = "post">
	PW
	<input type = "password" name="user_password"
		placeholder = "${currentNickname }님의 비밀번호" required>
	<br>
	<input type = "submit" value ="내 정보 보기">
</form>
</div>

<jsp:include page = "/layout/footer.jsp"/>