<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Board List" />
</jsp:include>

<div class="container" id="main" align="center">
<c:set var = "state">
	<c:choose>
		<c:when test="${ requestScope.state== 'write' }">
			글 쓰기
		</c:when>
		<c:when test="${ requestScope.state== 'update' }">
			글 수정
		</c:when>
		<c:when test="${ requestScope.state== 'delete' }">
			글 삭제
		</c:when>
	</c:choose>
</c:set>

<h2>
<c:choose>
	<c:when test = "${requestScope.result }">
		'${state }'(을)를 완료하였습니다.
	</c:when>
	<c:otherwise>
		'${state }'에 실패하였습니다.
	</c:otherwise>
</c:choose>
</h2>
<input type ="button" value="목록으로" onclick = "location.href='ProductList.do?p=1'">
</div>
<jsp:include page="/layout/footer.jsp"/>