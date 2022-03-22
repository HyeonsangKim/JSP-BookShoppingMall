<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp" >
	<jsp:param name = "title" value = "mypage"/>
</jsp:include>
<c:set var = "dto" value = "${requestScope.dto }" scope= "page"/>
<c:remove var="dto" scope= "request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">주문하기</h1>
<div class="container" id="main" align="center">
<form action = "OrderLogic.do" method = "post">
	<table border = "1" class="table">
	<input type = "hidden" name="userName" value="${dto.nickname }">
	<input type = "hidden" name="email" value="${dto.email }">
	<input type = "hidden" name="phone" value="${dto.phone }">
	<input type = "hidden" name="address" value="${dto.address }">
	<input type = "hidden" name="fullprice" value="${price}">
		<tr>
			<th>ID</th>
			<td>${dto.id}</td>
		</tr>			
		<tr>
			<th>
				Nickname
			</th>
			<td>
				${dto.nickname }
			</td>
		</tr>
		<tr>
			<th>
				Email
			</th>
			<td>
				${dto.email }
			</td>
		</tr>
		<tr>
			<th>
				Phone
			</th>
			<td>
				${dto.phone }
			</td>
		</tr>
		<tr>
			<th>
				가입일
			</th>
			<td>
				<h4>${dto.regdate }</h4>
			</td>
			
		</tr>
		<tr>
			<th>
				Address
			</th>
			<td>
				${dto.address }
			</td>
		</tr>
		<tr>
			<th>
				총 가격
			</th>
			<td>
				${price }
			</td>
		</tr>
		<tr>
			<td colspan = "2" align = "center">
				<input type = "submit" value = "주훈하기" >
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>