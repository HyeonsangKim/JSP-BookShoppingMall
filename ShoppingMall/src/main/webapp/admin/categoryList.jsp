<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp" >
	<jsp:param name = "title" value = "categoryList"/>
</jsp:include>


<h1 align="center">카테고리 리스트</h1>
<div class="container" id="main" align="center">
<c:choose>
	<c:when test = "${categoryList == null }">
		<H2>게시글이 없습니다.</H2>
	</c:when>
	<c:otherwise>
		<table width=600 class="table">
			<tr class ="m1">
				<th>번호</th>
				<th>코드</th>
				<th>카테고리명</th>
				<th>삭제</th>
			</tr>
		<c:forEach var="n" items="${categoryList }">
			<tr>
				<td>${n.cnum }</td>
				<td>${n.code }</td>
				<td>${n.cname }</td>
				<td>
					<a href="CategoryDelete.do?id=${n.cnum }">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
</c:otherwise>
</c:choose>
<a href="CategoryInput.do">카테고리 등록하기</a>
</div>
<jsp:include page = "/layout/footer.jsp" />