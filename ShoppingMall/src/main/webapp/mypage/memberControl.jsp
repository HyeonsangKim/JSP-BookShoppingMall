<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name = "title" value="adminPage"/>
</jsp:include>

<c:set var = "dto" value = "${requestScope.dto }" scope= "page"/>
<c:set var ="admin" value="${sessionScope.admin }" scope="session"/>
<c:remove var="dto" scope= "request"/>

 
<c:if test = "${ admin != 'admin' }">
	<script>
		alert("권한이 없습니다.");
		history.back();
	</script>
</c:if>
<h1 align="center">회원 목록</h1>
<div class="container" id="main" align="center">
<table border="1" class="table">
	<thead class="thead-dark">
	<tr align = "center">
		<th width = 15%>ID</th>
		<th width = 15%>NAME</th>
		<th width = 20%>EMAIL</th>
		<th width = 15%>PASSWORD</th>
		<th width= 5%>DELETE</th>
	</tr>
	</thead>
	<c:forEach var="dto" items="${memberList }">
		<tr>
			<td>${dto.id }</td>
			<td>${dto.nickname }</td>
			<td>${dto.email }</td>
			<td>${dto.password }</td>
			<td align="center">
			<a href="MemberDelete.do?id=${dto.id }">삭제</a>
			</td>
		</tr>
	</c:forEach>
</table>
<input type = "button" value="메인" onclick="location.href='/ShoppingMall/index.do'">
</div>
<jsp:include page= "/layout/footer.jsp"/>