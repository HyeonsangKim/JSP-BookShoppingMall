<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="board" />
</jsp:include>

<h1 align="center">고객 문의</h1>
<div class="container" id="main" align="center">
<c:choose>
	<c:when test = "${list == null }">
		<H2>게시글이 없습니다.</H2>
	</c:when>
	<c:otherwise>
		<table border="1" width="100%" class="table">
			<thead class="thead-dark">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="n" items="${list }">
					<tr>
						<td>${n.id}</td>
						<td class="text-align-left"><a
							href="BoardRead.do?p=${n.id }">${n.title}</a><span>
								[${n.cmtCount }]</span></td>
						<td>${n.nickname }</td>
						<td><fmt:formatDate pattern="yy/MM/dd" value="${n.regdate }" />
						</td>
						<td align="center">${n.hit }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan = "5" align = "center">
						<c:if test = "${currentPage > 3 }">
							<a href="BoardList.do?p=${currentPage -3 }">[이전]</a>
						</c:if>
						<c:if test = "${currentPage > 2 }">
							<a href="BoardList.do?p=${currentPage -2 }">[${currentPage -2 }]</a>
						</c:if>
						<c:if test = "${currentPage > 1 }">
							<a href="BoardList.do?p=${currentPage -1 }">[${currentPage -1 }]</a>
						</c:if>
						${ currentPage}
						<c:if test = "${currentPage < totalPages }">
							<a href="BoardList.do?p=${currentPage + 1 }">[${currentPage + 1 }]</a>
						</c:if>
						<c:if test = "${currentPage + 1 < totalPages }">
							<a href="BoardList.do?p=${currentPage + 2 }">[${currentPage + 2 }]</a>
						</c:if>
						<c:if test = "${currentPage + 2 < totalPages }">
							<a href="BoardList.do?p=${currentPage + 3 }">[다음]</a>
						</c:if>
					</td>
				</tr>
				
			</tbody>
			
		</table>
	</c:otherwise>
</c:choose>
<c:if test="${sessionScope.currentId != null }">
	<div align = "center">
		<input type ="Button" value="글쓰기" onclick = "location.href='BoardWriteView.do'">
	</div>
</c:if>
<c:set var="page" value="${(empty param.p)?1:param.p }" />
<c:set var="startNum" value="${page-(page-1)%5 }" />
<c:set var="lastNum"
	value="${fn:substringBefore(Math.ceil(count/10),'.') }" />

<div class="">
	<div>
		<span>${(empty param.p)?1:param.p }</span>
		/ ${lastNum } pages
		${totalPages}
	</div>
</div>

<form>
<div class="input-group">
	<label class="hidden">검색분류</label> 
	<select name="f">
		<option ${(param.f == "title")?"selected":"" } value="title">제목</option>
		<option ${(param.f == "nickname")?"selected":"" } value="nickname">작성자</option>
	</select> 
	<label class="hidden">검색어</label> 
	<input class="form-control" type="text" name="q"
		value="${param.q }" /> 
	<input class="btn btn-primary" type="submit"
		value="검색" />
</div>

</form>

</div>
<jsp:include page="/layout/footer.jsp" />