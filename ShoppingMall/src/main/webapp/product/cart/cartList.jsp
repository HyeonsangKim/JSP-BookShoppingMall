<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Product" />
</jsp:include>


<h1 align="center">상품 목록</h1>

<div class="container" id="main" align="center">
	<c:choose>
		<c:when test="${sessionScope.currentId == null }">
			<H2>상품이 없습니다.</H2>
		</c:when>
		<c:otherwise>
		<c:forEach var="c" items="${cartList }">
			<table width="540" cellpadding="0" cellspacing="0" border="1" class="table"
					align="center">
						<tr class="m1">
							<th width="20%">상품명 : ${c.product_name }</th>
							<th width="10%">수량</th>
							<th width="20%">단가</th>
						</tr>
						<tr align="center">
							<td align="center"><img width="160" height="160"
								src="${pageContext.request.contextPath}/admin/uploadFile/${c.product_image }"><br></td>
							<td align="center">
								<form action="CartModify.do" name="frm" method="post">
									<input type="text" size=2 name="pqty" value="${c.amount }">개
									<input type="hidden" name="pnum" value="${c.cart_id }"><br><br>
									<input type="submit" value="수정">
								</form>
							</td>
							<td align="center">
							${c.price } 원 <br> [${c.point }포인트]<br>
								<a href="CartDelete.do?pnum=${c.cart_id }">삭제</a>
							</td>
						</tr>
						<c:set var="totPrice" value="${totPrice + c.price }"/>
						<c:set var="totPoint" value="${totPoint + c.point }"/>
					</table>	
					</c:forEach>	
			<!-- 장바구니 전체 총액 계산 -->
		
					장바구니 총액: ${totPrice }원<br>
					총 누적 포인트 : ${totPoint }포인트<br>
					
				<a href="Order.do?user=${sessionScope.currentId}&price=${totPrice}">[주문하기]</a>&nbsp;&nbsp;<a
						href="javascript:history.go(-2);">[계속 쇼핑]</a>
					
		</c:otherwise>
	</c:choose>

	<jsp:include page="/layout/footer.jsp" />