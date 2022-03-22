<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Product" />
</jsp:include>


<h1 align="center">상품 목록 </h1>
<div class="container" id="main" align="center">
	<c:choose>
		<c:when test="${productList == null }">
			<H2>상품이 없습니다.</H2>
		</c:when>
		<c:otherwise>
			<table border="1" class="table" width=600>

				<c:forEach var="n" items="${productList }">
					
					<tr>
						<td align="center" colspan="2"> <b>상품이름 : ${n.pname } </b></td>
					</tr>
					<form name="frm" method="post" action="CartAdd.do?pnum=${n.pnum }">
					<input type="hidden" name="userId" value="${sessionScope.currentId }">
					<input type="hidden" name="pimage" value="${n.pimage }">
					<input type="hidden" name="pname" value="${n.pname }">
					<input type="hidden" name="price" value="${n.price }">
					<input type="hidden" name="point" value="${n.point }">
					<%-- <input type="hidden" name="pnum" value="${n.pnum }"> --%>
					<tr>
						<td align="center"><img width="160" height="160"
							src="${pageContext.request.contextPath}/admin/uploadFile/${n.pimage }">
						</td>
						<td>카테고리 : ${n.pcategory_fk }<br>
							상품가격 : ${n.price } <br> 회사 : ${n.pcompany } <br> 상품수량 :
							<input type="text" name="pqty" size="2" value="1">개<br>
							<table border="0" width="90%" align="center">
								<tr>
									<td>
										<button type="submit"  border="0"><img
											src="../admin/uploadFile/btn_cart(1).gif"></button>
									</td>
									<td><a href="#"> <img
											src="../admin/uploadFile/btn_buy.png" border="0">
									</a></td>
									<td><c:if test="${sessionScope.currentId == 'admin' }">
											<a href="ProductDelete.do?id=${n.pnum }">삭제</a>
										</c:if></td>
								</tr>
							</table>
							
						</td>
					</tr>
					</form>
					<tr height="250" valign="top">
						<td colspan="2" align="center"><br> <b>상품 소개</b><br> ${n.pcontents }
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="center"><c:if
							test="${currentPage > 3 }">
							<a href="ProductListLogic.do?p=${currentPage -3 }">[이전]</a>
						</c:if> <c:if test="${currentPage > 2 }">
							<a href="ProductListLogic.do?p=${currentPage -2 }">[${currentPage -2 }]</a>
						</c:if> <c:if test="${currentPage > 1 }">
							<a href="ProductListLogic.do?p=${currentPage -1 }">[${currentPage -1 }]</a>
						</c:if> ${ currentPage} <c:if test="${currentPage < totalPages }">
							<a href="ProductListLogic.do?p=${currentPage + 1 }">[${currentPage + 1 }]</a>
						</c:if> <c:if test="${currentPage + 1 < totalPages }">
							<a href="ProductListLogic.do?p=${currentPage + 2 }">[${currentPage + 2 }]</a>
						</c:if> <c:if test="${currentPage + 2 < totalPages }">
							<a href="ProductListLogic.do?p=${currentPage + 3 }">[다음]</a>
						</c:if></td>
				</tr>
			</table>

		</c:otherwise>
	</c:choose>
	<form>
		<div class="input-group">
			<label class="hidden">카테고리</label> <select name="f">
				<option ${(param.f == "전체")?"selected":"" } value="전체">전체</option>
				<option ${(param.f == "가구")?"selected":"" } value="가구">중국사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">일본사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">한국사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">유럽고대사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">유럽중세사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">유럽근대사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">아시아고대사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">아시아중세사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">아시아근대사</option>
			</select> <label class="hidden">검색어</label> <input class="form-control"
				type="text" name="q" value="${param.q }" /> <input
				class="btn btn-primary" type="submit" value="검색" />
		</div>

	</form>
	<c:if test="${sessionScope.currentId == 'admin' }">
		<a href="ProductWriteView.do">상품 등록하기 </a>
	</c:if>
</div>

<jsp:include page="/layout/footer.jsp" />